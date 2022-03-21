package io.github.fzdwx.http;

import io.github.fzdwx.inf.http.core.ContentType;
import io.github.fzdwx.inf.route.Router;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;

import static io.github.fzdwx.inf.Netty.HTTP;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/17 17:45
 */
@Slf4j
public class HttpServer {

    static byte[] faviconIco = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 80, 0, 0, 0, 80, 8, 6, 0, 0, 0, -114, 17, -14, -83, 0, 0, 8, 78, 73, 68, 65, 84, 120, 94, -19, 92, 9, 104, 28, 85, 24, -114, 7, -34, 55, -42, 98, -51, -50, 76, -102, 120, -32, -127, -118, 55, 120, -60, 43, -39, 55, 105, 106, 5, 35, 42, 42, -118, 7, -94, -11, 0, 17, 111, -87, 5, 17, 84, 60, 80, 81, -95, -32, 93, 60, 81, -21, -127, -38, 86, 5, 45, 69, 81, 107, 21, -37, 98, -113, -92, -35, -99, 73, -38, -58, -86, 37, -90, -121, 49, -15, -1, 102, -33, 108, 102, -2, 121, -69, 59, -69, 59, -109, -60, -35, -7, -32, 35, -39, -7, -1, -9, -34, -4, -1, -66, 121, -17, 127, -1, 123, -77, 13, 13, 19, 8, -106, 110, 94, -106, 105, 106, 63, -103, 95, 79, 16, 18, -10, -108, -50, 61, 44, 93, -40, 25, -61, 60, -123, -53, 18, -124, 4, 57, -16, 53, -30, 95, 118, -54, 60, -111, -53, 18, -124, -128, -83, -101, -41, -110, 3, 71, -120, 125, 86, 115, 91, -118, -53, 19, -108, 64, -42, 48, -113, -109, 14, 4, -65, 30, 105, -24, -38, -119, -21, 36, 40, -126, -11, 77, -45, 39, 123, 28, 8, -50, -28, 58, 9, -118, 96, -92, -95, 97, 7, -81, 3, 109, 93, -4, -39, 109, -52, -40, -113, -21, 37, 40, 0, 56, -117, -11, -64, 17, -37, 72, -33, -59, -11, 18, 20, 64, 95, 115, -37, 65, -46, 113, 91, 61, 78, -20, 30, 105, -104, -75, 35, -41, 77, -96, -128, -99, 106, 63, 67, 58, -83, -121, 2, -21, 53, 121, 39, 54, -119, 54, -82, -101, 64, 1, 91, 19, -41, 73, -89, -83, -52, -22, -26, -85, -93, -67, -48, 124, -106, -21, 38, 80, -64, 54, -60, -53, -46, 105, 63, 18, 103, 123, 31, 99, -82, -101, -128, 1, -29, 28, 57, 106, 3, 28, 70, -77, -17, -105, -98, -34, -104, -101, 76, -90, 116, 30, -56, -53, 36, -16, -64, 110, 50, -49, 116, -99, -123, -57, 55, 107, -92, -81, -14, 58, 48, -85, -91, -49, -27, 101, 18, 120, 96, 25, -23, 55, 71, 29, 40, -18, -27, 14, -76, 53, -13, 10, 94, 38, -127, 4, -11, 62, -99, -100, -12, -113, -73, -73, 89, 122, -6, 14, 95, 15, -44, -59, -19, -68, 92, 2, 9, 111, -17, 35, 14, -84, 108, 17, -69, 102, 13, -15, -126, -81, 7, 26, -30, 54, 94, 46, 1, 33, 99, -92, 91, -3, 61, 45, -3, 10, -82, 83, 15, 92, -28, -67, 78, -97, 111, -28, 101, -21, 30, -104, 89, -55, 57, 89, -81, -93, 122, 83, -23, -109, 54, 76, 106, -35, -117, -2, -33, -18, -21, -127, -55, 24, -24, 7, 82, 85, -106, 38, 62, -11, -11, 50, 67, -52, -125, -116, 30, -23, 75, -4, -67, -113, 28, 107, -120, -77, 120, 29, 117, 13, 62, -58, 17, 7, -6, -12, -74, 38, -56, 2, -114, 37, 38, 89, 25, 15, -56, 65, 15, 112, 7, -47, 24, 119, 13, 100, -103, 70, -13, -104, -96, 76, 44, -15, -105, 55, 31, -90, 89, -7, 33, 76, 54, -34, -21, 117, 1, -52, -90, -36, 65, 52, -66, 61, -97, -105, -21, -26, 71, 92, 78, 19, -53, 125, -34, 58, -92, -109, -121, -88, 23, 127, -41, -93, -73, 31, -20, -107, -43, 52, 108, 45, 125, 121, -48, 57, -30, 19, 55, 117, 79, -15, 95, 23, -105, 19, -121, -35, 71, -37, 11, 90, -83, -68, 43, -27, -103, 76, 99, 123, 11, -105, -41, 28, -56, 121, -89, 91, 108, 102, 37, 126, -125, -19, 76, -56, 123, -115, -76, 65, -97, 55, 41, 28, -8, 30, -81, 11, -96, -119, -26, 82, 87, -121, -42, -50, -85, -42, 106, 29, -5, 115, -99, -102, -127, -52, 52, 91, 126, -57, -104, 63, -83, -98, 122, -34, -66, -112, -29, 47, 93, 91, -86, 112, -34, 8, 114, -124, -68, 62, -64, 54, -38, -114, -16, -23, -23, -30, 109, -82, 83, 51, -80, 53, -15, 28, 51, 118, 85, 111, -117, -104, 4, -39, -81, 71, 117, -19, 66, -41, 22, 114, -57, 73, 46, -27, 117, -71, -96, -57, -10, -128, -128, 62, -11, 74, -82, 23, 59, 54, 30, 62, 125, 111, 126, 45, 74, 96, -4, 34, -29, -122, 60, -122, 110, -52, 52, 78, 59, 20, 50, 25, 11, -66, 17, 112, -124, 36, -110, 10, -68, 62, 23, 114, -3, -52, -54, -104, 107, 70, 26, 90, 119, -26, -70, 81, -94, 111, 114, -37, -98, -66, 11, 89, -61, -68, 1, -119, 76, -70, -39, 115, -30, -40, 123, -96, -103, -14, 73, -113, -111, -125, -18, -47, 13, -57, 121, -70, 120, 61, -24, -124, 81, 98, -103, -57, -86, -53, -125, 66, -103, -13, -71, 62, -104, -43, -52, -117, -71, 110, -75, -128, 95, -32, 31, -57, 79, -28, 47, 46, 119, 103, 63, 108, -28, -12, 88, -122, 57, 75, 53, -21, 85, 2, 111, -110, 52, 103, -100, -72, 8, -41, -15, -40, -38, -122, -7, 22, 55, -98, 51, -37, -44, 126, 44, -81, -45, 5, -55, 31, -27, -6, -110, 31, 114, -35, 74, -31, 60, 61, -28, 15, -53, -39, -93, 17, -37, -31, 39, -82, -109, -121, -36, -48, -55, -49, -126, -28, -15, -81, -112, -109, -61, -70, -108, -21, -122, 5, -59, 111, -89, -115, 26, -106, -37, -41, 112, 14, 17, -47, -110, 77, 97, 120, -128, -12, -115, -49, -32, 117, 2, -14, 32, -46, 122, -82, 47, -71, -75, -37, 104, -35, -115, -105, 9, 11, -40, 11, -69, 97, -65, -89, -50, -63, 80, 9, -35, 94, 77, 28, 105, -79, -59, 61, 113, -128, 38, -127, -105, -112, 49, -58, -58, 55, 47, 83, 12, -106, -106, -66, 69, -42, -47, -1, 123, -117, -40, -57, 121, 108, 13, -15, -127, -62, -24, 2, 52, -25, -85, -38, -92, 96, -5, -15, -96, -18, 40, -53, 61, -31, -123, 54, -100, -116, -72, 102, -66, 72, -27, 7, 88, 125, 91, -15, -8, -14, 50, 5, -127, -96, -44, 10, 58, -47, 33, 85, -76, 26, -53, 48, -60, 108, -68, -100, 10, 88, 97, -96, 28, 61, -82, 119, -29, 51, 122, 33, -81, -77, 20, 49, -18, 96, -62, 112, -54, 55, -73, -91, 104, -55, -9, 12, -41, -31, 12, -101, -71, 113, 98, 79, -78, -57, -79, 75, 81, 15, -24, 14, 59, 101, 65, 30, -14, -31, -33, -124, -105, -61, -74, -98, -2, -126, 42, -65, -46, 13, -124, 85, -112, -29, -36, 16, 54, -52, 73, -1, 66, 69, 61, -27, 48, -97, -79, 46, -59, 98, -55, 87, -36, 47, 28, -116, -5, 39, -35, 97, 94, -42, 71, 26, -1, 120, -7, -48, -128, -25, 3, 21, -86, -71, -103, 122, -42, 28, -84, 52, -8, -29, 70, -113, -16, -5, 116, -77, -33, 98, -47, 111, 21, -24, -43, 113, -48, -19, -15, 46, -100, 71, -44, 89, 9, -103, 115, 44, -25, 126, -125, 101, 56, 49, 6, 86, 29, -107, 96, -20, -29, 21, -105, -32, 74, -20, 93, -28, -41, -74, -70, 120, 7, 105, 43, -124, 22, 10, -35, -8, -88, -119, -101, 93, 27, 50, 122, -38, -92, 107, 43, 2, 58, -59, -71, 57, -110, 72, 68, 102, -116, -5, 21, 13, -108, -96, -7, 8, -54, -45, -1, 79, -29, 112, 80, 5, 95, 68, 85, -60, 112, -31, -76, -81, -119, 78, -6, -4, 47, -105, -105, 102, -124, -37, 7, 100, -4, -11, -63, 6, 74, 114, -109, 19, 3, 26, -26, -43, 114, 38, -2, 65, -95, 19, 27, -77, 83, -51, -61, -100, -57, -106, -106, -116, 92, 22, -126, 43, 34, 93, -51, 56, 107, 85, 77, -84, 83, 52, 84, -108, -120, 43, 49, 123, -94, 7, 90, -71, 64, 52, -96, 19, 19, 45, -36, -73, -52, -2, 112, 89, 105, -90, -46, -45, -71, 15, -86, 6, 85, 60, 51, -48, 80, 9, -46, 36, -12, 4, -54, 34, 24, -90, -49, -53, -72, 60, 46, 34, 113, -31, -76, 91, 34, 86, 44, -64, -126, -55, -117, -86, -128, -124, 3, 85, -66, 69, -47, 96, 49, -10, 103, 26, -69, 118, 119, 82, 85, -102, -8, 76, 33, -113, -123, 8, -63, -48, -82, 85, -63, -40, -115, 67, -18, -36, -10, -56, 80, 44, 107, 82, -120, 24, 63, -99, -78, -71, -11, 100, 64, 30, 53, -111, -35, 70, 123, 21, -114, -37, 127, 20, -117, 105, -85, 6, 57, -31, 2, 69, -93, -91, -104, 69, 111, -24, 75, 117, 28, -83, -112, 69, -51, 109, 72, -78, -54, -34, 87, 118, -52, -119, 72, -127, -37, 28, 41, 100, -42, -72, -4, -112, 64, 70, -13, 86, -18, 37, -102, -96, 60, 34, -70, 103, 103, 104, -83, -3, 32, -105, -123, 34, 117, 16, -97, -63, 113, -128, 26, -6, 62, -48, 112, 105, 98, 9, 118, 60, -110, 9, 100, -28, 47, 10, 121, -43, -60, 122, 25, 97, -117, 92, -126, -122, 94, -14, 121, -72, 5, 61, -105, -37, 27, 57, -24, 91, 122, 74, -47, 120, 105, 26, -30, 55, 108, -6, 32, -115, 79, -97, -105, 4, -28, 85, 16, 103, 105, -80, -14, 65, -3, 104, -121, -53, 67, 114, 49, -73, 53, 22, -48, 10, -29, 38, 69, -29, 97, -71, 0, 49, 37, 122, 34, 59, -115, 85, 41, -121, -88, 71, -33, -113, -98, 39, -9, 85, 22, 40, 116, -62, -111, 58, 6, -73, 53, 22, 20, 74, -93, -121, -90, 33, -26, -63, 88, -89, -82, -36, -74, -28, -38, -128, 78, 8, 98, 67, 29, -121, -112, 80, -113, -29, -68, -112, 9, -38, 66, 68, 86, -55, 111, 105, 76, -112, -71, -62, -64, 13, -108, 69, 67, 124, -18, 110, 103, 34, 75, 35, 67, 14, 59, -96, -89, 102, 55, 94, 121, 112, -77, 62, -71, -119, -51, -100, -81, -48, 43, -117, 89, -67, -29, 108, -65, -91, 49, -63, -13, 34, 76, -107, 52, -105, 35, -5, -19, -42, 107, 107, -26, -83, 65, 29, 37, 103, -69, 101, 114, -39, 115, 115, -71, 66, -89, 108, -70, -69, -124, -79, 67, -58, 88, -127, 27, -88, -112, 91, -112, 118, -54, 30, 34, 26, -83, -112, -29, 23, 102, 113, 108, 52, -95, -100, 83, 94, -95, 83, 9, 99, 13, -96, 57, -84, 8, 111, 124, -126, -16, 111, 110, 99, -84, 112, -9, 57, 106, -123, 110, -46, 99, -52, -128, 60, 31, 50, 44, -56, 54, -45, 13, 108, -29, 55, -12, 63, -31, 54, 39, 91, -82, -103, -45, -8, 54, -60, -104, -62, -55, 88, 107, -30, -50, 74, -14, -123, -29, 66, -70, 79, 39, 67, 62, -47, -34, 122, 114, 78, 26, -28, -62, -111, 76, -32, -90, 39, 6, 51, 56, -110, -31, -58, -96, 19, 22, -104, -91, -79, 35, 70, 55, 60, -88, 48, 98, 60, 56, 104, -21, -30, -98, 49, 89, -25, 70, 9, 103, -61, -102, 2, 102, -123, 65, 99, 71, 106, 63, -20, 1, -128, 9, 9, -7, -5, 7, -40, 10, -16, -66, 125, 62, 22, 68, 123, 51, -57, 117, 114, -120, 18, -74, -42, 113, -126, 53, 118, -101, 74, 61, 53, -7, -93, 61, 114, 9, -72, 88, 97, 112, -108, 92, -116, 118, 120, -37, 53, 3, -25, 76, -118, 46, 62, 86, 24, 94, 53, -79, 23, 50, -90, -53, -79, -15, 66, 20, -87, -89, 0, 61, 41, -78, -70, 64, -119, 67, -27, -27, 114, 97, 93, 57, -49, 5, -78, -46, -76, 28, -4, 89, -31, -112, -48, 68, 121, -44, -61, -21, -82, 27, -40, 83, 59, 53, -85, -126, -51, 111, -55, 126, -108, -25, 117, -42, 29, -112, 93, -74, 74, 29, 114, 12, 114, 56, -7, 33, 30, 15, 104, 102, 126, 76, -31, -92, -126, -124, 62, -81, -93, -82, -127, 19, -11, 86, -8, -61, 71, -53, -86, 57, -127, 95, -77, -16, -2, 102, 76, 49, 38, 111, -83, 23, -127, -91, -101, 115, -71, -61, -4, 52, -25, -14, 50, 9, 60, -112, -17, -44, -15, -41, 97, 93, 110, -113, -28, -52, 114, -83, -93, -48, 126, -117, -9, -115, -10, 4, 69, -80, 46, 37, -102, 45, -1, -101, -99, -32, 16, -82, 115, -35, 4, 5, -128, 23, -89, 125, -67, -81, -106, 95, -92, -114, 3, 89, 67, -100, -22, 117, 32, 62, 115, -99, 4, 37, 48, -6, -77, 79, -23, 69, 92, -106, 32, 4, -36, -41, -52, 42, 122, -47, 47, 65, -2, 109, -10, 5, -55, 47, -103, -41, 56, -2, 3, 39, -54, -9, -49, 127, 75, -85, -62, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};

    @SneakyThrows
    public static void main(String[] args) {
        final var router = Router.router()
                .GET("/", (req, resp) -> {
                    resp.html("""
                            <meta charset="UTF-8">
                            <title>测试服务器</title> 
                            <h1>hello world</h1>
                              """
                    );
                })
                .GET("/to", (req, resp) -> {
                    resp.redirect("https://www.baidu.com/s?wd=http%20%E9%87%8D%E5%AE%9A%E5%90%91&rsv_spt=1&rsv_iqid=0xe4c6f4ba0004188b&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=18&rsv_sug1=1&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&inputT=4755&rsv_sug4=5267");
                })
                .GET("/tohello", (req, resp) -> {
                    resp.redirect("/hello");
                })
                .GET("/file", (req, resp) -> {
                    resp.contentDisposition("qwe.exe")
                            .file("C:\\Users\\98065\\Downloads\\VSCodeUserSetup-x64-1.64.2.exe");
                })
                .GET("/image", (req, resp) -> {
                    resp.contentType(ContentType.IMAGE_JPEG)
                            .end(faviconIco);
                })
                .GET("/stream", (rep, resp) -> {
                    resp.contentDisposition("vscode.exe")
                            .output(new FileInputStream("C:\\Users\\98065\\Downloads\\VSCodeUserSetup-x64-1.64.2.exe"));
                })
                .GET("/ws", (req, resp) -> {

                    req.upgradeToWebSocket().then(ws -> {
                        ws.registerClose(h -> {
                            System.out.println("close!!!!!!!!");
                        });

                        ws.registerOpen(h -> {
                            System.out.println("open!!!!!!!!");
                        });

                        ws.registerText(wsg -> {
                            System.out.println("text!!!!!!!!");

                            ws.reject("拒绝连接");
                        });
                    });
                })
                .GET("/hello", (req, resp) -> {
                    resp.json("你好-get");
                })
                .POST("/hello", (req, resp) -> resp.json("你好-post"))
                .faviconIco(faviconIco);

        HTTP(8888, router).name("我的http 服务器 !")
                // .log(LogLevel.INFO, ByteBufFormat.HEX_DUMP)
                // .workerCnt(10)
                .dev()
                .bind();
    }
}