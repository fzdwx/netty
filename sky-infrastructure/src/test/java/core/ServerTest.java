package core;

import io.github.fzdwx.lambada.Seq;
import io.github.fzdwx.lambada.Threads;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.logging.LogLevel;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/5/6 15:44
 */
class ServerTest {

    @Test
    void test_server() {
        final Server s = new Server()
                .withGroup(0, 0)
                .withLog(LogLevel.INFO)
                .withInitChannel(ch -> {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(final ChannelHandlerContext ctx) throws Exception {
                            final ByteBufAllocator alloc = ctx.alloc();
                            Seq.range(2)
                                    .forEach(i -> {
                                        ctx.writeAndFlush(Netty.wrap(alloc, i + ""));
                                        Threads.sleep(Duration.ofMillis(100));
                                    });
                        }
                    });
                })
                .listen(8888);

        s.dispose();
    }
}