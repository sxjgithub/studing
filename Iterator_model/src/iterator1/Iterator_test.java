package iterator1;

//���һ�����������������������ݵĹ���
public class Iterator_test {
	public static void main(String[] args) {
		//1.��վ��ʹ���ߵĽǶȿ���
		Cat cat1 = new Cat("cat1", 13) ;
		Cat cat2 = new Cat("cat2", 24) ;
		ArrayList alist = new ArrayList();
		alist.add1(cat1).add1(cat2) ;
		
		System.out.println(alist.size());
		
//���ڻ���Ҫ���һ��������Ϊ��������ô����أ�
		
		LinkedList llist = new LinkedList() ;
		llist.add2(cat1).add2(cat2) ;
		System.out.println(llist.size()) ;
		
//����ȷʵ�������һ��LinkedList����������ArrayList ��LinkedList�����������ӷ�������һ����
//�滻���������ʱ��Ҫ�޸ķ��������룬��û�к÷�����ʹ�����ķ�����ͳһ�� �뵽����ӿڣ����ö�̬����
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

