package fiap.application.usecase.especialidades.integrados;

import fiap.sus.application.usecases.especialidades.AtualizaEspecialidadeUseCase;
import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.infrastructure.repository.impl.especialidades.BuscaEspecialidadesRepositoryImpl;
import fiap.sus.infrastructure.repository.impl.especialidades.EspecialidadesRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({
        AtualizaEspecialidadeUseCase.class,
        EspecialidadesRepositoryImpl.class,
        BuscaEspecialidadesRepositoryImpl.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AtualizaEspecialidadeUseCaseIT {

    @Autowired
    private AtualizaEspecialidadeUseCase atualizaEspecialidadeUseCase;

    @Autowired
    private EspecialidadesJpaRepository especialidadesJpaRepository;

    @Test
    @Transactional
    void deveAtualizarEspecialidade(){

        var especialidade = EspecialidadesDomain.builder()
                .id(1L)
                .nome("Cardiologia")
                .descricao("Especialidade do coração")
                .build();

        var especialidadeSalva =  atualizaEspecialidadeUseCase.atualizar(especialidade);

        var especialidadesSalvas = especialidadesJpaRepository.findById(especialidadeSalva.getId());
        assertThat(especialidadesSalvas)
                .isPresent();

        assertThat(especialidadesSalvas.get().getNome())
                .isEqualTo(especialidade.getNome());

        assertThat(especialidadesSalvas.get().getDescricao())
                .isEqualTo(especialidade.getDescricao());
    }

}
