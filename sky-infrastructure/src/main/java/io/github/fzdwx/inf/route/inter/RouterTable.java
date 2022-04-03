package io.github.fzdwx.inf.route.inter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/18 12:28
 * @since 0.06
 */
public class RouterTable<T> extends ArrayList<Routing<T>> {

    /**
     * @since 0.06
     */
    public Routing<T> matchOne(String path, RequestMethod method) {
        for (Routing<T> l : this) {
            if (l.matches(method, path)) {
                return l;
            }
        }

        return null;
    }

    /**
     * @since 0.06
     */
    public List<T> matchAll(String path, RequestMethod method) {
        return this.stream()
                .filter(l -> l.matches(method, path))
                .sorted(Comparator.comparingInt(Routing::index))
                .map(Routing::target)
                .collect(Collectors.toList());
    }
}