package fiap.sus.api.mappers;

import fiap.sus.api.dto.unidade.UnidadeRequest;
import fiap.sus.api.dto.unidade.UnidadeResponse;
import fiap.sus.domain.model.UnidadeDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface UnidadeMapper {

    // ---- Request → Domain ----
    @Mapping(target = "especialidades", ignore = true) // Ignora no primeiro passo (será preenchido depois)
    UnidadeDomain toDomain(UnidadeRequest request);

    // ---- Domain → Persistence ----
//    @Mapping(target = "especialidades", source = "especialidades", qualifiedByName = "especialidadesDomainToPersistence")
    UnidadeResponse toResponse(UnidadeDomain domain);




//
//    public static UnidadeRequest toDTO(UnidadeDomain unidadeDomain) {
//        return new UnidadeRequest(unidadeDomain.getId(), unidadeDomain.getNome(),
//                unidadeDomain.getEndereco(), unidadeDomain.isAtivo(), unidadeDomain.getEspecialidades());
//    }

}
