package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.CheckInDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInUseCase {

    private final CheckInDomainRepository checkInOutRepository;

    public void checkIn(CheckInOutDomain checkInOutDomain) {
        checkInOutRepository.checkIn(checkInOutDomain);
    }

}
