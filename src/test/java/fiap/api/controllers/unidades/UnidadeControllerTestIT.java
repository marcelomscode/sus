package fiap.api.controllers.unidades;

import fiap.Helper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnidadeControllerTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class SalvarAtualizarUnidade {

        @Test
        void deveSalvarUnidadeRestAssured() {
            var unidade = Helper.getUnidade();

            var response = given()
                    .when()
                    .contentType("application/json")
                    .body(unidade)
                    .post("/api/unidades")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract();
            assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        }

        @Test
        void deveAtualizarUnidade() {
            var unidade = Helper.getUnidade();
            unidade.setId(5L);
            unidade.setNome("Unidade Atualizada");

            given()
                    .when()
                    .contentType("application/json")
                    .body(unidade)
                    .put("/api/unidades")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("schemas/unidades.schema.json"));
        }
    }

    @Nested
    class DeveExcluirUnidade {

        @Test
        void deveExcluirUnidade() {

            var response = given()
                    .when()
                    .contentType("application/json")
                    .delete("/api/unidades/{id}", 1L)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .extract();
            assertThat(response.statusCode()).isEqualTo(204);
        }

        @Test
        void deveLancarExcecaoExcluirUnidadeInvalida() {

            given()
                    .when()
                    .contentType("application/json")
                    .delete("/api/unidades/{id}", 11L)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("schemas/unidades.invalida.schema.json"));
        }
    }

    @Nested
    class DeveBuscarUnidades {

        @Test
        void deveBuscarUnidadePorIdREstAssered(){
            var idUnidade = 1L;

            var response = given()
                    .when()
                    .contentType("application/json")
                    .get("/api/unidades/{id}", idUnidade)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract();

            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.body().jsonPath().getString("id")).isEqualTo("1");
            assertThat(response.body().jsonPath().getString("nome")).isEqualTo("Unidade 1");
            assertThat(response.body().jsonPath().getString("endereco")).isEqualTo("Rua A, 123");
            assertThat(response.body().jsonPath().getString("especialidades[0].id")).isEqualTo("2");
        }

        @Test
        void deveBuscaListaUnidades() {
            var response = given()
                    .when()
                    .contentType("application/json")
                    .get("/api/unidades")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract();

            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.body().jsonPath().getList("id")).hasSize(5);
            assertThat(response.body().jsonPath().getString("nome[0]")).isEqualTo("Unidade 1");
            assertThat(response.body().jsonPath().getString("nome[1]")).isEqualTo("Unidade 2");
        }

        @Test
        void deveBuscaMedicosPorUnidade() {

            var idUnidade = 1L;

            given()
                    .when()
                    .contentType("application/json")
                    .param("data", "2023-10-01")
                    .get("/api/unidades/medicos/{idUnidade}/checkin", idUnidade)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("idUnidade", is(1))
                    .body("medicosAtendendo[0].checkIn", is("2023-10-01T08:00:00"))
                    .body("medicosAtendendo[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .extract();
        }

        @Test
        void deveGerarExecaoQuandoIdNaoEncontrado() {
            var idUnidade = 11L;

            given()
                    .when()
                    .contentType("application/json")
                    .get("/api/unidades/{id}", idUnidade)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("erros.erro", is("Unidade não encontrada com id: " + idUnidade))
                    .extract();
        }
    }

}

