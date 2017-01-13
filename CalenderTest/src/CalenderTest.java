import java.text.DateFormatSymbols;
import java.util.*;

/**
 * test the methods of calender
 * @author liang
 *
 */
public class CalenderTest {

/*	public static void main(String[] args) 
	{
		// Construct d as current date
		GregorianCalendar d = new GregorianCalendar();
		
		int today = d.get(Calendar.DAY_OF_MONTH);
		int month = d.get(Calendar.MONTH);
		
		//set d to start date of the month
		d.set(Calendar.DAY_OF_MONTH, 1);
		
		int weekday = d.get(Calendar.DAY_OF_WEEK);
		
		int firstDayOfWeek = d.getFirstDayOfWeek();
		
		//indentationÐÐÊ×Ëõ½ø
		int indent = 0;
		while(weekday != firstDayOfWeek)
		{
			indent++;
			d.add(Calendar.DAY_OF_MONTH, -1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
			
		}
		
		//print weekday names
		String[] weekDayNames = new DateFormatSymbols().getShortWeekdays();
		do
		{
			System.out.printf("%4s", weekDayNames);
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		while(weekday!= firstDayOfWeek);
		System.out.println();
		for(int i = 1; i <=indent; i++)
			System.out.print("   ");
		d.set(Calendar.DAY_OF_MONTH, 1);
		do
		{
			//print day
			int day = d.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);
			
			//mark current day with *
			if(day == today)
				System.out.print("*");
			else
				System.out.print(" ");
			
			//advance d to the next day
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
			
			//start a new line at the start of the week
			if(weekday == firstDayOfWeek)
				System.out.println();
		}
		while(d.get(Calendar.MONTH) == month);
		//the loop exists when the d is a day of the month
		
		//print final end of line if necessary
		if(weekday != firstDayOfWeek)
			System.out.println();
		
	}*/
	/*
	 * Binary search which can catch the equal things
	 */
	
		public static void main(String[] args)
		{
			int[] a = {4,4,4,4,5,6,7,8,9,10};
			int key = 4;
			System.out.println(binarySearch2(a, key));
		}
		public static int binarySearch2(int[] a, int key)
		{
			int lo = 0, hi = a.length-1;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo)/2;
				if		(key < a[mid]) hi = mid - 1;
				else if (key > a[mid]) lo = mid + 1;
				else    {
					int i = 1;
					if(a[0] == a[mid])
						return 0;
					while(a[mid] == a[mid-i])
						i++;
					return mid-i+1;
				}		
			}
			return -1;
		}




}
