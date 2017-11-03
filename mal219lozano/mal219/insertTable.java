import java.sql.*;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class insertTable {
    public static void main(String[] args) {
	//	File file = new File("cust_id.txt");
	//	File file2 = new File("veh_id.txt");
	//	String[] array = filething(file);
	//String[] veh_array = filething(file2);
	File file3 = new File("charge_id.txt");
	String[] chrg_array = filething(file3);
       	//for(int i = 0; i < array.length; i++){
	// System.out.println(array[i]);
	//	} 
	try {
	    Class.forName ("oracle.jdbc.driver.OracleDriver");
	} catch(Exception e){e.printStackTrace();}    
	try (
	     Connection con=DriverManager.getConnection("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241","mal219","P819031736");
	     Statement s=con.createStatement();
	     Statement t=con.createStatement();
	     ) {
		Random rand = new Random();
		
		String q;
		
                int i;

		for(int k = 0; k < chrg_array.length; k++){
		    q = "insert into charges (charge_id, fuel_charge, drop_off_charge, insurance) values ('" + chrg_array[k].trim() + "', 20, 10, 25)";
		i = s.executeUpdate(q);
		System.out.println("Values: " + i + " - " + k);
		}
		/* // reservation table
		int reserv_id;
		String startDate = "";
		String endDate = "";
		String[] loc = {"ATL","LAX","ORD","DFW","JFK","DEN","SFO","LAS","SEA","MIA","CLT","PHX","MCO","IAH","EWR","MSP","PHL","ABE"};
		int lc;
		int veh;
		int chrg;
		for(int k =0; k < array.length; k++){
		    reserv_id = rand.nextInt(99999999) + 300000000;
		    startDate = createDate();
		    endDate = createDatePlus(startDate);
		    System.out.println("d" + endDate);
		    lc = rand.nextInt(17);
		    veh = rand.nextInt(veh_array.length-1);
		    chrg = rand.nextInt(99999) + 50000000;
		    q = "insert into reservation (customer_id,reservation_id, start_date, end_date, drop_off, vehicle_id, charge_id) values('"+array[k].trim()+"','" + reserv_id + "', to_date('"+startDate +"','DD/MM/YY'), " + " to_date('" + endDate + "','DD/MM/YY'),'" + loc[lc] + "','"+ veh_array[veh].trim() + "','" + chrg +  "')";
		    i = s.executeUpdate(q);
		    System.out.println("Values:" + i + " - " + k);
		    startDate ="";
		    endDate = "";
		} 
		//vehicle id
/*
		int vehicleid;
		for(int k =0; k < 200; k++){
		    vehicleid = rand.nextInt(89999) + 10000;
		    q = "insert into vehicles (vehicle_id) values ('" + vehicleid+ "')";
		    i = s.executeUpdate(q);
		    System.out.println("value returned: " + i + " " + k);

		}
		*/
		//Discount table
		/*
		int discode;
		int disrate;
		for(int k = 0; k < 15; k++){
		    discode = rand.nextInt(89999) + 10000;
		    disrate = rand.nextInt(14) + 1;
                q = "insert into discounts (discount_code, discount_rate) values ("+ discode + " ," + disrate + ")";
                i = s.executeUpdate(q);
                System.out.println ("value returned: " + i + " " + k);
		}
		*/

		//Customers Table
		/*
		ResultSet rs = s.executeQuery("select count(name) from student");
		rs.next();
		int count = rs.getInt(1);
		System.out.println("number of rows: " + count);
		String[] nameArray = {"Sophia", "Jackson","Emma","Aiden","Olivia","Lucas","Ava","Liam","Mia","Noah","Isabella","Ethan","Riley","Mason","Aria","Caden","Zoe","Oliver","Charlotte","Elijah","Lily","Grayson","Layla","Jacob","Amelia","Michael","Emily","Benjamin","Madelyn","Carter","Aubrey","James","Adalyn","Jayden","Madison","Logan","Chloe","Alexander","Harper","Caleb","Abigail","Ryan","Aaliyah","Luke","Avery","Daniel","Evelyn","Jack","Kaylee","William","Ella","Owen","Ellie","Gabriel","Scarlett","Matthew","Arianna","Connor","Hailey","Jayce","Nora","Isaac","Addison","Sebastian","Brooklyn","Henry","Hannah","Muhammad","Mila","Cameron","Leah","Wyatt","Elizabeth","Dylan","Sarah","Nathan","Eliana","Nicholas","Mackenzie","Julian","Peyton","Eli", "Maria","Levi","Grace","Isaiah","Adeline","Landon","Elena","David","Anna","Christian","Victoria","Andrew","Camilla","Brayden","Lillian","John","Natalie","Lincoln"}; 
		
		String[] addresses = {"Main St","Second St","Third Ave","First St","Fourth Ave","Park St","Fifth Ave","Main St","Sixth St","Oak Blvd","Seventh Ave","Pine St","Maple St","Cedar St","Eighth Ave","Elm St","View Ave","Washington Ave","Ninth St","Lake Ave","Hill Rd"};

		String[] cities = {"Washington","Jurupa Valley","San Angelo","Davie","Greeley","Vista","South Bend","Las Cruces","Woodbridge","Hillsboro","Davenport","Edison","Rialto","San Mateo","El Cajon","Tyler","Lewisville","Clovis","Lakeland","Wichita Falls","Santa Maria","Green Bay","Burbank","Sandy Springs","Daly City","Broken Arrow","W Palm Beach","Norwalk","Boulder","Pompano Beach","Palm Bay","College Station","Everett","N Charleston","West Covina","Waterbury","Pearland","Pueblo","Richmond","Ventura","Centennial","Murrieta","Manchester","Billings","High Point","Cambridge","Antioch","Gresham","Lowell","Richardson","Inglewood","West Jordan","Temecula","Elgin","Rochester","Fairfield","Clearwater","Westminster","Miami Gardens","Costa Mesa","Carlsbad","Downey","Lansing","Peoria","Provo","Arvada","Wilmington","Round Rock","Springfield","El Monte","Ann Arbor","Independence","Beaumont","Fargo","Odessa","Columbia","Evansville","Allentown","Norman","Berkeley","Vallejo","Abilene","Victorville","Athens"};

		String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI"};		
		String p;
		//		ResultSet rsids;
		int ids;
		int drivers;
		int name;
		int cadd;
		int ccity;
		int cstate;
		for(int i = 1; i <= 1000; i++){
		    ids = rand.nextInt(99999999) + 1000000000;
		    drivers = rand.nextInt(99999999) + 2000000000;
		    name = rand.nextInt(99);
		    cadd = rand.nextInt(20);
		    ccity = rand.nextInt(82);
		    cstate = rand.nextInt(49);
		    p = "insert into customers (CUSTOMER_ID, DRIVERS_LICENSE, CUST_name, Street_address, city, state_c) values ('" + ids+"' , " + drivers+ ", '" + nameArray[name]+"','"+ addresses[cadd] + "','" + cities[ccity]+ "','" + states[cstate]+"')";
		    int k = t.executeUpdate(p);
		    System.out.println("value returned: " + k);
		}
		*/
		/*
		String q;
		ResultSet result;
		int i;
		q = "insert into customers (CUST_NAME) select name from student";
		i = s.executeUpdate(q);
		System.out.println ("value returned: " + i);
		*/
		s.close();
		t.close();
		con.close();
    } catch(Exception e){e.printStackTrace();}
    }
    public static String[] filething(File file){
	Scanner scan = null;
	Scanner scan2 = null;
	try{ 
	    scan = new Scanner(file).useDelimiter("\\t|[\\n\\r\\f]+");
	    scan2 = new Scanner(file).useDelimiter("\\t|[\\n\\r\\f]+");	
	}
	catch(FileNotFoundException e){
	    System.out.println("My bad");
	    System.exit(1);
	}
	int arraylen = 0;	
	while(scan2.hasNext()){
	    if(scan2.hasNextLine()){
		arraylen++;
		scan2.next();
	    }
	    // arraylen++;
	}//end while
	String something = "";
	String[] retArray = new String[arraylen];
	int counter = 0;
	while(scan.hasNext()){
	    if(scan.hasNextLine()){
		something = scan.next();
		retArray[counter] = something;
	    }//end of if
	    counter++;
}//end while
return retArray;
}
    public static String createDate(){
	Random rand = new Random();
	String year = "17";
	String month = "";
	String day = "";
	int monthrand = rand.nextInt(11) + 1;
	int dayrand = rand.nextInt(27) + 1; // 1 -> 01
	if(dayrand < 10){
	    day = "0" + dayrand;
	}
	else{
	    day = ""+ dayrand;
	}
	switch(monthrand){
	case 1: 
	    month = "JAN";
	    break;
	case 2:
	    month = "FEB";
	    break;
	case 3:
	    month = "MAR";
	    break;
	case 4:
	    month = "APR";
	    break;
	case 5:
	    month = "MAY";
	    break;
	case 6:
	    month = "JUN";
	    break;
	case 7:
	    month = "JUL";
	    break;
	case 8:
	    month = "AUG";
	    break;
	case 9:
	    month = "SEP";
	    break;
	case 10:
	    month = "OCT";
	    break;
	case 11:
	    month = "NOV";
	    break;
	case 12:
	    month = "DEC";
	    break;
	default:
	    System.out.println("ERROR");
	}
	String date =  day + "/" + month + "/" + year;
	System.out.println(date);
	return date;
    }
    public static String createDatePlus(String date){
	/*Random rand = new Random();
        String year = "17";
        String month = "";
        String day = "";
        int monthrand = rand.nextInt(11) + 1;
        int dayrand = rand.nextInt(27) + 1;*///DD/MM/YY
	String day = date.substring(0,2);
	//	System.out.println(date);
	String month = date.substring(3,6);
	//	System.out.println(date);
	String year = date.substring(7,9);
	//System.out.println(date);
	int dayint = Integer.parseInt(day);
	dayint += 2;
	boolean overflow = false;
	if(dayint > 28){
	    dayint = 2;
	    overflow = true;
	}
	day = "";
        if(dayint < 10){
            day = "0" + dayint;
        }
        else{
            day = ""+ dayint;
        }
	if(overflow){
        switch(month){
        case "DEC":
	    year = "";
            month = "JAN";
	    year = "18";
            break;
        case "JAN":
            month = "FEB";
            break;
        case "FEB":
	    month = "MAR";
	    break;
	case "MAR":
	    month = "APR";
	    break;
	case "APR":
	    month = "MAY";
	    break;
	case "MAY":
	    month = "JUN";
	    break;
	case "JUN":
	    month = "JUL";
	    break;
	case "JUL":
	    month = "AUG";
	    break;
	case "AUG":
	    month = "SEP";
	    break;
	case "SEP":
	    month = "OCT";
	    break;
	case "OCT":
	    month = "NOV";
	    break;
	case "NOV":
	    month = "DEC";
	    break;
	default:
	    System.out.println("ERROR");
	}
	}
    String retdate =  day + "/" + month + "/" + year;
    System.out.println(retdate);
    return retdate;


    }
}

