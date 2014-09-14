package dynamicProgramming;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by pavlop on 3/30/14.
 *
 * Имеется 5 неделимых предметов. Для каждого предмета известна его масса (в кг). Величины массы яв-
 ляются натуральными числами. Требуется определить, существует ли среди имеющихся предметов та-
 кой их набор, суммарная масса предметов которого равна 16 кг. Если такой набор существует, то опре-
 делить список предметов в нем.

 *
 */
public class SelectItemsWithMass {
    int numOfCalls = 0;
    //save kes as targetMass_startAt
    Map <String, List> cache = new HashMap<String, List>();


    public List selectItemsToGetRequiredMass(int[] items, int targetMass, int startAt) {
        String key = targetMass+"_"+startAt;
        //System.out.println("start for startAt: "+startAt + " target: "+ targetMass);
        if (cache.containsKey(key)) return cache.get(key);
        numOfCalls++;

        if(startAt >= items.length) {cache.put(key, null); return null;}
        if(targetMass < 0) {cache.put(key, null); return null;}

        //first item completes the target sum?
        if (targetMass - items[startAt] == 0) {
            List c = new  ArrayList();
            c.add(items[startAt]);
            cache.put(key, c);
            return c;
        }

        List withElement, withoutElement ; //= new ArrayList();


        withElement = selectItemsToGetRequiredMass(items, targetMass - items[startAt], startAt+1);
        if (withElement != null && !withElement.isEmpty()) {
            withElement.add(items[startAt]);
            cache.put(key, withElement);
            return withElement;
        } else {
            withoutElement = selectItemsToGetRequiredMass(items, targetMass, startAt+1);
            cache.put(key, withoutElement);
            return withoutElement;
        }


    }


    @Test
    public void testSelectItemsForMass() {
        int[] masses = {1,4,5,7};
        int[] massesNotOrdered = {5,4,1,7};
        int tartgetMass = 12;

        cache.clear();
        List actual = selectItemsToGetRequiredMass(masses, tartgetMass, 0);
        Collections.sort(actual);

        cache.clear();
        List actual2 = selectItemsToGetRequiredMass(massesNotOrdered, tartgetMass ,0);
        Collections.sort(actual2);

        System.out.println("actual="+actual);
        System.out.println("actual2=" + actual2);
        System.out.println("numOfCalls = "+ numOfCalls);

        assertTrue(Iterables.elementsEqual(actual, Arrays.asList(1,4,7)));
        assertTrue(Iterables.elementsEqual(actual2, Arrays.asList(5, 7)));

    }


    @Test
    public void testSelectItemsForMassBig() {
        cache.clear();
        int[] masses = {20 ,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int tartgetMass = 1000;
        List actual = selectItemsToGetRequiredMass(masses, tartgetMass, 0);
        System.out.println("actua Big l="+actual);
        System.out.println("numOfCalls = "+ numOfCalls);


    }

}
