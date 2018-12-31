package distrubuted_lock_zookeeper;

public class WatchTest {
	private static String ZOOKEEPER_IP_PORT = "localhost:2181";
	
	private ZkClient client = new ZKClient(ZOOKEEPER_IP_PORT, sessionTimeout:1000, connectionTimeout:1000, new Serializable);
	
	@Test
	public void watchTest() throws Exception{
		
		//1.创建一个持久结点
		String path = "/watcher";
		client.createPersistent(path);
		
		//2.实例化一个监听器
		IZkDataListener listener = new IZDataListener() {
			public void handleDataDeleted(String dataPath) throws Exception{
				//捕获到结点被删除的事件
				System.out.println("收到结点被删除事件，被删除的结点为" + dataPath);
			}
			
			public void handleDataChange(String dataPath, Object data) throws Exception{
				
			}
		};
		
		//3.给该节点增加监听器
		this.client.subscribeDataChanges(path, listener);
		Thread.currentThread().join();
	}
}
