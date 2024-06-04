package homework3;

import java.util.ArrayList;

public class Node<Entry> {
    public String key;
    public ArrayList<Entry> values;
    public Node<Entry> left, right;
    public boolean color;

    public Node(String key, Entry value) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
        this.left = null;
        this.right = null;
        this.color = true;
    }

}
