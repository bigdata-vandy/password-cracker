package edu.vanderbilt.accre

/**
  * Created by joshuaarnold on 8/24/17.
  */
package object passwords {

  def intPower(num: Int, exp: Int): Long = {
    var result = 1L
    val numLong = num.toLong

    for (i <- 0 until exp) result *= numLong

    result
  }

}
