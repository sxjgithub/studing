package bridge1;

/**
 * 品牌接口
 * @author sxj
 *
 */
public interface Brand { 
	void info();
}	


class LenovelBrand implements Brand{

	@Override
	public void info() {
		System.out.println("联想牌");
	}	
}

class DellBrand implements Brand{

	@Override
	public void info() {
		System.out.println("戴尔牌");
	}	
}