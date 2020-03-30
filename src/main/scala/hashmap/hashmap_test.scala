package hashmap_test

import chisel3._
import chisel3.util._
import scala.collection.mutable.HashMap

object hashmap_test extends App {
  val taskId: HashMap[String,String] =
    HashMap(
      ("load","000"),
      ("store","001"),
      ("gemm","010"),
      ("finish","011"),
      ("alu","100")
     )

  println(taskId.get("load"))
  println(taskId.get("store"))
  println(taskId.get("gemm"))
  println(taskId.get("alu"))
  println(taskId.get("finish"))

}
