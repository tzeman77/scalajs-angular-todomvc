package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSName}

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.{ Location, Scope }

object D3 {
  val d3 = js.Dynamic.global.d3
  def timeFormat(fmt: String)(v: js.Any) =
    d3.time.format(fmt)(v)
}

object Data extends js.GlobalScope {
  val cumulativeLineData: js.Array[Any] = js.native
}

@JSExport
object CumulativeLineCtrl extends Controller {

  import D3._

  override def initialize(scope: ScopeType) {
    scope.options = Options(Chart.cumulativeLineChart transitionDuration(300)
      useInteractiveGuideline(true)
      clipVoronoi(false)
      height(450)
      color(d3.scale.category10().range())
      margin(Chart.margin top(20) left(70) right(20) bottom(60))
      x({ d: js.Array[Double] => d(0) })
      y({ d: js.Array[Double] => d(1) / 100 })
      average { d: js.Dynamic => d.mean.asInstanceOf[js.UndefOr[Double]].map(_/100) }
      xAxis(Chart.axis("Day [dd.mm.yy]") showMaxMin(true) staggerLabels(true)
        tickFormat({d: Double => D3.timeFormat("%d.%m.%y")(new js.Date(d))})
      )
      yAxis(Chart.axis("Increase [%]") labelDistance(20)
        tickFormat {d: Double => d3.format(",.1%")(d)})
      toJs)
    scope.data = Data.cumulativeLineData
  }

  trait ScopeType extends ChartScope
}


// vim: set ts=2 sw=2 et:
