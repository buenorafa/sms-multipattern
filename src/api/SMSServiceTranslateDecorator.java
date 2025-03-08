package api;

import model.SMS;

public class SMSServiceTranslateDecorator implements SMSServiceInterface {
    // wrappee
    private SMSService service;
    private TranslationService translationService;

    public SMSServiceTranslateDecorator(SMSService service, TranslationService translationService) {
        this.service = service;
        this.translationService = translationService;
    }

    @Override
    public void sendMessage(SMS sms) {
        String message = sms.getTexto();
        String translatedMessage = translatePtToEn(message);
        sms.setTexto(translatedMessage);

        service.sendMessage(sms);
    }

    private String translatePtToEn(String text) {
        try {
            return translationService.translate("pt", "en", text);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao traduzir a mensagem. Enviando no idioma original.");
            return text;
        }
    }
}
