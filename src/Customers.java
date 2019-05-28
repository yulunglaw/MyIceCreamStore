
public class Customers {

	private String name;
	private String password;
	private int membershipID;
	
	public Customers(String name, String password, int membershipID) {
		this.name = name;
		this.password = password;
		this.membershipID = membershipID;
	}

	public String getName() {
		return name;
	}

	public int getMembershipID() {
		return membershipID;
	}
}
