package fiap.sus.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.UnidadePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadePersistence, Long> {

}
