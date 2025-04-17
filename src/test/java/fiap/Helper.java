package fiap;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.mappers.UnidadePersistenceMapper;
import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.persistence.UnidadePersistence;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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

    public static EspecialidadesDomain getEspecialidadeDomain() {
        return EspecialidadesDomain
                .builder()
                .id(1L)
                .nome("Cardiologia")
                .descricao("Cardiologia é a especialidade médica que estuda o coração.")
                .build();
    }


    public static CheckInOutPersistence getCheckInOutPersistence() {

        return CheckInOutPersistence
                .builder()
                .id(1L)
                .idMedico(1L)
                .UUID(UUID.randomUUID().toString())
                .idUnidade(1L)
                .checkIn(LocalDateTime.now())
                .data(LocalDateTime.now())
                .build();
    }

    public static CheckInOutDomain getCheckInOutDomain() {

        return CheckInOutDomain
                .builder()
                .id(1L)
                .idMedico(1L)
                .UUID("921945ff-508d-42ba-91b1-1871ecd3fbae")
                .idUnidade(1L)
                .checkIn(LocalDateTime.now())
                .data(LocalDateTime.now())
                .build();
    }


}
