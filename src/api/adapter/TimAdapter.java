package api.adapter;

import api.TimService;
import model.SMS;

public class TimAdapter implements SMSSender {

    private TimService service;

    public TimAdapter() {
        service = TimService.getInstance();
    }

    @Override
    public boolean sendSMS(SMS sms) {
        return service.sendSMS(sms);
    }
}
