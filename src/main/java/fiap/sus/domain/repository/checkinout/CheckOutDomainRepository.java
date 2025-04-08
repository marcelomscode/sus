package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

public interface CheckOutDomainRepository {

    void checkOut(CheckInOutDomain checkInOutDomain);

}
