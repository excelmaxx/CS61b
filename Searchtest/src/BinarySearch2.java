/*
 * Binary search which can catch the equal things
 */
public class BinarySearch2 
{
	public static void main()
	{
		int[] a = {1,2,3,4,5,6,7,8,9,10};
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
			else return mid;		
		}
		return -1;
	}


}
