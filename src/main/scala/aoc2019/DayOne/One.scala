package aoc2019.DayOne

import scala.io.Source

object One {
  private val input = "One.txt"
  private lazy val modules = Source.fromInputStream(getClass.getResourceAsStream(input)).mkString.trim
  private val masses = modules.linesIterator.map(_.toInt).toSeq

  private def calculateFuel(mass: Double) = mass / 3 - 2

  private def calculateFuel(mass: Int) = mass / 3 - 2

  private def needsFuel(mass: Int) = calculateFuel(mass.toDouble) >= 0

  private def calculateTotalFuel(mass: Int): Int = {
    @scala.annotation.tailrec
    def sumFuel(mass: Int, sum: Int): Int = {
      if(needsFuel(mass)) {
        val fuelReq = calculateFuel(mass)
        sumFuel(fuelReq, sum + fuelReq)
      } else
        sum
    }
    sumFuel(mass, 0)
  }

  def main(args: Array[String]): Unit = {
    val moduleFuel = masses.map(calculateFuel).sum
    val totalFuel = masses.map(calculateFuel).map(calculateTotalFuel).sum
    println(moduleFuel)
    println(moduleFuel + totalFuel)
  }
}
