package command2;

import java.util.ArrayList;
import java.util.List;


/*Female 可以命令 (order)她的男朋友Man 做很多事
 * */
public class Command_test {
	public static void main(String[] args) {
		Female f = new Female("小乔") ;
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
	//这时候可以考虑增加一个Command类
		Command c1 = new ShoppingCommand() ;
		man.addCommand(c1) ;
		Command c2 = new CleanCommand();
		man .addCommand(c2);  //增加命令
		
		man.doCommands();  //执行所有命令
	}
}

class Man{
	List<Command> commands = new ArrayList<Command>() ;  //接收一系列Command
	
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

//以下是具体的命令类
class ShoppingCommand extends Command {  //继承Command类
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
