package abstractClasses;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends Person
{
	private double salary;
	private Date hireDay;
	
	public Employee(String n, double s, int year, int month, int day)
	{
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		hireDay = calendar.getTime();
	}
	public String getDescription()   //如果没有则上级的getDescription则无法启动
	{
		return String.format("an employee with a salary of $%.2f", salary);
	}
	
	/*public boolean equals(Object otherObject)
	{
		if(this == otherObject) return true; //quick test if the objects are identical
		if(otherObject == null) return false; //must false if object is null
		if(getClass() !=otherObject.getClass()) //class have to match
			//
			// if(!(otherObject instanceof ClassName))
			return false;
		Employee other = (Employee) otherObject;
		return name.equals(other.name)
				&& salary == other.salary;
				&& hireDay.equals(others.hireDay);
	}*/
	
}
