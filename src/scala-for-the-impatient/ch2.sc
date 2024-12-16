/*******************************************
 * 2.1 Conditional Expressions
 *******************************************/
// in scala if/else is not a statement, it is an expression that returns whatever follows the if or else block
val zero = if true then 0 else 1

// you could construct an if statment that doesn't return anything. That would be an if statement that yields a Unit
val unit = if true then "true"

/*******************************************
 * 2.3 Block expressions and assignments
 *******************************************/

// {} don't define code blocks, they define expressions whose value is that of the last expression
val area = {
  val x = 2
  val y = 4
  2 * 4
}

// you could also exclude the braces. This is commonly seen in for loops and if statements and is preferred
for (x <- 1 to 10)
  val y = x + 1
  if x % 2 == 0 then
    println(y)


/*******************************************
 * 2.4 Input and Output
 *******************************************/

// you can you string interpolators
var name = "Randy"
var greeting = f"Hello $name"

// expressions and formatting are supported
var balance = 1111.11
var discount = .123
var output = f"the new balance is ${balance - balance * discount}%5.2f"

// escape $ and % in an interpolated string by doubling it
var escOutput = f"After applying the discount of ${discount * 100}%% your new balance is $$${balance - balance * discount}%5.2f"

/*******************************************
 * 2.5 and 2.6 for loop
 *******************************************/
// we won't use for loops a lot in scala because we will typically use a method to traverse, transform, fitler, etc.
// the for loop is different in scala. There is no for(var i = 0; i < 10; i++). We could accomplish this with the following
for i <- 1 to 10 do
  println(i)

// with this syntax you can iterate anything
val countries = Array("USA", "GB", "FR")
for country <- countries do println(country)

// scala for loops support multiple generators
val roundRobin = scala.collection.mutable.Set[String]()
for
  c1 <- countries
  c2 <- countries
do
  if c1 != c2 then roundRobin += f"$c1 v $c2"

// we can modify the last loop by moving the guard to the definition of the for loop
roundRobin.clear()
for
  c1 <- countries
  c2 <- countries
  if c1 != c2
do
  roundRobin += f"$c1 v $c2"

// we can modify it further to yield the results instead of defining a set before the loop
val roundRobin2 = for
  c1 <- countries
  c2 <- countries
  if c1 != c2
yield f"$c1 v $c2"


/*******************************************
 * 2.7 function
 *******************************************/

// in addition to methods scala allows functions to be defined outside of objects and classes
def addIfEven(x: Int, y: Int): Int =
  def isEven(x: Int) = x % 2 == 0
  if isEven(x) && isEven(y) then x + y else 0

addIfEven(2,2)
addIfEven(1,1)

// a function that we only care about its side effect returns Unit
def log(message: String): Unit =
  println(f"${java.time.Instant.now}: $message")
end log

log("Hello World")

/*******************************************
 * 2.8 default and named args
 *******************************************/

// you can name your arguments and give them default values

def decorate(
              str: String,
              left: String = "[",
              right: String = "]") =
  left + str + right

decorate("Hello")
decorate("Hello", "(")
decorate("Hello", "(", ")")
decorate(left = "<", right = ">", str = "Hello")

/*******************************************
 * 2.9 variable arguments
 *******************************************/

// you can implement a function that can take a variable number of arguments
def sum(args: Int*) =
  var result = 0
  for arg <- args do result += arg
  result

sum(2,3,4)

// if you already have a sequence of arguments you need to spread them with a postfix *
def recursiveSum(args: Int*): Int =
  if args.length == 0 then 0
  else args.head + recursiveSum(args.tail*)

recursiveSum(1,2,3,4,5)


/*******************************************
 * 2.11 Functions without Params
 *******************************************/

// if you define a function without params you can leave the parenthesis off. By convention this indicates the function is idempotent. If the function is not idempotent (consider math.random) then keep the parenthesis on.
def sayHello = "Hello"

val greeting = sayHello


/*******************************************
 * 2.13 Exceptions
 *******************************************/

// basically the same as java except there are no checked exceptions
def squareRoot(x: Int) =
  if x > 0 then scala.math.sqrt(x)
  else throw IllegalArgumentException("x should not be negative")

try
  println(squareRoot(-4))
catch
  case ex: IllegalArgumentException => println(ex.getMessage)

// in java we have a try-with-resources pattern. In scala this is accomplished with the Using helper object
scala.util.Using.Manager{use =>
  val reader = use(openReader)
  val writer = use(openWriter)
  process(reader, writer)
}//the reader and writer are closed for us
