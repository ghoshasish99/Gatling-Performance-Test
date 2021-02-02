import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PerformanceTest extends Simulation {

  val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "application/json")


  val scn = scenario("My First Test")
     .exec(http("Get Page 2")
     .get("api/users?page=2")
     .check(status.is(200)))
     .pause(1)
      
     .exec(http("Get Page 3") 
     .get("api/users?page=3")
     .check(status.is(200)))
     .pause(1)


  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}