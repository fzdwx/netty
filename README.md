# Netty Showcase

使用Netty写的一些小demo

## features

- [x] Http Server
- [x] Websocket Server
- [ ] todo... need your idea !

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

## 启动一个websocket 服务器
```java
final var router = Router.router()
        .GET("/ws", (req, resp) -> {
            // 处理weboskcet请求
            req.upgradeToWebSocket(((session, text) -> {
                log.info(" receive : {}", text);
            }));
            System.out.println("ttttttttttttttttt");
        })

new HttpServ(8888, router).start();
```

## Dev page
```java
  new HttpServ(8888, router)
        .name("我的http 服务器 !")
        .dev() /* open dev mode */
        .start();;
```
![img.png](assert/img.png)