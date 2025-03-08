package api;

// AdapterFactory
public class SMSSenderFactory {

    public static SMSSender getSMSSender(String type) {

        return switch (type) {
            case "TimAdapter" -> new TimAdapter();
            case "VivoAdapter" -> new VivoAdapter();
            default -> throw new UnsupportedOperationException("Operadora não suportada: " + type);
        };
    }
}
