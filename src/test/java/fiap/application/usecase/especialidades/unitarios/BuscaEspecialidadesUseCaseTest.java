package fiap.application.usecase.especialidades.unitarios;

import fiap.sus.application.usecases.especialidades.BuscaEspecialidadesUseCase;
import fiap.sus.domain.repository.especialidade.BuscaEspecialidadesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static fiap.Helper.getEspecialidadeDomain;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaEspecialidadesUseCaseTest {

    @Mock
    BuscaEspecialidadesRepository repository;

    @InjectMocks
    BuscaEspecialidadesUseCase atualizaEspecialidadeUseCase;

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
    void deveListarTodasEspecialidades(){
        var especialidade = getEspecialidadeDomain();

        when(repository.buscarTodasEspecialidades()).thenReturn(List.of(especialidade));

        atualizaEspecialidadeUseCase.listarTodasEspecialidades();

        verify(repository, times(1)).buscarTodasEspecialidades();
    }

}
