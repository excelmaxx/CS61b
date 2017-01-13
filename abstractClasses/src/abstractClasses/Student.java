package abstractClasses;

public class Student extends Person
{
	private String major;
	
	/**
	 * 
	 */
	public Student(String n, String m)
	{
		super(n);   //调用上级的init函数  相当于Person（n）
		major = m;
		
	}
	
	public String getDescription()
	{
		return "a student majoring in" + major;
	}

}
