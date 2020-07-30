package com.example.demo.Netty;

public class nettyTest {
/*    //socket连接处理器
    static class EchoServerHandler extends
            ChannelInboundHandlerAdapter {
        //处理读事件
        @Override
        public void channelRead(
                ChannelHandlerContext ctx, Object msg){
            ctx.write(msg);
        }
        //处理读完成事件
        @Override
        public void channelReadComplete(
                ChannelHandlerContext ctx){
            ctx.flush();
        }
        //处理异常事件
        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx,  Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
    public static void main(String args[]) {
        final EchoServerHandler serverHandler
                = new EchoServerHandler();
//boss线程组
        EventLoopGroup bossGroup
                = new NioEventLoopGroup(1);
//worker线程组
        EventLoopGroup workerGroup
                = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch){
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //bind服务端端口
            ChannelFuture f = b.bind(9090).sync();
            f.channel().closeFuture().sync();
        } finally {
            //终止工作线程组
            workerGroup.shutdownGracefully();
            //终止boss线程组
            bossGroup.shutdownGracefully();
        }


    }
*/
}
