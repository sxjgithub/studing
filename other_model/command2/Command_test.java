package command2;

import java.util.ArrayList;
import java.util.List;


/*Female �������� (order)����������Man ���ܶ���
 * */
public class Command_test {
	public static void main(String[] args) {
		Female f = new Female("С��") ;
		Man m = new Man() ;
		f.order(m);
	}
}


class Female{
	private String name ;
	public Female(String name) {
		this.name = name ;
	}
	
	public void order(Man man){
		//man.doThing1() ;  
	//��ʱ����Կ�������һ��Command��
		Command c1 = new ShoppingCommand() ;
		man.addCommand(c1) ;
		Command c2 = new CleanCommand();
		man .addCommand(c2);  //��������
		
		man.doCommands();  //ִ����������
	}
}

class Man{
	List<Command> commands = new ArrayList<Command>() ;  //����һϵ��Command
	
	public void addCommand(Command c){
		commands.add(c) ;
	}
	
	public void doCommands(){
		for(Command c: commands){
			c.dothing();
		}
	}
	
	public void undoCommand(){}
}

//�����Ǿ����������
class ShoppingCommand extends Command {  //�̳�Command��
	@Override
	public void dothing() {
		System.out.println("shopping") ;
		
	}
	@Override
	public void undo() {
		System.out.println("no shopping");
		
	}
}
class CleanCommand extends Command{
	@Override
	public void dothing() {
		System.out.println("cleanning") ;
	}
	@Override
	public void undo() {
		System.out.println("no clean") ;
	}
}
