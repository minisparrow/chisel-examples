package hashmap_test

import chisel3._
import chisel3.util._
import scala.collection.mutable.HashMap

object VTA_ISA extends App {
  private val taskId: HashMap[String,String] =
    HashMap(
      ("load","000"),
      ("store","001"),
      ("gemm","010"),
      ("finish","011"),
      ("alu","100")
     )

  private val memId: HashMap[String,String] =
    HashMap(
      ("uop","00"),
      ("wgt","01"),
      ("inp","10"),
      ("acc","11")
    )

  private val aluId: HashMap[String,String] =
    HashMap(
      ("minpool","00"),
      ("maxpool","01"),
      ("add","10"),
      ("shift","11")
    )

  println(taskId.get("load"))
  println(taskId.get("store"))
  println(taskId.get("gemm"))
  println(taskId.get("alu"))
  println(taskId.get("finish"))

}
