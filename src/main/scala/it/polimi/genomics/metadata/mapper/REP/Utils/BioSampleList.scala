package it.polimi.genomics.metadata.mapper.REP.Utils

import it.polimi.genomics.metadata.mapper.REP.REPTableId
import it.polimi.genomics.metadata.mapper.RemoteDatabase.DbHandler.conf

import scala.collection.mutable.ListBuffer

class BioSampleList(lines: Array[String], repTableId: REPTableId) {

  private var _list = new ListBuffer[String]()

  private var r = conf.getString("import.rep_biosample_accession_pattern").r
  for (l <- lines) {
    val mi = r.findAllIn(l)
    if (mi.hasNext) {
      val temp = mi.next()
      val replicateNumber = l.split("\t")(0).split("__", 3)
      _list += replicateNumber(2)
    }
  }
  if (_list.isEmpty) {
    r = conf.getString("import.tads_biosample_accession_pattern").r
    for (l <- lines) {
      val mi = r.findAllIn(l)
      if (mi.hasNext) {
        val temp = mi.next()
        val replicateNumber = l.split("\t")(0).split("__")(1)
        _list += replicateNumber
      }
    }
  }
  if (_list.isEmpty) {
    _list += "1"
  }


  repTableId.bioSampleArray(_list.toArray)

  def BiosampleList: List[String] = _list.toList

}
