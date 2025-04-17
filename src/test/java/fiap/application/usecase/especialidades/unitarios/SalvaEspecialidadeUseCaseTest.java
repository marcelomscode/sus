package fiap.application.usecase.especialidades.unitarios;

import fiap.sus.application.usecases.especialidades.SalvaEspecialidadeUseCase;
import fiap.sus.domain.repository.especialidade.EspecialidadeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static fiap.Helper.getEspecialidadeDomain;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SalvaEspecialidadeUseCaseTest {

    @Mock
    EspecialidadeRepository repository;

    @InjectMocks
    SalvaEspecialidadeUseCase salvaEspecialidadeUseCase;

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
    void deveSalvarEspecialidade(){

        var especialidade = getEspecialidadeDomain();

        when(repository.save(especialidade)).thenReturn(especialidade);

        salvaEspecialidadeUseCase.save(especialidade);

        verify(repository, times(1)).save(especialidade);
    }


}
