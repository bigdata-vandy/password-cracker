
List.range[BigInt](0, 4)
List.range(0, 4).scan(1)((b: Int, _) => b * 26).reverse

val foo: BigInt = 26
List.fill[BigInt](5)(-1).scan(1: BigInt)((b, _) => b * foo) reverse

val charSequence = (('a' to 'z') ++ ('A' to 'Z')) toList

charSequence(9)

BigInt(1) / BigInt(26)

BigInt(3200724154710L) /% BigInt(5429503678976L)

List.fill[BigInt](8)(BigInt(26)).reduce(_ * _)