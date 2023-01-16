package com.example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

public class GithubLowLevelClient {
    private final HttpClient httpClient;
    private final URI uri;

    public GithubLowLevelClient(@Client(GithubConfiguration.GITHUB_API_URL) HttpClient httpClient,
                                GithubConfiguration configuration) {
        this.httpClient = httpClient;
        uri = UriBuilder.of("/repos")
                .path(configuration.getOrganization())
                .path(configuration.getRepo())
                .path("releases")
                .build();
    }

    Mono<List<GithubRelease>> fetchReleases() {
        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(USER_AGENT, "Micronaut HTTP Client")
                .header(ACCEPT, "application/vnd.github.v3+json, application/json");
        return Mono.from(httpClient.retrieve(req, Argument.listOf(GithubRelease.class)));
    }
}
