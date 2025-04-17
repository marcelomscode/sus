package fiap.application.usecase.especialidades.integrados;

import fiap.sus.application.usecases.especialidades.SalvaEspecialidadeUseCase;
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
        SalvaEspecialidadeUseCase.class,
        EspecialidadesRepositoryImpl.class,
        BuscaEspecialidadesRepositoryImpl.class  // Importando a implementação que estava faltando
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalvaEspecialidadeUseCaseIT {

    @Autowired
    private SalvaEspecialidadeUseCase salvaEspecialidadeUseCase;

    @Autowired
    private EspecialidadesJpaRepository especialidadesJpaRepository;

    @Test
    @Transactional
    void deveSalvarEspecialidadeNoBancoDeDados() {

        var especialidade = EspecialidadesDomain.builder()
                .nome("Cardiologia")
                .descricao("Especialidade do coração")
                .build();

       var especialidadeSalva =  salvaEspecialidadeUseCase.save(especialidade);

        var especialidadesSalvas = especialidadesJpaRepository.findById(especialidadeSalva.getId());
        assertThat(especialidadesSalvas)
                .isPresent();

        assertThat(especialidadesSalvas.get().getNome())
                .isEqualTo(especialidade.getNome());

        assertThat(especialidadesSalvas.get().getDescricao())
                .isEqualTo(especialidade.getDescricao());

    }


}
