package sedgewick.connectivity;

/**
 *
 *
 *
 */
public class QuickUnionWeighted implements DynamicConnectivity {
    private Integer[] parents;
    private Integer groupCount[];

    public QuickUnionWeighted(int size) {
        parents = new Integer[size];
        groupCount = new Integer[size];
    }

    public void connect(Integer element1, Integer element2) {
        if (parents[element1] == null) {
            parents[element1] = element1;
            groupCount[element1] = 1;
        }
        if (parents[element2] == null) {
            parents[element2] = element2;
            groupCount[element2] = 1;
        }
        int firstRoot = getRoot(element1);
        int secondtRoot = getRoot(element2);
        if (groupCount[firstRoot] > groupCount[secondtRoot]) {
            parents[firstRoot] = secondtRoot;
            groupCount[secondtRoot] += groupCount[firstRoot];
        } else {
            parents[secondtRoot] = firstRoot;
            groupCount[firstRoot] += groupCount[secondtRoot];
        }
    }

    public boolean isConnected(Integer element1, Integer element2) {
        int firstRoot = getRoot(element1);
        int secondtRoot = getRoot(element2);
        return firstRoot == secondtRoot;
    }

    private Integer getRoot(Integer element1) {
        int id = element1;
        while (parents[id] != null && parents[id] != id) {
            id = parents[id];
        }
        return id;
    }

}
