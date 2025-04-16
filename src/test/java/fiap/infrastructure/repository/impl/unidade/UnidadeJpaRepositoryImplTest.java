package fiap.infrastructure.repository.impl.unidade;

import fiap.sus.domain.exceptions.UnidadeException;
import fiap.sus.infrastructure.repository.impl.unidade.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static fiap.Helper.getUnidade;
import static fiap.Helper.getUnidadePersistence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UnidadeJpaRepositoryImplTest {

    @Mock
    UnidadeJpaRepository repository;

    @InjectMocks
    UnidadeRepositoryImpl unidadeRepositoryImpl;

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
    void deveSalvarUnidadePersistence() {
        var unidade = getUnidadePersistence(getUnidade());

        when(repository.save(unidade)).thenReturn(unidade);

        unidadeRepositoryImpl.save(getUnidade());

        verify(repository, times(1)).save(unidade);
    }

    @Test
    void deveListarTodasUnidades() {
        var unidade = getUnidadePersistence(getUnidade());

        when(repository.findAll()).thenReturn(List.of(unidade));

        var result = unidadeRepositoryImpl.buscaTodasUnidades();

        assertThat(result).isNotNull().hasSize(1);

    }

    @Test
    void deveBuscarUnidadePorId() {
        var unidade = getUnidadePersistence(getUnidade());

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(unidade));

        var result = unidadeRepositoryImpl.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.get().getNome()).isEqualTo(unidade.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarUnidadePorId() {

      when(repository.findById(1L)).thenReturn(Optional.empty());

      assertThatThrownBy( () -> unidadeRepositoryImpl.findById(1L))
              .isInstanceOf(UnidadeException.class)
              .hasMessageContaining("Unidade não encontrada com id: " + 1L);
    }


    @Test
    void deveExcluirUnidade() {

        var unidade = getUnidadePersistence();

        doNothing().when(repository).deleteById(unidade.getId());

        unidadeRepositoryImpl.deleteById(unidade.getId());

        verify(repository, times(1)).deleteById(unidade.getId());
    }


    @Test
    void deveAtualizarUnidade() {
        var unidadePersistence = getUnidadePersistence(getUnidade());

        var unidade = getUnidade();
        unidade.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(unidadePersistence));
        when(repository.save(unidadePersistence)).thenReturn(unidadePersistence);

        var result = unidadeRepositoryImpl.update(unidade);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo(unidadePersistence.getNome());
    }



}
