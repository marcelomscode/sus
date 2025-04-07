package fiap.sus.api.controllers;

import fiap.sus.application.usecases.checkinout.CheckInOutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkinout")
@RequiredArgsConstructor
public class CheckInOutController {

    private final CheckInOutUseCase checkInUseCase;

    @PutMapping("/checkin/{idMedico}/{idUnidade}")
    public void checkIn() {
        // Implement check-in logic here
    }

    @PutMapping("/checkout{idMedico}/{idUnidade}")
    public void checkOut() {
        // Implement check-out logic here
    }

}
