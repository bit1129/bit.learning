package com.bit.restlet.spring;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class HttpUtils {
    public static String post(String url, byte[] bytes) throws IOException {
        PostMethod method = null;
        try {
            HttpClient client = new HttpClient();
            method = new PostMethod(url);
//            method.setRequestHeader("Content-Type", "application/octet-stream");
            method.setRequestEntity(new ByteArrayRequestEntity(bytes));
            client.executeMethod(method);
            int sc = method.getStatusCode();
            String result = method.getResponseBodyAsString();
            System.out.println(result);
            return result;
        } catch (IOException e) {
            return "Failed: " + e.getMessage();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }

    }

    public static String post2(String url, byte[] bytes) throws IOException {
        PostMethod method = null;
        try {
            HttpClient client = new HttpClient();
            method = new PostMethod(url);
            method.setRequestHeader("Content-Type", "application/octet-stream");
            method.setRequestEntity(new ByteArrayRequestEntity(bytes));
            client.executeMethod(method);
            int sc = method.getStatusCode();
            String result = method.getResponseBodyAsString();
            System.out.println(result);
            return result;
        } catch (IOException e) {
            return "Failed: " + e.getMessage();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }

    }

    public static String postMinicPresto(File file, String url) throws Exception {
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody("file", file)
                .build();
        URI uri = new URI(url);
        final HttpPost post = new HttpPost(uri);
//        post.setHeader("Content-Type", "application/octet-stream");
        post.setEntity(entity);
        System.out.println("-------------->" + entity.getContentLength());
        final String json = HttpManager.post(post);
        return json;
    }
}
