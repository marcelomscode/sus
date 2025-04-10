package fiap.sus.uteis;

import fiap.sus.uteis.variaveis.ConstantesGlobais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatasConversao {

    private DatasConversao() {
        throw new IllegalStateException(ConstantesGlobais.CLASSES_UTILITARIAS);
    }

    public static LocalDate toLocalDate(String localDate) {

        return DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .parse(localDate, LocalDate::from);

    }


}
