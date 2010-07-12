// 最大回避ヒープ
object BinaryTree{
  import scala.math._

  case class Fork[T](val cnt:Int, val v:T, val lhs:Tree[T], val rhs:Tree[T]){
    override def toString = "(" + v.toString + "," + (lhs.toString) + "," + rhs.toString + ")"
    def dump  : String = "Fork(%d,%s,%s,%s)".format(cnt, v.toString, lhs.dump, rhs.dump)
    def size = cnt
  }

  def merge[T<%Ordered[T]](lhs:Tree[T],rhs:Tree[T]):Tree[T] = (lhs,rhs) match {
    case (a, Tree(None)) => a
    case (Tree(None), b) => b
    case (Tree(Some(a)), Tree(Some(b))) => if (a.v <= b.v){ join(a,b) }else{ join(b, a)}
    case _ => throw new java.lang.RuntimeException("no reach")
  }

  implicit def forkToTree[T<%Ordered[T]](fork:Fork[T]):Tree[T] = Tree(Some(fork))

  def join[T<%Ordered[T]](a:Fork[T], b:Fork[T]) =  List[Tree[T]](a.lhs, a.rhs, b) sortWith(_.size < _.size) match{
    case List(x,y,z) => Fork(a.size + b.size, a.v, z, merge(x,y))
  }

  case class Tree[T<%Ordered[T]](val fork:Option[Fork[T]]){
    def isEmpty = fork.isEmpty
    def minElem = fork map(_.v) getOrElse { throw new java.lang.RuntimeException("minElem")}
    def deleteMin = fork map( f=> merge(f.lhs, f.rhs))
    def insert(v:T) = merge(Fork(1, v, Tree[T](None), Tree[T](None)), this)
    
    def size = fork map(_.size) getOrElse{0}
    override def toString = fork map(_.toString) getOrElse {"None"}
    def dump : String = {
      "Tree(%s)".format(fork.map(_.dump).getOrElse("None"))
    }
  }
}

object App extends Application{
  import BinaryTree._
  println( (Tree[Int](None) /: (1 to 7))(_.insert(_)).toString)
}
