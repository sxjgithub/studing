package iterator2;

public class Iterator_test {
	public static void main(String[] args) {
		Collection c = new ArrayList() ; //通过接口，多态，现在方法统一了
		for(int i = 0; i < 10; i++){
			c.add(new Cat(String.valueOf(i), i)) ;
		}
		System.out.println(c.size());
		
//现在考虑容器的遍历，首先站在使用者的角度设计(模拟java自带的)
		Iterator iter = c.iterator() ;  //这样该怎样设计呢？
		while(iter.hasNext()){
			System.out.print(iter.next() + "  ") ;
		}
	}
}

class Cat{
	private String name ;
	private int weight ;
	public Cat(String name, int weight) {
		this.name = name ;
		this.weight = weight ;
	}
	
	public String toString(){
		return this.name + "|" + this.weight ;
	}
}

class ArrayList implements Collection{
	private Object[] list = new Object[10] ;
	private int count = 0 ;
	
	@Override
	public ArrayList add(Object o){
		if(count >= this.list.length){
			Object[] newList = new Object[this.list.length * 2] ;
			System.arraycopy(this.list, 0, newList, 0, this.list.length);
			this.list = newList ;
		}
		this.list[this.count ++] = o ;
		return this ;
	}
	
	@Override
	public int size(){
		return this.count;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator() ;
	}
	
	//private Iterator = new ArrayListIterator
	class ArrayListIterator implements Iterator{//使用内部类,因为Iterator
		//要有Collection里面的数据，如果写成外部类，还要单独传Collection对象给Iterator对象
		//使用内部类使 ArrayListIterator可以共享Collection对象里的数据
		//好好体会-------------------
		int index = 0 ;
		@Override
		public Boolean hasNext() {
			if(index < count){
				return true ;
			}
			return false ;
		}

		@Override
		public Object next() {			
			return list[index ++];
		}	
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}

class LinkedList implements Collection { 
	private Node head = null ;
	private Node tail = null ;
	private int count = 0 ;
	
	@Override
	public LinkedList add(Object o) {
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
	//----------------------------------------------------------------------
	public void print(){
		Node pNode = head ;
		while(pNode != null){
			System.out.print(pNode.getObject() + "  ") ;
			pNode =pNode.getNextNode() ;
		}
	}
	
	public int size(){
		return this.count ;
	}
	
	public Iterator iterator(){
		return new LinkedListIterator() ;
	}
	
	class LinkedListIterator implements Iterator{
		private int index = 0 ;
		Node p = head ;
		@Override
		public Boolean hasNext() {
			
			if(this.index < count){
				return true ;
			}
			return false ;
		
		}

		@Override
		public Object next() {
			index ++ ;
			Node q = p ;
			p = p.getNextNode() ;
			return q.getObject() ;
		}
		
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
