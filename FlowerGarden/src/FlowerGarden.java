import com.sun.tools.javac.comp.Flow;

import java.util.*;

public class FlowerGarden {
    int[] h, b, w;

    public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
        int n = height.length; h = height; b = bloom; w = wilt;
        int res[] = new int[n];

        for(int i = 0; i < n; i++) {
            int bestPos = -1;
            for(int j = 0; j < n; j++) {
                boolean ok = true;
                for(int k=0; ok && k<n; k++) {
                    if(h[k] != -1 && k!=j) ok = front(j, k);
                }
                if(ok && (bestPos == -1 || h[j] > h[bestPos]))
                        bestPos = j;
            }
            res[i] = h[bestPos];
            h[bestPos] = -1;
        }
        return res;
    }

    boolean front(int i, int j) {
        if((b[i] >= b[j] && b[i] <= w[j])||
                (b[j] >= b[i] && b[j] <= w[i]))
            return h[i] < h[j];
        return true;
    }

//    class Flower implements Comparable {
//        Flower(int height, int bloom, int wilt) {
//            this.height = height;
//            this.bloom = bloom;
//            this.wilt = wilt;
//        }
//
//        int height;
//        int bloom;
//        int wilt;
//
//        @Override
//        public int compareTo(Object o) {
//            Flower that = (Flower) o;
//            if (isOverlapping(this, that)) {
//                return  this.height - that.height;
//            } else {
//                return  that.height - this.height;
//            }
//            if(this.height > that.height)
//                return isOverlapping(this, that)? 1: -1;
//            else
//                return isOverlapping(this, that)? -1: 1;
//        }
//        @Override
//        public String toString() {
//            return  " "+height;
//        }
//    }
//
//    public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
//        Flower[] flowers = new Flower[height.length];
//        for (int i = 0; i < flowers.length; i++) flowers[i] = new Flower(height[i], bloom[i], wilt[i]);
////        Arrays.sort(flowers);
////        System.out.println("flowers"+Arrays.toString(flowers));
//
//        List<Flower> res = new LinkedList<>();
//        for(Flower candidate: flowers) {
//            int finalPos = res.size()-1;
//            int posToInsert = res.size()-1;
//            for(;posToInsert >= 0; --posToInsert) {
//                if(candidate.compareTo(res.get(posToInsert)) < 0) {
//                    continue;
//                } else{
//                    finalPos = posToInsert;
//                    //break;
//                }
//            }
//            res.add(finalPos + 1, candidate);
//        }
//        System.out.println("res"+res);
//
//        int[] sortedHeights = new int[flowers.length];
//        for (int i = 0; i < flowers.length; i++) sortedHeights[i] = res.get(i).height;
//        return sortedHeights;
//    }

   /* public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
        List<Integer> result = new ArrayList<>();


        for (int candId = 0; candId < height.length; candId++) {
            int posToInsert=0;
            while (posToInsert < result.size()) {
                if(shouldGoAfterCur(candId, posToInsert, height, bloom, wilt)) posToInsert++;
                else break;
            }
            result.add(posToInsert, candId);
        }

        //convert to result
        int[] resArr = new int[height.length];
        for(int i = 0; i < height.length; i++)
            resArr[i] = height[result.get(i)];

        System.out.println(result);
        System.out.println(Arrays.toString(resArr));
        return resArr;
    }


    boolean shouldGoAfterCur(int candId, int curId, int[] height, int[] bloom, int[] wilt) {
        if (isOverlapping(bloom[curId], wilt[curId], bloom[candId], wilt[candId])) {
            return height[candId] > height[curId];
        } else {
            return height[candId] < height[curId];
        }
    }*/



/*

        public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
//        System.out.println("bloom="+Arrays.toString(bloom));
//        System.out.println("wilt="+Arrays.toString(wilt));
//        System.out.println("heignts="+Arrays.toString(height));
        printArrays(bloom, wilt, height);

        boolean[] alreadyInGroup = new boolean[bloom.length];
        List<LinkedList<Integer>> groups = new ArrayList<LinkedList<Integer>>();

        for (int startGroup = 0; startGroup < bloom.length; startGroup++) {
            if(alreadyInGroup[startGroup]) continue;

            LinkedList<Integer> group = new LinkedList<>();
            LinkedList<Integer> candidates = new LinkedList<>();
            candidates.addLast(startGroup);

            while (candidates.size() > 0) {
                System.out.println("candidates="+candidates);
                int cand = candidates.getFirst();
                candidates.removeFirst();

                //add candidate to group
                group.add(cand);
                alreadyInGroup[startGroup] = true;

                int candBloom = bloom[cand];
                int candWilt = wilt[cand];

                //find all the elements that are overlapped with current one
                for(int i = 0; i < bloom.length; i ++) {
                    if(alreadyInGroup[i]) continue;
                    int curBloom = bloom[i];
                    int curWilt = wilt[i];

                    if(isOverlapping(candBloom, candWilt, curBloom, curWilt)) {
                        alreadyInGroup[i] = true;
                        candidates.addLast(i);
                    }
                }
            }
            if(group.size() > 0) groups.add(group);
        }
        System.out.println("groups="+groups);


        //convert groups to heights
        for (List<Integer> group:groups) {
            for(int i = 0; i < group.size(); i++)
                group.set(i, height[group.get(i)]);
        }

        //sort ascending each group
        for (List<Integer> group:groups) {
            Collections.sort(group);
        }

        ArrayList<Integer> res = new ArrayList<>();
//        System.out.println("groups sorted="+groups);


        while (!groups.isEmpty()) {
            int maxGroupId = 0;
            for (int groupId = 1; groupId<groups.size(); groupId++) {

                if(groups.get(groupId).peek() > groups.get(maxGroupId).peek() ) {
                    maxGroupId = groupId;
                }
            }

            res.add(groups.get(maxGroupId).pop());
            if(groups.get(maxGroupId).size() == 0) groups.remove(maxGroupId);
        }

        System.out.println("res=" + res);
        int[] resArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) resArray[i] = res.get(i);

        return resArray;
	}
*/
    boolean isOverlapping (int a1, int a2, int b1, int b2) {
        if(a1 >= b1 && a1 <= b2) return true;
        if(a2 >= b1 && a2 <= b2) return true;
        if(b1 >= a1 && b1 <= a2) return true;
        if(b2 >= a1 && b2 <= a2) return true;
        return false;
    }

//    boolean isOverlapping (Flower f1, Flower f2) {
//        if(f1.bloom >= f2.bloom && f2.wilt >= f1.bloom) return true;
//        if(f1.bloom <= f2.bloom && f1.wilt >= f2.bloom) return true;
//
//        return false;
//    }

    public void printArrays(int[] ... arrays) {

        for (int[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%03d ", arr[i]);
            System.out.println();
        }
    }

    public void swap(int from, int to, int[] ... arrays) {
        for (int[] arr : arrays) {
            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
        }
    }

}
