package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope

@JSExport
object RadarCtrl extends Controller {

  override def initialize(scope: ScopeType) {
    scope.labels = Data.radarLabels
    scope.data = Data.radarData
    scope.series = js.Array("My First dataset", "My Second dataset")
    scope.options = Data.radarOptions
  }

  trait ScopeType extends Scope {
    var labels: js.Array[String] = js.native
    var data: js.Any = js.native
    var legend: Boolean = true
    var series: js.Array[String]
    var options: js.Any = js.native
  }
}

// vim: set ts=2 sw=2 et:
