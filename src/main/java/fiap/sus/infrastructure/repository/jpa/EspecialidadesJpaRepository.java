package fiap.sus.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadesJpaRepository extends JpaRepository<EspecialidadesPersistence, Long> {
}
