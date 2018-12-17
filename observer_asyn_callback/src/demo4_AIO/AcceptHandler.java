package demo4_AIO;

public class AcceptHandler implements Handler<AsynSocketChannel, AsynServerHandler>{

	@Override
	public void completed(AsynSocketChannel socketChannel, AsynServerHandler serverHandler) {
		//serverHandler.channel.accept(serverHandler, this);
		
		String msg = null;
		socketChannel.read(msg, msg, new ReadHandler(socketChannel));
			
		
	}

	@Override
	public void fail() {
		
	}

}
