package crackinginterview.x8.dynamic;

import java.util.*;

/**
 * C Write a method to return all subsets of a set.
 */
public class SubsetsOfASet {
    public Collection<Set> allSubsets = new HashSet<Set>();

    public void getAllSubsets (List sourceset, int startAt) {

        System.out.println("getAllSubsets startAt="+startAt);
        if (startAt >= sourceset.size()) return;

        Collection<Set> tmp = new HashSet<Set>();
        for(Set set : allSubsets) {
            Set newSetWitCur = new HashSet(set);
            newSetWitCur.add(sourceset.get(startAt));
            tmp.add(newSetWitCur);
        }
        allSubsets.addAll(tmp);

        Set subsetWithSingle = new HashSet();
        subsetWithSingle.add(sourceset.get(startAt));
        allSubsets.add(subsetWithSingle);
        getAllSubsets(sourceset, startAt+1);
    }

    public static void main (String s[]) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        SubsetsOfASet subsetsClass = new SubsetsOfASet();
        subsetsClass.getAllSubsets(list, 0);
        System.out.println(subsetsClass.allSubsets);


    }
}
