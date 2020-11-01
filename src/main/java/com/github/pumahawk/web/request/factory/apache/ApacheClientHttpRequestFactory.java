package com.github.pumahawk.web.request.factory.apache;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;

public class ApacheClientHttpRequestFactory implements ClientHttpRequestFactory {

    private HttpClient client;

    public ApacheClientHttpRequestFactory() {
        this.client = HttpClients.createDefault();
    }

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return new ApacheClientHttpRequest(this.client, uri, httpMethod);
    }

	static HttpUriRequest buildRequest(HttpHeaders headers, URI uri, HttpMethod method, byte[] output) {
        RequestBuilder requestBuilder = RequestBuilder.create(method.name()).setUri(uri).setEntity(new ByteArrayEntity(output));
        headers.toSingleValueMap().forEach((name, value) -> {
            if (!name.toLowerCase().equals("content-length")) {
                requestBuilder.addHeader(name, value);
            }
        });
        return  requestBuilder.build();
	}
}
