package fiap.api.controllers.especialidades;

import fiap.sus.api.dto.especialidade.EspecialidadeRequest;
import fiap.sus.api.dto.especialidade.NovaEspecialidadeRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class EspecialidadeControllerIT {


    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

@Nested
class SalvarEspecialidade {
    @Test
    void deveSalvarEspecialidade() {
        var especialidade = NovaEspecialidadeRequest.builder()
                .nome("Cardiologia")
                .descricao("Especialidade médica que cuida do coração e sistema cardiovascular")
                .build();

        given()
                .when()
                .contentType("application/json")
                .body(especialidade)
                .post("especialidades")
                .then()
                .statusCode(200)
                .body(is("Especialidade salva com sucesso"));
    }

    @Test
    void deveAtualizarEspecialidade() {

        var especialidade = NovaEspecialidadeRequest.builder()
                .nome("Psicologia")
                .descricao("Cuidando bem dos profissionais de saúde mental")
                .build();

        given()
                .when()
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTc0NTA4NTE5NH0.LLyk40rWfjUgXsevvUT8B8NTWMLBCL41_6K2ZpbxLYpmFXCv3rS4IAPHVBeXIf_Ypp5J_9RLZw_cbw8uTTYaUMuqPyZyLEZuF5wRq-yuDk6HYtlDtodw0OIKLskEfU82xE9QkhIEjizZY5grDbwUQCxL5RgOybgmIqMbsmbsCenBgrkLaRLMytnt3vuenvmEYlMmvoQvSmraGimba5-q4sSGtmJuZ3p0ktM433YtwJSXFS9fOZ9Vd_wgrVID7csRKcipXya9B8W0r3Mt6vZ-yegq-Z6o8LiRVmWF-ZHnB62vXsRGO5QgQQsFGtnTItix_ziECwZy0BJlwP2V8Ifg4A")
                .contentType("application/json")
                .body(especialidade)
                .post("especialidades")
                .then()
                .statusCode(200)
                .body(is("Especialidade salva com sucesso"));

        var especialidadeAtualizada = EspecialidadeRequest.builder()
                .id(5L)
                .nome("Psicologia")
                .descricao("Cuidando bem dos profissionais de TI")
                .build();

        given()
                .when()
                .contentType("application/json")
                .body(especialidadeAtualizada)
                .put("especialidades")
                .then()
                .statusCode(200)
                .body("id", is(5))
                .body("nome", is("Psicologia"))
                .body("descricao", is("Cuidando bem dos profissionais de TI"));
    }

}

    @Nested
    class ListarEspecialidades {

        @Test
        void deveListarTodasAsEspecialidades() {

            given()
                    .when()
                    .contentType("application/json")
                    .get("especialidades")
                    .then()
                    .statusCode(200)
                    .body("[0].id", is(1))
                    .body("[0].nome", is("Dermatologia"))
                    .body("[0].descricao", is("Especialidade médica que cuida da pele e suas doenças"))
                    .body("[1].id", is(2))
                    .body("[1].nome", is("Pediatria"))
                    .body("[1].descricao", is("Especialidade médica que cuida da saúde infantil"))
                    .body("[2].id", is(3))
                    .body("[2].nome", is("Ginecologia"))
                    .body("[2].descricao", is("Especialidade médica que cuida da saúde da mulher"))
                    .body("[3].id", is(4))
                    .body("[3].nome", is("Oftalmologia"))
                    .body("[3].descricao", is("Especialidade médica que cuida dos olhos e visão"));
        }
    }


}
