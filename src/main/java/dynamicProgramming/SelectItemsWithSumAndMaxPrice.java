package dynamicProgramming;

import org.junit.Test;

import java.util.*;

/**
 * Задача для самостоятельного решения 4.1.
 Имеется N неделимых предметов. Для каждого предмета известна его стоимость (в руб.) и масса (в кг).
 Величины стоимости и массы являются натуральными числами. Требуется определить, существует ли
 такой набор предметов из предложенных, суммарная масса которого равна К кг. Если искомый набрр
 существует, то определить такой список предметов в наборе, чтобы их суммарная стоимость была мак-
 симальной. Вывести номера предметов найденного набора и их суммарную стоимость.
 Например, при
 N = 5, К = 16,
 C1 = 5, M1 = 4,
 С2 = 7, М2 = 5,
 С3 = 4, М3 = 3,
 С4 = 9, M4 = 7,
 С5 = 8, М5= 6
 искомый набор будет состоять из предметов с номерами 1, 2, 4 и их стоимость будет равна 21 рублю.
 */


public class SelectItemsWithSumAndMaxPrice {

    int numOfCalls = 0;
    int numOfWithoutOptimization = 0;
    int puttingCount;
    //save kes as targetMass_startAt
    Map<String, List<Integer>> cache = new HashMap<String, List<Integer>>();


    public void selectItemsWithSumAndMaxPrice(int[] masses, int[] prices, int targetMass) {
        //step 1:
        // select all possible combintations
        // after that calculate their values
       // Map<String, List> = new HashMap<>()
        selectItemsToGetRequiredMassAllVariants(masses, targetMass, 0);
        System.out.println("cache="+cache);
        int maxPrice = 0;
        List bestItemsList = null;
        for(String key : cache.keySet()) {
            String[] dubleKey = key.split("_");
            if (dubleKey[0].equals(targetMass+"") && cache.get(key) != null) {
                List<Integer> usedItems = cache.get(key);
                System.out.println("candidateList:"+usedItems);
                int priceTotal = 0;
                for (Integer masID: usedItems ) {
                    priceTotal += prices[masID];
                }
                if (priceTotal > maxPrice) {
                    maxPrice = priceTotal;
                    bestItemsList = usedItems;
                }
            }
        }
        System.out.println("bestItemsList"+ bestItemsList);


    }

    public void selectItemsToGetRequiredMassAllVariants(int[] items, int targetMass, int startAt) {
        String key = targetMass+"_"+startAt;
        numOfWithoutOptimization++;
        //System.out.println("start for startAt: "+startAt + " target: "+ targetMass);
        if (cache.containsKey(key)) return;
        numOfCalls++;

        if(targetMass < 0) {putIfNotExist(key, null); return ;}
        if(startAt >= items.length) {putIfNotExist(key, null); return ;}

        //first item completes the target sum?
        if (targetMass - items[startAt] == 0) {
            List c = new ArrayList<Integer>();
            System.out.println("putting:"+key);
            c.add(startAt);
            putIfNotExist(key, c);
            return;
        }

        List withElement, withoutElement;
        int massAfterIncludingCur=targetMass - items[startAt];
        selectItemsToGetRequiredMassAllVariants(items, massAfterIncludingCur, startAt + 1);
        withElement = cache.get(massAfterIncludingCur+"_"+(startAt+1));
        //System.out.println("withElement="+withElement);
        if (withElement != null && !withElement.isEmpty()) {
            System.out.println("withElement:"+massAfterIncludingCur+"_"+(startAt+1)+ " was GOD. Addidng "+key);
            withElement.add(startAt);
            putIfNotExist(key, withElement);
        } else {
            System.out.println("withElement:"+massAfterIncludingCur+"_"+(startAt+1)+ " was BAD");
            putIfNotExist(key, null);
        }

        //try to skip current element
        selectItemsToGetRequiredMassAllVariants(items, targetMass, startAt + 1);
        withoutElement = cache.get(targetMass+"_"+(startAt+1));
        if (withoutElement != null && !withoutElement.isEmpty()) {
            System.out.println("withoutElement:"+targetMass+"_"+(startAt+1)+ " was GOD. Adding "+key);
            putIfNotExist(key, withoutElement);
        } else {
            System.out.println("withoutElement:"+targetMass+"_"+(startAt+1)+ " was BAD");
            putIfNotExist(key, null);
        }

    }
      
    private void  putIfNotExist(String key, List mass){
        if(!cache.containsKey(key) || ( mass!=null )) {
            puttingCount++;
            if(key.startsWith("5")) {
                System.out.println("!!!!!putiing list:" + mass);
            }
            cache.put(key, mass);
        }

    }

    @Test
    public void testSelectItemsForMass() {
        int[] masses = {0,1,2,3,4,5};
        int tartgetMass = 5;
        selectItemsWithSumAndMaxPrice(masses, new int[] {1,1,1,1,1,1,1,1,1,1}, tartgetMass);
        System.out.println("numOfWithoutOptimization=" + numOfWithoutOptimization);
        System.out.println("numOfCalls="+numOfCalls);
        System.out.println("puttings="+puttingCount);
    }

}
