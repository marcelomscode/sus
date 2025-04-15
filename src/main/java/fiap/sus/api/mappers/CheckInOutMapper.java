package fiap.sus.api.mappers;

import fiap.sus.api.dto.checkinout.CheckInOutRequest;
import fiap.sus.api.dto.checkinout.CheckInOutResponse;
import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.uteis.variaveis.ConstantesGlobais;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckInOutMapper {

    private CheckInOutMapper() {
        throw new IllegalStateException(ConstantesGlobais.CLASSES_UTILITARIAS);
    }

    public static CheckInOutResponse toCheckInResponse(CheckInOutDomain checkInDomain) {
        return new CheckInOutResponse(
                checkInDomain.getId(),
                checkInDomain.getIdMedico(),
                checkInDomain.getUUID(),
                checkInDomain.getIdUnidade(),
                verificaSeDataNulaEFormata(checkInDomain.getCheckIn()),
                verificaSeDataNulaEFormata(checkInDomain.getCheckOut()),
                verificaSeDataNulaEFormata(checkInDomain.getData())
                );
    }

    public static CheckInOutDomain toCheckInDomain(CheckInOutRequest checkInOutDTO) {
        return CheckInOutDomain
                .builder()
              //.id(checkInOutDTO.getId())
                .idMedico(checkInOutDTO.getIdMedico())
                .UUID(checkInOutDTO.getUUID())
                .idUnidade(checkInOutDTO.getIdUnidade())
                .build();
    }

    private static String verificaSeDataNulaEFormata(LocalDateTime data) {
        if (data == null) {
            return null;
        }
        return data.format(DateTimeFormatter.ofPattern(ConstantesGlobais.DATA_FORMATADA));
    }

}
