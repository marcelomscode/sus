package fiap.infrastructure.repository.jpa.especialidades;

import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static fiap.Helper.getEspecialidade;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EspecialidadesJpaRepositoryTestIT {

    @Autowired
    private EspecialidadesJpaRepository repository;

    @Test
    void deveSalvarEspecialidade() {

        var especialidade = EspecialidadesPersistence
                .builder()
                .nome("Cardiologia")
                .descricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos.")
                .build();

        var unidadeSalvo = repository.save(especialidade);

        assertThat(unidadeSalvo)
                .isNotNull()
                .isEqualTo(especialidade);
    }

    @Test
    void deveAtualizarEspecialidade() {

        var especialidade = getEspecialidade();

        var unidadeSalvo = repository.save(especialidade);

        unidadeSalvo.setDescricao("Cardiologia é a especialidade médica que estuda o coração e os vasos sanguíneos e suas doenças.");

        var unidadeAtualizada = repository.save(especialidade);
        assertThat(unidadeAtualizada)
                .isNotNull()
                .isEqualTo(unidadeSalvo);
    }

}
