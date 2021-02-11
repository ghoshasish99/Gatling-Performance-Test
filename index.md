# Gatling Test

![Gatling Test](https://github.com/ghoshasish99/gatling-test/workflows/Gatling%20Test/badge.svg)

Gatling HighCharts Dependency
 ```
 <dependency>
    <groupId>io.gatling.highcharts</groupId>
    <artifactId>gatling-charts-highcharts</artifactId>
    <version>3.1.0</version>
 </dependency>
 ```

Scala-library Dependency
```
<dependency>
    <groupId>org.scala-lang</groupId>
    <artifactId>scala-library</artifactId>
    <version>2.12.0</version>
</dependency>
```

To execute the test, use `mvn gatling:test`

To set the `http` config :
```scala
val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "application/json")
```
To create a request :
```scala
.exec(http("Create User")
.post("api/users")
.formParam("name", "Ashish")
.formParam("job", "Test Engineer")
```
To check response code :
```scala
.check(status.is(201))
```
To check a Json tag :
```scala
.check(jsonPath("$.name").is("Ashish"))
```
To test your JsonPath you can use [this.](https://jsonpath.com/)

To store a Json tag as a session variable :
```scala
.check(jsonPath("$.id").saveAs("id"))
```
Example of how to set up load :
```scala
setUp(
    scn.inject(
        nothingFor(5),
        atOnceUsers(10),
        rampUsers(10) during (20)
        ).protocols(httpConf)
  ).maxDuration(60)
````
To read more about Gatling, please refer to [this website.](https://gatling.io/)
