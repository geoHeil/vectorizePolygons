package com.github.geoheil.sparkvectorization

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util
import java.util.Arrays

import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.WKTWriter
import org.geotools.coverage.grid.GridCoverage2D
import org.geotools.gce.arcgrid.ArcGridReader
import org.geotools.process.raster.PolygonExtractionProcess

import scala.collection.JavaConversions._

object Vectorizer {

  @transient private lazy val extractor = new PolygonExtractionProcess

  @transient private lazy val writer = new WKTWriter
  val imageString =
    """
      |ncols         4
      |nrows         6
      |xllcorner     0.0
      |yllcorner     0.0
      |cellsize      50.0
      |NODATA_value  -9999
      |-9999 -9999 5 2
      |-9999 20 100 36
      |3 8 35 10
      |32 42 50 6
      |88 75 27 9
      |13 5 1 -9999
    """.stripMargin

  def getAsciiGridFromstring(inputAsciiGrid: String): GridCoverage2D = {
    // http://docs.geotools.org/latest/userguide/library/coverage/arcgrid.html
    val stringAsStream = new ByteArrayInputStream(inputAsciiGrid.getBytes(StandardCharsets.UTF_8))
    new ArcGridReader(stringAsStream).read(null)
  }

  def getWKTForValueRange(input: GridCoverage2D, valueRange: Int): String = {
    val r = org.jaitools.numeric.Range.create(Integer.valueOf(valueRange), true, Integer.valueOf(200), false)
    val classificationRanges = Seq(r)
    // TODO optimization: can we initialize an Array of fixed length?
    val vectorizedFeatures = extractor.execute(input, 0, true, null, util.Arrays.asList(-9999), classificationRanges, null).features
    var firstElement = true
    var result:String = null
    while ( {
      vectorizedFeatures.hasNext
    }) {
      val vectorizedFeature = vectorizedFeatures.next
      val wktRes = vectorizedFeature.getDefaultGeometry match {
        case g: Geometry => write(g)
      }
      if (firstElement) {
        firstElement = false
        result = wktRes
      }
      else {
        println("##################################")
        println("error, can only output single geometry")
        println(result)
        println("##################################")
        throw new Exception("only single element is allowed")
      }
    }
    result
  }

  private def write(g: Geometry): String = writer.write(g)

}
