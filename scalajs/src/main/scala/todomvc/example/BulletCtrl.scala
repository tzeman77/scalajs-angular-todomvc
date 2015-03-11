package todomvc.example

import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.Any.{ fromBoolean, fromFunction1, fromString, jsArrayOps, wrapArray }
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops
import scala.scalajs.js.annotation.{JSExport, JSExportAll}
import scala.util.{ Failure, Success }

import org.scalajs.dom.console

import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.{ Location, Scope }
import js.JSConverters._

object D3 {
  val d3 = js.Dynamic.global.d3
  def timeFormat(fmt: String)(v: js.Any) =
    d3.time.format(fmt)(v)
}

object Data extends js.GlobalScope {
  val cumulativeLineData: js.Array[Any] = js.native
  val discreteBarData: js.Array[Any] = js.native
  val donutData: js.Array[Any] = js.native
  val historicalBarData: js.Array[Any] = js.native
  val multiBarHorizontalData: js.Array[Any] = js.native
}

class Axis(body: Map[String, Any]) {
  private def v[T](n: Symbol, v: T) = new Axis(body + (n.name -> v))
  def axisLabel(l: String) = v('axisLabel, l)
  def showMaxMin(b: Boolean) = v('showMaxMin, b)
  def staggerLabels(b: Boolean) = v('staggerLabels, b)
  def labelDistance(d: Int) = v('axisLabelDistance, d)
  def tickFormat(f: js.Function1[Double, js.Any]) = v('tickFormat, f)
  def rotateLabels(deg: Int) = v('rotateLabels, deg)
  def toJs = body.toJSDictionary
}

class Margin(body: Map[String, Int]) {
  private def v(v: Symbol, i: Int) = new Margin(body + (v.name -> i))
  def top(i: Int) = v('top, i)
  def bottom(i: Int) = v('bottom, i)
  def left(i: Int) = v('left, i)
  def right(i: Int) = v('right, i)
  def toJs = body.toJSDictionary
}

class Legend(m: Map[String, Any]) {
  private def v[T](n: Symbol, v: T) = new Legend(m + (n.name -> v))
  def margin(m: Margin) = v('margin, m)
}

class Chart(body: Map[String, Any]) {
  private def v[T](n: Symbol, v: T) = new Chart(body + (n.name -> v))

  def transitionDuration(ms: Int) = new Chart(body + ("transitionDuration" -> ms))
  def height(h: Int) = new Chart(body + ("height" -> h))
  def width(w: Int) = new Chart(body + ("width" -> w))
  def x(f: js.Function1[js.Dynamic, js.Dynamic]) = v('x, f)
  def y(f: js.Function1[js.Dynamic, js.Dynamic]) = v('y, f)
  def average(f: js.Function1[js.Dynamic, Any]) = v('average, f)
  def color(colors: js.Array[String]) = v('color, colors)
  def color(colors: js.Any) = v('color, colors)
  def useInteractiveGuideline(b: Boolean) = v('useInteractiveGuideline, b)
  def clipVoronoi(b: Boolean) = v('clipVoronoi, b)
  def xAxis(a: Axis) = v('xAxis, a.toJs)
  def yAxis(a: Axis) = v('yAxis, a.toJs)
  def margin(m: Margin) = v('margin, m.toJs)
  def showValues(b: Boolean) = v('showValues, b)
  def showControls(b: Boolean) = v('showControls, b)
  def showLabels(b: Boolean) = v('showLabels, b)
  def valueFormat(f: js.Function1[js.Dynamic, js.Dynamic]) = v('valueFormat, f)
  def donut(b: Boolean) = v('donut, b)
  def legend(l: Legend) = v('legend, l)

  def toJs = body.toJSDictionary
}

object Chart {
  private def as(t: Symbol) = new Chart(Map("type" -> t.name))

  def bulletChart = as('bulletChart)
  def cumulativeLineChart = as('cumulativeLineChart)
  def discreteBarChart = as('discreteBarChart)
  def pieChart = as('pieChart)
  def historicalBarChart = as('historicalBarChart)
  def multiBarHorizontalChart = as('multiBarHorizontalChart)

  def axis(l: String) = new Axis(Map()) axisLabel(l)
  def margin = new Margin(Map())
  def legend = new Legend(Map())
}

@JSExportAll
case class Options(var chart: js.Dictionary[Any])

@JSExportAll
case class Series(val key: String, val values: js.Array[js.Array[Double]])

class ChartData(body: Map[String, Any] = Map()) {
  def title(t: String) = new ChartData(body + ("title" -> t))
  def subtitle(s: String) = new ChartData(body + ("subtitle" -> s))

  /* bullet chart */
  import js.JSConverters._
  def ranges(is: Int*) = new ChartData(body + ("ranges" -> is.toJSArray))
  def measures(is: Int*) = new ChartData(body + ("measures" -> is.toJSArray))
  def markers(is: Int*) = new ChartData(body + ("markers" -> is.toJSArray))

  def toJs = body.toJSDictionary
}

object ChartData {
  def apply() = new ChartData
}

trait ChartScope extends Scope {
  var options: Options = js.native
  var data: js.Any = js.native
}

@JSExport
object BulletCtrl extends Controller {

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.bulletChart transitionDuration(500) toJs)
    scope.data = (ChartData() title("Temperature") subtitle("deg. C")
      ranges(30, 60, 120) measures(37) markers(100) toJs)
  }

  trait ScopeType extends ChartScope

}


// vim: set ts=2 sw=2 et:
