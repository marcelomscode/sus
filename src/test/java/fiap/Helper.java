package fiap;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.mappers.UnidadePersistenceMapper;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.persistence.UnidadePersistence;

import java.util.Set;

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

        var especialidades = Set.of(
                new EspecialidadesDomain(1L, "Cardiologia", "Cardiologista"),
                new EspecialidadesDomain(2L, "Neurologia", "Neurologista")
        );

        return new UnidadeDomain(null, "Jaçanã", "Rua dos Três Irmãos, 123", especialidades,true);
    }


    public static UnidadePersistence getUnidadePersistence(UnidadeDomain unidade) {
        return UnidadePersistence
                .builder()
                .nome(unidade.getNome())
                .endereco(unidade.getEndereco())
                .especialidades(UnidadePersistenceMapper.toPersistenceList(unidade.getEspecialidades()))
                .ativo(unidade.isAtivo())
                .build();
    }

    public static EspecialidadesPersistence getEspecialidade() {
        return EspecialidadesPersistence
                .builder()
                .nome("Cardiologia")
                .descricao("Cardiologia é a especialidade médica que estuda o coração.")
                .build();
    }



}
