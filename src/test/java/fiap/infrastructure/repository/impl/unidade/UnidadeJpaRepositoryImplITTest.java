package fiap.infrastructure.repository.impl.unidade;


import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.impl.unidade.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UnidadeRepositoryImpl.class)
class UnidadeJpaRepositoryImplITTest {

    @Autowired
    private UnidadeRepositoryImpl repository;

    @Autowired
    private UnidadeJpaRepository jpaRepository;

    @Nested
    class SalvarAtualizaUnidade {

        @Test
        void deveSalvarUnidadeComSucesso() {

            var especialidades = Set.of(
                    new EspecialidadesDomain(1L, "Cardiologia", "Coração"),
                    new EspecialidadesDomain(2L, "Pediatria", "Crianças")
            );

            UnidadeDomain unidadeDomain = new UnidadeDomain(null,
                    "Hospital Central", "Rua Principal, 123",
                    especialidades, true);

            repository.save(unidadeDomain);

            assertThat(jpaRepository.count()).isEqualTo(6);

            UnidadePersistence unidadeSalva = jpaRepository.findAll().get(5);
            assertThat(unidadeSalva.getNome()).isEqualTo("Hospital Central");
            assertThat(unidadeSalva.getEndereco()).isEqualTo("Rua Principal, 123");

        }

        @Test
        void deveAtualizarUnidadeComSucesso() {

            var especialidades = Set.of(
                    new EspecialidadesDomain(1L, "Cardiologia", "Coração"),
                    new EspecialidadesDomain(2L, "Pediatria", "Crianças")
            );

            UnidadeDomain unidadeDomain = new UnidadeDomain(6L,
                    "Hospital Central", "Rua Principal, 123",
                    especialidades, true);

            repository.save(unidadeDomain);

            assertThat(jpaRepository.count()).isEqualTo(6);

            UnidadePersistence unidadeSalva = jpaRepository.findById(unidadeDomain.getId()).get();
            assertThat(unidadeSalva.getNome()).isEqualTo("Hospital Central");
            assertThat(unidadeSalva.getEndereco()).isEqualTo("Rua Principal, 123");

        }
    }

    @Nested
    class BuscarUnidade {

        @Test
        void buscaTodasUnidades(){
            var todasUnidades = repository.buscaTodasUnidades();
            assertThat(todasUnidades)
                    .hasSize(5);
        }

        @Test
        void findById(){
            var unidade = repository.findById(1L);

            assertThat(unidade)
                    .isPresent()
                    .get()
                    .extracting(UnidadeDomain::getNome)
                    .isEqualTo("Unidade 1");
        }
    }

    @Nested
    class excluiUnidade{

        @Test
        void deleteById(){

            var qtdUnidadesAntesExclusao = jpaRepository.findAll().size();

            repository.deleteById(1L);

            var qtdUnidadesDepoisExclusao = jpaRepository.findAll().size();

            assertThat(qtdUnidadesDepoisExclusao).isLessThan(qtdUnidadesAntesExclusao);
        }

    }

}
