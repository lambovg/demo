package com.example;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import org.reactivestreams.Publisher;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;


@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
public interface GithubApiClient {

    @Get(value = "/repos/${github.organization}/${github.repo}/releases", processes = "application/json")
    Publisher<GithubRelease> fetchReleases();
}
