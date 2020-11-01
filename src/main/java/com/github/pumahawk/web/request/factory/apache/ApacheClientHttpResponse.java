package com.github.pumahawk.web.request.factory.apache;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

class ApacheClientHttpResponse implements ClientHttpResponse {

    private final HttpResponse response;

    public ApacheClientHttpResponse(HttpResponse response) {
        this.response = response;
	}

	@Override
    public InputStream getBody() throws IOException {
        return this.response.getEntity().getContent();
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        for (Header header : this.response.getAllHeaders()) {
            headers.add(header.getName(), header.getValue());
        }
        return headers;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.valueOf(this.response.getStatusLine().getStatusCode());
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return this.response.getStatusLine().getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return this.response.getStatusLine().getReasonPhrase();
    }

    @Override
    public void close() {
        if (this.response instanceof Closeable) {
            try {
                ((Closeable) this.response).close();
            } catch (IOException e) {}
        }
    }
    
}
