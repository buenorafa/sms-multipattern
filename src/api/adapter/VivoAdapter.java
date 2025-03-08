package api.adapter;

import api.SMSException;
import api.VivoService;
import model.SMS;

import java.time.LocalDateTime;

public class VivoAdapter implements SMSSender {
    private VivoService service;

    public VivoAdapter() {
        this.service = VivoService.getInstance();
    }

    @Override
    public boolean sendSMS(SMS sms) {
        String origem = sms.getOrigem();
        String destino = sms.getDestino();
        String mensagem = sms.getTexto();
        LocalDateTime timestamp = sms.getTimestamp();

        String[] mensagemArray = dividirMensagem(mensagem);

        try {
            service.enviarSMS(origem, destino, timestamp, mensagemArray);
            return true;
        } catch (SMSException e) {
            System.err.println("Erro ao enviar SMS pela Vivo: " + e.getMessage());
            return false;
        }
    }

    private String[] dividirMensagem(String mensagem) {
        if (mensagem == null || mensagem.isEmpty()) {
            return new String[]{""}; // Retorna uma string vazia se não houver mensagem
        }

        int tamanhoMax = 120;
        int totalCaracteres = mensagem.length();
        int partes = (int) Math.ceil((double) totalCaracteres / tamanhoMax);
        String[] mensagemArray = new String[partes];

        for (int i = 0; i < partes; i++) {
            int inicio = i * tamanhoMax; // Calcula o início do trecho
            int fim = Math.min(inicio + tamanhoMax, totalCaracteres); // Calcula o fim (sem ultrapassar o texto)
            mensagemArray[i] = mensagem.substring(inicio, fim); // Extrai a substring
        }
        return mensagemArray;
    }
}
