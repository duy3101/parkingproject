package uwb.parkingproject.model;

public class Fruit {
    private String str1;
	private String str2;
	private String str3;

	public Fruit(String s1, String s2, String s3) {
		this.str1 = s1;
		this.str2 = s2;
		this.str3 = s3;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String s1) {
		this.str1 = s1;

    }
    
    public String getStr2() {
		return str2;
	}

	public void setStr2(String s2) {
		this.str2 = s2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String s3) {
		this.str3 = s3;
	}
}