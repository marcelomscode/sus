package fiap.sus.api.mappers;

import fiap.sus.api.dto.UnidadeDTO;
import fiap.sus.domain.model.UnidadeDomain;

public class UnidadeMapper {


    public static UnidadeDomain  toDomain(UnidadeDTO unidadeDTO) {
        return new UnidadeDomain(unidadeDTO.getId(), unidadeDTO.getNome(), unidadeDTO.getEndereco(), unidadeDTO.isAtivo());
    }

    public static UnidadeDTO toDTO(UnidadeDomain unidadeDomain) {
        return new UnidadeDTO(unidadeDomain.getId(), unidadeDomain.getNome(), unidadeDomain.getEndereco(), unidadeDomain.isAtivo());
    }

}
