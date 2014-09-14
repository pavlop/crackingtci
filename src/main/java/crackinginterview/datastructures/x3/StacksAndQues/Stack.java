package crackinginterview.datastructures.x3.StacksAndQues;
import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import crackinginterview.datastructures.x2.linkedLists.SingleNode;
import junit.framework.TestCase;
import junit.framework.Test;
//import org.junit.Test;

import java.util.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value=Suite.class)
@SuiteClasses(value={TestCase.class})
public class Stack <T>  {
    private SingleNode<T> head;
    public int size = 0;
    private boolean empty;

    public void push(T value) {
        SingleNode<T> node = new SingleNode<T>(value);
        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public T pop (){
        if (size>0) {
            SingleNode<T> res = head;

            head = head.next;
            size--;
            return res.value;
        } else  {
            return null;
        }
    }

    public T peek () {
        if(head == null) return null;
        return head.value;

    }

    @Override
    public String toString() {
        String res = "";
        Stack <T> stackCopy = new Stack<T>();
        while (size >0 ) {
            T value = pop();
            stackCopy.push(value);
            res += value + ",";
        }

        while (stackCopy.size >0 ) {
            push(stackCopy.pop());
        }


        return res;
    }

    @org.junit.Test
    public Test myListTestSuite() {
        return ListTestSuiteBuilder.using(
                // This class is responsible for creating the collection
                // And providing data, which can be put into the collection
                // Here we use a abstract generator which will create strings
                // which will be put into the collection
                new TestStringListGenerator() {
                    @Override
                    protected List<String> create(String[] elements) {
                        // Fill here your collection with the given elements
                        return new ArrayList<String>(Arrays.asList(elements));
                    }
                })
                // The name of the test suite
                .named("My List Tests")
                        // Here we give a hit what features our collection supports
                .withFeatures(ListFeature.GENERAL_PURPOSE,
                        CollectionFeature.ALLOWS_NULL_VALUES,
                        CollectionFeature.SERIALIZABLE,
                        CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
                        CollectionSize.ANY)
                .createTestSuite();
    }

    public boolean isEmpty() {
        return empty;
    }
}
