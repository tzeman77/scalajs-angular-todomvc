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

class Chart(body: Map[String, Any]) {
  def transitionDuration(ms: Int) = new Chart(body + ("transitionDuration" -> ms))
  def height(h: Int) = new Chart(body + ("height" -> h))
  import js.JSConverters._
  def toJs = body.toJSDictionary
}

object Chart {
  def bulletChart = new Chart(Map("type" -> "bulletChart"))
}

@JSExportAll
case class Options(var chart: js.Dictionary[Any])

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
  var data: js.Dictionary[Any] = js.native
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
