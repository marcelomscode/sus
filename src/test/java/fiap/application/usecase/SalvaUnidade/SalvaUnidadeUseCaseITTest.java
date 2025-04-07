package fiap.application.usecase.SalvaUnidade;

import fiap.sus.application.usecases.unidades.SalvaUnidadeUseCase;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.repository.impl.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static fiap.application.Helper.getUnidade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({SalvaUnidadeUseCase.class, UnidadeRepositoryImpl.class})
public class SalvaUnidadeUseCaseITTest {

    @Autowired
    private SalvaUnidadeUseCase salvaUnidadeUseCase;

    @Autowired
    private UnidadeJpaRepository jpaRepository;

    @Nested
    class SalvaUnidade {

        @Test
        void deveSalvarUnidadeComSucesso() {

            var unidade = getUnidade();
            unidade.setId(6L);

            salvaUnidadeUseCase.save(unidade);

            var unidadeSalva = jpaRepository.findById(unidade.getId());
            assertThat(unidadeSalva).isPresent();
            assertThat(unidadeSalva.get().getNome()).isEqualTo("Jaçanã");
            assertThat(unidadeSalva.get().getEndereco()).isEqualTo("Rua dos Três Irmãos, 123");
        }

        @Test
        void deveLancarExcecaoAoSalvarUnidadeNomeNull() {

            var unidade = new UnidadeDomain(1L, null, "Rua dos Três Irmãos, 123", true);

            assertThatThrownBy(
                    () -> salvaUnidadeUseCase.save(unidade)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Nome da unidade não pode ser nulo ou vazio");
        }

        @Test
        void deveLancarExcecaoAoSalvarUnidadeNomeVazio() {

            var unidade = new UnidadeDomain(1L, "", "Rua dos Três Irmãos, 123", true);

            assertThatThrownBy(
                    () -> salvaUnidadeUseCase.save(unidade)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Nome da unidade não pode ser nulo ou vazio");
        }
    }


}
