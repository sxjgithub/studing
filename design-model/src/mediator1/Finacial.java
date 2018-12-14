package mediator1;

public class Finacial implements Department{
	private Mediator m;
	
	
	public Finacial(Mediator m) {
		super();
		this.m = m;
		m.register("finacial", this);
	}

	@Override
	public void selfAction() {
		System.out.println("财务部对外汇报，数钱数到手软!");
		
	}

	@Override
	public void outAction() {
		System.out.println("财务部对内汇报，汇报工作，钱太多了，怎么花！");
		
	}

}
