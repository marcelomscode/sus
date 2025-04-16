package fiap.infrastructure.repository.jpa.unidade;

import fiap.sus.domain.exceptions.UnidadeException;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static fiap.Helper.getUnidadePersistence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UnidadeJpaRepositoryTest {

    @Mock
    private UnidadeJpaRepository repository;

    @Mock
    private CheckInOutJpaRepository checkInOutRepository;

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
    class SalvarUnidade {

        @Test
        void deveSalvarUnidade() {

            var unidade = getUnidadePersistence();
            when(repository.save(unidade)).thenReturn(unidade);

            var unidadeSalvo = repository.save(unidade);
            assertThat(unidadeSalvo)
                    .isNotNull()
                    .isEqualTo(unidade);

            verify(repository, times(1)).save(unidade);
        }

        @Test
        void deveLancarExcecaoAoSalvarUnidade() {

            var unidade = getUnidadePersistence();

            when(repository.save(unidade)).thenThrow(new RuntimeException("Erro ao salvar unidade"));

            try {
                repository.save(unidade);
            } catch (RuntimeException e) {
                assertThat(e.getMessage()).isEqualTo("Erro ao salvar unidade");
            }
            verify(repository, times(1)).save(unidade);
        }
    }

    @Nested
    class atualizaUnidade{

        @Test
        void deveAtualizarUnidade() {

            var unidade = getUnidadePersistence();

            when(repository.save(unidade)).thenReturn(unidade);

            var unidadeAtualizada = repository.save(unidade);
            assertThat(unidadeAtualizada)
                    .isNotNull()
                    .isEqualTo(unidade);

            verify(repository, times(1)).save(unidade);
        }

        @Test
        void deveLancarExcecaoAoAtualizarUnidade() {

            var unidade = getUnidadePersistence();

            when(repository.save(unidade)).thenThrow(
                    new UnidadeException("Erro ao atualizar unidade", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()));

            try {
                repository.save(unidade);
            } catch (UnidadeException e) {
                assertThat(e.getMessage()).isEqualTo("Erro ao atualizar unidade");
            }
            verify(repository, times(1)).save(unidade);
        }
    }


    @Nested
    class excluirUnidade {

        @Test
        void deveExcluirUnidade() {

            var unidade = getUnidadePersistence();

            doNothing().when(repository).delete(unidade);

            repository.delete(unidade);

            verify(repository, times(1)).delete(unidade);
        }

        @Test
        void deveExcluirUnidadeVerificandoParametrosPassados() {
            var unidade = getUnidadePersistence();

            doAnswer((Answer<Void>) invocation -> {
                var id = invocation.getArgument(0);
                Assertions.assertEquals(unidade, id);
                return null;
            }).when(repository).delete(unidade);
            repository.delete(unidade);
        }

    }

    @Nested
    class ListarUnidades {

        @Test
        void deveBuscarUnidadePorId() {

            var unidade = getUnidadePersistence();

            when(repository.findById(anyLong())).thenReturn(Optional.of(unidade));

            var unidadeBuscada = repository.findById(1L);

            assertThat(unidadeBuscada)
                    .isPresent()
                    .containsSame(unidade);

            verify(repository, times(1)).findById(1L);
        }

        @Test
        void deveRetornarListaDeUnidades() {
            var unidade1 = getUnidadePersistence();

            var unidade2 = UnidadePersistence
                    .builder()
                    .id(2L)
                    .nome("Unidade Centro")
                    .endereco("Rua da Consolação, 2000")
                    .build();

            when(repository.findAll()).thenReturn(List.of(unidade1, unidade2));

            var unidadesBuscadas = repository.findAll();

            assertThat(unidadesBuscadas)
                    .isNotEmpty()
                    .hasSize(2)
                    .containsExactlyInAnyOrder(unidade1, unidade2);

            verify(repository, times(1)).findAll();
        }

        @Test
        void deveBuscarMedicosAtendendoNaUnidade() {
            var unidade = getUnidadePersistence();

            when(checkInOutRepository.buscaMedicosComCheckInEmUmaUnidade(unidade.getId(),
                    LocalDate.of(2025,4,10))).thenReturn(List.of());

            var medicos = checkInOutRepository.buscaMedicosComCheckInEmUmaUnidade(unidade.getId(),
                    LocalDate.of(2025,4,10));

            assertThat(medicos)
                    .isNotNull()
                    .isEmpty();

            verify(checkInOutRepository, times(1)).buscaMedicosComCheckInEmUmaUnidade(unidade.getId(),
                    LocalDate.of(2025,4,10));
        }

    }

}
