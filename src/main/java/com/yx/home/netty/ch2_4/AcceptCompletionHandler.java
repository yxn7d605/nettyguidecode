package com.yx.home.netty.ch2_4;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 处理新的socket客户端连接
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        // 继续接收新的连接
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        // 分配1K的内存空间
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 异步读操作，buffer作为attachment传递到ReadCompletionHandler，ReadCompletionHandler处理读操作
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    /**
     * 如果处理失败则打印异常，并且调用countDown来终止主线程
     *
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
