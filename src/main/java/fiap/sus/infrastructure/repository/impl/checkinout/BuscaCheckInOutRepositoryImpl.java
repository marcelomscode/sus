package fiap.sus.infrastructure.repository.impl.checkinout;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.BuscaCheckInOutDomainRepository;
import fiap.sus.infrastructure.mappers.CheckinOutPersistenceMapper;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BuscaCheckInOutRepositoryImpl implements BuscaCheckInOutDomainRepository {

    private final CheckInOutJpaRepository checkInOutJpaRepository;
    private static final String CHECKINOUT_MEDICO_UNIDADE = "Buscando check-in/check-out do médico na unidade [{}]";

    @Autowired
    public BuscaCheckInOutRepositoryImpl(CheckInOutJpaRepository checkInOutJpaRepository) {
        this.checkInOutJpaRepository = checkInOutJpaRepository;
    }

    @Override
    public CheckInOutDomain buscaCheckInOutPorMedicoPorUnidade(long idMedico, long idUnidade) {

        log.info("Buscando check-in/check-out do médico [{}] na unidade [{}]", idMedico, idUnidade);
        var checkInOut = checkInOutJpaRepository.findByIdMedicoAndIdUnidade(idMedico, idUnidade);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn para Medico " + idMedico + " na unidade de " +
                    idUnidade + " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }
        return CheckinOutPersistenceMapper.toCheckInDomain(checkInOut);
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade) {

        log.info(CHECKINOUT_MEDICO_UNIDADE, idUnidade);
        var checkInOut = checkInOutJpaRepository.findByIdUnidade(idUnidade);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn na unidade " + idUnidade + " não econtrados," +
                    " verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico) {

        log.info("Buscando check-in/check-out do médico [{}]", idMedico);
        var checkInOut = checkInOutJpaRepository.findByIdMedico(idMedico);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn para Medico " + idMedico +
                    " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorMedico(String idMedico) {

        log.info("Buscando check-in/check-out do médico [{}]", idMedico);
        var checkInOut = checkInOutJpaRepository.findByUUID(idMedico);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn para Medico " + idMedico +
                    " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(long idMedico, long idUnidade, LocalDate data) {

        log.info(CHECKINOUT_MEDICO_UNIDADE, data);
        var checkInOut = checkInOutJpaRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorData(idMedico, idUnidade, data);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn na unidade " + idUnidade + " com Médico " + idMedico + " " +
                    " e data " + data + " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(String uuid, long idUnidade, LocalDate data) {
        log.info(CHECKINOUT_MEDICO_UNIDADE, data);
        var checkInOut = checkInOutJpaRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorData(uuid, idUnidade, data);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn na unidade " + idUnidade + " com Médico " + uuid + " " +
                    " e data " + data + " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorUnidadeEData(long idUnidade, LocalDate data) {

        log.info(CHECKINOUT_MEDICO_UNIDADE, data);
        var checkInOut = checkInOutJpaRepository.buscaCheckInOutPorUnidadeEData(idUnidade, data);
        if (Objects.isNull(checkInOut)) {
            throw new CheckOutInException("Dados de CheckIn na unidade " + idUnidade +
                    " e na data " + data + " não econtrados, verifique os dados e tente novamente.",
                    HttpStatus.BAD_REQUEST, 400);
        }

        return checkInOut.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

    @Override
    public List<CheckInOutDomain> buscaMedicosComCheckInEmUmaUnidade(long idUnidade, LocalDate data) {

        var response = checkInOutJpaRepository.buscaMedicosComCheckInEmUmaUnidade(idUnidade, data);
        if (response.isEmpty()) {
            throw new CheckOutInException("No momento não temos médicos atendendo na unidade " + idUnidade
                    + " tente novamente mais tarde.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        return response.stream().map(CheckinOutPersistenceMapper::toCheckInDomain).toList();
    }

}
