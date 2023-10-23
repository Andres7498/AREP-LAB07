package org.example.machine1;

import org.example.URLReader;

import static spark.Spark.*;

public class HelloSecureSpark1 {

    public static void main(String... args) {
        secure(getKeyStore(), getPwdStore() , null, null);
        port(getPort());

        get("/helloLocal", (req, res) -> "Hello Local Machine 1");
        get("/helloRemote", (req, res) -> URLReader.readSecureUrl(getUrl(), getKeyOtherStore(), getPwdStore()));
    }

    private static String getUrl() {
        if (System.getenv("URL") != null) {
            return System.getenv("PORT");
        }
        return "https://ec2-54-165-48-250.compute-1.amazonaws.com:4567/helloLocal";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

    public static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/certificates/machine1/ecikeystore1.p12";
    }

    public static String getKeyOtherStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/certificates/machine2/ecikeystore2.p12";
    }

    public static String getPwdStore() {
        if (System.getenv("PWDSTORE") != null) {
            return System.getenv("PWDSTORE");
        }
        return "123456";
    }

}
