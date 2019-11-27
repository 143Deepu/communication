package com.sms.communication.constants;

public  enum GatewayClient
{
 NEXMO(1,"Nexmo"),
 TWILIO(2,"Twilio");

    private final int code;
    private final String type;

     GatewayClient(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }
    public String getType() {
        return type;
    }

}
