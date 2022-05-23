package sky.starter.ext;

import http.HttpServerRequest;
import http.HttpServerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import sky.starter.domain.SkyRouteDefinition;

import java.util.Comparator;

/**
 * request result handler.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/18 17:01
 */
public interface RequestResultHandler extends Comparable<RequestResultHandler>, Ordered {

    Comparator<? super RequestResultHandler> sort = Comparator.comparingInt(RequestResultHandler::getOrder);

    boolean support(Object result, SkyRouteDefinition definition);

    default void handle(Object result, SkyRouteDefinition definition, final HttpServerRequest request, HttpServerResponse response) {
        if (request.isWebsocket()) {
            return;
        }
        if (response.isEnd()) {
            return;
        }

        apply(result, definition, response);
    }

    void apply(Object result, SkyRouteDefinition definition, HttpServerResponse response);

    @Override
    default int compareTo(@NotNull RequestResultHandler o) {
        return sort.compare(this, o);
    }

}