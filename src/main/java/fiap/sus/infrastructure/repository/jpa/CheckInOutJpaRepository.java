package fiap.sus.infrastructure.repository.jpa;

import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckInOutJpaRepository extends JpaRepository<CheckInOutPersistence, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2")
    CheckInOutPersistence findByIdMedicoAndIdUnidade(long idMedico, long idUnidade);

    @Deprecated
    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2 AND check_in like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckIn(long idMedico, long idUnidade, LocalDate checkIn);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE uuid = ?1 AND id_unidade = ?2 AND check_in like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckIn(String uuid, long idUnidade, LocalDate checkIn);

    @Deprecated
    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2 AND check_out like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckout(long idMedico, long idUnidade, LocalDate checkIn);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE uuid = ?1 AND id_unidade = ?2 AND check_out like ?3%")
    CheckInOutPersistence findByIdMedicoAndIdUnidadeAndCheckout(String uuid, long idUnidade, LocalDate checkIn);

    List<CheckInOutPersistence> findByIdUnidade(long idUnidade);

    List<CheckInOutPersistence> findByIdMedico(long idMedico);

    List<CheckInOutPersistence> findByUUID(String idMedico);

    @Deprecated
    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_medico = ?1 AND id_unidade = ?2 AND data like ?3%")
    List<CheckInOutPersistence> buscaCheckInOutPorMedicoEPorUnidadeEPorData(long idMedico, long idUnidade, LocalDate checkIn);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE uuid = ?1 AND id_unidade = ?2 AND data like ?3%")
    List<CheckInOutPersistence> buscaCheckInOutPorMedicoEPorUnidadeEPorData(String uuid, long idUnidade, LocalDate checkIn);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_unidade = ?1 AND data like ?2%")
    List<CheckInOutPersistence> buscaCheckInOutPorUnidadeEData(long idUnidade, LocalDate data);

    @Query(nativeQuery = true, value = "SELECT * FROM checkinout WHERE id_unidade = ?1 AND data like ?2% AND check_out is null")
    List<CheckInOutPersistence> buscaMedicosComCheckInEmUmaUnidade(long idUnidade, LocalDate data);

}
