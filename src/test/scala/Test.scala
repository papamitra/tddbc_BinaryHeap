import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

import BinaryTree._

class Specs extends Spec with ShouldMatchers {
  describe("Noneから作ったTreeは") {
    val bt = Tree[Int](None)
    it("sizeは0") {
      assert(bt.size === 0)
    }

    it("isEmptyはtrueを返す"){
      assert(bt.isEmpty=== true)
    }

    it("minElemはRuntimeExceptionを投げる"){
      // TODO;
//      bt.minElem must thrownAn[java.lang.RuntimeException]
    }

    it("deleteMinはNoneを返す") {
      assert(bt.deleteMin === None)
    }

    it ("3をinsert すると、Tree(Some(Fork(1, 3, Tree[Int](None), Tree[Int](None)))))") {
      assert(bt.insert(3) === Tree(Some(Fork(1, 3, Tree[Int](None), Tree[Int](None)))))
    }
    
    it ("toString すると None になる") {
      assert(bt.toString === "None")
    }
    
  }

  describe("NoneにinsertしたTreeは") {
    val bt = Tree[Int](None).insert(3)
    it ("sizeが1") {
      assert(bt.size === 1)
    }

    it("delelteMinしてSome(Tree(None))になる"){
      assert(bt.deleteMin === Some(Tree[Int](None)))
    }

    it("3より小さい値をinsertすると、ルートが置き換わる"){
      val leaf = Tree[Int](None)
      val t = Tree(Some(Fork(1, 3, leaf, leaf)))
      assert(bt.insert(0) === Tree(Some(Fork(2, 0, t, leaf))))
    }

    it("3よりbigger 値をinsertすると、ルートが置き換わる"){
      val leaf = Tree[Int](None)
      val t = Tree(Some(Fork(1, 5, leaf, leaf)))
      assert(bt.insert(5) === Tree(Some(Fork(2, 3, t, leaf))))
    }
    it("3をinsertすると、ルートと右辺の要素が3になる"){
      val leaf = Tree[Int](None)
      val t = Tree(Some(Fork(1, 3, leaf, leaf)))
      assert(bt.insert(3) === Tree(Some(Fork(2, 3, t, leaf))))
    }
    it("isEmptyはfalseを返す") {
      assert(bt.isEmpty === false)
    }
    it("minElemは3を返す"){
      assert(bt.minElem === 3)
    }
    it ("toStringが文字列を返す") {
      assert(bt.toString === "(3,None,None)")
    }
  }

  describe("Noneに2つ要素(3と5)をinsertしたTreeは") {
    val bt = Tree[Int](None).insert(3).insert(5)
    it ("size が 2 を返す"){
      assert(bt.size === 2)
    }
    it("isEmpty がfalse を返す") {
      assert(bt.isEmpty === false)
    }

    it("minElem が 3 を返す") {
      assert(bt.minElem === 3)
    }
    
    it("deleteMinを行うと5 のみのTree を返す") {
      assert(bt.deleteMin === Some(Tree[Int](None).insert(5)))
    }
  }

  describe("merge test") {
    def merge = BinaryTree.merge[Int] _
    val leaf = Tree[Int] (None)
    it ("NoneとNoneで例外") {
      val t1 = leaf
      val t2 = leaf
      assert(merge(t1,t2)=== t1)
    }

    it ("Tree a < Tree b のとき、うまいこといく") {
      val t1 = leaf.insert(4)
      val t2 = leaf.insert(6)
      val t = Tree(Some(Fork(1, 6, leaf, leaf)))
      assert(merge(t1, t2) === Tree(Some(Fork(2, 4, t, leaf))))
    }
  }

  describe("dumpメソッド") {
    it ("Tree(None)をdumpするとTree(None)"){
      assert("Tree(None)" === Tree[Int](None).dump)
    }

//    it ("Noneにinsert(3)してdumpするとTree(Some(Fork(1,3,None,None)))"){
//      assert("Tree(Some(Fork(1,3,None,None)))" === Tree[Int](None).insert(3).dump)
//kj    }
  }
}
