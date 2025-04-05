package fiap.application.usecase;

import fiap.sus.application.usecases.SalvaUnidadeUseCase;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SalvaUnidadeUseCaseTest {

    @Mock
    UnidadeDomainRepository repository;

    @InjectMocks
    SalvaUnidadeUseCase salvaUnidadeuseCase;

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
    void deveSalvarUnidadeUsecase() {
        // Arrange
        UnidadeDomain unidade = new UnidadeDomain(null, "Jaçanã", "Rua dos Três Irmãos, 123");

        // Act
        salvaUnidadeuseCase.save(unidade);

        // Assert
        verify(repository, times(1)).save(unidade);
    }


}
