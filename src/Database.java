import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */


public class Database {
	
	private String path;
	private String[] accounts;
	
	public Database(String path) throws FileNotFoundException, IOException {
		this.path = path;
		this.accounts = getAllAccounts();
	}
	
	/**
	 * Initializes the database with all accounts.
	 * 
	 * @return an array of all accounts
	 */
	
	public String[] getAllAccounts() throws FileNotFoundException, IOException {
		int count = 0;
		String[] accounts = new String[10];
		
		FileReader altered = null;
		InputStreamReader original = null;
		try {
			altered = new FileReader(System.getProperty("user.dir") + File.separator + path);			
		} catch (FileNotFoundException e) {
			original = new InputStreamReader(getClass().getResourceAsStream(path));
		}
		
		try (BufferedReader br = new BufferedReader(original != null ? original : altered)) {
			String line;
			
			while ((line = br.readLine()) != null) {
				if (count >= accounts.length) {
					accounts = Arrays.copyOf(accounts, accounts.length + 10);
				}
				accounts[count++] = line;
			}
		}
		
		return Arrays.copyOf(accounts, count);
	}
	
	/**
	 * Retrieves an account by account number.
	 * 
	 * @param accountNumber the account number of the account to retrieve
	 * @return a BankAccount
	 */
	
	public BankAccount getAccount(long accountNumber) {
		for (String account : accounts) {
			if (account.startsWith(String.valueOf(accountNumber)) && account.endsWith("Y")) {
				return new BankAccount(Long.parseLong(account.substring(0,9)), new User(account.substring(45, 60), account.substring(25, 45), Integer.parseInt(account.substring(9, 13)), account.substring(60, 68), Long.parseLong(account.substring(68, 78)), account.substring(78, 108), account.substring(108, 138),account.substring(138, 140),  account.substring(140, 148)), Double.parseDouble(account.substring(13, 25)), account.substring(148, 149));
			}
		}
		System.out.println("returning null");
		return null;
	}
	
	/**
	 * Updates a BankAccount.
	 * 
	 * @param account the primary account being updated
	 * @param destination the secondary account being updated
	 * @throws IOException 
	 */
	
	public void updateAccount(BankAccount account, BankAccount destination) throws IOException {
		boolean newAccount = true;
		
		for (int i = 0; i < accounts.length; i++) {	
			if (Long.parseLong(accounts[i].substring(0,9)) == account.getAccountNumber()) {
				accounts[i] = account.toString();
				newAccount = false;
			}
			
			if (destination != null) {
				if (accounts[i].startsWith(String.valueOf(destination.getAccountNumber()))) {
					accounts[i] = account.toString();
				}
			}
		}
		
		if (newAccount) {
			accounts = Arrays.copyOf(accounts, accounts.length + 1);
			accounts[accounts.length - 1] = account.toString();
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + path))) {
			for (String acct : accounts) {
				//System.out.println(acct);
				bw.write(acct);
				bw.newLine();
			}
		}
	}
	
	/**
	 * Retrieves the largest account number in the database.
	 * 
	 * @return the largest account number
	 */
	
	public long getMaxAccountNumber() {
		long max = -1L;
		
		for (String account : accounts) {
			long accountNumber = Long.parseLong(account.substring(0, 9));
			
			if (accountNumber > max) {
				max = accountNumber;
			}
		}
		
		return max;
	}
}