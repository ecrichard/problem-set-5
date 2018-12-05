import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class ATM {
	private int menuNumber;
	private Scanner sc;
	private BankAccount bankaccount;
	private BankAccount destination;
	private Database Database;

	public ATM() throws FileNotFoundException, IOException {
		menuNumber = 0;
		sc = new Scanner(System.in);
		Database = new Database("accounts-db.txt");
	}
	
	public void showBasicMenu() {
		System.out.println("	open account(1)\n 	login(2)\n 	quit(3)");
	}
	
	public void showSubMenu() {
		System.out.println("	deposit funds(1)\n 	withdraw funds(2)\n 	transfer funds(3)\n 	view balance(4)\n	view personal information(5)\n	update personal information(6)\n	close account(7)\n	logout(8)");
	}
	
	
	public void newAccount() throws IOException {
		int stopper = 0;
		while(stopper == 0) {
			String newAccountt = "                                                                                                                                                     ";
			BufferedReader reader = new BufferedReader(new FileReader("accounts-db.txt"));
			int lines = 0;
			while (reader.readLine() != null) lines++;
			reader.close();
			long acctNum = 100000000;
			lines += 1;
			acctNum += lines;
			StringBuilder builder = new StringBuilder(newAccountt);
			builder.insert(0, acctNum);
			
		
			System.out.println("what do you want your pin to be?");
			String pinn = sc.nextLine();
			pinn = sc.nextLine();
			int lengthp = pinn.length();
			while(lengthp != 4) {
				System.out.println("please enter a 4 digit pin.");
				pinn = sc.nextLine();
				lengthp = String.valueOf(pinn).length();
			}
			builder.insert(9, pinn);
			
			
			System.out.println("What is your last name?");
			String lastNn = sc.nextLine();
			int lengthl = lastNn.length();
			while(!(lengthl < 31)) {
				System.out.println("please enter a name 20 letters or less.");
				lastNn = sc.nextLine();
				lengthl = String.valueOf(lastNn).length();
			}
			builder.insert(25, lastNn);
			
			
			System.out.println("What is your first name?");
			String firstNn = sc.nextLine();
			int lengthf = firstNn.length();
			while(!(lengthf <= 30)) {
				System.out.println("please enter a name 30 letters or less.");
				firstNn = sc.nextLine();
				lengthf = String.valueOf(firstNn).length();
			}
			builder.insert(45, firstNn);
			
			
			System.out.println("what is your birth day formatted YYYYMMDD?");
			String dateOBb = sc.nextLine();
			int lengthd = dateOBb.length();
			while(!(lengthd == 8)) {
				System.out.println("please enter a birthday in the correct length/format: YYYYMMDD.");
				dateOBb = sc.nextLine();
				lengthd = String.valueOf(dateOBb).length();
			}
			builder.insert(60, dateOBb);
			
			
			System.out.println("what is your phone number?");
			long phoneNn = sc.nextLong();
			int lengthn = String.valueOf(phoneNn).length();
			while(!(lengthn == 10)) {
				System.out.println("please enter a 10 digit phone number.");
				phoneNn = sc.nextLong();
				lengthn = String.valueOf(phoneNn).length();
			}
			builder.insert(68, phoneNn);
			
			
			System.out.println("what is your street address?");
			String streetAddresss = sc.nextLine();
			streetAddresss = sc.nextLine();
			int lengths = streetAddresss.length();
			while(!(lengths < 31)) {
				System.out.println("please enter a street address less that 31 characters.");
				streetAddresss = sc.nextLine();
				lengths = String.valueOf(streetAddresss).length();
			}
			builder.insert(78, streetAddresss);
			
			
			System.out.println("what is your city?");
			String cityy = sc.nextLine();
			int lengthc = cityy.length();
			while(!(lengthc < 31)) {
				System.out.println("please enter a city less that 31 characters.");
				cityy = sc.nextLine();
				lengthc = String.valueOf(cityy).length();
			}
			builder.insert(108, cityy);
			
			
			System.out.println("what is your state?");
			String statee = sc.nextLine();
			int lengthss = statee.length();
			while(!(lengthss == 2)) {
				System.out.println("please enter state 2 letter abbreviation.");
				statee = sc.nextLine();
				lengthss = String.valueOf(statee).length();
			}
			builder.insert(138, statee);
			
			
			System.out.println("what is your postal code?");
			String postalCodee = sc.nextLine();
			int lengthpc = postalCodee.length();
			while(!(lengthpc == 5)) {
				System.out.println("please enter a 5 digit postal code.");
				postalCodee = sc.nextLine();
				lengthpc = String.valueOf(postalCodee).length();
			}
			builder.insert(140, postalCodee);
			builder.insert(13, 0.00);
			builder.insert(148, 'Y');
			
			String filecontent = builder.toString();
			
			
			FileWriter fr = new FileWriter("accounts-db.txt", true);
			BufferedWriter br = new BufferedWriter(fr);
			//br.write("\n");
			br.write(filecontent);

			br.close();
			fr.close();
			System.out.println("If you would like to make another account press 0. If you don't press any other number.");
			stopper = sc.nextInt();
			System.out.println(stopper);
		}
	}
	
	public void welcome() throws IOException {
		menuNumber = -1;
		while(menuNumber != 3) {
			showBasicMenu();
			System.out.println("Please select a menu number");
			menuNumber = sc.nextInt();
			while(!(menuNumber == 1 || menuNumber == 2 || menuNumber == 3)) {
				showBasicMenu();
				System.out.println("Please select a menu number");
				menuNumber = sc.nextInt();
			}
			//System.out.println("inside menu loop");
			//System.out.println("menuNumber is " + menuNumber);
			
			if(menuNumber == 1) {
				//open account
				newAccount();
				
			} else if(menuNumber == 2) {
				int pin = -1;
				System.out.println("Account #:  ");
				long accountNumber = sc.nextLong();
				System.out.println("Pin:  ");
				sc.nextLine();
				pin = sc.nextInt();
				
				bankaccount = Database.getAccount(accountNumber);

				while (bankaccount == null || (bankaccount.getAccountHolder().getPin() != pin && !bankaccount.getStatus().equals("Y"))) {
					if(bankaccount != null && bankaccount.getStatus().equals("N")) {
						System.out.println("The account you entered is closed.");
					}
					System.out.println("Please enter a valid account and pin");
					System.out.println("Account #:  ");
					accountNumber = sc.nextLong();
					System.out.println("Pin:  ");
					sc.nextLine();
					pin = sc.nextInt();
					bankaccount = Database.getAccount(accountNumber);
				}
			
				
				
				System.out.println("Welcome!");
				menuNumber = -1;
				if(menuNumber == 8) {
					//Database.updateAccount(bankaccount, bankaccount);
					System.out.println("Thank you! Have a nice day!");
				}
				while(menuNumber != 8) {
					showSubMenu();
					menuNumber = sc.nextInt();
					while(!(menuNumber == 1 || menuNumber == 2 || menuNumber == 3 || menuNumber == 4 || menuNumber == 5 || menuNumber == 6 || menuNumber == 7 || menuNumber == 8)) {
						System.out.println("please select a valid menu number");
						showSubMenu();
						menuNumber = sc.nextInt();
					}
					if (menuNumber == 1) {
						//deposit
						System.out.println("How much money would you like to deposit? \n");
						System.out.print("$ ");
						double amount = sc.nextDouble();
						bankaccount.deposit(amount);
					}
					else if (menuNumber == 2) {
						//withdraw
						System.out.println("How much money would you like to withdraw? \n ");
						System.out.print("$ ");
						double amount = sc.nextDouble();
						bankaccount.withdraw(amount);
					}
					else if (menuNumber == 3) {
						//transfer
						System.out.println("How much money do you want to transfer? \n $ ");
						double amount = sc.nextDouble();
						System.out.println("What is the account number you want to transfer money to? \n Account #:");
						long transferAccount = sc.nextLong();
						destination = Database.getAccount(transferAccount);
						
						if (destination != null) {
							bankaccount.transfer(amount, destination);
						}
						
					}
					else if (menuNumber == 4) {
						//view balance
						bankaccount.balance();
					}
					else if (menuNumber == 5) {
						//view personal information
						bankaccount.getAccountHolder().personal();
					}
					else if (menuNumber == 6) {
						//update personal information
						bankaccount.getAccountHolder().edit();
					}
					else if (menuNumber == 7) {
						//close account
						bankaccount.close();
					}
					
					else if(menuNumber == 8) {
						if(bankaccount!= null ) {
							Database.updateAccount(bankaccount, bankaccount);
						}
						System.out.println("Thank you! Have a nice day!");
					}
				}
				
			} 
			else if(menuNumber == 3) {
				System.out.println("Thank you have a nice day!");
			}
	
		}
	}		
}