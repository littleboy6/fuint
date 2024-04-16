package com.fuint.common.http;

import com.fuint.utils.StringUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * 调用REST接口并解析数据
 *
 * Created by FSQ
 * CopyRight https://www.fuint.cn
 */
@Component
public class HttpRESTDataClient {

    public static final Logger logger = LoggerFactory.getLogger(HttpRESTDataClient.class);

    private static final OkHttpClient client = new OkHttpClient();

    public static String requestGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body().string();
    }

    public static byte[] requestPost(String url, String postData) throws IOException {
        String postBody = postData;
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body().bytes();
    }

    public static String requestPost(String url, String contentType, String postData) throws IOException {
        MediaType mediaType = null;
        if (StringUtil.isNotEmpty(contentType)) {
            mediaType = MediaType.parse(contentType);
        }
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, postData))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body().string();
    }
}
