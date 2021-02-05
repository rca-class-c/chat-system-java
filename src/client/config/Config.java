package client.config;

public class Config {

    public static String SERVER_HOSTNAME;
    public static int PORT;

    public static void setServerHostname(String serverHostname) {
        SERVER_HOSTNAME = serverHostname;
    }

    public static String getServerHostname() {
        return SERVER_HOSTNAME;
    }

    public static void setPORT(int PORT) {
        Config.PORT = PORT;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getAPIURL() {
        return  "http://" + getServerHostname() + ":" + PORT;
    }
}