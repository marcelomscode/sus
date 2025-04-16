package fiap.api.controllers.unidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.Helper;
import fiap.sus.api.controllers.UnidadeController;
import fiap.sus.api.mappers.EspecialidadeDomainMapper;
import fiap.sus.api.mappers.UnidadeDomainMapper;
import fiap.sus.application.usecases.unidades.AtualizaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.BuscaMedicosAtendendoNaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.BuscaUnidadesUseCase;
import fiap.sus.application.usecases.unidades.DeletaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.SalvaUnidadeUseCase;
import fiap.sus.domain.model.UnidadeDomain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UnidadeControllerTest {


    private MockMvc mockMvc;

    @Mock
    private SalvaUnidadeUseCase salvaUnidadeUseCase;

    @Mock
    private BuscaUnidadesUseCase buscaUnidadePorIdUseCase;

    @Mock
    private DeletaUnidadeUseCase deletaUnidadeUseCase;

    @Mock
    private AtualizaUnidadeUseCase atualizaUnidadeUseCase;

    @Mock
    private EspecialidadeDomainMapper mapper;

    @Mock
    private UnidadeDomainMapper unidadeDomainMapper;

    @Mock
    private BuscaMedicosAtendendoNaUnidadeUseCase buscaMedicosAtendendoNaUnidadeUseCase;


    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = org.mockito.MockitoAnnotations.openMocks(this);
        UnidadeController controller = new UnidadeController(salvaUnidadeUseCase,
                buscaUnidadePorIdUseCase,
                deletaUnidadeUseCase,
                atualizaUnidadeUseCase,
                unidadeDomainMapper,
                mapper,
                buscaMedicosAtendendoNaUnidadeUseCase);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }


    @Nested
    class SalvarUnidade {

        @Test
        void deveSalvarUnidade() throws Exception {
            var unidade = Helper.getUnidade();

            doNothing().when(salvaUnidadeUseCase).save(unidade);

            mockMvc.perform(
                            post("/api/unidades")
                                    .contentType("application/json")
                                    .content(asJsonString(unidade))
                    )
                    .andExpect(status().isCreated());
            verify(salvaUnidadeUseCase, times(1)).save(any(UnidadeDomain.class));
        }
    }


    @Nested
    class DeveExcluirUnidade {
//TODO        @Test
//        void deveExcluirUnidade() {
//            fail("Not yet implemented");
//        }
//
//        @Test
//        void deveExcluirUnidadeVerificandoParametrosPassados() {
//            fail("Not yet implemented");
//        }
    }


    @Nested
    class DeveBuscarUnidadePorId {
        @Test
        void deveBuscarUnidadePorId() throws Exception {
            when(buscaUnidadePorIdUseCase.findById(any(Long.class))).thenReturn(Optional.of(Helper.getUnidade()));

            mockMvc.perform(
                            get("/api/unidades/{id}", 1L)
                                    .contentType("application/json")
                    )
                    .andExpect(status().isOk());

            verify(buscaUnidadePorIdUseCase, times(1)).findById(anyLong());
        }

        @Test
        void deveGerarExecaoQuandoIdNaoEncontrado() throws Exception {
//TODO implementar
//            fail("Not yet implemented");
//
////            when(buscaUnidadePorIdUseCase.findById(1L)).thenThrow(RuntimeException.class);
////
////            mockMvc.perform(
////                    get("/api/unidades/{id}", 1L)
////                            .contentType("application/json")
////            ).andExpect(status().isNotFound());
////
////        }
        }
    }

        @Nested
        class DeveListarUnidades {
//TODO Implementar
//            @Test
//            void deveBuscarUnidadePorNome() {
//                fail("Not yet implemented");
//            }
//
//            @Test
//            void deveBuscarUnidadePorIdVerificandoParametrosPassados() {
//                fail("Not yet implemented");
//            }
//
//            @Test
//            void deveRetornarListaDeUnidades() {
//                fail("Not yet implemented");
//            }
//        }


        }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    }

