package duan1.config;

import java.awt.Font;

public class Config {
    public static Boolean DEBUG = true;
    public static String API_URL = DEBUG ? "http://lavenes.ddns.net:3006" : "https://server.duan1.lavenes.com";
    public static String APP_VERSION = "Alpha 1.0.1";
    public static String JWT_SECRECT = "SECKEYDA1";
    public static Font FONT_IONICONS = new Font("SF Pro Text", Font.BOLD, 12);
    public static String[] PAYMENT_METHODS = new String[]{"momo", "cash"};
}
