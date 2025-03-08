import api.*;
import model.SMS;

public class Main {
    public static void main(String[] args) {

        String origem = "1583986762683";
        String destinoVivo = "1583986868686";
        String destinoTim = "4183999669966";
        String destinoInvalido = "5183999669966";

        SMS mensagem1 = new SMS(origem, destinoVivo, "Essa mensagem não foi gerada pelo ChatGPT");
        SMS mensagem2 = new SMS(origem, destinoTim, "Essa mensagem não foi gerada pelo Copilot");
        // inválido
        // SMS mensagem3 = new SMS(origem, destinoInvalido, "Essa mensagem não foi gerada pelo Gemini");

        // sem traducao
        SMSService smsService = new SMSService();

        smsService.sendMessage(mensagem1);
        smsService.sendMessage(mensagem2);
        // inválido
        // smsService.sendMessage(mensagem3, false);

        // com traducao
        TranslationService translationService = new WhatsMateTranslationService();
        SMSServiceTranslateDecorator smsServiceTranslate = new SMSServiceTranslateDecorator(smsService, translationService);

        smsServiceTranslate.sendMessage(mensagem1);
        smsServiceTranslate.sendMessage(mensagem2);
        // inválido
        // smsServiceTranslate.sendMessage(mensagem3, true);

    }
}