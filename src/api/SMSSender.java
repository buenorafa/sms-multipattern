package api;

import model.SMS;

// SMSAdapter
public interface SMSSender {

    public boolean sendSMS(SMS sms);
}
