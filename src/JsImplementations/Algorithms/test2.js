var oddEvenList = function(head) {
    if (!head) return null;
    if (!head.next) return head;
    let node = head.next.next;
    let isOdd = true;
    let oddTail = head;
    let evenTail = head.next;
    let evenHead = head.next;

    // Track tail of odd and even than connect them
    while(node) {
        if (isOdd) {
            oddTail.next = node;
            oddTail = oddTail.next;
        } else {
            evenTail.next = node;
            evenTail = evenTail.next;
        }
        node = node.next;
        isOdd = !isOdd;
    }
    oddTail.next = evenHead;
    // Clean up last element to prevent cycle
    evenTail.next = null;
    
    return head;
};

class Node {
    constructor(val, next) {
        this.val = val || 0;
        this.next = next || null;
    }
}

let head = new Node(5);
head.next = new Node(6);
head.next.next = new Node(7);
head.next.next.next = new Node(8);
head.next.next.next.next = new Node(9);

console.log(oddEvenList(head).next.next.next);