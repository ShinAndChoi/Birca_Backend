package com.app.birca.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Value("${invoke-url.url}")
    private String invokeUrl;

    @Value("${service-key.key}")
    private String serviceKeyUrl;

    @Bean
    public WebClient kakao() {
        return WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }

    @Bean
    public WebClient ocr() {
        return WebClient.builder()
                .baseUrl(invokeUrl)
                .build();
    }

    @Bean
    public WebClient confirmRegisterNumber() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(serviceKeyUrl);
        factory.setEncodingMode(VALUES_ONLY);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .build();
    }

}
