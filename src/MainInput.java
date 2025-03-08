import api.SMSService;
import api.SMSServiceTranslateDecorator;
import api.TranslationService;
import api.WhatsMateTranslationService;
import model.SMS;

public class MainInput {
    public static void main(String[] args) {

        SMSService smsService = new SMSService();

        TranslationService translationService = new WhatsMateTranslationService();
        SMSServiceTranslateDecorator smsServiceTranslate = new SMSServiceTranslateDecorator(smsService, translationService);

        String origem = "1583986762683";
        String destino;

        do {
            System.out.println("Digite o número de destino: ");
            destino = System.console().readLine();

            System.out.println("Digite o texto da mensagem: ");
            String texto = System.console().readLine();

            SMS sms = new SMS(origem, destino, texto);

            try {
                smsService.sendMessage(sms);
                smsServiceTranslate.sendMessage(sms);
            } catch (Exception e) {
                System.out.println("Erro ao enviar mensagem: " + e.getMessage());
            }
        } while (!destino.isEmpty());

        System.out.println("Nenhum número de destino foi digitado. Encerrando...");
    }
}
