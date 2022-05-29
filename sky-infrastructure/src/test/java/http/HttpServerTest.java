package http;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import core.http.HttpServer;
import core.http.route.Router;
import io.github.fzdwx.lambada.Seq;
import io.github.fzdwx.lambada.Threads;
import io.github.fzdwx.lambada.http.ContentType;
import io.github.fzdwx.lambada.http.HttpMethod;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 16:44
 */
class HttpServerTest {


    @Test
    void test22() {
        HttpServer.create()
                .requestHandler(((request, response) -> {
                    System.out.println(request.remoteAddress().toString());


                    final FileUpload file = request.readFile("file");
                    final FileUpload f2 = request.readFile("file");
                    System.out.println(file);
                    System.out.println(request.bodyToString());
                    response.end();
                }))
                .listen(8888)
                .dispose();
    }

    @Test
    void test_http() {
        final Router router = Router.router().GET("/ws", (req, res) -> {
            req.upgradeToWebSocket((ws -> {

                ws.enableIdleState();

                ws.mountOpen((h) -> {
                    ws.send("hello");
                });

                ws.mountEvent(h -> {
                    System.out.println(h);
                });

            }));
        }).GET("/post", (req, res) -> {
            res.contentType(ContentType.STREAM_JSON);
            res.writeFlush("123123123\n");
            Threads.sleep(Duration.ofSeconds(1L));
            res.writeFlush("aaaaaaaaaaa\n");
            Threads.sleep(Duration.ofSeconds(1L));
            res.writeFlush("bbbbbbbbbb\n");
            Threads.sleep(Duration.ofSeconds(1L));
            res.end("ccccccccc\n");
        }).GET("/event", (req, res) -> {
            res.contentType(ContentType.EVENT_STREAM);
            Seq.range(100).onClose(() -> res.end("end"))
                    .forEach(i -> {
                        res.writeFlush(i + "\n");
                        Threads.sleep(Duration.ofMillis(100L));
                    });
            // System.out.println("end");
        }).GET("/1111", (req, res) -> {
            res.redirect("http://www.baidu.com");
        }).GET("/error", (req, resp) -> {
            throw new RuntimeException("json 序列化错误");
        }).GET("/test/2222", (req, resp) -> {
            resp.end("eeeeeee");
        });

        // 动态添加路由
        router.POST("/d", (req, res) -> {
            final JSONObject jsonObject = JSON.parseObject(req.bodyToString());

            final String path = jsonObject.getString("path");
            final String text = jsonObject.getString("text");
            final String method = jsonObject.getString("method");

            router.addRoute(HttpMethod.of(method), path, (req1, res1) -> {
                res1.end(text);
            });
            res.end();
        });

        final int port = 9999;
        HttpServer.create()
                .requestHandler((req, response) -> {

                    final FileUpload file = req.readFile("file");
                    System.out.println(file);
                    // final Route<HttpHandler> route = router.match(req);
                    //
                    // if (route != null) {
                    //     route.handler().handle(req, response);
                    //     return;
                    // }
                    //
                    // response.notFound(req.toString());
                    response.end();
                })
                .listen(port)
                .dispose();

        // Lang.sleep(Duration.ofSeconds(1000000000L));
    }
}