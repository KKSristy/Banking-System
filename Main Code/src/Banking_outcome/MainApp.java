package Banking_outcome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class BankDetailed {  
     static String accno;  
     static String name;  
     static String acc_type;
     static long newbalance;
     static long balance;  
   static Scanner sc = new Scanner(System.in);  
   public void openAccount() {
      		System.out.print("Enter Account No: ");  
      		accno = sc.next();  
      		System.out.print("Enter Account type: ");  
      		acc_type = sc.next();  
      		System.out.print("Enter Name: ");  
      		name = sc.next();  
      		System.out.print("Enter Balance: ");  
      		balance = sc.nextLong();
      	
   	try {
   	    Class.forName("com.mysql.cj.jdbc.Driver");
   	    Connection connection = DriverManager.getConnection(
   	            "jdbc:mysql://localhost:3306/sristy", "root", "");
   	    Statement statement = connection.createStatement();
   	    String i = "insert into banking values('" +accno+ "', '" +acc_type+ "', '" +name+ "', '"+balance+ "')";
   	    int x = statement.executeUpdate(i);
   	    connection.close();
   	 System.out.println("successfull");
   	} catch (Exception e) {
   	    System.out.println(e);
   	}
   }
   public static void searchAccount() {  
 		System.out.print("Enter Account No: ");  
 		accno = sc.next();  
 		System.out.print("Enter Account type: ");  
 		acc_type = sc.next();
   	try {
   	    Class.forName("com.mysql.cj.jdbc.Driver");
   	    Connection connection = DriverManager.getConnection(
   	            "jdbc:mysql://localhost:3306/sristy", "root", "");
   	    Statement statement = connection.createStatement();
   	    String j = "select * from banking WHERE accno = '" + accno + "' AND acc_type = '" + acc_type + "'";
   	    ResultSet rs = statement.executeQuery(j);
   	    if (rs.next()){
   	    	System.out.println("Name of account holder: " + rs.getString(3));  
   	        System.out.println("Account no.: " + rs.getString(1));  
   	        System.out.println("Account type: " + rs.getString(2));  
   	        System.out.println("Balance: " + rs.getString(4));
   	    }
   	    connection.close();
   	} catch (Exception e) {
   	    System.out.println(e);
   	}  
   }
   public static void deposit() {
		   System.out.print("Enter newbalance No: ");
		     newbalance = sc.nextLong();
	 	   System.out.print("Enter Account No: ");  
      	     accno = sc.next();
      	   System.out.print("Enter balance No: ");
 		     balance = sc.nextLong();
	   try {
	   	    Class.forName("com.mysql.cj.jdbc.Driver");
	   	    Connection connection = DriverManager.getConnection(
	   	            "jdbc:mysql://localhost:3306/sristy", "root", "");
	   	    Statement statement = connection.createStatement();
	   	    String j = ("UPDATE banking set balance = '" + newbalance + "' WHERE accno = '" + accno + "' AND balance = '" + balance +"'");
	   	 int x = statement.executeUpdate(j);
	   	    connection.close();
		   	 if (x > 0) {
		   		 System.out.print("Balance updated");
		   	 }
		   	 else 
		   		 System.out.print("Balance Doesn't update");
	   	} catch (Exception e) {
	   	    System.out.println(e);
	   	}  
   } 
   public static void withdrawal() {
	   System.out.print("Enter withdrawal amount: ");
	     newbalance = sc.nextLong();
	   System.out.print("Enter Account No: ");  
		 accno = sc.next();
       System.out.print("Enter balance No: ");
	     balance = sc.nextLong();
 try {
 	    Class.forName("com.mysql.cj.jdbc.Driver");
 	    Connection connection = DriverManager.getConnection(
 	            "jdbc:mysql://localhost:3306/sristy", "root", "");
 	    Statement statement = connection.createStatement();
 	    String j = ("UPDATE banking set balance = '" + newbalance + "' WHERE accno = '" + accno + "' AND balance = '" + balance +"'");
	   	 int x = statement.executeUpdate(j);
 	    connection.close();
 	 	 if (x > 0) {
 	 		 System.out.print("Balance reduced");
 	 	 }
 	 	 else 
 	 		 System.out.print("Balance Doesn't reduced");
 	} catch (Exception e) {
 	    System.out.println(e);
 	}
   }
}
public class MainApp extends BankDetailed{  
    public static void main(String arg[]) {  
        try (Scanner sc = new Scanner(System.in)) {
			int ch;
			do {  
			    System.out.println("\n ***Banking System Application***");  
			    System.out.println(" 1. number of accounts to input\n 2. Search by Account number\n 3. Deposit amount \n 4. Withdraw amount \n 5. Exit ");  
			    System.out.println("Enter your choice: ");
			    ch = sc.nextInt();
					switch (ch) {

			        case 1:  
			        	System.out.print("How many number of customers do you want to input? ");  
			        	int n = sc.nextInt();
					    BankDetailed B[] = new BankDetailed[n];
			            for (int i = 0; i < B.length; i++) {  
			                B[i] = new BankDetailed();  
			                B[i].openAccount();  
			            }
			            
			        case 2:
			        	BankDetailed.searchAccount();
			            break;
			            
			        case 3: 
			            BankDetailed.deposit();  
			            break;
			        case 4: 
			            BankDetailed.withdrawal();  
			            break;
			        case 5:  
			            System.out.println("See you soon...");  
			            break;  
			    }  
			}  
			while (ch != 5);
		}
    }
}
