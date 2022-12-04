package duan1.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.bson.Document;

import duan1.config.Config;

public class Momo {
    public static Document create() throws ClientProtocolException, IOException{
        Document body = new Document();

        body.put("amount", 1000000);
        body.put("extraData", "");

        return HttpClient.post("http://localhost:9004/create_pay", body, new Document());
    }
}
