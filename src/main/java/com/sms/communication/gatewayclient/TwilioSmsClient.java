package com.sms.communication.gatewayclient;

import com.sms.communication.constants.GatewayClient;
import com.sms.communication.dto.SmsRequest;
import com.sms.communication.dto.TwilioSmsDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service("twilio")
public class TwilioSmsClient implements SmsClient {
    private static  final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsClient.class);
    @Autowired
    private  TwilioSmsDto TwilioSmsDto;
    public TwilioSmsClient(TwilioSmsDto TwilioSmsDto)
    {
        this.TwilioSmsDto = TwilioSmsDto;
    }

    @Override
    public int GatewayClient(){
        return GatewayClient.TWILIO.getCode();
    }
    @Override
    public boolean SendSms(SmsRequest smsRequest) {
        boolean result = true;
        try
        {
            if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
                PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(TwilioSmsDto.getTrailNumber());
                String messageText = "Welcome To FullPlate!";
                Message message2 =  Message.creator(to, from,messageText)
                        .setStatusCallback(URI.create("http://postb.in/1234abcd"))
                        .create();
                LOGGER.info("Twilio Output:");
                LOGGER.info("Send Sms {} " + smsRequest);

                LOGGER.info("SMS Status:" + String.valueOf(message2.getStatus()));
                if (String.valueOf(message2.getStatus()).equals("sent") || String.valueOf(message2.getStatus()).equals("queued") || String.valueOf(message2.getStatus()).equals("delivered"))
                {
                    LOGGER.info("Message sent successfully.");
                    return result = true;
                }
                else
                {
                    LOGGER.info("Message NOT Sent successfully.");
                     return result = false;
                }
            }
            else
            {
//                throw new IllegalArgumentException("Phone Number [" + smsRequest.getPhoneNumber() + "] is not valid number");
                return result = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result = false;
    }
    private boolean isPhoneNumberValid(String phoneNumber)
    {
        //TODO: Implement phone number validator
        return true;
    }
}
