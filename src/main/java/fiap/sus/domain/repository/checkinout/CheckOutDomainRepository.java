package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

public interface CheckOutDomainRepository {

    void medicoRealizaCheckOut(CheckInOutDomain checkInOutDomain);

}
