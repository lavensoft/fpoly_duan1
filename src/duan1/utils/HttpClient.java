package duan1.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.Document;

public class HttpClient {
    public static CloseableHttpResponse post(String url, Document data, Document headers) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(data.toJson());
        
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();

        System.out.println(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));

        return response;
    }

    public static CloseableHttpResponse uploadFile(File file) throws ParseException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:3609/api/v1/files/upload_media");

        //BODY
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody(
        "file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        //HEADER
        httpPost.addHeader("app", "63452fa601a4792a134bf3f2");
        httpPost.addHeader("api_key", "AIzaSyAGLoe812akN97h2LVKZoUc3eHdeFGz9SI");

        CloseableHttpResponse response = client.execute(httpPost);

        System.out.println(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        
        client.close();

        return response;
    }
}
