package demo2;
/**
 * 两个阈值的下标
 * @author sxj
 *
 */
public class ThresholdIndex {
	private int Firstmax;
	private int secondMax;
	public int getFirstmax() {
		return Firstmax;
	}
	
	public ThresholdIndex(int firstmax, int secondMax) {
		super();
		Firstmax = firstmax;
		this.secondMax = secondMax;
	}

	public void setFirstmax(int firstmax) {
		Firstmax = firstmax;
	}
	public int getSecondMax() {
		return secondMax;
	}
	public void setSecondMax(int secondMax) {
		this.secondMax = secondMax;
	}
	
}
