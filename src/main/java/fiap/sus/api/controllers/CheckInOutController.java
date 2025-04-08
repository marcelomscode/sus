package fiap.sus.api.controllers;

import fiap.sus.api.dto.CheckInOutDTO;
import fiap.sus.api.mappers.CheckInOutMapper;
import fiap.sus.application.usecases.checkinout.CheckInUseCase;
import fiap.sus.application.usecases.checkinout.CheckOutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkinout")
@RequiredArgsConstructor
public class CheckInOutController {

    private final CheckInUseCase checkInUseCase;
    private final CheckOutUseCase checkOutUseCase;

    @PostMapping("/checkin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> checkIn(@RequestBody CheckInOutDTO checkInOutDTO) {
       var checkin = CheckInOutMapper.toCheckInDomain(checkInOutDTO);

      checkInUseCase.checkIn(checkin);

      return ResponseEntity.ok("Check-in realizado com sucesso!");
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> checkOut(@RequestBody CheckInOutDTO checkInOutDTO) {
        var checkOut = CheckInOutMapper.toCheckInDomain(checkInOutDTO);

        checkOutUseCase.checkOut(checkOut);

        return ResponseEntity.ok("Check-out realizado com sucesso!");
    }



}
