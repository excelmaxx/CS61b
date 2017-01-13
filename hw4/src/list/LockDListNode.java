package list;

public class LockDListNode extends DListNode{
	
	boolean lockState;
	
	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
		lockState = false; 
		// TODO Auto-generated constructor stub
	}
	
	
}