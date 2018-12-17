package demo5_AIO;

public interface OSHandler {
	abstract AsynSocketChannel acceptHandler(AsynServerSocketChannel channel);
	
}
