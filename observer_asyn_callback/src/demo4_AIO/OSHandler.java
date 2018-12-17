package demo4_AIO;

public abstract class OSHandler {
	abstract AsynSocketChannel acceptHandler(AsynServerSocketChannel channel);
	
}
