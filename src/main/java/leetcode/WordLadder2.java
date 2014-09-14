package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class WordLadder2 {

    public ArrayList<ArrayList<String>> findLadders(String start, String end, Set<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if (start == null || end == null) return res;
        ArrayList<String> tmparray = new ArrayList<String>();

        //If the start and end are equal, direct return
        if (start.equals(end)) {
            tmparray.add(start);
            tmparray.add(end);
            res.add(tmparray);
            return res;
        }

        //Create a hashmap, save all precursors of each node.
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        //If using bfs layer preorder queue there end so end traversal (the shortest one on the end)
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        ArrayList<String> currentlevel = new ArrayList<String>();
        while (!queue.isEmpty()) {
            currentlevel.clear();
            while (!queue.isEmpty()) {
                String top = queue.poll();
                if (dict.contains(top)) dict.remove(top);
                currentlevel.add(top);
            }

            //Each cycle of each String char from a through z, dict inside look at whether there are
            for (String curs : currentlevel) {
                for (int i = 0; i < curs.length(); ++i) {
                    for (char j = 'a'; j <= 'z'; ++j) {
                        char[] tmpchar = curs.toCharArray();
                        tmpchar[i] = j;
                        String tmps = new String(tmpchar);
                        if (tmps.equals(end)) {
                            map.get(end).add(curs);
                            queue.offer(tmps);
                        }
                        else if (!tmps.equals(curs) && dict.contains(tmps)) {
                            if (!queue.contains(tmps)) queue.offer(tmps);
                            map.get(tmps).add(curs);
                        }
                    }
                }
            }
            if (queue.contains(end))
                break;
        }
        tmparray.add(end);
        buildpath(start, end, map, tmparray, res);
        return res;
    }

    //According precursor to generate a path node
    public void buildpath(String start, String end,
                          HashMap<String, ArrayList<String>> map, ArrayList<String> tmparray,
                          ArrayList<ArrayList<String>> res) {
        ArrayList<String> pre = map.get(end);
        if (end.equals(start)) {
            ArrayList<String> tmparray2 = new ArrayList<String>(tmparray);
            Collections.reverse(tmparray2);
            res.add(tmparray2);
            return;
        }
        for (String s: pre) {
            tmparray.add(s);
            buildpath(start, s, map, tmparray, res);
            tmparray.remove(tmparray.size() - 1);
        }

    }

    @Test
    public void test() {
        Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList(new String[]{"hot","cog","dot","dog","hit","lot","log"}));
        Assert.assertEquals("[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]", ""+findLadders("hit", "cog", dict));
    }

}
