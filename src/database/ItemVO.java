package database;

public class ItemVO {
	/* 상품 정보를 저장할 멤버들과 멤버에 접근하는 메서드들 가지는 클래스 */
	private String iname = null;			// 상품명
	private String iprice = null;			// 가격
	private String istore = null;			// 판매 지점
	private String imanufacturer = null;	// 제조사
	private String iamount = null;			// 중량/부피
	private String iquantity = null;		// 개수
	private String iregisterdate = null;	// 등록일
	private boolean isbookmark = false;		// 즐겨찾기 설정 여부
	
	// 상품명
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
 
	// 가격
	public String getIprice() {
		return iprice;
	}
	public void setIprice(String iprice) {
		this.iprice = iprice;
	}

	// 판매 지점
	public String getIstore() {
		return istore;
	}
	public void setIstore(String istore) {
		this.istore = istore;
	}
	
	// 제조사
	public String getImanufacturer() {
		return imanufacturer;
	}
	public void setImanufacturer(String imanufacturer) {
		this.imanufacturer = imanufacturer;
	}

	// 중량/부피
	public String getIamount() {
		return iamount;
	}
	public void setIamount(String iamount) {
		this.iamount = iamount;
	}
	
	// 개수
	public String getIquantity() {
		return iquantity;
	}
	public void setIquantity(String iquantity) {
		this.iquantity = iquantity;
	}
	
	// 등록일
	public String getIregisterdate() {
		return iregisterdate;
	}
	public void setIregisterdate(String iregisterdate) {
		this.iregisterdate = iregisterdate;
	}

	// 즐겨찾기 설정 여부
	public boolean getIsbookmark() {
		return isbookmark;
	}
	public void setIsbookmark(boolean isbookmark) {
		this.isbookmark = isbookmark;
	}
}
