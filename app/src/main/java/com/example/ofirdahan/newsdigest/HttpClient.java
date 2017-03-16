package com.example.ofirdahan.newsdigest;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String getUrlResponse() throws IOException {
        HttpClient httpClient = new HttpClient();
        String response = httpClient.run("http://hn.algolia.com/api/v1/search?tags=front_page");
        return response;
    }
}
