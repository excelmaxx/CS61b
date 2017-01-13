public class Solution {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1 || head == null){
            return head;
        }
        int i2 = k-1;        //we need two index here for easy here has another i2
        int temp1 = 0;
        int temp2 = 0;
        ListNode node1, node2;
        ListNode node1B, node2B;
        for(int i = 0; i < k/2; i++){
            //find the value of i and i2
            node1 = head;
            node1B = null;
            int j = 0;
            for( ; j < i; j++){
                node1B = node1;
                node1 = node1.next;
            }
            temp1 = node1.val;
            
            node2 = node1;
            node2B = node1B;
            for( ; j < i2; j++){
                node2B = node2;
                node2 = node2.next;
            }
            temp2 = node2.val;
            //add new two nodes 
            //
            System.out.println(temp1);
            System.out.println(temp2);            
            ListNode tempNode = node1.next;
            ListNode newhead = null;
            if(node1 == head){   // when the node is head, we have to handle a new head
                newhead = new ListNode(temp2);
                newhead.next = tempNode;
            }else{
            	if(node1B == head) node1B = newhead;
                node1B.next = new ListNode(temp2);
                node1B.next.next = tempNode;
                
            }
                //node2 is from k to k/2 or k/2+1
            tempNode = node2.next;
            node2B.next = new ListNode(temp1);
            node2B.next.next = tempNode;
            //remove old two nodes 
            //there are no reference to them and the java can remove them
            //i2--
            i2--;
        }
        return newhead;
    }
    public static void main(String[] args){
    	ListNode head = new ListNode(0);
    	ListNode node = head;
    	for(int i = 1; i < 10; i++){
    		node.next = new ListNode(i);
    		node = node.next;
    	}
    	ListNode newHead = reverseKGroup(head, 5);
    	System.out.println(newHead.val);
    	
    }
}