package util;

public class Util {

    public static String getAdapterType(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Numero de telefone inválido");
        }
        int csp;
        try {
            csp = Integer.parseInt(phoneNumber.substring(0, 2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número inválido");
        }

        return switch (csp) {
            case 41 -> "TimAdapter";
            case 15 -> "VivoAdapter";
            default -> throw new UnsupportedOperationException("Operadora não suportada: " + csp);
        };
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    public static boolean isValidPhoneNumber(String destino) {
        return destino != null && destino.length() == 13 && isNumeric(destino);
    }
}
