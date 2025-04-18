package fiap.api.controllers.checkinout;

import fiap.sus.api.dto.checkinout.CheckInOutRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckInOutControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class MedicoFazCheckInOut {

        @Test
        void medicoDeveRealizarCheckIn() {

            var checkIn = getCheckInOutRequest();

            given()
                    .when()
                    .body(checkIn)
                    .contentType(ContentType.JSON)
                    .post("/api/checkinout/checkin")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("idUnidade", is(1))
                    .body("uuid", is("b0810acc-9a4c-4471-9225-510fde248663"))
                    .body("idMedico", is(1));
        }

        @Test
        void medicoDeveRealizarCheckOut() {

            var checkOut = getCheckInOutRequestTeste();

            var checkIn = getCheckInOutRequestTeste();

            //Faz primeiro o checkin
            given()
                    .when()
                    .body(checkIn)
                    .contentType(ContentType.JSON)
                    .post("/api/checkinout/checkin")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("idUnidade", is(1))
                    .body("uuid", is("b0810acc-9a4c-4471-9225-510fde24866355"))
                    .body("idMedico", is(1));

            //Faz o checkout
            given()
                    .when()
                    .body(checkOut)
                    .contentType(ContentType.JSON)
                    .post("/api/checkinout/checkout")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("idUnidade", is(1))
                    .body("uuid", is("b0810acc-9a4c-4471-9225-510fde24866355"))
                    .body("idMedico", is(1));
        }

        private static CheckInOutRequest getCheckInOutRequest() {
            return CheckInOutRequest.builder()
                    .idMedico(1L)
                    .UUID("b0810acc-9a4c-4471-9225-510fde248663")
                    .idUnidade(1L)
                    .build();
        }

        private static CheckInOutRequest getCheckInOutRequestTeste() {
            return CheckInOutRequest.builder()
                    .idMedico(1L)
                    .UUID("b0810acc-9a4c-4471-9225-510fde24866355")
                    .idUnidade(1L)
                    .build();
        }


    }

    @Nested
    class BuscaCheckInOutPorMedicoUnidade {

        @Test
        void deveBuscarCheckInOutPorMedicoUnidade() {
            var idMedico = 1L;
            var idUnidade = 1L;

            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get("/api/checkinout/medico-unidade/{idMedico}/{idUnidade}", idMedico, idUnidade)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("[0].idUnidade", is(1))
                    .body("[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .body("[0].idMedico", is(1))
                    .body("[0].checkIn", is("01/10/2023 08:00:00"))
                    .body("[0].data", is("01/10/2023 00:00:00"));
        }

        @Test
        void deveBuscarCheckInOutPorUnidade(){
            var idUnidade = 1L;

            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get("/api/checkinout/unidade/{idUnidade}", idUnidade)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("[0].idUnidade", is(1))
                    .body("[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .body("[0].idMedico", is(1))
                    .body("[0].checkIn", is("01/10/2023 08:00:00"))
                    .body("[0].data", is("01/10/2023 00:00:00"));
        }

        @Test
        void deveBuscarCheckInOutPorMedico(){

            var idMedicoUUID = "123e4567-e89b-12d3-a456-426614174000";

            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get("/api/checkinout/medico/{idMedico}", idMedicoUUID)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("[0].idUnidade", is(1))
                    .body("[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .body("[0].idMedico", is(1))
                    .body("[0].checkIn", is("01/10/2023 08:00:00"))
                    .body("[0].data", is("01/10/2023 00:00:00"));
        }

        @Test
        void deveBuscarCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(){

            var idMedico = "123e4567-e89b-12d3-a456-426614174000";
            var idUnidade = 1L;
            var data = "01/10/2023";

            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .param("data", data)
                    .get("/api/checkinout/unidade/{idUnidade}/medico/{idMedico}/data", idUnidade, idMedico)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("[0].idUnidade", is(1))
                    .body("[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .body("[0].idMedico", is(1))
                    .body("[0].checkIn", is("01/10/2023 08:00:00"))
                    .body("[0].data", is("01/10/2023 00:00:00"));

        }

        @Test
        void deveBuscarCheckInOutPorData(){

            var idUnidade = 1L;
            var data = "01/10/2023";

            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .param("data", data)
                    .get("/api/checkinout/unidade/{idUnidade}/data", idUnidade)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("[0].idUnidade", is(1))
                    .body("[0].uuid", is("123e4567-e89b-12d3-a456-426614174000"))
                    .body("[0].idMedico", is(1))
                    .body("[0].checkIn", is("01/10/2023 08:00:00"))
                    .body("[0].data", is("01/10/2023 00:00:00"));
        }

    }


}
