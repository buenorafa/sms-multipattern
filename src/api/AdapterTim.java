package api;

import model.SMS;

public class AdapterTim implements SMSSender {
    private TimService service;

    public AdapterTim() {
        service = TimService.getInstance();
    }
    @Override
    public boolean sendSMS(SMS sms) {
        return service.sendSMS(sms);
    }
}
