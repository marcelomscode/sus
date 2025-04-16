package fiap.infrastructure.repository.impl.especialidades;

import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.infrastructure.repository.impl.especialidades.BuscaEspecialidadesRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static fiap.Helper.getEspecialidade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BuscaEspecialidadesRepositoryImplTest {


    @Mock
    EspecialidadesJpaRepository repository;


    @InjectMocks
    BuscaEspecialidadesRepositoryImpl unidadeRepositoryImpl;

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
    void deveBuscarPorIdEspecialidade() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(getEspecialidade()));

        var especialidade = unidadeRepositoryImpl.buscarPorId(id);
        assertThat(especialidade).isNotNull();
        assertThat(especialidade.getNome()).isEqualTo("Cardiologia");
    }

    @Test
    void deveLancarExcecaoAoBuscarPorId() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        var exception = assertThrows(EspecialidadeException.class, () -> {
            unidadeRepositoryImpl.buscarPorId(id);
        });

        assertThat(exception.getMessage()).isEqualTo("Especialidade não encontrada: " + id);
    }

    @Test
    void deveBuscarTodasEspecialidades(){

        var especialidades = List.of(getEspecialidade(), getEspecialidade());

        when(repository.findAll()).thenReturn(especialidades);

        var expecialidadesBuscadas = unidadeRepositoryImpl.buscarTodasEspecialidades();

        assertThat(expecialidadesBuscadas)
                .isNotNull()
                .hasSameSizeAs(especialidades);
    }

    @Test
    void deveLancarExcecaoAoBuscarTodasEspecialidades() {

        when(repository.findAll()).thenReturn(List.of());

        var exception = assertThrows(EspecialidadeException.class, () -> {
            unidadeRepositoryImpl.buscarTodasEspecialidades();
        });

        assertThat(exception.getMessage()).isEqualTo("Nenhuma especialidade encontrada");
    }


}
