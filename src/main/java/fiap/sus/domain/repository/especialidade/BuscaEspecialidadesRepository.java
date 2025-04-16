package fiap.sus.domain.repository.especialidade;

import fiap.sus.domain.model.EspecialidadesDomain;

import java.util.List;

public interface BuscaEspecialidadesRepository {

    List<EspecialidadesDomain> buscarTodasEspecialidades();

    EspecialidadesDomain buscarPorId(long id);

}
