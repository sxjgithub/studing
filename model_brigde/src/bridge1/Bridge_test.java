package bridge1;



public class Bridge_test {
	public static void main(String[] args) {
		Computer c = new Laptop(new DellBrand());
		c.info();
	}
}


