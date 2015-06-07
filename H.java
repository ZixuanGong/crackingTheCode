public final class H {
    public static Node toCharList(String s) {
        Node head = new Node();
        Node p = head;
        for (char c: s.toCharArray()) {
            Node n = new Node(c);
            p.next = n;
            p = p.next;
        }
        return head.next;
    }

    public static Node toNumList(String s) {
        Node head = new Node();
        Node p = head;
        for (char c: s.toCharArray()) {
            Node n = new Node(Character.getNumericValue(c));
            p.next = n;
            p = p.next;
        }
        return head.next;
    }


    public static Node num2list(int n) {
        Node head = new Node();
        int val;

        while (n > 0) {
            val = n % 10;
            n /= 10;

            Node node = new Node(val);
            node.next = head.next;
            head.next = node;
        }
        return head.next;
    }

    public static boolean areEqualLists(Node p, Node q) {
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

    public static boolean areEqual(Node p, Node q) {
        return p.d_char == q.d_char ? true : false;
    }

    public static void printCharList(Node head) {
        Node p = head;
        while (p != null) {
            System.out.print(p.d_char + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void printNumList(Node head) {
        Node p = head;
        while (p != null) {
            System.out.print(p.d_int + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static int getLen(Node head) {
        Node p = head;
        int len = 0;

        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }
}