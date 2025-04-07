package fiap.application.usecase.SalvaUnidade;

import fiap.sus.application.usecases.unidades.SalvaUnidadeUseCase;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static fiap.application.Helper.getUnidade;
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

    @Nested
    class SalvaUnidade {
        @Test
        void deveSalvarUnidadeUsecase() {
            // Arrange
            var unidade = getUnidade();

            // Act
            salvaUnidadeuseCase.save(unidade);

            // Assert
            verify(repository, times(1)).save(unidade);
        }
    }



}
