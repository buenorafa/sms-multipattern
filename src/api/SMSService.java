package api;

import model.SMS;
import util.Util;

public class SMSService {
    private final TranslationService translationService;

    public SMSService(TranslationService translationService) {
        this.translationService = translationService;
    }

    public void sendMessage(SMS sms, boolean translate) {
        String destino = sms.getDestino();

        String adapterType = Util.getAdapterType(destino);
        SMSSender smssender = SMSSenderFactory.getSMSSender(adapterType);

        if (translate) {
            String message = sms.getTexto();
            try {
                String translatedMessage = this.translateMessage(message);
                sms.setTexto(translatedMessage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Falha ao traduzir a mensagem. Enviando no idioma original.");
            }
        }
        smssender.sendSMS(sms);
    }

    public String translateMessage(String fromLang, String toLang, String message) throws Exception {
        String translatedMessage;
        try {
            translatedMessage = translationService.translate(fromLang, toLang, message);
        } catch (Exception e) {
            //mudar para print
            throw e;
        }
        return translatedMessage;
    }

    public String translateMessage(String message) throws Exception {
        return translateMessage("pt", "en", message);
    }
}