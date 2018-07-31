package com.github.geoheil.sparkvectorization

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}


object SparkVectorization extends App {

  val spark = SparkSession
    .builder()
    .config(new SparkConf()
      .setAppName("sparkvectorization")
      .setMaster("local[*]"))
    .getOrCreate()

  import spark.implicits._

  val df = Seq(("foo", Vectorizer.imageString)).toDF("id", "grid")
  df.printSchema
  df.show

  val wktUDF = udf((s: String) => {
    val grid = Vectorizer.getAsciiGridFromstring(s)
    Vectorizer.getWKTForValueRange(grid, 10)
  })

  val result = df.withColumn("wkt", wktUDF(col("grid")))
  result.printSchema
  result.show
}
