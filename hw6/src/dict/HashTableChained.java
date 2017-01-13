/* HashTableChained.java */

package dict;

import list.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	protected List[] table;
	protected int size; 
	protected int length;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
 public boolean isPrime(int num){
	 for(int i = 2; i < num/2; i++)
		 if(num%i == 0)
			 return false;
	 return true;
 }

	
  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	  //int length = (int)(sizeEstimate*0.6);
//big mistake have been here
		length = (int)(sizeEstimate*0.6);	
		while(!isPrime(length))
		{
			length++;
		}

	  table = new DList[length];
	  size = 0;	
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  table = new DList[101];
	  length = 101;
	  size = 0;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
//	  if(code > 0)
	  return (97*code + 179) % (length);
	//  else 
		//  return (97 * (-code) + 179) % length;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	  System.out.println(length);
	  if(size == 0)
		  return true;
	  else 
		  return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  int hashval = Math.abs(compFunction((int)key.hashCode()));
	  Entry temp = new Entry();
	  temp.key = key;
	  temp.value = value;
	  if(table[hashval] == null)
		  table[hashval] = new DList();
	  table[hashval].insertFront(temp);
	  size++;
	  return temp;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
 * @throws InvalidNodeException 
   **/

  public Entry find(Object key) throws InvalidNodeException{
    // Replace the following line with your solution.
	  int hashval = compFunction((int)key);
	  if(table[hashval] != null){
		  ListNode node = table[hashval].front();
		  while(node.isValidNode())
		  {
			  if(((Entry)node.item()).key().equals(key))
				  return (Entry)node.item();
			  else
				  node = node.next();
		  }
	  }
	  return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
 * @throws InvalidNodeException 
   */

  public Entry remove(Object key) throws InvalidNodeException {
    // Replace the following line with your solution.
	  int hashval = compFunction((int)key);
	  if(!table[hashval].isEmpty()){
		  ListNode node = table[hashval].front();
		  while(node.isValidNode())
		  {
			  if(((Entry)node.item()).key().equals(key))
			  {
				  node.remove();
				  size--;
				  return (Entry)node.item();				  
			  }

			  else
				  node = node.next();
		  }
	  }
	
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  for(int i=0; i < length; i++)
	  {
		  table[i] = null;
	  }
  }
  public int collisions(){
	  int wells = 0;
	  for(int i =0; i<length; i++){
		  if(table[i] == null ||table[i].isEmpty()){
			  continue;
		  }else{
			  wells++;
		  }
	  }
	  return size-wells;
  }
  public static void main(String[] argv) throws InvalidNodeException
  {
	  HashTableChained ht = new HashTableChained(10);
	  System.out.print("isEmpty, ture: " + ht.isEmpty());
	  for(int i = 0; i < 10; i++)
		  ht.insert(i, i);
	  System.out.println("print entry 1" + (String)ht.find(7).key.toString() + (String)ht.find(7).value.toString());
  }
  
}
