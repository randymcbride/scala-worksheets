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
//scala methods can be any symbol and dot notation is optional. This means 1 + 1 could also be written as 1.+(1) because + is just a method
1 + 1
1.+(1)

// and this applies to all methods:
val arr = Array(2,1,3)
val evenOnly =  (x: Int) => x.%(2) == 0
val evens = arr filter evenOnly

// scala has no ++ operator
var counter = 0
counter += 1
println(counter)

// operators work on things like BigInt and BigDecimal
val totalCost = BigDecimal("9.99")
val totalTax = BigDecimal("0.11")

val balance = totalCost + totalTax // much better than java totalCost.add(totalTax);

/*******************************************
 * 1.5 More about methods
 *******************************************/
//parameterless methods can be (and should be for stylistic reasons) called without parens
"CBA".sorted

//Java uses static methods. The Scala equivalent are methods of singleton objects. If you import the object you can use all of its methods without the object name
import scala.math.*
sqrt(100)
pow(10,10)

//its common for classes to have companion objects. For example BigInt has a companion object called BigInt
BigInt.probablePrime(100, util.Random)

/*******************************************
 * 1.6 The apply method
 *******************************************/

// if you see something like this myArray(4). This is how you would get the 5th element of the array.
val myArray = Array(1,2,3,4,5)
myArray(4)

//this is actually a call to the apply method.
myArray.apply(4)

//many classes have apply methods
"ABC".apply(2) == 'C'

//CAUTION: sometimes the () notation conflicts with contextual parmeters. For example
"Bonjour".sorted(3) // this yeilds an error because sorted does not take parameters
//do this instead
val sortedBonjour = "Bonjour".sorted
val thirdSortedElement = sortedBonjour(3)
//or call apply directly
val o = "Bonjour".sorted.apply(3)
