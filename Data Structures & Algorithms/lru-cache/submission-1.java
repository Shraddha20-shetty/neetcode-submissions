

class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        detach(node);
        insertToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            detach(old);
            map.remove(key);
        }
        if (map.size() == capacity) {
            evict(tail.prev); 
        }
        Node node = new Node(key, value);
        insertToFront(node);
        map.put(key, node);
    }

    private void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void evict(Node node) {
        map.remove(node.key);
        detach(node);
    }

    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    
}
