

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            // Step 1: find kth node
            ListNode node = prev;
            for (int i = 0; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) break; // fewer than k nodes left

            // Step 2: mark group boundaries
            ListNode start = prev.next;
            ListNode next = node.next;

            // Step 3: reverse k nodes
            ListNode curr = start;
            ListNode prevNode = next;
            while (curr != next) {
                ListNode temp = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = temp;
            }

            // Step 4: reconnect
            prev.next = node;
            prev = start;
        }

        return dummy.next;
    }
}
