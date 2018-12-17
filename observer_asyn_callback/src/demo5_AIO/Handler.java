package demo5_AIO;

public interface Handler<V,A> {
	void completed(V values, A attachment);
	void fail();
}
