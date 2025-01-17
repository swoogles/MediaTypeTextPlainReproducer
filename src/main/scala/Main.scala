import zio._
import zio.http.Server
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}

import zio.schema.Schema

import zio.http._
import zio.http.endpoint.Endpoint
import zio.schema.DeriveSchema.gen

case class UserIdsCsvRaw(
                          content: String
                        )

object UserIdsCsvRaw {
  implicit val schema: Schema[UserIdsCsvRaw] = Schema.primitive[String].transform(UserIdsCsvRaw(_), _.content)
}

object Main extends ZIOAppDefault {

  val postUsersCsv =
    Endpoint(
      Method.POST / "users"
    )
      .in[UserIdsCsvRaw](MediaType.text.plain)
      .out[Unit]

  val getUsersCsv =
    Endpoint(
      Method.GET / "users"
    )
      .out[UserIdsCsvRaw](Status.Ok, MediaType.text.plain)
      .examplesOut(
        "premade" -> UserIdsCsvRaw(
          """user1
            |user2
            |user3
            |""".stripMargin
        )
      )

  val openApiCohorts =
    OpenAPIGen.fromEndpoints(
      title = "API",
      version = "0.1",
      List(getUsersCsv, postUsersCsv)
    )

  def run =
    Server
      .serve(
        SwaggerUI.routes("docs", openApiCohorts)
      ).provide(
        Server.default
      )

}