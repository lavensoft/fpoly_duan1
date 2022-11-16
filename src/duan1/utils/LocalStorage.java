package duan1.utils;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class LocalStorage {
    public static Preferences prefs = Preferences.userNodeForPackage(LocalStorage.class);

    public static void put(String key, String value) {
        prefs.put(key, value);
    }

    public static String get(String key) {
        String value =  prefs.get(key, null);

        return value;
    }

    public static void clear() throws BackingStoreException {
        prefs.clear();
    }
}
