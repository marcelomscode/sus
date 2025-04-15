package fiap.sus.infrastructure.mappers;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.infrastructure.persistence.CheckInOutPersistence;

public class CheckinOutPersistenceMapper {

    private CheckinOutPersistenceMapper() {
        throw new IllegalStateException("Classe Utilitaria, não deve ser instanciada");
    }

    public static CheckInOutDomain toCheckInDomain(CheckInOutPersistence checkinOutPersistence) {
        return CheckInOutDomain
                .builder()
                .id(checkinOutPersistence.getId())
                .idUnidade(checkinOutPersistence.getIdUnidade())
                .UUID(checkinOutPersistence.getUUID())
                .idMedico(checkinOutPersistence.getIdMedico())
                .checkIn(checkinOutPersistence.getCheckIn())
                .checkOut(checkinOutPersistence.getCheckOut())
                .data(checkinOutPersistence.getData())
                .build();
    }

    public static CheckInOutPersistence toCheckInOutPersistence(CheckInOutDomain checkInOutDomain) {
        return CheckInOutPersistence
                .builder()
                .id(checkInOutDomain.getId())
                .idUnidade(checkInOutDomain.getIdUnidade())
                .idMedico(checkInOutDomain.getIdMedico())
                .UUID(checkInOutDomain.getUUID())
                .checkIn(checkInOutDomain.getCheckIn())
                .checkOut(checkInOutDomain.getCheckOut())
                .build();
    }

    public static CheckInOutDomain toCheckOutDomain(CheckInOutPersistence checkinOutPersistence) {
        return CheckInOutDomain
                .builder()
                .idUnidade(checkinOutPersistence.getIdUnidade())
                .idMedico(checkinOutPersistence.getIdMedico())
                .checkIn(checkinOutPersistence.getCheckIn())
                .checkOut(checkinOutPersistence.getCheckOut())
                .build();
    }

    public static CheckInOutPersistence toCheckOutPersistence(CheckInOutDomain checkInOutDomain) {
        return CheckInOutPersistence
                .builder()
                .id(checkInOutDomain.getId())
                .idUnidade(checkInOutDomain.getIdUnidade())
                .idMedico(checkInOutDomain.getIdMedico())
                .checkIn(checkInOutDomain.getCheckIn())
                .checkOut(checkInOutDomain.getCheckOut())
                .build();
    }


}
