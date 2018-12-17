package demo4_AIO;

public interface Handler<T,V> {
	void completed(T t, V v);
	void fail();
}
