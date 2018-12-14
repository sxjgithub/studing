package mediator1;

import java.util.HashMap;
import java.util.Map;

public class President implements Mediator{
	//说白了，就是中介者里有 部门名称 和 部门对象 的map。然后根据部门名称找到部门对象，调用部门名称的方法。
	private Map<String, Department> map = new HashMap<String, Department>();
	
	
	@Override
	public void register(String dname, Department d) {
		map.put(dname, d);
		
	}

	@Override
	public void command(String dname) {
		map.get(dname).selfAction();	
	}

}
