package duan1.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.bson.Document;

import duan1.config.Config;

public class Momo {
    public static Document create(Double amount, String extraData) throws ClientProtocolException, IOException{
        Document body = new Document();

        body.put("amount", amount);
        body.put("extraData", extraData);

        return HttpClient.post(Config.API_URL + "/create_pay", body, new Document());
    }
}
