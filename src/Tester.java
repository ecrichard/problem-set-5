import java.io.IOException;

/**
 * This class has only one responsibility: start the ATM program!
 */

public class Tester {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		ATM atm;

		try {
			atm = new ATM();
			atm.welcome();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Database db = new Database();
		//try {
		//	db.getAccount(1L);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//.printStackTrace();
		//}
		/*
		 * Rather than hard coding one or more BankAccount objects, you'll need to read them in
		 * from our very primitive database (i.e., a flat-file). After making changes, of course,
		 * you'll need to update the database accordingly.
		 */
	}
}