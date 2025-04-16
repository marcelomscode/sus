package fiap.sus.domain.repository.medico;

import fiap.sus.domain.model.MedicoDomain;

public interface BuscaInformacoesMedicoRepository {

    MedicoDomain buscaMedicoByUUID(String uuid);

}
