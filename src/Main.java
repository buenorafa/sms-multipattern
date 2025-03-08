import api.*;
import model.SMS;

public class Main {
    public static void main(String[] args) {

        TranslationService translationService = new WhatsMateTranslationService();
        SMSService smsService = new SMSService(translationService);

        String origem = "1583986762683";
        String destinoVivo = "1583986868686";
        String destinoTim = "4183999669966";
        String destinoInvalido = "5183999669966";

        SMS mensagem1 = new SMS(origem, destinoVivo, "Essa mensagem não foi gerada pelo chatGPT");
        SMS mensagem2 = new SMS(origem, destinoTim, "Essa mensagem não foi gerada pelo chatGPT");
        // inválido
        SMS mensagem3 = new SMS(origem, destinoInvalido, "Essa mensagem não foi gerada pelo chatGPT");

        // sem traducao
        smsService.sendMessage(mensagem1, false);
        smsService.sendMessage(mensagem2, false);
        // inválido
        // smsService.sendMessage(mensagem3, false);

        // com traducao
        smsService.sendMessage(mensagem1, true);
        smsService.sendMessage(mensagem2, true);
        // inválido
        // smsService.sendMessage(mensagem3, true);

    }
}