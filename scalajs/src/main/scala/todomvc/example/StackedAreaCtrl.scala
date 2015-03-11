package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope

@JSExport
object StackedAreaCtrl extends Controller {

  import D3._
  import js.DynamicImplicits.number2dynamic

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.stackedAreaChart transitionDuration(700)
      height(450)
      margin(Chart.margin top(20) left(50) right(20) bottom(60))
      useInteractiveGuideline(true)
      useVoronoi(false)
      clipEdge(true)
      x { d: js.Dynamic => number2dynamic(d.asInstanceOf[js.Array[Double]](0)) }
      y { d: js.Dynamic => number2dynamic(d.asInstanceOf[js.Array[Double]](1)) }
      xAxis(Chart.axis("Day [dd.mm.yy]") showMaxMin(false)
        tickFormat({d: Double => D3.timeFormat("%d.%m.%y")(new js.Date(d))})
      )
      yAxis(Chart.axis("Value") labelDistance(20)
        tickFormat {d: Double => d3.format(",.2f")(d)})
      toJs)
    scope.data = Data.stackedAreaData
  }

  trait ScopeType extends ChartScope
}

// vim: set ts=2 sw=2 et:
