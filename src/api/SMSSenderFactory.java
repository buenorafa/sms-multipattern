package api;

import api.adapter.SMSSender;
import api.adapter.VivoAdapter;

// AdapterFactory
public class SMSSenderFactory {

    public static SMSSender getSMSSender(String type) {

        return switch (type) {
            case "Tim" -> TimService.getInstance();
            case "Vivo" -> new VivoAdapter();
            default -> throw new UnsupportedOperationException("Operadora não suportada: " + type);
        };
    }
}
