package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.CheckOutDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckOutUseCase {

    private final CheckOutDomainRepository checkInOutRepository;

    public void checkOut(CheckInOutDomain checkInOutDomain) {
        checkInOutRepository.checkOut(checkInOutDomain);
    }

}
