package it.polimi.genomics.importer.ModelDatabase.Encode.Table

import it.polimi.genomics.importer.ModelDatabase.Encode.EncodeTableId
import it.polimi.genomics.importer.ModelDatabase.SamplePair
//import it.polimi.genomics.importer.ModelDatabase.Encode.Utils.PlatformRetriver
import it.polimi.genomics.importer.ModelDatabase.Utils.Statistics
import it.polimi.genomics.importer.ModelDatabase.Item


class ItemEncode(encodeTableId: EncodeTableId) extends EncodeTable(encodeTableId) with Item {

//todo comment
//   private var platformRetriver: PlatformRetriver = _

  override def setParameter(param: String, dest: String, insertMethod: (String,String) => String): Unit = dest.toUpperCase() match {
    case "SOURCEID" => this.sourceId = insertMethod(this.sourceId,param)
    case "SIZE" => this.size = insertMethod(this.size.toString,param).toLong
    case "PLATFORM" => this.platform = insertMethod(this.platform, param)
    case "PIPELINE" => this.pipeline = insertMethod(this.pipeline,param)
    case "SOURCEURL" => this.sourceUrl = insertMethod(this.sourceUrl,param)
    case _ => noMatching(dest)
  }

  override def insert(): Int = {
//    this.definePlatformRetriver()
    val id = dbHandler.insertItem(experimentTypeId, datasetId,this.sourceId, this.size, this.platform, this.pipeline, this.sourceUrl)
 //   this.retriveDerivedItems(id) //TODO comment
 //   this.retrievePairs(id)
    Statistics.itemInserted += 1
    id
  }

//  todo comment
 /*  def specialInsert(): Int ={
    val id = dbHandler.insertItem(experimentTypeId, datasetId,this.sourceId,this.size,this.platform,this.pipeline,this.sourceUrl)
    Statistics.itemInserted += 1
    id
  }*/


  override def update(): Int = {
//    this.definePlatformRetriver()
    val id = dbHandler.updateItem(experimentTypeId,datasetId,this.sourceId,this.size,this.platform,this.pipeline,this.sourceUrl)
 //   this.retriveDerivedItems(id) //todo comment
  //  this.retrievePairs(id)
    Statistics.itemUpdated += 1
    id
  }

  override def updateById(): Unit = {
//    this.definePlatformRetriver()
    val id = dbHandler.updateItemById(this.primaryKey, experimentTypeId,datasetId,this.sourceId,this.size,this.platform,this.pipeline,this.sourceUrl)
//    this.retriveDerivedItems(id) //todo comment
    Statistics.itemUpdated += 1
    id
  }

//  todo comment
/*   def specialUpdate(): Int ={
    val id = dbHandler.updateItem(experimentTypeId,datasetId,this.sourceId,this.size,this.platform,this.pipeline,this.sourceUrl)
    Statistics.itemUpdated += 1
    id
  }*/

//  private def definePlatformRetriver(): Unit = {
//      platformRetriver = new PlatformRetriver(this.filePath, this.sourceId, this.encodeTableId)
//      val temp = platformRetriver.getPipelineAndPlatformHelper(this.sourceId)
//      this.pipeline = temp(0)
//      this.platform = temp(1)
//  }

//TODO comment
/*    private def retriveDerivedItems(id: Int): Unit = {
    if (conf.getBoolean("import.derived_item")) {
      platformRetriver.getItems(id, this.experimentTypeId, this.encodeTableId.caseId)
    }
  }
*/
  //TODO
 /* private def retrievePairs(id: Int): Unit = {
    for()
    //    if (conf.getBoolean("import.derived_item")) {
    //      platformRetriver.getItems(id, this.experimentTypeId, this.encodeTableId.caseId)
    //    }
    //  }
    val pair = new SamplePair(encodeTableId)
  }*/

/*  def defineDerivedFrom(file: JsonNode, initialItemId: Int, finalItemId: Int, precDescription: String): DerivedFromEncode = {
      val derivedFrom = new DerivedFromEncode(encodesTableId)
      derivedFrom.initialItemId = initialItemId
      derivedFrom.finalItemId = finalItemId
      derivedFrom.description = precDescription
      if(file.has("analysis_step_version")) {
        setDescription(file)
      }
      derivedFrom
  }*/
}