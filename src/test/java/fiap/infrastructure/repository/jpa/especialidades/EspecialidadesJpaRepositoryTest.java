package fiap.infrastructure.repository.jpa.especialidades;

import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static fiap.Helper.getEspecialidade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class EspecialidadesJpaRepositoryTest {

    @Mock
    private EspecialidadesJpaRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveSalvarUnidade() {

        var especialidade = EspecialidadesPersistence
                .builder()
                .nome("Cardiologia")
                .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                .build();

        when(repository.save(especialidade)).thenReturn(especialidade);

        var unidadeSalvo = repository.save(especialidade);

        assertThat(unidadeSalvo)
                .isNotNull()
                .isEqualTo(especialidade);
    }


    @Test
    void deveAtualizarEspecialidade() {

        var especialidade = getEspecialidade();

        when(repository.save(especialidade)).thenReturn(especialidade);

        var unidadeSalvo = repository.save(especialidade);

        unidadeSalvo.setDescricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos e suas doenças.");

        when(repository.save(unidadeSalvo)).thenReturn(unidadeSalvo);

        var unidadeAtualizada = repository.save(especialidade);
        assertThat(unidadeAtualizada)
                .isNotNull()
                .isEqualTo(unidadeSalvo);
    }

}
