package fiap.sus.api.controllers;

import fiap.sus.application.usecases.CheckInUseCase;
import fiap.sus.application.usecases.CheckOutUseCase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkinout")
public class CheckInOutController {

    private final CheckInUseCase checkInUseCase;
    private final CheckOutUseCase checkOutUseCase;

    public CheckInOutController(CheckInUseCase checkInUseCase, CheckOutUseCase checkOutUseCase) {
        this.checkInUseCase = checkInUseCase;
        this.checkOutUseCase = checkOutUseCase;
    }

    @PutMapping("/checkin/{idMedico}/{idUnidade}")
    public void checkIn() {
        // Implement check-in logic here
    }

    @PutMapping("/checkout{idMedico}/{idUnidade}")
    public void checkOut() {
        // Implement check-out logic here
    }

}
