import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PerformanceTest extends Simulation {

  val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "application/json")

  val csvFeeder = csv("testdata/userdetails.csv").circular 

  def createUser() = {
     feed(csvFeeder)
      .exec(http("Create User")
       .post("api/users")
       .formParam("name", "${name}")
       .formParam("job", "${job}")
     .check(status.is(201))
     .check(jsonPath("$.name").is("${name}"))
     .check(jsonPath("$.id").saveAs("id")))
     //.exec{session => println(session); session}
  }  
  
  def deleteUser() = {
     exec(http("Delete User") 
       .delete("api/users/${id}")
     .check(status.is(204)))
     //.exec{session => println(session); session}
  }
  
  val scn = scenario("Create and Delete User")
  .forever() {
     exec(createUser())
     .pause(1)
     .exec(deleteUser())
     .pause(1)
  }      

  setUp(
    scn.inject(
        nothingFor(5),
        atOnceUsers(10),
        rampUsers(10) during (20)
        ).protocols(httpConf)
  ).maxDuration(60)

}