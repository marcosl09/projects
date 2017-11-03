import java.sql.*;
import java.util.*;

public class TeachingRecord{
    static Connection con = null;
    static boolean isLogged;
    public static void main(String[] args){
	try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	catch (Exception e){
	    e.printStackTrace();
	}
	do{
	    //gets username and password from user
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter user id: ");
	    String username = sc.nextLine();
	    System.out.print("Enter password: ");
	    String passwd = sc.nextLine();
	    isLogged = login(username, passwd); //checks if login is correct
	}while(isLogged == false);
	try{
		//variables needed to run query 
		ResultSet result;
		String q = "select name, id from instructor where name like ?";
		PreparedStatement ps = con.prepareStatement(q);

		//Scans for substring
		boolean isResult = true;
		Scanner scan = new Scanner(System.in);
		do{
		    System.out.print("Enter a search substring (case sensentive): ");
		    String  name = scan.nextLine();
		    name = "%" + name +"%";

		    //executes query to find name
		    ps.setString(1,name);
		    result = ps.executeQuery();
		    if(!result.next()){
			System.out.println("No result.");
			isResult = false;
		    }

		    else{
			isResult = true;
			do{
			    System.out.println(String.format("%-15.15s",result.getString("name")) + "  " + String.format("%-9.9s",result.getString("id")));
			}while (result.next());
		    }
		}while(isResult == false);

		//variables needed to run query
                ResultSet record;
                String q2 = "select distinct course.dept_name, course.course_id, course.title, sec_id, semester,  year from instructor, teaches, course" +
" WHERE teaches.id = ? and teaches.course_id = course.course_id ORDER BY dept_name asc, course_id asc, year asc, semester desc";
                PreparedStatement ps2 = con.prepareStatement(q2);

		//scans for an id
		boolean isTrueId = false;
		String id = "";
		int i = 0;
		do{
		System.out.print("Enter an instructor's id: ");
		id = scan.next();
		try{
		    i = Integer.parseInt(id);
		    if(i <= 0 || i >= 99999){
			//just checks if within range
		    }
		}
		catch(Exception e){
		    System.out.println("Not an ID. Try again.");
		}
		ps2.setInt(1,i);
		record = ps2.executeQuery();
		if(!record.next())
		    System.out.println("No Record");
		else{
		    isTrueId = true;
		    do{
			System.out.println(String.format("%-10.10s", record.getString("dept_name")) + " " +
					   String.format("%3.3s",record.getString("course_id")) + " " +
 String.format("%-20.20s", record.getString("title")) + " " +  String.format("%3.3s",record.getString("sec_id")) + " " +
 String.format("%-6.6s",record.getString("semester")) + " " +
 String.format("%4.4s", record.getString("year")));
		    }while(record.next());
		}
		}while(isTrueId == false);
    

		ps.close();
		ps2.close();
		con.close();
	}catch (Exception e) {e.printStackTrace();}

    }
    public static boolean login(String user, String password){
	try{
	    con = DriverManager.getConnection("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241", user, password);
	    return true; 
	}
	catch(Exception e){
	    System.out.println("Wrong username and password. Try again.");
	    return false;
	}
    }
}