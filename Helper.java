public final class Helper {
    public static Node str2list(String s) {
        Node head = new Node();
        Node p = head;
        for (char c: s.toCharArray()) {
            Node n = new Node(c);
            p.next = n;
            p = p.next;
        }
        return head.next;
    }

    public static boolean areEqual(Node p, Node q) {
        while (p != null && q != null) {
            if (p.d_char == q.d_char) {
                p = p.next;
                q = q.next;
            } else {
                return false;
            }
        }

        if (p != null || q != null)
            return false;

        return true;
    }
}