package abstractClasses;

public class PersonTest 
{
	public static void main(String[] args)
	{
		Person[] people = new Person[2];
		
		//fill the people array with students and employee objects
		people[0] = new Employee("harry", 50000, 1989,10, 1);
		people[1] = new Student("morris", "computer science");
		
		//print out names and description of all Person objects;
		for(Person p : people)
			System.out.println(p.getName() + "£¬ " + p.getDescription());
	}

}
