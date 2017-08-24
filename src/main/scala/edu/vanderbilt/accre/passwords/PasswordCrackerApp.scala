package edu.vanderbilt.accre.passwords

import org.apache.spark.sql.SparkSession

/**
  * Created by joshuaarnold on 8/24/17.
  */
object PasswordCrackerApp {

  def main(args: Array[String]): Unit = {

    val Array(passwordLength, numCharacters) = args.length match {
      case 2 => args map (_.toInt)
      case _ => throw new IllegalArgumentException(
        "Usage: PasswordCrackerApp passwordLength numCharacters")
    }

    val spark = SparkSession
        .builder()
        .appName("PasswordCrackerApp")
        .getOrCreate()

    val sc = spark.sparkContext

    val cracker = new PasswordCracker()

    val targetHashes = List(
      "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b",
      "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f",
      "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad"
    )
    sc.parallelize(0L until intPower(numCharacters, passwordLength))
        .map(cracker.hashFromSerial(_))
        .filter{ case (word, hash) => targetHashes contains hash}
        .collect.foreach(println)

  }

}
