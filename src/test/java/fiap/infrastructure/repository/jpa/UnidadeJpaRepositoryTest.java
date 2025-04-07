package fiap.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import java.util.List;
import java.util.Optional;
import static fiap.application.Helper.getUnidadePersistence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UnidadeJpaRepositoryTest {

    @Mock
    private UnidadeJpaRepository repository;

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
    }

}
