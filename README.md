# Sky | Netty Based Transport Tool Kit.

🚀 Use sky you can quickly create an http service or websocket service

## Features

- [x] Http Server
- [x] Websocket Server
- [ ] Spring boot starter [in development](https://github.com/fzdwx/sky/issues/11) （已经基本可用）
- [ ] 😙give me some issue!

## Showcase

```xml
<dependency>
  <groupId>io.github.fzdwx</groupId>
  <artifactId>sky-http-springboot-starter</artifactId>
  <version>0.11.3.2</version>
</dependency>
```

```java
import http.HttpServerRequest;

@SpringBootApplication
@RestController
public class BurstServerApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(BurstServerApplication.class);
    }

    // normal request
    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    // upgrade to websocket
    @GetMapping("connect")
    public void connect(@RequestParam String name, HttpServerRequest request) {
        request.upgradeToWebSocket(ws->{
            ws.mountOpen(h -> {
                ws.send("Hello " + name);
            });
        });
    }
}
```
