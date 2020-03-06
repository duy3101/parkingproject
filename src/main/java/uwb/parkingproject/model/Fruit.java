package uwb.parkingproject.model;

public class Fruit {
    private String fruit;
	private String num;
	private String str;

	public Fruit(String fruit, String num, String str) {
		this.fruit = fruit;
		this.num = num;
		this.str = str;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
    }
    
    public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}