package fiap.infrastructure.repository.impl.especialidades;

import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.BuscaEspecialidadesRepository;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.repository.impl.especialidades.EspecialidadesRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EspecialidadesRepositoryImplTest {

    @Mock
    EspecialidadesJpaRepository repository;

    @Mock
    BuscaEspecialidadesRepository buscaEspecialidadesRepository;

    @InjectMocks
    EspecialidadesRepositoryImpl unidadeRepositoryImpl;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class salvaEspecialidades {

        @Test
        void deveSalvarEspecialidade() {

            var especialidade = EspecialidadesPersistence
                    .builder()
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                    .build();

            var especialidadeDomain = EspecialidadesDomain
                    .builder()
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                    .build();

            when(repository.save(any(EspecialidadesPersistence.class))).thenReturn(especialidade);

            var unidadeSalvo = unidadeRepositoryImpl.save(especialidadeDomain);

            assertThat(unidadeSalvo)
                    .isNotNull()
                    .isInstanceOf(EspecialidadesDomain.class);

            assertThat(unidadeSalvo.getNome())
                    .isEqualTo("Cardiologia");

            assertThat(unidadeSalvo.getDescricao())
                    .isEqualTo("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.");

        }
        @Test
        void deveLancarExcecaoAoSalvarEspecialidade(){
            var especialidadeDomain = EspecialidadesDomain
                    .builder()
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                    .build();

            when(repository.save(any(EspecialidadesPersistence.class))).thenThrow(EspecialidadeException.class);

            assertThatThrownBy( () -> unidadeRepositoryImpl.save(especialidadeDomain))
                    .isInstanceOf(EspecialidadeException.class)
                    .hasMessageContaining("Erro ao salvar especialidade.");
        }

        @Test
        void deveAtualizarEspecialidade() {
            var especialidade = EspecialidadesPersistence
                    .builder()
                    .id(45L)
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos e veias e artérias.")
                    .build();

            var especialidadeDomain = EspecialidadesDomain
                    .builder()
                    .id(45L)
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                    .build();

            when(repository.save(any(EspecialidadesPersistence.class))).thenReturn(especialidade);

            when(repository.findById(any(Long.class))).thenReturn(Optional.of(especialidade));

            var unidadeSalvo = unidadeRepositoryImpl.atualizar(especialidadeDomain);

            assertThat(unidadeSalvo.getDescricao())
                    .contains("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.");



        }

        @Test
        void deveLancarExcecaoAoAtualizarEspecialidade() {

            var especialidadeDomain = EspecialidadesDomain
                    .builder()
                    .nome("Cardiologia")
                    .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                    .build();

            when(buscaEspecialidadesRepository.buscarPorId(any(Long.class))).thenReturn(especialidadeDomain);

            when(repository.save(any(EspecialidadesPersistence.class))).thenThrow(EspecialidadeException.class);

            assertThatThrownBy( () -> unidadeRepositoryImpl.atualizar(especialidadeDomain))
                    .isInstanceOf(EspecialidadeException.class)
                    .hasMessageContaining("Erro ao atualizar especialidade: Especialidade não encontrada.");
        }



    }


}
