package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

public interface CheckInDomainRepository {

    void medicoRealizarCheckIn(CheckInOutDomain checkInOutDomain);

}
