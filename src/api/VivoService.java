package api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VivoService {
    private static VivoService instance;

    private VivoService() {
    }

    public static VivoService getInstance() {
        if (instance == null) {
            instance = new VivoService();
        }
        return instance;
    }

    public void enviarSMS(String origem, String destino, LocalDateTime time, String[] msgs) throws SMSException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        if (msgs.length == 0)
            throw new SMSException("Mensagem vazia. Nao e possivel o envio");

        System.out.println("Vivo SMS");
        System.out.println("Enviado em " + dtf.format(time));
        System.out.println("-------------------");
        System.out.println("From: " + origem + " To " + destino);
        System.out.println("-------------------");

        for (String msg : msgs) {
            System.out.println(msg);
        }
        System.out.println();

    }

}
