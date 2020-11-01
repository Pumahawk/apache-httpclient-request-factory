package com.github.pumahawk.web.request.factory.apache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AbstractClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

class ApacheClientHttpRequest extends AbstractClientHttpRequest {

    private final HttpClient client;

    private final URI uri;

    private final HttpMethod method;

    private ByteArrayOutputStream body = new ByteArrayOutputStream(1024);

    public ApacheClientHttpRequest(HttpClient client, URI uri, HttpMethod method) {
        this.client = client;
        this.uri = uri;
        this.method = method;
    }

    @Override
    public String getMethodValue() {
        return this.method.name();
    }

    @Override
    public URI getURI() {
        return this.uri;
    }

    @Override
    protected OutputStream getBodyInternal(HttpHeaders headers) throws IOException {
        return this.body;
    }

    @Override
    protected ClientHttpResponse executeInternal(HttpHeaders headers) throws IOException {
        HttpUriRequest request = ApacheClientHttpRequestFactory.buildRequest(headers, this.uri, this.method, body.toByteArray());
        return new ApacheClientHttpResponse(this.client.execute(request));
    }

    
}
