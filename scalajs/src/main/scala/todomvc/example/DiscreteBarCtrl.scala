package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportAll}

import com.greencatsoft.angularjs.Controller


@JSExport
object DiscreteBarCtrl extends Controller {

  import D3._

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.discreteBarChart transitionDuration(500)
      height(450)
      margin(Chart.margin top(20) left(70) right(20) bottom(60))
      x { d: js.Dynamic => d.label }
      y { d: js.Dynamic => d.value }
      showValues(true)
      valueFormat { d: js.Dynamic => d3.format(",.2f")(d) }
      xAxis(Chart.axis("Type"))
      yAxis(Chart.axis("Value") labelDistance(20))
      toJs)
    scope.data = Data.discreteBarData
  }

  trait ScopeType extends ChartScope
}



// vim: set ts=2 sw=2 et:
