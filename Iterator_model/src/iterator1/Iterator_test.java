package iterator1;

//设计一个容器，该容器有增加数据的功能
public class Iterator_test {
	public static void main(String[] args) {
		//1.先站在使用者的角度考虑
		Cat cat1 = new Cat("cat1", 13) ;
		Cat cat2 = new Cat("cat2", 24) ;
		ArrayList alist = new ArrayList();
		alist.add1(cat1).add1(cat2) ;
		
		System.out.println(alist.size());
		
//现在还想要设计一个以链表为容器，怎么设计呢？
		
		LinkedList llist = new LinkedList() ;
		llist.add2(cat1).add2(cat2) ;
		System.out.println(llist.size()) ;
		
//现在确实又设计了一个LinkedList容器，但是ArrayList 和LinkedList两个容器增加方法名不一样，
//替换相关容器的时候还要修改方法名代码，有没有好方法，使容器的方法名统一？ 想到定义接口，利用多态性质
	}
}
class Cat{
	private String name ;
	private int weight ;
	public Cat(String name, int weight) {
		this.name = name ;
		this.weight = weight ;
	}
}

class ArrayList{
	private Object[] list = new Object[10] ;
	private int count = 0 ;
	
	public ArrayList add1(Object o){
		if(count >= this.list.length){
			Object[] newList = new Object[this.list.length * 2] ;
			System.arraycopy(this.list, 0, newList, 0, this.list.length);
			this.list = newList ;
		}
		this.list[this.count ++] = o ;
		return this ;
	}
	
	public int size(){
		return this.count;
	}
}


class LinkedList { 
	private Node head = null ;
	private Node tail = null ;
	private int count = 0 ;
	
	public LinkedList add2(Object o) {
		Node newNode = new Node(o, null) ;
		if(this.head == null){
			this.head = newNode ;
			this.tail = newNode ;
		}else{
			this.tail.setNextNode(newNode);
			this.tail = newNode ;
		}
		this.count ++ ;
		return this ;
	}
	
	public int size(){
		return this.count ;
	}
	
}

class Node{
	private Object object ;
	private Node nextNode ;
	
	public Node(Object o, Node next) {
		this.object = o ;
		this.nextNode = next ;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}

