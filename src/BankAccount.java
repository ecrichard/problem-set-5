//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transferring funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {
	static Scanner sc = new Scanner(System.in);
	DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
	private long accountNumber;
	private User accountHolder;
	private double accountBalance;
	private String status;
	
	public BankAccount (long accountNumber, User accountHolder, double accountBalance, String status) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.accountBalance = accountBalance;
		this.status = status;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public User getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(User accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void withdraw(double amount) {
		while(!(amount <= accountBalance)) {
			System.out.println("Cannot withdraw more money than in the account. Please input a amount less than or equal to " + accountBalance);
			System.out.print("$ ");
			amount = sc.nextDouble();
		}
		while(amount < 0) {
			System.out.println("please input a positive deposit amount");
			System.out.print("$ ");
			amount = sc.nextDouble();
		}
		accountBalance = accountBalance - amount;
		System.out.println("Your new balance is: $" + df2.format(accountBalance));
	}
	public void deposit(double amount) {
		while(amount < 0) {
			System.out.println("please input a positive deposit amount");
			System.out.print("$ ");
			amount = sc.nextDouble();
		}
		accountBalance = accountBalance + amount;
		System.out.println("Your new balance is: $" + df2.format(accountBalance));
	}
	public void transfer(double amount, BankAccount dest) throws NumberFormatException, IOException {
		
		this.withdraw(amount);
		dest.deposit(amount);	
	}
	public void balance() {
		System.out.println("Your current balance is: $" + df2.format(accountBalance));
	}
	public void close() {
		System.out.println("To confirm you want to close the account enter: N. If you don't want to close the account enter: Y");
		String status = sc.nextLine();
		while(!(status.equals("N") || status.equals("Y"))) {
			System.out.println("To confirm you want to close the account enter: N. If you don't want to close the account enter: Y");
			status = sc.nextLine();
		}
		setStatus(status);
	}
	
	@Override
	public String toString() {
		return String.format("%09d%04d%-1.2f%-20s%-15s%8s%10d%-30s%-30s%2s%5s%s", accountNumber, accountHolder.getPin(), accountBalance, accountHolder.getLastN(), accountHolder.getFirstN(), accountHolder.getDateOB(), accountHolder.getPhoneN(), accountHolder.getStreetAddress(), accountHolder.getCity(), accountHolder.getState(), accountHolder.getPostalCode(), status);
	}
	
	
//	public void update_db(long accountNumber) throws IOException {
//		String newAccountt = "                                                                                                                                                     ";
//		StringBuilder builder = new StringBuilder(newAccountt);
//		builder.insert(0, getAccountNumber());
//		builder.insert(9, getAccountHolder().getPin());
//		builder.insert(25, getAccountHolder().getLastN());
//		builder.insert(45, getAccountHolder().getFirstN());
//		builder.insert(60, getAccountHolder().getDateOB());
//		builder.insert(68, getAccountHolder().getPhoneN());
//		builder.insert(78, getAccountHolder().getStreetAddress());
//		builder.insert(108, getAccountHolder().getCity());
//		builder.insert(138, getAccountHolder().getState());
//		builder.insert(140, getAccountHolder().getPostalCode());
//		builder.insert(13, getAccountBalance());
//		builder.insert(148, getStatus());
//		String updated_line = builder.toString();
//		
//		
//	}
}