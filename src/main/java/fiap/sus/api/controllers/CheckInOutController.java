package fiap.sus.api.controllers;

import fiap.sus.api.dto.checkinout.CheckInOutRequest;
import fiap.sus.api.dto.checkinout.CheckInOutResponse;
import fiap.sus.api.mappers.CheckInOutMapper;
import fiap.sus.application.usecases.checkinout.BuscaCheckInOutUseCase;
import fiap.sus.application.usecases.checkinout.CheckInUseCase;
import fiap.sus.application.usecases.checkinout.CheckOutUseCase;
import fiap.sus.uteis.DatasConversao;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/checkinout")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CheckInOutController {

    private final CheckInUseCase checkInUseCase;
    private final CheckOutUseCase checkOutUseCase;
    private final BuscaCheckInOutUseCase buscaCheckInOutUseCase;

    @PostMapping("/checkin")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realiza o check-in de um médico na unidade.",
            description = "Realiza o check-in de um médico na unidade")
    public ResponseEntity<CheckInOutRequest> medicoRealizarCheckIn(@RequestBody CheckInOutRequest request) {
        var checkin = CheckInOutMapper.toCheckInDomain(request);

        checkInUseCase.medicoRealizarCheckIn(checkin);

        return ResponseEntity.ok(request);
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realiza o check-out de um médico na unidade.",
            description = "Realiza o check-out de um médico na unidade")
    public ResponseEntity<CheckInOutRequest> medicoRealizaCheckOut(@RequestBody CheckInOutRequest request) {
        var checkOut = CheckInOutMapper.toCheckInDomain(request);

        //Apenas um teste
        checkOutUseCase.medicoRealizaCheckOut(checkOut);

        return ResponseEntity.ok(request);
    }

    @GetMapping("medico-unidade/{idMedico}/{idUnidade}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca check-in/check-out por médico e unidade.",
            description = "Busca check-in/check-out por médico e unidade")
    public ResponseEntity<List<CheckInOutResponse>> buscaCheckInOutPorMedicoPorUnidade(@PathVariable Long idMedico, @PathVariable Long idUnidade) {

        var checkInOut = buscaCheckInOutUseCase.buscaCheckInOutPorMedicoPorUnidade(idMedico, idUnidade);

        var checkInOutDto = checkInOut.stream().map(CheckInOutMapper::toCheckInResponse).toList();

        log.info("FIM da busca check-in/check-out do médico [{}] na unidade [{}]", idMedico, idUnidade);
        return ResponseEntity.ok(checkInOutDto);
    }

    @GetMapping("unidade/{idUnidade}")
    @Operation(summary = "Busca todos os check-in/check-out feitos em uma unidade.",
            description = "Busca todos check-in/check-out feitos em uma unidade")
    public ResponseEntity<List<CheckInOutResponse>> buscaCheckInOutPorUnidade(@PathVariable Long idUnidade) {

        var checkInOut = buscaCheckInOutUseCase.buscaCheckInOutPorUnidade(idUnidade);

        var checkInOutDto = checkInOut.stream().map(CheckInOutMapper::toCheckInResponse).toList();

        log.info("FIM da busca check-in/check-out do médico na unidade [{}]", 1L);
        return ResponseEntity.ok(checkInOutDto);
    }

    @GetMapping("medico/{idMedico}")
    @Operation(summary = "Busca todos os check-in/check-out feitos por um médico.",
            description = "Busca todos check-in/check-out feitos por um médico")
    public ResponseEntity<List<CheckInOutResponse>> buscaCheckInOutPorMedico(@PathVariable String idMedico) {

        var checkInOut = buscaCheckInOutUseCase.buscaCheckInOutPorMedico(idMedico);

        var checkInOutDto = checkInOut.stream().map(CheckInOutMapper::toCheckInResponse).toList();

        log.info("FIM da busca check-in/check-out do médico [{}]", idMedico);
        return ResponseEntity.ok(checkInOutDto);

    }

    @GetMapping("unidade/{idUnidade}/medico/{idMedico}/data")
    @Operation(summary = "Busca todos check-in/check-out por médico, unidade e data.",
            description = "Busca todos check-in/check-out por médico, unidade e data")
    public ResponseEntity<List<CheckInOutResponse>> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(
            @PathVariable String idMedico, @PathVariable long idUnidade, @PathParam("data") String data) {

        var checkInOut = buscaCheckInOutUseCase.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(idMedico, idUnidade,
                DatasConversao.toLocalDate(data));
        var checkInOutDto = checkInOut.stream().map(CheckInOutMapper::toCheckInResponse).toList();
        log.info("FIM da busca check-in/check-out do médico [{}] na unidade [{}] com data [{}]", idMedico, idUnidade, data);
        return ResponseEntity.ok(checkInOutDto);
    }

    @GetMapping("unidade/{idUnidade}/data")
    @Operation(summary = "Busca todos check-in/check-out de todos os médicos por unidade e data.",
            description = "Busca todos check-in/check-out de todos os médicos por unidade e data")
    public ResponseEntity<List<CheckInOutResponse>> buscaCheckInOutPorData(@PathVariable long idUnidade,
                                                                           @PathParam("data") String data) {

        var checkInOut = buscaCheckInOutUseCase.buscaCheckInOutPorUnidadeEData(idUnidade, DatasConversao.toLocalDate(data));
        var checkInOutDto = checkInOut.stream().map(CheckInOutMapper::toCheckInResponse).toList();

        log.info("FIM da busca check-in/check-out da unidade [{}] com data [{}]", idUnidade, data);
        return ResponseEntity.ok(checkInOutDto);
    }

}
