object Main extends App {
  class RichStrIter extends StringIterator("Scala") with RichIterator
  val richStrIter = new RichStrIter
  richStrIter forEach println
}

abstract class A {
  val message: String
}

class B extends A {
  val message = "I'm an instance of class B"
}

trait C extends A {
  def loudMessage = message.toUpperCase()
}

class D extends B with C

abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next(): T
}

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext: Boolean = i < s.length()
  def next(): this.T = {
    val ch = s charAt i
    i += 1
    ch
  }
}

trait RichIterator extends AbsIterator {
  def forEach(f: T => Unit): Unit = while (hasNext) f(next())
}