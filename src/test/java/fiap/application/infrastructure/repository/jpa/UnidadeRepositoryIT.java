package fiap.application.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.UnidadeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UnidadeRepositoryIT {

    @Autowired
    UnidadeRepository repository;

    @Test
    void devePermitirCriarTabela() {
        var totalRegistros = repository.count();
        assertThat(totalRegistros).isNotNegative();
    }

    @Test
    void deveSalvarUnidade() {

        var novaUnidade = UnidadePersistence
                .builder()
                .nome("Jaçanã")
                .endereco("Rua dos Três Irmãos, 100")
                .build();

        var unidadeSalva = repository.save(novaUnidade);
        assertThat(unidadeSalva)
                .isInstanceOf(UnidadePersistence.class)
                .isNotNull();
        assertThat(unidadeSalva.getId()).isEqualTo(novaUnidade.getId());
        assertThat(unidadeSalva.getNome()).isEqualTo(novaUnidade.getNome());
        assertThat(unidadeSalva.getEndereco()).isEqualTo(novaUnidade.getEndereco());
    }

    @Test
    void deveLancarExcecaoAoSalvarUnidadeComNomeMaiorQuePermitido() {
        var unidade = UnidadePersistence
                .builder()
                .nome("Unidade Santana com nome muito longo para ser aceito no banco de dados")
                .endereco("Avenida Paulista, 1000")
                .build();

        assertThatThrownBy(() -> repository.saveAndFlush(unidade))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void deveLancarExcecaoAoTentarSalvarUnidadeInvalida() {
        // 1. Crie uma entidade inválida (ex: nome nulo, violando @Column(nullable = false))
        UnidadePersistence unidadeInvalida = UnidadePersistence.builder()
                .nome(null)  // Supondo que 'nome' é obrigatório na entidade
                .endereco("Endereço Válido")
                .build();

        // 2. Execute e verifique a exceção
        assertThatThrownBy(() -> repository.saveAndFlush(unidadeInvalida))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("NULL not allowed for column \"NOME\"");
    }


    @Test
    void deveExcluirUnidade() {
        fail("Not yet implemented");
    }

    @Test
    void deveExcluirUnidadeVerificandoParametrosPassados() {
        fail("Not yet implemented");
    }

    @Test
    void deveBuscarUnidadePorId() {
        var novaUnidade = UnidadePersistence
                .builder()
                .nome("Jaçanã")
                .endereco("Rua dos Três Irmãos, 100")
                .build();

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

    @Test
    void deveBuscarUnidadePorIdVerificandoParametrosPassados() {
        fail("Not yet implemented");
    }

    @Test
    void deveRetornarListaDeUnidades() {
        fail("Not yet implemented");
    }


}
