package fiap.infrastructure.repository.impl.medico;

import fiap.sus.api.dto.MedicoResponse;
import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.infrastructure.external.MedicoFeignClient;
import fiap.sus.infrastructure.repository.impl.medico.BuscaInformacoesMedicoRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BuscaInformacoesMedicoRepositoryImplTest {

    @Mock
    MedicoFeignClient checkInOutJpaRepository;

    @InjectMocks
    BuscaInformacoesMedicoRepositoryImpl checkOutDomainRepositoryImpl;

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
    void buscaMedicoByUUID() {
        var uuid = "123e4567-e89b-12d3-a456-426614174000";

        var medicoResponse = MedicoResponse.builder()
                .id(uuid)
                .firstName("Luiz")
                .lastName("Silva")
                .crm("123456")
                .build();

        when(checkInOutJpaRepository.getMedico(anyString())).thenReturn(medicoResponse);

        var medico = checkOutDomainRepositoryImpl.buscaMedicoByUUID(uuid);

        assertThat(medico).isNotNull();
        assertThat(medico.getUUID()).isEqualTo(medicoResponse.getId());
        assertThat(medico.getNome()).isEqualTo(medicoResponse.getFirstName() + medicoResponse.getLastName());
        assertThat(medico.getCrm()).isEqualTo(medicoResponse.getCrm());

    }

    @Test
    void deveLancarExcecaoAoBuscarMedicoComUUIDInvalido() {
        var uuid = "123e4567-e89b-12d3-a456-426614174000";

        when(checkInOutJpaRepository.getMedico(anyString())).thenThrow(MedicoException.class);

        assertThatThrownBy( () -> checkOutDomainRepositoryImpl.buscaMedicoByUUID(uuid))
                .isInstanceOf(MedicoException.class)
                .hasMessageContaining("Erro ao buscar médico por ID: " + uuid);
    }

}
