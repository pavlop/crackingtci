package crackinginterview.datastructures.x2.linkedLists;

/**
 * Created by pavlop on 3/13/14.
 */
public class SingleNode<T> {
    public T value;
    public SingleNode<T> next;

    public SingleNode(T value, SingleNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public SingleNode(T value) {
        this.value = value;
        this.next = null;
    }

    public String toString(){
        return value+"";
    }
}
