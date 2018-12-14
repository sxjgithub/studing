package mediator1;
/**
 * 中介者模式，    
 * @author sxj
 *
 */
public class Test {
	public static void main(String[] args) {
		Mediator m = new President(); //中介者
		Finacial finacial = new Finacial(m);
		Development development = new Development(m);
		
		development.outAction();
		
	}
}
