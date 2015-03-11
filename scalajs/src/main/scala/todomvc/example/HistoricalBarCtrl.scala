package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller

@JSExport
object HistoricalBarCtrl extends Controller {

  import D3._
  import js.DynamicImplicits.number2dynamic

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.historicalBarChart
      height(450)
      margin(Chart.margin top(20) left(60) right(20) bottom(80))
      x { d: js.Dynamic => number2dynamic(d.asInstanceOf[js.Array[Double]](0)) }
      y { d: js.Dynamic => number2dynamic(d.asInstanceOf[js.Array[Double]](1) / 100000) }
      showValues(true)
      valueFormat { d: js.Dynamic => d3.format(",.1f")(d) }
      xAxis(Chart.axis("Day [dd.mm.yy]") showMaxMin(false) rotateLabels(50)
        tickFormat({d: Double => D3.timeFormat("%d.%m.%y")(new js.Date(d))})
      )
      yAxis(Chart.axis("Value") labelDistance(30)
        tickFormat {d: Double => d3.format(",.1f")(d)})
      toJs)
    scope.data = Data.historicalBarData
  }

  trait ScopeType extends ChartScope
}



// vim: set ts=2 sw=2 et:
