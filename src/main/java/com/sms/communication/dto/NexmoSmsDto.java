package com.sms.communication.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("nexmo")
public class NexmoSmsDto
{
        private static String apiKey;
        private static String secretKey;
        private static String brandName;

        public static String getApiKey() {
                return apiKey;
        }

        public void setApiKey(String apiKey) {
                this.apiKey = apiKey;
        }

        public static String getSecretKey() {
                return secretKey;
        }

        public void setSecretKey(String secretKey) {
                this.secretKey = secretKey;
        }

        public static String getBrandName() {
                return brandName;
        }

        public void setBrandName(String brandName) {
                this.brandName = brandName;
        }
}
