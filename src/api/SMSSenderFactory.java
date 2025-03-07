package api;

public class SMSSenderFactory {
    public static SMSSender getSMSSender(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 13 || !isNumeric(phoneNumber)) {
            throw new IllegalArgumentException("Numero de telefone inválido");
        }
        int csp;
        try {
            csp = Integer.parseInt(phoneNumber.substring(0,2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número inválido");
        }

        switch (csp) {
            case 41:
                return new AdapterTim();
            case 15:
                return new AdapterVivo();
            default:
                throw new UnsupportedOperationException("Operadora não suportada: " + csp);
        }
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
