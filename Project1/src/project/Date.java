package project;
import java.util.Calendar;

/**
 * The date class creates a date, that date being todays date by default  or a specific date.
 * The date class can create those dates and check if those dates are real and compare two dates.
 * @author Manav Bali
 * @author Daniel Lopez
 *
 */
public class Date implements Comparable<Date> { 

	private int year;
	private int month;
	private int day;
	
	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100; 
	public static final int QUATERCENTENNIAL = 400; 
	public static final int THE_EIGHTYS = 1980;
	public static final int JAN = 1;
	public static final int FEB = 2; 
	public static final int MAR = 3; 
	public static final int APR = 4;
	public static final int MAY = 5;
	public static final int JUN = 6; 
	public static final int JUL = 7; 
	public static final int AUG = 8;
	public static final int SEP = 9;
	public static final int OCT = 10; 
	public static final int NOV = 11; 
	public static final int DEC = 12;
	
	/**
	 * Grabs the current instances of date and returns a string.
	 * @return string of date.
	 */
	public String toString() {
	String date= this.month+"/"+this.day+"/"+this.year;
	return date;
	}
	
	/**
	 * Separates the string into the correct instance variable. 
	 * Creates a date object.
	 * @param date the specific date.
	 */
	public Date(String date) { //take “mm/dd/yyyy” and create a Date object
		String seperate[] = date.split("/");
		month = Integer.parseInt(seperate[0]);
		day = Integer.parseInt(seperate[1]);
		year = Integer.parseInt(seperate[2]);
	} 
	
	/**
	 * Creates a default date with that date being todays date.
	 * Creates a date object.
	 */
	public Date() { //create an object with today’s date (see Calendar class)
		Calendar today = Calendar.getInstance();
		 month = today.get(Calendar.MONTH);
		 year = today.get(Calendar.YEAR);
		 day = today.get(Calendar.DATE);
		 month++;
	}
	
	/**
	 * Checks the date to see if its a real date. 
	 * Checks for future dates and dates that don't follows the rules of the months/days.
	 * Dates before 1980 will not be accepted and dates past todays date will also not be accepted.
	 * @return true is date is valid, false otherwise.
	 */
	public boolean isValid() { 
		Date todaysDate = new Date(); 
		if (year == todaysDate.year) {
			if(month == todaysDate.month &&day == todaysDate.day) 
				return true;
			if (month < todaysDate.month && year < todaysDate.year)
				return true;
			if ((month == todaysDate.month && day > todaysDate.day) || month > todaysDate.month)
				return false;	
		}
		if (year > todaysDate.year || year < THE_EIGHTYS) {
			return false;
		}
		int maxMonth = 12;
		int minMonth = 1;
		if (month > maxMonth || month < minMonth) {
			return false;
		}
		else if((month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT|| month == DEC) && (day > 31 || day < 1) || 
		   (month == APR || month == JUN || month == SEP || month == NOV) && (day > 30 || day < 1)) { 
			return false;
		}
		boolean leapYear = leapYear(); 
		if (leapYear == true && (month == 2 && (day > 29 || day <1 ))) 
			return false;
		else if (leapYear == false && (month == 2 && (day > 28 || day <1 )))
			return false;
		return true;
	}
	
	/**
	*Checks to see if the year is a leap year.
	*@return true if leap year, false otherwise
	*/
	public boolean leapYear() {
		Date todaysDate = new Date(); 
		boolean leapYear = false;
		if (year % QUADRENNIAL == 0) {
			if(year % CENTENNIAL != 0) {
				leapYear = true;	
			}
		}
		if (year % QUATERCENTENNIAL == 0) {
			if( year % CENTENNIAL == 0) {
				leapYear = true;
			}
		}
		if(year == todaysDate.year) {
			leapYear = false;
		}	
		return leapYear;
	}
	
	/**
	 *Compares two dates.
	 * @param date the date to be compared.
	 * @return int 1 if bigger, 0 if the same, -1 if smaller.
	 */
	@Override
	public int compareTo(Date date) { //sort by release date 
		int compareM = date.month;
		int compareD = date.day;
		int compareY = date.year;
		if(this.year < compareY)
			return -1;
		else if(this.year > compareY)
			return 1;
		else if(this.year == compareY && this.month == compareM && this.day == compareD)
			return 0;
		else if (this.year == compareY && this.month == compareM && this.day < compareD)	
			return -1;
		else if (this.year == compareY && this.month == compareM && this.day > compareD)
			return 1;
		else if(this.year == compareY&& this.month < compareM) 		
			return -1;
		else 
			return 1;
	} 
	
	
// Testbed main for Date.java
public static void main (String arg []) {
		
	Date date1 = new Date("13/15/2018"); // month over 12
	boolean expectedResult1 = false;
	boolean result1 = date1.isValid();
	
	if (result1 == expectedResult1) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	
	Date date2 = new Date("2/29/2016"); //leap year 
	boolean expectedResult2 = true;
	boolean result2 = date2.isValid();
	
	if (result2 == expectedResult2) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date3 = new Date("2/29/2015"); //non leap year
	boolean expectedResult3 = false;
	boolean result3 = date3.isValid();
	
	if (result3 == expectedResult3) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date4 = new Date("0/19/2009"); //month under 1
	
	boolean expectedResult4 = false;
	boolean result4 = date4.isValid();
	
	if (result4 == expectedResult4) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date5 = new Date("10/19/2022"); //future date year
	
	boolean expectedResult5 = false;
	boolean result5 = date5.isValid();
	
	if (result5 == expectedResult5) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date6 = new Date("10/02/2021"); //future date same year
	
	boolean expectedResult6 = false;
	boolean result6 = date6.isValid();
	
	if (result6 == expectedResult6) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date7 = new Date("12/31/1979"); //date before 1980
	
	boolean expectedResult7 = false;
	boolean result7 = date7.isValid();
	
	if (result7 == expectedResult7) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date8 = new Date("1/31/2020"); //month at 31
	
	boolean expectedResult8 = true;
	boolean result8 = date8.isValid();
	
	if (result8 == expectedResult8) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	Date date9 = new Date("3/32/2020"); //month over 31
	
	boolean expectedResult9 = false;
	boolean result9 = date9.isValid();
	
	if (result9 == expectedResult9) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date10 = new Date("11/30/2020"); //month at 30
	
	boolean expectedResult10 = true;
	boolean result10 = date10.isValid();
	
	if (result10 == expectedResult10) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
	Date date11 = new Date("9/31/2020"); //month over 30
	
	boolean expectedResult11 = false;
	boolean result11 = date11.isValid();
	
	if (result11 == expectedResult11) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	Date today = new Date();
	today.day++; //tomorrows date
	String t = today.toString();
	Date date12 = new Date(t); //tomorrow date
	
	boolean expectedResult12 = false;
	boolean result12 = date12.isValid();
	
	if (result12 == expectedResult12) {
		System.out.println("Pass");
	}
	else {
		System.out.println("Fail");
	}
	
}

}