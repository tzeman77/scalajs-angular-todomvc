package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller


@JSExport
object DonutCtrl extends Controller {

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.pieChart
      height(450)
      donut(true)
      x { d: js.Dynamic => d.key }
      y { d: js.Dynamic => d.y }
      showLabels(true)
      legend(Chart.legend margin(Chart.margin top(5) right(70) bottom(5) left(0)))
      toJs)
    scope.data = Data.donutData
  }

  trait ScopeType extends ChartScope
}



// vim: set ts=2 sw=2 et:


// vim: set ts=2 sw=2 et:
