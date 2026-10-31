package utils;

import java.time.LocalDate;
import java.util.Locale;


public class Sql {

   public static String texto(String valor) {
        if (valor == null) return "NULL";
        return "'" + valor.replace("'", "''") + "'";
    }

   public static String textoOuNulo(String valor) {
        return (valor == null || valor.isBlank()) ? "NULL" : texto(valor);
    }

   public static String numero(Double valor) {
        return valor == null ? "NULL" : String.format(Locale.US, "%.2f", valor);
    }

   public static String data(LocalDate data) {
        return data == null ? "NULL" : "'" + data + "'";
    }
}
