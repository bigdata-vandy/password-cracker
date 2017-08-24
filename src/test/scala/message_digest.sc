val s: String = "Apple"
val m = java.security.MessageDigest.getInstance("SHA-256")

val bytes = m.digest(s.getBytes("UTF-8"))

val hash = bytes map("%02x".format(_)) mkString

for (ab <- hash.toList.grouped(2)) {
  println( Integer.parseInt(ab.mkString, 16) )
}