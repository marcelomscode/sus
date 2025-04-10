package fiap.sus.infrastructure.repository.impl.checkinout;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.CheckOutDomainRepository;
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
public class CheckOutDomainRepositoryImpl implements CheckOutDomainRepository {

    private final CheckInOutJpaRepository checkInOutRepository;

    @Override
    public void checkOut(CheckInOutDomain checkInOutDomain) {

        log.info("Verificando se o médico [{}] já possui check-out hoje na unidade [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());

        var checkOutExistente = checkInOutRepository.findByIdMedicoAndIdUnidadeAndCheckout
                (checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade(), LocalDate.now());

        if (Objects.nonNull(checkOutExistente)) {
            var logMensagem = "O médico [{}] já possui check-out hoje na unidade [{}]";
            log.warn(logMensagem, checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());
            var mensagem =  "O médico já possui check-out feito hoje nessa unidade "
                    +checkInOutDomain.getIdUnidade()+": ";
            throw new CheckOutInException(mensagem, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        log.info("Verificando se o médico [{}] efetuou o check-in hoje na unidade [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());

        var checkInExistente = checkInOutRepository.findByIdMedicoAndIdUnidadeAndCheckIn
                (checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade(), LocalDate.now());

        if (Objects.isNull(checkInExistente)) {
            var mensagem = "O médico [{}] não efetuou o check-in hoje na unidade [{}]";
            log.warn(mensagem, checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());
            throw new CheckOutInException("O médico não efetuou o check-in hoje na unidade: "
                    + checkInOutDomain.getIdUnidade(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        log.info("Inserindo no CheckOut a data e hora atual  do sistema " + LocalDateTime.now() + " para o médico [{}] na unidade [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade());
        checkInExistente.setCheckOut(LocalDateTime.now());

        checkInOutRepository.save(checkInExistente);

        log.info("CheckOut realizado com sucesso para o médico [{}] na unidade [{}] em [{}]",
                checkInOutDomain.getIdMedico(), checkInOutDomain.getIdUnidade(), LocalDateTime.now());
    }

}
