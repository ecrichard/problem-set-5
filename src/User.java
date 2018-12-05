import java.util.Scanner;
//import java.text.DecimalFormat;
/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class User {
	static Scanner sc = new Scanner(System.in);
	int menuNumber = 0;
	private String firstN;
	private String lastN;
	private int pin;
	private String dateOB;
	private long phoneN;
	private String streetAddress;
	private String city;
	private String state;
	private String postalCode;
	public User(String firstN, String lastN, int pin, String dateOB, long phoneN, String streetAddress, String city, String state, String postalCode) {
		this.firstN = firstN;
		this.lastN = lastN;
		this.pin = pin;
		this.dateOB = dateOB;
		this.phoneN = phoneN;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	public String getFirstN() {
		return firstN;
	}
	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}
	public String getLastN() {
		return lastN;
	}
	public void setLastN(String lastN) {
		this.lastN = lastN;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getDateOB() {
		return dateOB;
	}
	public void setDateOB(String dateOB) {
		this.dateOB = dateOB;
	}
	public long getPhoneN() {
		return phoneN;
	}
	public void setPhoneN(long phoneN) {
		this.phoneN = phoneN;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void showEditMenu() {
		System.out.println("What do you want to edit\n	Pin(1)\n 	Phone Number(2)\n 	Street Address(3)\n 	City(4)\n	State(5)\n	Postal code(6)\n	Quit editing(7)");
	}
	public void edit() {
		while(menuNumber != 7) {
			showEditMenu();
			menuNumber = sc.nextInt();
			while (!(menuNumber == 1 || menuNumber == 2 || menuNumber == 3 || menuNumber == 4 || menuNumber == 5 || menuNumber == 6 || menuNumber == 7)){
				System.out.println("please enter a valid menu number.");
				showEditMenu();
				menuNumber = sc.nextInt();
			}
			if (menuNumber == 1) {
				//pin
				System.out.println("Please enter your current pin twice.");
				sc.nextInt();
				int pin = sc.nextInt();
				if (pin == getPin()){
					System.out.println("Please enter your new pin twice.");
					String newpin = sc.nextLine();
					int length = newpin.length();
					while(length != 4) {
						System.out.println("please enter a 4 digit pin.");
						sc.nextLine();
						newpin = sc.nextLine();
						length = String.valueOf(newpin).length();
					}
					setPin(Integer.parseInt(newpin));
				}
			}
			else if (menuNumber == 2) {
				//phone number
				System.out.println("What is your new phone number?");
				long newpnumber = sc.nextLong();
				int length = String.valueOf(newpnumber).length();
				while(length != 10) {
					System.out.println("please enter a 10 digit phone number");
					newpnumber = sc.nextLong();
					length = String.valueOf(newpnumber).length();
				}
				setPhoneN(newpnumber);
			}
			else if (menuNumber == 3) {
				//street address
				System.out.println("What is your new street address?");
				String newAddress = sc.nextLine();
				newAddress = sc.nextLine();
				int length = newAddress.length();
				while (length > 30) {
					System.out.println("please enter an address 30 characters or less");
					newAddress = sc.nextLine();
					length = String.valueOf(newAddress).length();
				}
				setStreetAddress(newAddress);
			}
			else if(menuNumber == 4) {
				//city
				System.out.println("What is your new city address?");
				String newAddress = sc.nextLine();
				newAddress = sc.nextLine();
				int length = newAddress.length();
				while (length > 30) {
					System.out.println("please enter a city 30 characters or less");
					newAddress = sc.nextLine();
					length = String.valueOf(newAddress).length();
				}
				setCity(newAddress);
			}
			else if(menuNumber == 5) {
				//state
				System.out.println("What is your new state address?");
				String newAddress = sc.nextLine();
				newAddress = sc.nextLine();
				int length = newAddress.length();
				while (length != 2) {
					System.out.println("please enter a 2 character state abbreviation.");
					newAddress = sc.nextLine();
					length = String.valueOf(newAddress).length();
				}
				setState(newAddress);
			}
			else if(menuNumber == 6) {
				//postal code
				System.out.println("What is your new postal code?");
				String newpcode = sc.nextLine();
				newpcode = sc.nextLine();
				int length = String.valueOf(newpcode).length();
				while(length != 5) {
					System.out.println("please enter a 5 character postal code.");
					newpcode = sc.nextLine();
					length = newpcode.length();
				}
				setPostalCode(newpcode);
			}
			else if(menuNumber == 7) {
				break;
			}
		}
		
	}
	public void personal() {
		System.out.println("Name:        " + getLastN() + ", " + getFirstN());
		System.out.println("Pin:         " + getPin());
		String out = getDateOB().substring(0,4) + "-" + getDateOB().substring(4,6) + "-" + getDateOB().substring(6);
		System.out.println("Date of Birth:   " + out );
		System.out.println("Address:     " + getStreetAddress() + ", " + getCity() + ", " + getState() + ", " + getPostalCode());
	}
}