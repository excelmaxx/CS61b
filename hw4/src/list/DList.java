/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
	  head = newNode(null, null, null);
	  head.next = head;
	  head.prev = head;
	  size = 0;
    //  Your solution here.
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
	  DListNode oldFront = head.next;
	  DListNode temp = newNode(item, head, oldFront);
	  oldFront.prev = temp;
	  head.next = temp;
	  size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    // Your solution here.
	  DListNode oldBack = head.prev;
	  DListNode temp = newNode(item, oldBack, head);
	  head.prev.next = temp;
	  head.prev = temp;
	  size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
	  if(isEmpty())
		  return null;
	  else
		  return head.next;
	  
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
	  if(isEmpty())
		  return null;
	  else
		  return head.prev;
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
	  if(node == null)
		  return null;
	  else if(node.next.item == null)
		  return null;
	  else 
		  return node.next;
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
	  if(node == null)
		  return null;
	  else if(node.prev.item == null)
		  return null;
	  else 
		  return node.prev;
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    // Your solution here.
	  if(node == null)
		  return;
	  else
	  {
		  DListNode temp = newNode(item, node, node.next);
		  node.prev.next = temp;
		  node.prev = temp;
		  size++;
	  }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    // Your solution here.
	  if(node == null)
		  return;
	  else{
		  DListNode temp = newNode(item, node.prev, node);
		  node.next.prev = temp;
		  node.next = temp;
		  size++;
	  }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
	  if(node == null)
		  return;
	  else{
		  node.prev.next = node.next;
		  node.next.prev = node.prev;
		  size--;
	  }

  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
/*  public static void main(String[] args){
	  DList list1 = new DList();
	  for(int i = -1; i < 5; i++)
	  {
		  list1.insertBack(i);
	  }
	  System.out.println(list1.toString());
	  System.out.println("Now we are testing DList.");
	  DList sl1 = new DList();
    sl1.insertBack(new Integer(6));
    sl1.insertBack(new Integer(9));
    sl1.insertBack(new Integer(12));
    System.out.println("Here is a list after insertBack 6, 9, 12: "
		       + sl1.toString());
    System.out.println();
    
    
    sl1.insertFront(new Integer(3));
    sl1.insertBack(new Integer(15));
    System.out.println("Here is the same list after insertBack(15) and insertFront(3): "
		       + sl1.toString());
    newtest(sl1);

    testEmpty();
   testAfterInsertFront();
    testAfterinsertBack();
  }
  private static void testEmpty() {
	    DList lst1 = new DList();
	    DList lst2 = new DList();
	    System.out.println();
	    System.out.println("Here is a list after construction: "
			       + lst1.toString());
	    verify(lst1.toString().equals("[  ]"),
			      "toString on newly constructed list failed");
		
	    System.out.println("isEmpty() should be true. It is: " +
			       lst1.isEmpty());
	    verify(lst1.isEmpty() == true,
			      "isEmpty() on newly constructed list failed");    

	    System.out.println("length() should be 0. It is: " +
			       lst1.length());
	    verify(lst1.length() == 0, 
			      "length on newly constructed list failed");    
	    lst1.insertFront(new Integer(3));
	    System.out.println("Here is a list after insertFront(3) to an empty list: "
			       + lst1.toString());
	    verify(lst1.toString().equals("[  3  ]"),
			      "InsertFront on empty list failed");
	    lst2.insertBack(new Integer(5));
	    System.out.println("Here is a list after insertBack(5) on an empty list: "
			       + lst2.toString());
	    verify(lst2.toString().equals("[  5  ]"),
			      "insertBack on empty list failed");
	  }

	  /**
	   *  testAfterInsertFront() tests toString(), isEmpty(), length(),
	   *  insertFront(), and insertBack() after insertFront().  Prints summary
	   *  information of the tests and halts the program if errors are detected.
	   *

	  private static void testAfterInsertFront() {
	    DList lst1 = new DList();
	    lst1.insertFront(new Integer(3));
	    lst1.insertFront(new Integer(2));
	    lst1.insertFront(new Integer(1));
	    System.out.println();
	    System.out.println("Here is a list after insertFront 3, 2, 1: "
			       + lst1.toString());
	    verify(lst1.toString().equals("[  1  2  3  ]"),
			      "InsertFronts on non-empty list failed");
	    System.out.println("isEmpty() should be false. It is: " +
			       lst1.isEmpty());
	    verify(lst1.isEmpty() == false,
			      "isEmpty() after insertFront failed");    
	    System.out.println("length() should be 3. It is: " +
			       lst1.length());
	    verify(lst1.length() == 3, 
			      "length() after insertFront failed");
	    lst1.insertBack(new Integer(4));
	    System.out.println("Here is the same list after insertBack(4): "
			       + lst1.toString());
	    verify(lst1.toString().equals("[  1  2  3  4  ]"),
			      "insertBack on non-empty list failed");
	  }
	    
	  /**
	   *  testAfterinsertBack() tests toString(), isEmpty(), length(),
	   *  insertFront(), and insertBack() after insertBack().  Prints summary
	   *  information of the tests and halts the program if errors are detected.
	   *

	  private static void testAfterinsertBack() {
	    DList lst1 = new DList();
	    lst1.insertBack(new Integer(6));
	    lst1.insertBack(new Integer(7));
	    System.out.println();
	    System.out.println("Here is a list after insertBack 6, 7: "
			       + lst1.toString());
	    System.out.println("isEmpty() should be false. It is: " +
			       lst1.isEmpty());
	    verify(lst1.isEmpty() == false,
			      "isEmpty() after insertBack failed");    
	    System.out.println("length() should be 2. It is: " +
			       lst1.length());
	   verify(lst1.length() == 2, 
			      "length() after insertBackfailed");
	    lst1.insertFront(new Integer(5));
	    System.out.println("Here is the same list after insertFront(5): "
			       + lst1.toString());
	    verify(lst1.toString().equals("[  5  6  7  ]"),
			      "insertFront after insertBack failed");
	  }
	  private static void newtest(DList sl1) {
		  DListNode node = sl1.front();
		  System.out.println();
		  System.out.println("front() should be 3. It is: " +
			       sl1.front().item);
		  System.out.println("front's next should be 6. It is: " +
			       sl1.next(node).item);
		  System.out.println("front's next's prev should be 3. It is: " +
			       sl1.prev(sl1.next(node)).item);
		  sl1.remove(node);
		  System.out.println("After remove the front, the front() should be 6. It is: " +
			       sl1.front().item);
		  node = sl1.front();
		  sl1.insertBefore(new Integer(5),node);
		  sl1.insertAfter(new Integer(8),node);
		  System.out.println("After insertBefore() and insertAfter(), The first 3 nodes should be 5,6,8. The list is: " +
				  sl1.toString());
		  
	  }
	  //
	  static void verify(boolean invariant, String message) {
		    if (!invariant) {
		      System.out.println("*** ERROR:  " + message);
		      Thread.dumpStack();
		    }
  //testing 
   * 
   */
}


