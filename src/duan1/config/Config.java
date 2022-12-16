package duan1.config;

import java.awt.Font;

public class Config {
    public static Boolean DEBUG = false;
    public static String CACHE_PATH = "cache/";
    public static String API_URL = DEBUG ? "http://localhost:3006" : "https://server.duan1.lavenes.com";
    public static String APP_VERSION = "Alpha 1.5.0";
    public static String JWT_SECRECT = "SECKEYDA1";
    public static Font FONT_IONICONS = new Font("SF Pro Text", Font.BOLD, 12);
    public static String[] PAYMENT_METHODS = new String[]{"momo", "cash"};
}
