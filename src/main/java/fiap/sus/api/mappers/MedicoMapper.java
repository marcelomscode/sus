package fiap.sus.api.mappers;

import fiap.sus.api.dto.MedicoResponse;
import fiap.sus.domain.model.MedicoDomain;

public class MedicoMapper {

    public static MedicoDomain toDoMain(MedicoResponse response) {
        return new MedicoDomain(
                null,
                response.getId(),
          response.getFirstName() + response.getLastName(),
                response.getCrm()
        );
    }

}
