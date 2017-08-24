package edu.vanderbilt.accre.passwords

import scala.math.BigInt

/**
  * Created by joshuaarnold on 4/25/17.
  */
class PasswordCracker(val passwordLength: Int = 5,
                      val charSequence: List[Char] = ('a' to 'z').toList)
    extends Serializable {

  if (passwordLength < 1) throw new IllegalArgumentException("passwordLength must be strictly positive")
  if (charSequence.isEmpty) throw new IllegalArgumentException("charSequence must be non-empty")

  private val numChars: BigInt = BigInt.int2bigInt(charSequence.length)


  private def getHash(s: String): String = {
    // Besides "MD5", "SHA-256", and other hashes are available
    val m = java.security.MessageDigest.getInstance("SHA-256")
      .digest(s.getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
  }

  private def intPower(num: BigInt, exp: BigInt): BigInt = {
    var result = 1
    for (_ <- BigInt(0) until exp) {result = result * num.toInt }
    result
  }

  def hashFromSerial(serial: BigInt): (String, String) = {

    def loop(ser: BigInt, div: BigInt, acc: String): String =
      div match {
        case x if x == BigInt(0) => acc
        case _ =>
          val (letterIndex, ser1) = ser /% div
          loop(ser1, div / numChars , acc + charSequence(letterIndex.toInt))
    }

    val divisor: BigInt = List.fill[BigInt](passwordLength - 1)(numChars).reduce(_ * _)

    val word: String = loop(serial, divisor, "")
    val hash = getHash(word)
    (word, hash)

  }


}
