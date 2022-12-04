package duan1.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.io.IOException;

public class WebBrowser {
    public static void open(String url) throws IOException, URISyntaxException {
        URI uri = new URI(url);
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(uri);
        }
    }
}
