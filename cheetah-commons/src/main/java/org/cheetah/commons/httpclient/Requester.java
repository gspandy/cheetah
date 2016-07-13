package org.cheetah.commons.httpclient;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.client.config.RequestConfig;

import java.util.Map;

import static org.cheetah.commons.httpclient.Requester.METHOD.GET;

/**
 * 请求者，携带URL、entity、method、headers、parameters、requestConfig到HttpTransport
 * 该对象为不可变
 * Created by maxhuang on 2016/7/6.
 */
public class Requester {
    public enum METHOD {
        GET, POST, PUT, DELETE, HEAD, TRACE, OPTIONS
    }
    private String url;
    private String entity;
    private METHOD method;
    private Map<String, String> headers = Maps.newHashMap();
    private Map<String, String> parameters = Maps.newHashMap();
    private RequestConfig requestConfig;

    Requester() {
    }

    Requester(Builder builder) {
        this.url = builder.url;
        this.entity = builder.entity;
        this.method = builder.method;
        this.headers = builder.headers;
        this.parameters = builder.parameters;
        this.requestConfig = builder.requestConfig;
    }

    public String url() {
        return url;
    }

    public METHOD method() {
        return method;
    }

    public String entity() {
        return entity;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public Map<String, String> parameters() {
        return parameters;
    }

    public RequestConfig requestConfig() {
        return requestConfig;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public static Builder POST() {
        return new Builder(METHOD.POST);
    }

    public static Builder DELETE() {
        return new Builder(METHOD.DELETE);
    }

    public static Builder GET() {
        return new Builder(METHOD.GET);
    }

    public static Builder HEAD() {
        return new Builder(METHOD.HEAD);
    }

    public static Builder OPTIONS() {
        return new Builder(METHOD.OPTIONS);
    }

    public static Builder PUT() {
        return new Builder(METHOD.PUT);
    }

    public static Builder TRACE() {
        return new Builder(METHOD.TRACE);
    }

    public static class Builder {
        String url;
        String entity;
        METHOD method = GET;
        Map<String, String> headers;
        Map<String, String> parameters;
        RequestConfig requestConfig;

        Builder(METHOD method) {
            this.method = method;
        }

        public Requester build() {
            return new Requester(this);
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder entity(String entity) {
            this.entity = entity;
            return this;
        }

        public Builder method(METHOD method) {
            this.method = method;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = ImmutableMap.<String, String>builder().putAll(headers).build();
            return this;
        }

        public Builder parameters(Map<String, String> parameters) {
            this.parameters = ImmutableMap.<String, String>builder().putAll(parameters).build();
            return this;
        }

        public Builder requestConfig(RequestConfig requestConfig) {
            this.requestConfig = requestConfig;
            return this;
        }
    }
}
