package vta_isa 

import chisel3._
import chisel3.util._

import scala.collection.mutable.HashMap

object VTA_ISA extends App {
  private val xLen = 128
  private val depBits = 4

  private val idBits: HashMap[String, Int] =
    HashMap(("task", 3), ("mem", 2), ("alu", 2))

  private val taskId: HashMap[String, String] =
    HashMap(
      ("load", "000"),
      ("store", "001"),
      ("gemm", "010"),
      ("finish", "011"),
      ("alu", "100")
    )

  private val memId: HashMap[String, String] =
    HashMap(
      ("uop", "00"),
      ("wgt", "01"),
      ("inp", "10"),
      ("acc", "11")
    )

  private val aluId: HashMap[String, String] =
    HashMap(
      ("minpool", "00"),
      ("maxpool", "01"),
      ("add", "10"),
      ("shift", "11")
    )

  private def dontCare(bits: Int): String = "?" * bits

  private def instPat(bin: String): BitPat = BitPat("b" + bin)

  private def load(id: String): BitPat = {
    val rem = xLen - idBits("mem") - depBits - idBits("task")
    val inst = dontCare(rem) + memId(id) + dontCare(depBits) + taskId("load")
    instPat(inst)
  }

  private def store: BitPat = {
    val rem = xLen - idBits("task")
    val inst = dontCare(rem) + taskId("store")
    instPat(inst)
  }

  private def gemm: BitPat = {
    val rem = xLen - idBits("task")
    val inst = dontCare(rem) + taskId("gemm")
    instPat(inst)
  }

  private def alu(id: String): BitPat = {
    // TODO: move alu id next to task id
    val inst = dontCare(18) + aluId(id) + dontCare(105) + taskId("alu")
    instPat(inst)
  }

  private def finish: BitPat = {
    val rem = xLen - idBits("task")
    val inst = dontCare(rem) + taskId("finish")
    instPat(inst)
  }

  def LUOP = load("uop")

  def LWGT = load("wgt")

  def LINP = load("inp")

  def LACC = load("acc")

  def SOUT = store

  def GEMM = gemm

  def VMIN = alu("minpool")

  def VMAX = alu("maxpool")

  def VADD = alu("add")

  def VSHX = alu("shift")

  def FNSH = finish

  println(taskId.get("load"))
  println(taskId.get("store"))
  println(taskId.get("gemm"))
  println(taskId.get("alu"))
  println(taskId.get("finish"))

  println("dont care bits:", dontCare(5))
  println(instPat("010000?????"))

  println(LUOP.mask)
  println(LUOP.value)
  println(GEMM.mask)
  println(GEMM.value)
}





