package karate

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class UserSimulation extends Simulation{

  val getAllBooks = scenario("Get all books").exec(karateFeature("classpath:karate/BookTest.feature@Books"))

  setUp(
    getAllBooks.inject(rampUsers(10).during(10 seconds))
  )
}