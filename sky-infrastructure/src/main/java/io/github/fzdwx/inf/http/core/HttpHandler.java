package io.github.fzdwx.inf.http.core;

/**
 * handle
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/18 12:26
 * @since 0.06
 */
@FunctionalInterface
public interface HttpHandler {

    void handle(HttpServerRequest request, final HttpServerResponse resp) throws Exception;
}