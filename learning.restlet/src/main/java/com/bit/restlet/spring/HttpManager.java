package com.bit.restlet.spring;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;

public class HttpManager {

	private static final PoolingHttpClientConnectionManager cm;

	static {
		try {
			cm = new PoolingHttpClientConnectionManager();
			// Increase max total connection to 200
			cm.setMaxTotal(200);
			// Increase default max connection per route to 20
			cm.setDefaultMaxPerRoute(20);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private HttpManager() { }
	
	public static HttpClient getClient() {
		return HttpClients
				.custom()
				.setConnectionManager(cm)
				.build();
	}

	public static String post(HttpPost post) throws Exception {
        final HttpClient client = HttpManager.getClient();
        final HttpResponse response = client.execute(post);
        final HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity, StandardCharsets.UTF_8);
	}

    public static String get(HttpGet get) throws Exception {
        final HttpClient client = HttpManager.getClient();
        final HttpResponse response = client.execute(get);
        final HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity, StandardCharsets.UTF_8);
    }

}