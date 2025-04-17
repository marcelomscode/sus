package fiap.infrastructure.repository.impl.medico;

import fiap.sus.infrastructure.external.MedicoFeignClient;
import fiap.sus.infrastructure.repository.impl.medico.BuscaInformacoesMedicoRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.fail;

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
//        var uuid = "123e4567-e89b-12d3-a456-426614174000";
//
//        var medicoResponse = checkInOutJpaRepository.getMedico(uuid);
//
//        var medico = checkOutDomainRepositoryImpl.buscaMedicoByUUID(uuid);
//
//        assertThat(medico).isNotNull();
//        assertThat(medico.getId()).isEqualTo(medicoResponse.getId());
//        assertThat(medico.getNome()).isEqualTo(medicoResponse.getNome());
//        assertThat(medico.getSobrenome()).isEqualTo(medicoResponse.getSobrenome());
//        assertThat(medico.getCrm()).isEqualTo(medicoResponse.getCrm());

        fail("Not yet implemented");

    }

}
