/**
 * 1.3 commonly used types
 */

//types are classes
1.toString
1.to(10)

//there is no need for wrapper classes myArr below will become int[] in the vm
val x,y: Int = 1
val myArr: Array[Int] = Array(x,y)

// Scala uses java.lang.String. However, it augments it with operations in the
// StringOps class.
"Hello".intersect("World") //this is equivalent to:
scala.collection.StringOps("Hello").intersect("World") //this works because "Hello" was implicitly converted to StringOps on the previous line

//Similarly there is RichInt, RichDouble, etc. We actually used it above already.
1.to(10) //equivalent to
scala.runtime.RichInt(1).to(10)

//note that conversion is achieved using methods not casts
99.44.toInt
99.toChar

/*******************************************
 * 1.4 arithmetic and operator overloading
 *******************************************/

