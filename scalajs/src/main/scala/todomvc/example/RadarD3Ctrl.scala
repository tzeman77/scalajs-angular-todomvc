package todomvc.example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope

trait RadarChart extends js.Object {
  def draw(id: String, data: js.Any, cfg: js.Object): Unit = js.native
}

object RadarChart extends js.GlobalScope {
  val RadarChart: RadarChart = js.native
}

@JSExport
object RadarD3Ctrl extends Controller {

  override def initialize(scope: ScopeType) {
    RadarChart.RadarChart.draw("#radarD3", Data.radarD3data, Data.radarD3cfg)
  }

  trait ScopeType extends Scope {
  }
}

// vim: set ts=2 sw=2 et:
