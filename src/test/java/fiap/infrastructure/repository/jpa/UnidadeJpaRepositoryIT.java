package fiap.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Set;

import static fiap.application.Helper.getUnidade;
import static fiap.application.Helper.getUnidadePersistence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UnidadeJpaRepositoryIT {

    @Autowired
    UnidadeJpaRepository repository;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class CriarTabela {
        @Test
        void devePermitirCriarTabela() {
            var totalRegistros = repository.count();
            assertThat(totalRegistros).isGreaterThan(0);
        }
    }

    @Test
    void deveLancarExcecaoAoSalvarUnidadeComNomeMaiorQuePermitido() {
        var unidade = UnidadePersistence
                .builder()
                .nome("Unidade Santana com nome muito longo para ser aceito no banco de dados")
                .endereco("Avenida Paulista, 1000")
                .build();

        Set<ConstraintViolation<UnidadePersistence>> violations = validator.validate(unidade);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("nome");

    }

    @Nested
    class SalvarUnidade {

        @Test
        void deveSalvarUnidade() {

            var novaUnidade = getUnidadePersistence(getUnidade());

            var unidadeSalva = repository.save(novaUnidade);
            assertThat(unidadeSalva)
                    .isInstanceOf(UnidadePersistence.class)
                    .isNotNull();
            assertThat(unidadeSalva.getId()).isEqualTo(novaUnidade.getId());
            assertThat(unidadeSalva.getNome()).isEqualTo(novaUnidade.getNome());
            assertThat(unidadeSalva.getEndereco()).isEqualTo(novaUnidade.getEndereco());
        }


        @Test
        void deveLancarExcecaoAoTentarSalvarUnidadeInvalida() {
            UnidadePersistence unidadeInvalida = UnidadePersistence.builder()
                    .nome(null)
                    .endereco("Endereço Válido")
                    .build();

            assertThatThrownBy(() -> repository.saveAndFlush(unidadeInvalida))
                    .isInstanceOf(DataIntegrityViolationException.class)
                    .hasMessageContaining("NULL not allowed for column \"NOME\"");
        }
    }

//    @Test
//    void deveExcluirUnidade() {
//        fail("Not yet implemented");
//    }
//TODO Implementar teste de exclusão
//    @Test
//    void deveExcluirUnidadeVerificandoParametrosPassados() {
//        fail("Not yet implemented");
//    }

    @Test
    void deveBuscarUnidadePorId() {
        var novaUnidade = getUnidadePersistence(getUnidade());

        repository.save(novaUnidade);

        var mensagemOptional = repository.findById(novaUnidade.getId());
        assertThat(mensagemOptional)
                .isPresent()
                .containsSame(novaUnidade);

        mensagemOptional.ifPresent(mensagem -> {
            assertThat(mensagem.getId()).isEqualTo(novaUnidade.getId());
            assertThat(mensagem.getNome()).isEqualTo(novaUnidade.getNome());
            assertThat(mensagem.getEndereco()).isEqualTo(novaUnidade.getEndereco());
        });
    }

//    @Test
//    void deveBuscarUnidadePorIdVerificandoParametrosPassados() {
//        fail("Not yet implemented");
//    }

    @Test
    void deveRetornarListaDeUnidades() {

        var novaUnidade = getUnidadePersistence(getUnidade());

        repository.save(novaUnidade);

        var unidades = repository.findAll();
        assertThat(unidades)
                .isNotEmpty()
                .hasSize(6)
                .contains(novaUnidade);
    }

}
