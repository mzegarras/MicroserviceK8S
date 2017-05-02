package basic

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._


class RestSimulation extends Simulation { // 3

  val httpConf = http // 4
    .baseURL("http://msparametros.default.svc.cluster.local/ms-parametros/api/country/v1/list") // 5

  val scn = scenario("RestSimulation") // 7
    .exec(http("request_1")  // 8
    .get("/")
    .header("Content-Type", "application/json")) // 9
    .pause(5) // 10

  setUp( // 11
    //scn.inject(atOnceUsers(10000)) // 12
    scn.inject(rampUsers(20000) over (30 seconds))
  ).protocols(httpConf) // 13
}