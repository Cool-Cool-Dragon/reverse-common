package com.reverse.common.client;

import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author huan liu
 */
public class OkHttpClientWrapper {
    private static final int TIMEOUT_SECONDS = 10;
    private static final int MAX_IDLE_CONNECTIONS = 5;
    private static final long KEEP_ALIVE_DURATION = 5 * 60 * 1000L;

    private static OkHttpClient client;

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpClientWrapper.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder()
                            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MILLISECONDS))
                            .build();
                }
            }
        }
        return client;
    }

    public static String doGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = getInstance().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public static String doPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        // try-with-resource 风格
        try (Response response = getInstance().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return Objects.requireNonNull(response.body()).string();
        }

    }

    public static InputStream doFile(String url, RequestBody requestBody) throws IOException {
        InputStream inputStream;
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        // try-with-resource 风格
        try (Response response = getInstance().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            inputStream = Objects.requireNonNull(response.body()).byteStream();
        }
        return inputStream;
    }
}