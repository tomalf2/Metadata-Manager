package it.polimi.genomics.metadata.cleaner

import java.io.File

import it.polimi.genomics.metadata.step.CleanerStep.createSymbolicLink

///Users/abernasconi/Documents/gitProjects/GMQL-Importer/Example/example_for_cleaner/rules.txt


object Cleaner extends App {

  if (args.length < 2) {
    println("This jar at least 2 parameters to be run: base_path, output_directory_path. " +
      "Additionally you may specify a third file: rules_list_file")
    System.exit(0)
  }

  val input_directory_path = args(0)
  val output_directory_path = args(1)

  val out_dir = new File(output_directory_path)

  var rules_file: Option[String] = None

  if(args.length > 2){
    rules_file = Some(args(2))
  }

  val input_files = Utils.getListOfMetaFiles(new File(input_directory_path))

  val ruleBasePathOpt: Option[RuleBase] = {
    if (rules_file.isDefined)
      Option(new RuleBase(rules_file.get))
    else None
  }

  input_files.foreach({ inputFile: File =>

    val inputPath = inputFile.getAbsolutePath

    val outputPath = new File(out_dir, inputFile.getName).getAbsolutePath

    println("inputPath: " + inputPath)
    println("outputPath: " + outputPath)

    if (ruleBasePathOpt.isDefined) {
      ruleBasePathOpt.get.applyRBToFile(inputPath, outputPath)
    } else {
      createSymbolicLink(inputPath, outputPath)
    }

  })

}





