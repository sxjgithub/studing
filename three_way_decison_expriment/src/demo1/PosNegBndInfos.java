package demo1;
/**
 * 保存一次分类的正域，负域和边界域的类
 * @author sxj
 *
 */
public class PosNegBndInfos {
	private DataInfo[] posInfo;
	private DataInfo[] negInfo;
	private DataInfo[] bndInfo;
	
	
	
	
	public PosNegBndInfos(DataInfo[] posInfo, DataInfo[] negInfo,
			DataInfo[] bndInfo) {
		super();
		this.posInfo = posInfo;
		this.negInfo = negInfo;
		this.bndInfo = bndInfo;
	}




	public DataInfo[] getPosInfo() {
		return posInfo;
	}




	public void setPosInfo(DataInfo[] posInfo) {
		this.posInfo = posInfo;
	}




	public DataInfo[] getNegInfo() {
		return negInfo;
	}




	public void setNegInfo(DataInfo[] negInfo) {
		this.negInfo = negInfo;
	}




	public DataInfo[] getBndInfo() {
		return bndInfo;
	}




	public void setBndInfo(DataInfo[] bndInfo) {
		this.bndInfo = bndInfo;
	}




	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n负域数据为：\n");
		for(int i = 0; i < negInfo.length; i++){
			sb.append(negInfo[i].getIndex()).append("|").append(negInfo[i].getValue()).append("   ");
			if(i != 0 && i % 5 == 0 ){
				sb.append("\n");
			}
		}
		
		sb.append("\n边界域数据为：\n");
		for(int i = 0; i < bndInfo.length; i++){
			sb.append(bndInfo[i].getIndex()).append("|").append(bndInfo[i].getValue()).append("   ");
			if(i != 0 && i % 5 == 0 ){
				sb.append("\n");
			}
		}
		
		sb.append("\n正域数据为：\n");
		for(int i = 0; i < posInfo.length; i++){
			sb.append(posInfo[i].getIndex()).append("|").append(posInfo[i].getValue()).append("   ");
			if(i != 0 && i % 5 == 0 ){
				sb.append("\n");
			}
		}
		
		
		return sb.toString();
	}
	
}
