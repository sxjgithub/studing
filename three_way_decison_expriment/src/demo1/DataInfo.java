package demo1;

/**
 * ��¼�����е������ݵ����ݺ��±�
 * @author sxj
 *
 */
public class DataInfo {
	private float value ;
	private int index;
	public DataInfo(float value, int index) {
		super();
		this.value = value;
		this.index = index;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
