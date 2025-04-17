package fiap.sus.infrastructure.repository.impl.checkinout;

import fiap.sus.application.usecases.medicos.BuscaInformacoesMedicoUseCase;
import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.CheckInDomainRepository;
import fiap.sus.infrastructure.mappers.CheckinOutPersistenceMapper;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckInDomainRepositoryImpl implements CheckInDomainRepository {

    private final CheckInOutJpaRepository checkInOutJpaRepository;
    private final BuscaInformacoesMedicoUseCase buscaInformacoesMedicoUseCase;

    @Override
    public void medicoRealizarCheckIn(CheckInOutDomain checkInOutDomain) {

        var checkIn = CheckinOutPersistenceMapper.toCheckInOutPersistence(checkInOutDomain);

        log.info("Verificando se o médico [{}] já possui check-in hoje na unidade [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());

        var checkInExistente = checkInOutJpaRepository.findByIdMedicoAndIdUnidadeAndCheckIn
                (checkInOutDomain.getUUID(), checkInOutDomain.getIdUnidade(), LocalDate.now());

        if (Objects.nonNull(checkInExistente)) {
            log.warn("O médico [{}] já possui check-in hoje na unidade [{}]", checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());
            throw new CheckOutInException(
                    "O médico já possui check-in nessa unidade " + checkInOutDomain.getIdUnidade() + " feito hoje."
                    , HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        log.info("Inserindo no CheckIn a data e hora atual  do sistema " + LocalDateTime.now() + " para o médico [{}] na unidade [{}]",
                checkInOutDomain.getUUID(), checkInOutDomain.getIdUnidade());
        checkIn.setCheckIn(LocalDateTime.now());
        checkIn.setData(LocalDateTime.now());

        checkInOutJpaRepository.save(checkIn);

        log.info("CheckIn realizado com sucesso para o médico [{}] na unidade [{}] em [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade(), LocalDateTime.now());
    }

}
