package com.github.geoheil.sparkvectorization

import com.sun.media.jai.operator.ImageReadDescriptor
import org.jaitools.media.jai.vectorize.VectorizeDescriptor


object PlainJava extends App {

  val imageString = Vectorizer.imageString
  println("##########")
  println(VectorizeDescriptor.VECTOR_PROPERTY_NAME)
  println(ImageReadDescriptor.PROPERTY_NAME_IMAGE_READ_PARAM)
  println("##########")
  val gridCoverage2D = Vectorizer.getAsciiGridFromstring(imageString)

  println(Vectorizer.getWKTForValueRange(gridCoverage2D, 10))
}
