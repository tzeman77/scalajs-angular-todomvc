package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller

@JSExport
object PieCtrl extends Controller {

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.pieChart
      transitionDuration(500)
      height(400)
      x { d: js.Dynamic => d.key }
      y { d: js.Dynamic => d.y }
      showLabels(true)
      labelThreshold(0.01)
      legend(Chart.legend margin(Chart.margin top(5) right(35) bottom(5) left(0)))
      toJs)
    scope.data = Data.pieData
  }

  trait ScopeType extends ChartScope
}

// vim: set ts=2 sw=2 et:
