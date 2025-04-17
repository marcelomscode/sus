package fiap.application.usecase.especialidades.integrados;

import fiap.sus.application.usecases.especialidades.BuscaEspecialidadesUseCase;
import fiap.sus.infrastructure.repository.impl.especialidades.BuscaEspecialidadesRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest


@Import({BuscaEspecialidadesUseCase.class, BuscaEspecialidadesRepositoryImpl.class})
class BuscaEspecialidadesUseCaseTestIT {


    @Autowired
    BuscaEspecialidadesUseCase buscaEspecialidadesUseCase;



    @Test
    void deveListarTodasEspecialidades() {
        var especialidade = buscaEspecialidadesUseCase.listarTodasEspecialidades();

        assertThat(especialidade)
                .isNotNull();
        assertThat(especialidade.get(0).getNome())
                .isEqualTo("Dermatologia");

    }

}
