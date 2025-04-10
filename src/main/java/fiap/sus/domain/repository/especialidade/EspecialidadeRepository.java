package fiap.sus.domain.repository.especialidade;

import fiap.sus.domain.model.EspecialidadesDomain;

public interface EspecialidadeRepository {

    EspecialidadesDomain save(EspecialidadesDomain especialidade);

    EspecialidadesDomain atualizar(EspecialidadesDomain especialidade);



}
