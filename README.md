# Netty Showcase

使用Netty写的一些小demo

## 启动一个http 服务器

```java
final var router = Router.router()
        .GET("/", (req, resp) -> {
            resp.html("""
                    <meta charset="UTF-8">
                    <title>测试服务器</title> 
                    <h1>hello world</h1>
                      """
            );
        })
        .GET("/hello", (req, resp) -> resp.json("你好"))
        .faviconIco(faviconIco);

new HttpServ(8888, router).start();
```