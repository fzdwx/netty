package http;

import core.Server;
import core.Transport;
import http.ext.HttpExceptionHandler;
import http.ext.HttpRequestConsumer;
import io.github.fzdwx.lambada.fun.Hooks;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import serializer.JsonSerializer;

import java.net.InetSocketAddress;

/**
 * http server implementation
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 16:19
 */
public class HttpServer implements Transport<HttpServer> {

    private final Server server;
    private boolean sslFlag;
    private HttpDataFactory httpDataFactory;
    private HttpRequestConsumer consumer;
    private HttpExceptionHandler exceptionHandler;

    private HttpServer() {
        this.server = new Server();
    }

    public static HttpServer create() {
        return new HttpServer();
    }

    /**
     * @see #withGroup(EventLoopGroup, EventLoopGroup)
     */
    public HttpServer withGroup(final int bossCount, final int workerCount) {
        this.server.withGroup(bossCount, workerCount);
        return this;
    }

    /**
     * set event loop group
     */
    public HttpServer withGroup(final EventLoopGroup boss, final EventLoopGroup worker) {
        this.server.withGroup(boss, worker);
        return this;
    }

    /**
     * add server handler
     */
    public HttpServer withServerHandler(ChannelHandler handler) {
        this.server.withServerHandler(handler);
        return this;
    }

    /**
     * ser child log handler
     */
    public HttpServer withLog(final LoggingHandler loggingHandler) {
        this.server.withLog(loggingHandler);
        return this;
    }

    @Override
    public HttpServer withInitChannel(final Hooks<SocketChannel> hooks) {
        this.server.withInitChannel(hooks);
        return this;
    }

    @Override
    public ChannelInitializer<SocketChannel> channelInitializer() {
        return server.channelInitializer();
    }

    @Override
    public HttpServer withWorker(final EventLoopGroup worker) {
        this.server.withWorker(worker);
        return this;
    }

    /**
     * @see #withLog(LoggingHandler)
     */
    public HttpServer withLog(final LogLevel logLevel) {
        return this.withLog(new LoggingHandler(logLevel));
    }

    /**
     * enable ssl.
     */
    public HttpServer withSsl(final SslHandler sslHandler) {
        this.server.withSsl(sslHandler);
        this.sslFlag = true;
        return this;
    }

    /**
     * add server option
     */
    public <T> HttpServer withServerOptions(ChannelOption<T> option, T t) {
        this.server.withServerOptions(option, t);
        return this;
    }

    /**
     * add child option
     */
    public <T> HttpServer withChildOptions(ChannelOption<T> option, T t) {
        this.server.withChildOptions(option, t);
        return this;
    }

    /**
     * set http data factory
     */
    public HttpServer withHttpDataFactory(final HttpDataFactory factory) {
        this.httpDataFactory = factory;
        return this;
    }

    /**
     * default is {@link serializer.JsonSerializer#codec}
     */
    public HttpServer withSerializer(final JsonSerializer serializer) {
        this.server.withSerializer(serializer);
        return this;
    }

    /**
     * handler request and return response.
     */
    public HttpServer handle(final HttpRequestConsumer consumer) {
        this.consumer = consumer;
        return this;
    }

    /**
     * handler exception.
     */
    public HttpServer exceptionHandle(HttpExceptionHandler h) {
        this.exceptionHandler = h;
        return this;
    }

    /**
     * after start.
     */
    public HttpServer afterStart(Hooks<ChannelFuture> hooks) {
        this.server.afterStart(hooks);
        return this;
    }

    @Override
    public HttpServer start(final InetSocketAddress address) {
        this.withServerOptions(ChannelOption.SO_BACKLOG, 1024);
        this.withChildOptions(ChannelOption.TCP_NODELAY, true);
        this.withChildOptions(ChannelOption.SO_KEEPALIVE, true);

        this.server.withInitChannel(channel -> {
            channel.pipeline()
                    .addLast(new HttpServerCodec())
                    .addLast(new HttpObjectAggregator(1024 * 1024))
                    .addLast(new ChunkedWriteHandler()).addLast(new HttpServerExpectContinueHandler())
                    .addLast(new HttpServerHandler(consumer, exceptionHandler, sslFlag, httpDataFactory, serializer()));
        }).start(address);

        return this;
    }

    /**
     * start server
     */
    public HttpServer start(final int port) {
        return this.start(new InetSocketAddress(port));
    }

    public ChannelFuture dispose() {
        return this.server.dispose();
    }

    @Override
    public void close() {
        this.server.close();
    }

    @Override
    public boolean sslFlag() {
        return this.server.sslFlag();
    }

    public void shutdown() {
        this.server.close();
    }

    public String scheme() {
        return this.sslFlag ? "https" : "http";
    }

    public int port() {
        return this.server.port();
    }

    public JsonSerializer serializer() {
        return this.server.serializer();
    }

    @Override
    public HttpServer impl() {
        return this;
    }
}