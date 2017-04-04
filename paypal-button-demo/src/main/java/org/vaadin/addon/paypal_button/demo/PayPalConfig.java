package org.vaadin.addon.paypal_button.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Based on PayPal demo code (limited sample).
 */
public class PayPalConfig {

    private String restApiMerchantId;
    private String restAPIClientId;
    private String restAPIClientIdSandbox;
    private String restAPIClientIdProduction;
    private String restAPIClientSecret;
    private String restAPIEndpoint;
    private String restBNCode;
    private String userActionFlag;
    private String environment;

    public PayPalConfig() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String filename = "org/vaadin/addon/paypal_button/config.properties";
            input = this.getClass().getClassLoader()
                    .getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            // load a properties file from class path, inside static method
            prop.load(input);
            // get the property value from config.properties file
            String envPrefix = "LIVE_";
            environment = "production";
            if (prop.getProperty("SANDBOX_FLAG").equals("true")) {
                envPrefix = "SANDBOX_";
                environment = "sandbox";
            }
            // ButtonSource Tracker Code
            restBNCode = prop.getProperty("SBN_CODE");
            restApiMerchantId = prop.getProperty("MERCHANT_ID");
            restAPIClientId = prop.getProperty(envPrefix + "CLIENT_ID");
            restAPIClientIdSandbox = prop.getProperty("SANDBOX_CLIENT_ID");
            restAPIClientIdProduction = prop.getProperty("LIVE_CLIENT_ID");
            setRestAPIClientId(restAPIClientId);
            restAPIClientSecret = prop.getProperty(envPrefix + "CLIENT_SECRET");
            restAPIEndpoint = prop.getProperty(envPrefix + "ENDPOINT");
            java.lang.System.setProperty("https.protocols",
                    prop.getProperty("SSL_VERSION_TO_USE"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isSet(Object value) {
        return (value != null && value.toString().length() != 0);
    }

    public String getRestBNCode() {
        return restBNCode;
    }

    public String getUserActionFlag() {
        return userActionFlag;
    }

    private void setUserActionFlag(String userActionFlag) {
        this.userActionFlag = userActionFlag;
    }

    public String getRestAPIMerchantId() {
        return restApiMerchantId;
    }

    public void setRestAPIMerchantId(String merchantId) {
        restApiMerchantId = merchantId;
    }

    public String getRestAPIClientId() {
        return restAPIClientId;
    }

    public void setRestAPIClientId(String clientId) {
        restAPIClientId = clientId;
    }

    public String getRestAPIClientIdSandbox() {
        return restAPIClientIdSandbox;
    }

    public void setRestAPIClientIdSandbox(String clientId) {
        restAPIClientIdSandbox = clientId;
    }

    public String getRestAPIClientIdProduction() {
        return restAPIClientIdProduction;
    }

    public void setRestAPIClientIdProduction(String clientId) {
        restAPIClientIdProduction = clientId;
    }

    public String getRestAPIClientSecret() {
        return restAPIClientSecret;
    }

    public void setRestAPIClientSecret(String clientSecret) {
        restAPIClientSecret = clientSecret;
    }

    public String getRestAPIEndpoint() {
        return restAPIEndpoint;
    }

    public void setRestAPIEndpoint(String endpoint) {
        restAPIEndpoint = endpoint;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
