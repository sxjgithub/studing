package bridge1;

/**
 * Ʒ�ƽӿ�
 * @author sxj
 *
 */
public interface Brand { 
	void info();
}	


class LenovelBrand implements Brand{

	@Override
	public void info() {
		System.out.println("������");
	}	
}

class DellBrand implements Brand{

	@Override
	public void info() {
		System.out.println("������");
	}	
}