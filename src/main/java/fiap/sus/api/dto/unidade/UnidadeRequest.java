package fiap.sus.api.dto.unidade;

import fiap.sus.api.dto.especialidade.EspecialidadeUnidadeRequest;

import java.util.Set;

public record UnidadeRequest(Long id, String nome, String endereco, boolean ativo, Set<EspecialidadeUnidadeRequest> especialidades) {

}
