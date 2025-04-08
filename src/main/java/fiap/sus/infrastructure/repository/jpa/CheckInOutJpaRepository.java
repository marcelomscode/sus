package fiap.sus.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CheckInOutJpaRepository extends JpaRepository<CheckInOutPersistence, Long> {

    CheckInOutPersistence findByIdMedicoAndIdUnidade(long idMedico, long idUnidade);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2 AND check_in like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckIn(long idMedico, long idUnidade, LocalDate checkIn);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2 AND check_out like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckout(long idMedico, long idUnidade, LocalDate checkIn);


}
