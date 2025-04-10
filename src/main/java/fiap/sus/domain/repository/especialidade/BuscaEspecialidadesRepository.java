package fiap.sus.domain.repository.especialidade;

import fiap.sus.domain.model.EspecialidadesDomain;

import java.util.List;

public interface BuscaEspecialidadesRepository {

    List<EspecialidadesDomain> listarTodasEspecialidades();

    EspecialidadesDomain buscarPorId(long id);

    EspecialidadesDomain buscarPorNome(String nome);

}
