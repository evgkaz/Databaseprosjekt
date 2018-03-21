import java.sql.*;
import java.util.Scanner;

public class DBConnect {
	protected Connection conn;
	private String usrName = "root";
	private String passWd = "**";
	private Scanner sc;
	
	
	
	public DBConnect() throws Exception {
		connect();
		sc = new Scanner(System.in);
		System.out.println("Skriv inn hvor mange økter du har lyst til å se:");
		int n = Integer.valueOf(sc.nextLine());
		showWorkOuts(n);
	}

	
	private void showWorkOuts(int n) throws Exception {
		Statement sporring = conn.createStatement();
		//Finner først IDen til de som er lagt til sist
		ResultSet result = sporring.executeQuery("select * from treningsøkt ORDER BY treningsøkt.treningsid DESC LIMIT "
				+ " " + n  + ";");
		while(result.next()){
			System.out.println("ID: " + result.getString(1) + " Varighet: " + result.getString(2) + " Form: " + result.getString(3) + " Prestasjon: " + result.getString(4) + " Tidspunkt: " + result.getString(5) + " Dato: " + result.getString(6) + " Notat: " + result.getString(7));
		}
		
	}

	private void connect() throws SQLException  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false",usrName,passWd);
			System.out.println("Connection running");
		}
		catch (Exception ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
		
	}
	
	
}
