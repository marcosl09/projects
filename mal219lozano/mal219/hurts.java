import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Calendar;
import java.text.*;
public class hurts{
    static Connection con = null;
    static boolean isLogged;
    final static String DATE_FORMAT = "dd/MM/yy";

    public static void main(String[] args){
	try{
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	catch (Exception e){
	    e.printStackTrace();
	}
	Scanner scan = new Scanner(System.in);

	do{
	    //gets username and password from user
	    //   Scanner scan = new Scanner(System.in);
	    System.out.print("Enter user id: ");
	    String username = scan.nextLine();
	    System.out.print("Enter password: ");
	    String passwd = scan.nextLine();
	    isLogged = login(username, passwd); //checks if login is correct
	}while(isLogged == false);
	System.out.println("Connection Successful. Welcome to Hurts.");
	boolean isOption = false;
	String option1 = "";
	int op1 = 0;
	do{
	System.out.println("Are you a customer[0] or an employee[1]?");
	option1 = scan.next();
	try{
	    op1 = Integer.parseInt(option1);
	    if(op1 == 0 || op1 == 1){
		isOption = true;
		if(op1 == 0)//customer option
		    customerMain();
		else if(op1 == 1)//employee option
		    employeeMain();
		else
		    System.out.println("You're out of options");//should never run
	    }//end of if
	    else{
		System.out.println("Option not within range");
	    }
	}//end of try
	    catch(Exception e){
		System.out.println("Not a valid option. Try again.");
	    }
	}while(isOption == false);
    }

	//	con.close();

    public static boolean login(String user, String password){
	try{
	    con = DriverManager.getConnection("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241", user, password);
	    return true;
	}//end of try
	catch(Exception e){
	    e.printStackTrace();
	    System.out.println("Wrong username and password. Try again.");
	    return false;
	}
    }//end of login

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void customerMain(){
	Scanner scan = new Scanner(System.in);
	int op1;
	String option1;
	boolean isOption = false;
	do{
	System.out.println("Are you a new customer[0] or an existing customer[1]?");
        option1 = scan.next();
	try{
            op1 = Integer.parseInt(option1);
            if(op1 == 0 || op1 == 1){
		 isOption = true;
                if(op1 == 0)//customer option
                    newCustomer();
                else if(op1 == 1)//employee option
                    existingCustomer();
                else
                    System.out.println("You're out of options");//should never run
            }//end of if
            else{
                System.out.println("Option not within rage");
	    }//enf of else
        }//end of try
	catch(Exception e){
	    System.out.println("Not a valid option. Try again.");
	}//end of catch
	}while(isOption == false);
    }//end of customer
 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public static void employeeMain(){
       System.out.println("Welcome back. Do you want to check inventory[0], make a reservation[1], or create a new customer[2]?");
       Scanner scan = new Scanner(System.in);
       String input = scan.next();
       int op1 = 0;
       boolean isOption = false;
       String loc = "";
       boolean isLoc = false;
       try{
	   op1 = Integer.parseInt(input);
	   if(op1 >= 0 && op1 <= 2){
	       isOption = true;
	       if(op1 == 0){//inventory
		   do{
		   System.out.print("Enter the location code of your choosing: ");
		   loc = scan.nextLine().trim().toUpperCase();
		   String[] locs = {"ATL","LAX","ORD","DFW","JFK","DEN","SFO","LAS","SEA","MIA","CLT","PHX","MCO","IAH","EWR","MSP","PHL","ABE"};
		   for(int i = 0; i < locs.length; i++){
		       if(loc.equals(locs[i])){
			   isLoc = true;
		       }
		   }
		   if(!isLoc){
		   System.out.println("No service in this location.");
		   }
		   }while(!isLoc);
	       getCarsAvail(loc);
	       }
       else if(op1 == 1){//make reservation
	   System.out.print("Enter the customer's ID: ");
	   String id = scan.nextLine();
	   makeReservation(id);	   
       }
       else if(op1 == 2){
	   newCustomer();
	   }
	       else
		   System.out.println("You're out of options");//should never run
	   }//end of if
	   else{
	       System.out.println("Option not within rage");
	   }//enf of else
       }//end of try
       catch(Exception e){
	   System.out.println("Not a valid option. Try again.");
       }//end of catch

   }//end of employee


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void newCustomer(){
	Scanner scan = new Scanner(System.in);
	String name = "";
	String street = "";
	String city = "";
	String state = "";
	String response = "";
	String license = "";
	int licensenum = 0;
	System.out.println("Please create a profile");
	do{
	System.out.print("Enter your name: ");
        name = scan.nextLine().trim();
	System.out.print("Enter your street name: ");
        street = scan.nextLine().trim();
	System.out.print("Enter your city name: ");
	city = scan.nextLine().trim();
	System.out.print("Enter your state abbreviation (e.g. TX for Texas): ");
	state = scan.nextLine().trim();
	state = state.toUpperCase();
	String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI"};
	boolean isState = false;
	for(int i = 0; i < states.length; i++){
	    if(state.equals(states[i])){
		isState = true;
	    }
	}//end of for
	System.out.print("Enter your driver's license: ");
	license = scan.next();
	try{
	    licensenum = Integer.parseInt(license);
	    if(licensenum > 0 && licensenum <= 2099999999){
		//within range
	    }
	    else{
		System.out.println("Not a valid license");
	    }
	}
	catch(Exception e){
	    System.out.println("Not a valid license.");
	}
	//	scan.next();
	if(!isState){
	    System.out.println("Not a valid state");
	    response = "n";
	}//end of state
	else{
	    System.out.println("Check the information you entered. \nName: " + name + "\nStreet Name: " + street);
	    System.out.println(city + ", " + state + "\nLicense Number: " + licensenum);
	    System.out.println("Is the information correct? [y/n]");
	    response = scan.next();
	}
	}while(!response.equals("y"));
	try{
	String q = "insert into customers (customer_id, drivers_license, cust_name, street_address, city, state_c) values (?,?,?,?,?,?)";
	ResultSet record;
	PreparedStatement p = con.prepareStatement(q);
	Random rand = new Random();
	int custID = rand.nextInt(99999999) + 2010000000;
	String custstr = "" + custID;
	p.setString(1, custstr);
	p.setInt(2,licensenum);
	p.setString(3,name);
	p.setString(4, street);
	p.setString(5, city);
	p.setString(6, state); 
	int k = p.executeUpdate();
	System.out.println(k + " Profile sucessfully created.");
	System.out.println("Do you want to make a reservation [y/n]?");
	String reserve = scan.next();
	if(reserve.equals("y")){
	    makeReservation(custstr);
	}
	else{
	    System.out.println("Thank you for using Hurts. See you next time. \nThe program will now close");
	    p.close();
	    con.close();
	    System.exit(0);
	}//end of else
	}//end of try
	catch(Exception e){
	    System.out.println("Profile creation failed. Please try again later.");
	}
    }//end of new cust

    public static void existingCustomer(){
	Scanner scan = new Scanner(System.in);
	System.out.print("Enter your customer id: ");
	String custID = scan.nextLine();
	System.out.println("Do you want to make a reservation [y/n]");
	String response = scan.next();
	if(response.equals('y')){
	    makeReservation(custID);
	}
	else{
	    System.exit(0);
	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public static void makeReservation(String custID){
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	boolean isLoc = false;
	String loc = "";
	do{
	System.out.print("Enter the location code of your choosing: ");
	loc = scan.next().trim().toUpperCase();
	String[] locs = {"ATL","LAX","ORD","DFW","JFK","DEN","SFO","LAS","SEA","MIA","CLT","PHX","MCO","IAH","EWR","MSP","PHL","ABE"};
	for(int i = 0; i < locs.length; i++){
	    if(loc.equals(locs[i])){
		isLoc = true;
	    }
	}
	if(!isLoc){
	    System.out.println("No service in this location.");
	}
	}while(!isLoc);
	getCarsAvail(loc);
	boolean isVehReal = false;
	String veh_id = "";
	do{
	System.out.print("Enter the vehicle ID of the car you wish to rent: ");
	veh_id = scan.next();
	try{
	String q = "select status from vehicles where vehicle_id = ?";
	PreparedStatement ps = con.prepareStatement(q);
	ps.setString(1, veh_id);
	ResultSet record = ps.executeQuery();
	if(!record.next()){
	    System.out.println("Vehicle does not exist");
	    isVehReal = false;
	}
	else{
	    isVehReal =true;
	}//end of else
	ps.close();
	}//end of try
        catch(Exception e){System.out.println(e);}

        }while(!isVehReal);

	System.out.println("Car added to reservation.");
	boolean startDateValid = false;
	String startDate = "";
	do{
	System.out.print("Enter the date you want to pick up car (DD/MM/YY): ");
	startDate = scan.nextLine();
        startDateValid = isDateValid(startDate);
	if(!startDateValid){
	    System.out.println("Invalid Start Date");
	}
	}while(!startDateValid);
	boolean endDateValid = false;
	String endDate = "";
	do{
	System.out.print("Enter the date you want to drop off the car (DD/MM/YY): ");
	endDate = scan.nextLine();
	endDateValid = isDateValid(endDate);
	if(!endDateValid){
	    System.out.println("Invalid End Date");
	}//end of if
	}while(!endDateValid);
	boolean isLoc2 = false;
	String carDrop = "";
	do{
	    System.out.println("Enter the location where you want to drop off the car: ");
	    carDrop = scan.nextLine();
	String[] locs2 = {"ATL","LAX","ORD","DFW","JFK","DEN","SFO","LAS","SEA","MIA","CLT","PHX","MCO","IAH","EWR","MSP","PHL","ABE"};
        for(int i = 0; i < locs2.length; i++){
            if(carDrop.equals(locs2[i])){
                isLoc2 = true;
            }
        }
        if(!isLoc2){
            System.out.println("No service in this location.");
        }
        }while(!isLoc2);
	int charge = rand.nextInt(99999) + 60000000;
	String chargeID = "" + charge;
	int reserve = rand.nextInt(99999999) + 400000000;
	String reserveID = "" + reserve;
	try{
	    String q2 = "insert into reservation (customer_id, reservation_id, start_date, end_date, drop_off, vehicle_id, charge_id) values (?,?,to_date(?,'DD/MM/YY'), to_date(?, 'DD/MM/YY'), ?, ?,?)";
	    PreparedStatement ps2 = con.prepareStatement(q2);
	    ps2.setString(1, custID);
	    ps2.setString(2, reserveID);
	    ps2.setString(3, startDate);
	    ps2.setString(4, endDate);
	    ps2.setString(5, carDrop);
	    ps2.setString(6, veh_id);
	    ps2.setString(7, chargeID);
	    int l = ps2.executeUpdate();
	    System.out.println(l + " Reservation successfully made");
	    ps2.close();
	    con.close();
	}//end try
	catch(Exception e){
	    System.out.println("Reservation failed. Try again later");
	}

    }//end of class
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void getCarsAvail(String loc){
	try{
	    String q = "select vehicle_id, make, type from vehicles where location_id = ? and status = 'avail'";
	    PreparedStatement ps = con.prepareStatement(q);
	    ps.setString(1, loc);
	    ResultSet record = ps.executeQuery();
	    if(!record.next())
		System.out.println("No Record");
	    else{
		do{
		    System.out.println(record.getString("vehicle_id") + " " + record.getString("make") + " " + record.getString("type"));
		}while(record.next());
	    }//end of else
	    ps.close();
	}//end of try
	catch(Exception e){System.out.println(e);}
    }//end of class

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isDateValid(String date){
	//final static String DATE_FORMAT = "DD/MM/YY";
	try{
	    DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	    df.setLenient(false);
	    df.parse(date);
	    return true;
	}//end of try
	catch(ParseException e){
	    return false;
	}//end of catch
    }//end of date validator
 
}//end of public class
