package com.sms.communication.gatewayclient;

import com.sms.communication.dto.SmsRequest;

public interface SmsClient
{
    boolean SendSms(SmsRequest smsRequest);



}
