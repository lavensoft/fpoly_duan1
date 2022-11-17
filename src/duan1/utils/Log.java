package duan1.utils;

import java.util.Date;

public class Log {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static void print(String x, String className, String color) {
        Date dateNow = new Date();
        int hour = dateNow.getHours();
        int minutes = dateNow.getMinutes();
        int second = dateNow.getSeconds();

        System.out.println(
            color + "[ " + hour + ":" + minutes + ":" + second + " ] " + className + ": " + x + ANSI_WHITE
        );
    }

    //* INFO */
    public static void info(String x, String className) {
        print(x, className, ANSI_WHITE);
    }

    public static void info(Object x, String className) {
        info(String.valueOf(x), className);
    }

    //* SUCCESS */
    public static void success(String x, String className) {
        print(x, className, ANSI_GREEN);
    }

    public static void success(Object x, String className) {
        info(String.valueOf(x), className);
    }

    //* ERROR */
    public static void error(Exception e) {
        print(e.getMessage().toString(), e.getClass().getSimpleName(), ANSI_RED);
    }
}
