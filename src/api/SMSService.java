package api;

import model.SMS;
import util.Util;

public class SMSService implements SMSServiceInterface {

    public void sendMessage(SMS sms) {
        String destino = sms.getDestino();

        String adapterType = Util.getAdapterType(destino);
        SMSSender smssender = SMSSenderFactory.getSMSSender(adapterType);

        smssender.sendSMS(sms);
    }
}