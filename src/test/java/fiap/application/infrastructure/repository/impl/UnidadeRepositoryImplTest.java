package fiap.application.infrastructure.repository.impl;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.impl.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UnidadeRepositoryImplTest {

    @Mock
    UnidadeRepository repository;

    @InjectMocks
    UnidadeRepositoryImpl salvaUnidadeuseCase;

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
        var unidade = getUnidade();

        when(repository.save(any(UnidadePersistence.class))).thenReturn(getUnidadePersistence(unidade));

        salvaUnidadeuseCase.save(unidade);

        verify(repository, times(1)).save(getUnidadePersistence(unidade));
    }

    private static UnidadeDomain getUnidade() {
        return new UnidadeDomain(null, "Jaçanã", "Rua dos Três Irmãos, 123");
    }

    private static UnidadePersistence getUnidadePersistence(UnidadeDomain unidade) {
        return UnidadePersistence
                .builder()
                .nome(unidade.getNome())
                .endereco(unidade.getEndereco())
                .build();
    }


}
