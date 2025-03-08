import api.SMSService;
import api.SMSServiceTranslateDecorator;
import api.TranslationService;
import api.WhatsMateTranslationService;
import model.SMS;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainInput {
    public static void main(String[] args) {

        List<String> destinos = new ArrayList<>();

        if (args.length != 0) {
            destinos = Util.readLinesFromFile(args[0]);
        }

        SMSService smsService = new SMSService();

        TranslationService translationService = new WhatsMateTranslationService();
        SMSServiceTranslateDecorator smsServiceTranslate = new SMSServiceTranslateDecorator(smsService, translationService);

        String origem = "1583986762683";
        String destino;

        if (!destinos.isEmpty()) {

            System.out.println("Destinos selecionados: ");
            for (String d : destinos) {
                System.out.println(d);
            }

            System.out.println("Digite o texto da mensagem: ");
            String texto = System.console().readLine();

            for (String d : destinos) {
                SMS sms = new SMS(origem, d, texto);
                try {
                    smsService.sendMessage(sms);
                    smsServiceTranslate.sendMessage(sms);
                } catch (Exception e) {
                    System.out.println("Erro ao enviar mensagem para o número" + d + ": " + e.getMessage());
                }
            }
            return;
        }

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
                System.out.println("Erro ao enviar mensagem para o número" + destino + ": " + e.getMessage());
            }
        } while (!destino.isEmpty());

        System.out.println("Nenhum número de destino foi digitado. Encerrando...");
    }
}
