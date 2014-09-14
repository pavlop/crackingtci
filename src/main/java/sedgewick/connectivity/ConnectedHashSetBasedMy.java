package sedgewick.connectivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by pavlop on 3/19/14.
 */
public class ConnectedHashSetBasedMy implements DynamicConnectivity {

    private List<HashSet<Integer>> connectedAreas =  new ArrayList<HashSet<Integer>>();

    @Override
    public void connect(Integer element1, Integer element2) {
        Set set1 = getConnectedSetForElement(element1);
        Set set2 = getConnectedSetForElement(element2);
        if (set1 == set2 && set1 !=null ) {
            //already connected
            //do nothing
        }
        else if(set1 == null&& set2 == null) {
            createAreaForElement(element1).add(element2);
        } else if (set1 == null) {
            set2.add(element1);
        } else if (set2 == null) {
            set1.add(element2);
        } else {
            //two areas need to be connected
            connectedAreas.remove(set2);
            set1.addAll(set2);
        }
    }

    @Override
    public boolean isConnected(Integer element1, Integer element2) {
        Set set1 = getConnectedSetForElement(element1);
        Set set2 = getConnectedSetForElement(element2);
        System.out.println("isconnected set1:"+set1+" set2:"+set2);
        if(set1 == set2) {
            return true;
        }
        return false;
    }

    private Set getConnectedSetForElement (Integer element) {
        for(Set set: connectedAreas) {
            if(set.contains(element)) return set;
        }
        return null;
    }

    private Set createAreaForElement(Integer element) {
        HashSet newArea = new HashSet();
        connectedAreas.add(newArea);
        newArea.add(element);
        return newArea;
    }


    @Test
    public void doTesting() {
        DynamicConnectivity connectivity = new ConnectedHashSetBasedMy();
        connectivity.connect(1, 2);
        connectivity.connect(3, 4);
        connectivity.connect(5, 6);
        connectivity.connect(6,7);

        assertTrue(connectivity.isConnected(1, 2));
        assertTrue(connectivity.isConnected(5,7));
        assertTrue(connectivity.isConnected(7,5));
        assertFalse(connectivity.isConnected(7, 1)); 
        
        connectivity.connect(4,5);
        connectivity.connect(1,4);
        assertTrue(connectivity.isConnected(7, 1));
    }
}
