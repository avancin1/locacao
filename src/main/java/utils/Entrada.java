package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Entrada {
    private static final Scanner sc = new Scanner(System.in);

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        if (!sc.hasNextLine()) {
            System.out.println("\nEntrada encerrada. Saindo do sistema.");
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

   public static String lerTextoObrigatorio(String mensagem, int maximo) {
        while (true) {
            String texto = lerTexto(mensagem);
            if (texto.isEmpty()) {
                System.out.println("Campo obrigatório. Digite um valor.");
            } else if (texto.length() > maximo) {
                System.out.println("Máximo de " + maximo + " caracteres.");
            } else {
                return texto;
            }
        }
    }

    public static String lerTextoOpcional(String mensagem, int maximo) {
        while (true) {
            String texto = lerTexto(mensagem);
            if (texto.length() <= maximo) return texto;
            System.out.println("Máximo de " + maximo + " caracteres.");
        }
    }

   public static String lerDigitos(String mensagem, int tamanho) {
        while (true) {
            String digitos = lerTexto(mensagem).replaceAll("\\D", "");
            if (digitos.length() == tamanho) return digitos;
            System.out.println("Informe exatamente " + tamanho + " dígitos.");
        }
    }

    public static String lerPlaca(String mensagem) {
        while (true) {
            String placa = lerTexto(mensagem).toUpperCase().replace("-", "").replace(" ", "");
            if (placa.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) return placa;
            System.out.println("Placa inválida. Use o formato ABC1234 ou ABC1D23.");
        }
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static int lerIntEntre(String mensagem, int minimo, int maximo) {
        while (true) {
            int valor = lerInt(mensagem);
            if (valor >= minimo && valor <= maximo) return valor;
            System.out.println("Digite um valor entre " + minimo + " e " + maximo + ".");
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                return Double.parseDouble(lerTexto(mensagem).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex.: 150.50).");
            }
        }
    }

     public static double lerValorPositivo(String mensagem) {
        while (true) {
            double valor = lerDouble(mensagem);
            if (valor > 0 && valor <= 99999999.99) return valor;
            System.out.println("Digite um valor maior que zero (até 99999999.99).");
        }
    }

    public static LocalDate lerData(String mensagem) {
        while (true) {
            LocalDate d = converterData(lerTexto(mensagem + " (DD/MM/AAAA): "));
            if (d != null) return d;
            System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
        }
    }

   public static LocalDate lerDataOpcional(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem + " (DD/MM/AAAA, Enter = vazio): ");
            if (texto.isEmpty()) return null;
            LocalDate d = converterData(texto);
            if (d != null) return d;
            System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
        }
    }

   private static LocalDate converterData(String texto) {
        String[] partes = texto.split("/");
        if (partes.length != 3) return null;
        try {
            return LocalDate.of(Integer.parseInt(partes[2].trim()),
                                Integer.parseInt(partes[1].trim()),
                                Integer.parseInt(partes[0].trim()));
        } catch (NumberFormatException | DateTimeException e) {
            return null;
        }
    }

    public static boolean lerSimNao(String mensagem) {
        while (true) {
            String r = lerTexto(mensagem + " (S/N): ").toUpperCase();
            if (r.equals("S")) return true;
            if (r.equals("N")) return false;
            System.out.println("Responda S ou N.");
        }
    }
}