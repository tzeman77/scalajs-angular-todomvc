package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller

@JSExport
object MultiBarHorizontalCtrl extends Controller {

  import D3._

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.multiBarHorizontalChart
      height(450)
      margin(Chart.margin top(20) left(60) right(20) bottom(80))
      x { d: js.Dynamic => d.label }
      y { d: js.Dynamic => d.value }
      showControls(true)
      showValues(true)
      xAxis(Chart.axis("") showMaxMin(false))
      yAxis(Chart.axis("Values")
        tickFormat {d: Double => d3.format(",.2f")(d)})
      toJs)
    scope.data = Data.multiBarHorizontalData
  }

  trait ScopeType extends ChartScope
}

// vim: set ts=2 sw=2 et:
