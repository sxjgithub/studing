package mediator1;

public class Development implements Department{
	
	private Mediator m; //持有中介者（总经理）的引用
	
	
	public Development(Mediator m) {
		super();
		this.m = m;
		m.register("development", this);
	}

	@Override
	public void selfAction() {
		System.out.println("科研部对内汇报，专心科研，开发项目");
		
	}

	@Override
	public void outAction() {
		System.out.println("科研部对外汇报工作，没钱了，需要资金支持！");
		
		m.command("finacial"); //通过中介者联系财务部
	}

}

