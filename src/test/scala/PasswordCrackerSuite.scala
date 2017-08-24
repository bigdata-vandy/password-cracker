import edu.vanderbilt.accre.passwords.PasswordCracker
import org.scalatest.WordSpec
import org.scalatest.Matchers._

/**
  * Created by joshuaarnold on 8/24/17.
  */
class PasswordCrackerSuite extends WordSpec {

  trait DefaultCracker {
    val cracker = new PasswordCracker()
  }

  trait LongPassCracker {
    val cracker = new PasswordCracker(9)
  }


  "cracker" when {
    "cracking 5 letter passwords" should {
      "return the correct word, hash tuple" in new DefaultCracker {
        cracker.hashFromSerial(BigInt(274070)) shouldBe
            ("apple", "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b")
      }
    }
    "cracking 9 letter passwords" should {
      "return the correct word, hash tuple" in new LongPassCracker {
        cracker.hashFromSerial(BigInt(3200724154710L)) shouldBe
            ("pineapple", "b0fef621727ff82a7d334d9f1f047dc662ed0e27e05aa8fd1aefd19b0fff312c")
      }
    }
  }
}
