package ru.valuationserver.service


import breeze.plot.{Figure, Plot, hist, plot}
import breeze.stats.distributions.Gaussian
import breeze.linalg._

object BreezeServiceImpl extends App {
  val f: Figure = Figure()
  val p: Plot = f.subplot(0)
  val x: DenseVector[Double] = linspace(0.0, 1.0)
//  p += plot(x, x ^:^ 2.0)
//  p += plot(x, x ^:^ 3.0, '.')
  p.xlabel = "x axis"
  p.ylabel = "y axis"
  f.saveas("lines2.png") // save current figure as a .png, eps and pdf also supported
  val m: DenseMatrix[Int] = DenseMatrix.zeros[Int](5, 5)
  val p2: Plot = f.subplot(2, 1, 1)
  val g: Gaussian = breeze.stats.distributions.Gaussian(0, 1)
  p2 += hist(g.sample(100000), 100)
  p2.title = "A normal distribution"
  f.saveas("subplots2.png")
}
