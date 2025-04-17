package fiap.infrastructure.repository.impl.checkinout;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import fiap.sus.infrastructure.repository.impl.checkinout.BuscaCheckInOutRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuscaCheckInOutRepositoryImplTest {

    @Mock
    CheckInOutJpaRepository checkInOutJpaRepository;

    @InjectMocks
    BuscaCheckInOutRepositoryImpl buscaCheckInOutRepositoryImpl;


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
    class BuscaCheckInOutMedicoUnidade {

        @Test
        void buscaCheckInOutPorMedicoPorUnidade() {

            var checkInOutPersistence = new CheckInOutPersistence();
            when(checkInOutJpaRepository.findByIdMedicoAndIdUnidade(1, 1)).thenReturn(checkInOutPersistence);

            var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoPorUnidade(1, 1);

            assertThat(result)
                    .isNotNull()
                    .isInstanceOf(CheckInOutDomain.class);
        }

        @Test
        void deveLancarExcecaoAoBuscaCheckInOutPorMedicoPorUnidade() {

            var idMedico = 1;
            var idUnidade = 1;

            when(checkInOutJpaRepository.findByIdMedicoAndIdUnidade(anyLong(), anyLong())).thenReturn(null);

            try {
                buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoPorUnidade(idMedico, idUnidade);
            } catch (CheckOutInException e) {
                assertThat(e.getMessage()).contains("Dados de CheckIn para Medico " + idMedico + " na unidade de " + idUnidade + " não econtrados");
            }
        }

        @Nested
        class buscaCheckInOutUnidade {

            @Test
            void buscaCheckInOutPorUnidade() {

                var idUnidade = 1;

                var checkInOutPersistence = getCheckInOutPersistence();

                when(checkInOutJpaRepository.findByIdUnidade(idUnidade)).thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorUnidade(idUnidade);

                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());

                assertThat(result.get(0).getUUID())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getUUID());

                assertThat(result.get(0).getIdUnidade())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getIdUnidade());

            }


            @Test
            void deveLancarExcecaoAobuscarCheckInOutPorUnidade() {

                var idUnidade = 1;

                getCheckInOutPersistence();

                when(checkInOutJpaRepository.findByIdUnidade(anyLong())).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaCheckInOutPorUnidade(idUnidade);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("Dados de CheckIn na unidade " + idUnidade + " não econtrados, verifique os dados e tente novamente.");
                }
            }
        }

        @Nested
        class buscaCheckInOutMedico {

            @Test
            void buscaCheckInOutPorMedico() {
                var idMedico = 1;

                var checkInOutPersistence = getCheckInOutPersistence();

                when(checkInOutJpaRepository.findByIdMedico(idMedico)).thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoUUID(idMedico);

                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());

            }

            @Test
            void deveLancarExcacaoAobuscaCheckInOutPorMedico() {
                var idMedico = 1;

                when(checkInOutJpaRepository.findByIdMedico(idMedico)).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoUUID(idMedico);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("Dados de CheckIn para Medico " + idMedico + " não econtrados, verifique os dados e tente novamente.");
                }

            }
        }

        @Nested
        class buscaCheckInOutPorMedicoUUID {

            @Test
            void deveBuscarCheckInOutPorMedicoUUID() {

                var uuid = UUID.randomUUID().toString();
                var checkInOutPersistence = getCheckInOutPersistence();

                when(checkInOutJpaRepository.findByUUID(uuid)).thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoUUID(uuid);

                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());

                assertThat(result.get(0).getUUID())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getUUID());

                assertThat(result.get(0).getIdUnidade())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getIdUnidade());
            }

            @Test
            void deveLancarExcecaoBuscarCheckInOutPorMedicoUUID() {

                var uuid = UUID.randomUUID().toString();

                when(checkInOutJpaRepository.findByUUID(uuid)).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoUUID(uuid);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("Dados de CheckIn para Medico " + uuid + " não econtrados, verifique os dados e tente novamente.");
                }
            }
        }

        @Nested
        class BuscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn {

            @Test
            void deveBuscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn() {

                var uuid = UUID.randomUUID().toString();
                var idUnidade = 1L;
                var data = LocalDate.now();

                var checkInOutPersistence = getCheckInOutPersistence();

                when(checkInOutJpaRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorData(uuid, idUnidade, data)).thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(uuid, idUnidade, data);

                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());

                assertThat(result.get(0).getUUID())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getUUID());

                assertThat(result.get(0).getIdUnidade())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getIdUnidade());

            }

            @Test
            void deveLancarExecaoAoBuscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn() {
                var uuid = UUID.randomUUID().toString();
                var idUnidade = 1L;
                var data = LocalDate.now();

                when(checkInOutJpaRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorData(uuid, idUnidade, data)).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(uuid, idUnidade, data);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("Dados de CheckIn na unidade " + idUnidade + " com Médico " + uuid + " " +
                                    " e data " + data + " não econtrados, verifique os dados e tente novamente.");
                }
            }
        }

        @Nested
        class BuscaCheckInOutPorUnidadeEData {

            @Test
            void deveBuscarCheckInOutPorUnidadeEData() {

                var idUnidade = 1L;
                var data = LocalDate.now();

                var checkInOutPersistence = getCheckInOutPersistence();

                when(checkInOutJpaRepository.buscaCheckInOutPorUnidadeEData(idUnidade, data)).thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaCheckInOutPorUnidadeEData(idUnidade, data);

                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());

                assertThat(result.get(0).getUUID())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getUUID());

                assertThat(result.get(0).getIdUnidade())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getIdUnidade());

            }

            @Test
            void deveLancarExecaoAoBuscarCheckInOutPorUnidadeEData() {

                var idUnidade = 1L;
                var data = LocalDate.now();

                when(checkInOutJpaRepository.buscaCheckInOutPorUnidadeEData(idUnidade, data)).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaCheckInOutPorUnidadeEData(idUnidade, data);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("Dados de CheckIn na unidade " + idUnidade +
                                    " e na data " + data + " não econtrados, verifique os dados e tente novamente.");
                }
            }
        }

        @Nested
        class BuscaMedicosComCheckInEmUmaUnidade {

            @Test
            void deveBuscarMedicosComCheckInEmUmaUnidade() {

                var checkInOutPersistence = getCheckInOutPersistence();
                var idUnidade = 1L;
                var data = LocalDate.now();

                when(checkInOutJpaRepository.buscaMedicosComCheckInEmUmaUnidade(idUnidade, data))
                        .thenReturn(List.of(checkInOutPersistence));

                var result = buscaCheckInOutRepositoryImpl.buscaMedicosComCheckInEmUmaUnidade(idUnidade, data);
                assertThat(result.get(0).getId())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getId());
                assertThat(result.get(0).getUUID())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getUUID());
                assertThat(result.get(0).getIdUnidade())
                        .isNotNull()
                        .isEqualTo(checkInOutPersistence.getIdUnidade());
            }

            @Test
            void DevebuscarMedicosComCheckInEmUmaUnidade() {

                var idUnidade = 1L;
                var data = LocalDate.now();

                when(checkInOutJpaRepository.buscaMedicosComCheckInEmUmaUnidade(anyLong(), any())).thenReturn(null);

                try {
                    buscaCheckInOutRepositoryImpl.buscaMedicosComCheckInEmUmaUnidade(idUnidade, data);
                } catch (CheckOutInException e) {
                    assertThat(e.getMessage())
                            .contains("No momento não temos médicos atendendo na unidade " + idUnidade +
                                    " tente novamente mais tarde.");
                }
            }
        }
    }

    private static CheckInOutPersistence getCheckInOutPersistence() {
        var checkInOutPersistence = new CheckInOutPersistence();
        checkInOutPersistence.setId(1L);
        checkInOutPersistence.setUUID(UUID.randomUUID().toString());
        checkInOutPersistence.setIdUnidade(1L);
        return checkInOutPersistence;
    }

}
