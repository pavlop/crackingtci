package sedgewick.connectivity;

/**
 * Created by pavlop on 3/19/14.
 */
public interface DynamicConnectivity {


    public void connect(Integer element1, Integer element2);
    public boolean isConnected(Integer element1, Integer element2);
}
