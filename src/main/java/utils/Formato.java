package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formato {

    private static final DateTimeFormatter DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final Locale BR =
            Locale.forLanguageTag("pt-BR");

    public static String data(LocalDate data) {
        return data == null ? "-" : data.format(DATA);
    }

    public static String dinheiro(Double valor) {
        return valor == null
                ? "-"
                : String.format(BR, "R$ %,.2f", valor);
    }
}
