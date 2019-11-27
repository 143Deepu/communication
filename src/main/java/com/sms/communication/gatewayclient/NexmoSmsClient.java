package com.sms.communication.gatewayclient;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.sms.communication.constants.GatewayClient;
import com.sms.communication.dto.NexmoSmsDto;
import com.sms.communication.dto.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("Nexmo")
@Component
public class NexmoSmsClient implements SmsClient{
    private static  final Logger LOGGER = LoggerFactory.getLogger(NexmoSmsClient.class);

    @Autowired
    private NexmoSmsDto NexmoSmsDto;
    public NexmoSmsClient(NexmoSmsDto NexmoSmsDto)
    {
        this.NexmoSmsDto = NexmoSmsDto;
    }

    @Override
     public int GatewayClient(){
        return GatewayClient.NEXMO.getCode();
    }
    @Override
    public boolean SendSms(SmsRequest smsRequest) {
        boolean result = true;
        try{
            NexmoClient client = new NexmoClient.Builder()
                    .apiKey(NexmoSmsDto.getApiKey())
                    .apiSecret(NexmoSmsDto.getSecretKey())
                    .build();
            String messageText = "Welcome To FullPlate!";
            LOGGER.info("Nexmo Output:");
            LOGGER.info("Send Sms {} " + smsRequest);
            TextMessage message = new TextMessage(NexmoSmsDto.getBrandName(),smsRequest.getPhoneNumber(), messageText);

            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            for (SmsSubmissionResponseMessage responseMessage : response.getMessages())
            {
                LOGGER.info(String.valueOf(responseMessage));
            }
            if (response.getMessages().get(0).getStatus() == MessageStatus.OK)
            {
                LOGGER.info("Message sent successfully.");
                return result = true;
            }
            else
            {
                LOGGER.info( "Message failed with error: " + response.getMessages().get(0).getErrorText());
                return result = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result = false;
    }



}
