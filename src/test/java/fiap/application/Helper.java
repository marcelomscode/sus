package fiap.application;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.UnidadePersistence;

public class Helper {

    public static UnidadePersistence getUnidadePersistence() {
        return UnidadePersistence
                .builder()
                .id(1L)
                .nome("Unidade Santana")
                .endereco("Avenida Paulista, 1000")
                .build();
    }


    public static UnidadeDomain getUnidade() {
        return new UnidadeDomain(null, "Jaçanã", "Rua dos Três Irmãos, 123");
    }


    public static UnidadePersistence getUnidadePersistence(UnidadeDomain unidade) {
        return UnidadePersistence
                .builder()
                .nome(unidade.getNome())
                .endereco(unidade.getEndereco())
                .build();
    }


}
