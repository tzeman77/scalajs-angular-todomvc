package todomvc.example

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Angular

@JSExport
object TodoApp extends JSApp {

  override def main() {
    val module = Angular.module("todomvc", Seq("nvd3"))

    module.controller(BulletCtrl)
    module.controller(CumulativeLineCtrl)
    module.controller(DiscreteBarCtrl)
    module.controller(DonutCtrl)
    module.controller(HistoricalBarCtrl)
    module.controller(MultiBarHorizontalCtrl)

    module.controller(TodoCtrl)

    module.directive(TodoItemDirective)
    module.directive(EscapeDirective)
    module.directive(FocusDirective)

    module.factory(TaskServiceFactory)
  }
}
