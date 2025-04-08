package fiap.infrastructure.repository.impl;


import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.impl.unidade.UnidadeRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UnidadeRepositoryImpl.class)
class UnidadeJpaRepositoryImplITTest {

    @Autowired
    private UnidadeRepositoryImpl repository;

    @Autowired
    private UnidadeJpaRepository jpaRepository;

    @Nested
    class SalvarUnidade {

        @Test
        void deveSalvarUnidadeComSucesso() {
            // Arrange
            UnidadeDomain unidadeDomain = new UnidadeDomain(null, "Hospital Central", "Rua Principal, 123", true);

            // Act
            repository.save(unidadeDomain);

            // Assert
            assertThat(jpaRepository.count()).isEqualTo(6);

            UnidadePersistence unidadeSalva = jpaRepository.findAll().get(5);
            assertThat(unidadeSalva.getNome()).isEqualTo("Hospital Central");
            assertThat(unidadeSalva.getEndereco()).isEqualTo("Rua Principal, 123");

        }
    }


}
