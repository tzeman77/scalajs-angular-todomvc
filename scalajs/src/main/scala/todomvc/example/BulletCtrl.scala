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

@JSExportAll
case class Chart(var `type`: String, var transitionDuration: Int = 500)

@JSExportAll
case class Options(var chart: Chart)

@JSExportAll
case class Data(var title: String, var subtitle: String, var ranges: js.Array[Int],
  var measures: js.Array[Int], var markers: js.Array[Int])

@JSExport
object BulletCtrl extends Controller {
  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart("bulletChart"))
    scope.data = Data("Temperature", "deg. C", js.Array(30, 50, 120), js.Array(37),
      js.Array(100))
  }

  trait ScopeType extends Scope {

    var options: Options = js.native

    var data: Data = js.native
  }
}


// vim: set ts=2 sw=2 et:
