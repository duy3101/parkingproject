package uwb.parkingproject.model;

public class ReturnType {
    private String str1;
	private String str2;
    private String str3;
    private String str4;
	private String str5;

    public ReturnType(String s1) {
		this.str1 = s1;
	}

    public ReturnType(String s1, String s2) {
		this.str1 = s1;
		this.str2 = s2;
	}


	public ReturnType(String s1, String s2, String s3) {
		this.str1 = s1;
		this.str2 = s2;
		this.str3 = s3;
	}

    public ReturnType(String s1, String s2, String s3, String s4) {
		this.str1 = s1;
		this.str2 = s2;
        this.str3 = s3;
        this.str4 = s4;
	}

    public ReturnType(String s1, String s2, String s3, String s4, String s5) {
		this.str1 = s1;
		this.str2 = s2;
        this.str3 = s3;
        this.str4 = s4;
        this.str5 = s5;
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
    
    public String getStr4() {
		return str4;
	}

	public void setStr4(String s4) {
		this.str4 = s4;
    }
    
    public String getStr5() {
		return str5;
	}

	public void setStr5(String s5) {
		this.str5 = s5;
	}
}