package fiap.infrastructure.repository.impl;

import fiap.sus.infrastructure.repository.impl.unidade.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static fiap.application.Helper.getUnidade;
import static fiap.application.Helper.getUnidadePersistence;
import static org.assertj.core.api.Assertions.assertThat;
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
    void deveExcluirUnidade() {

        var unidade = getUnidadePersistence();

        doNothing().when(repository).delete(unidade);

        repository.delete(unidade);

        verify(repository, times(1)).delete(unidade);
    }


}
