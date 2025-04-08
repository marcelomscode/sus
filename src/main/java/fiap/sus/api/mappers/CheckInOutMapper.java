package fiap.sus.api.mappers;

import fiap.sus.api.dto.CheckInOutDTO;
import fiap.sus.domain.model.CheckInOutDomain;

public class CheckInOutMapper {

    private CheckInOutMapper() {
        throw new IllegalStateException("Classe Utilitaria, não deve ser instanciada");
    }

    public static CheckInOutDTO toCheckInDTO(CheckInOutDomain checkInDomain) {
        return new CheckInOutDTO(checkInDomain.getId(), checkInDomain.getIdMedico(), checkInDomain.getIdUnidade());
    }

    public static CheckInOutDomain toCheckInDomain(CheckInOutDTO checkInOutDTO) {
        return CheckInOutDomain
                .builder()
                .id(checkInOutDTO.getId())
                .idMedico(checkInOutDTO.getIdMedico())
                .idUnidade(checkInOutDTO.getIdUnidade())
                .build();
    }
}
