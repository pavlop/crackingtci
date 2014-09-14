package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows == 0) return result;

        ArrayList<Integer> level0 = new ArrayList<Integer>();
        level0.add(1);
        result.add(level0);

        for (int level = 1; level < numRows; level++) {
            ArrayList<Integer>  curLevel = new ArrayList<Integer>();
            int curLevelSize = result.get(level-1).size() + 1;
            for(int i = 0; i < curLevelSize; i++) {
                if (i == 0 || i == curLevelSize - 1) curLevel.add(1);
                else curLevel.add(result.get(level-1).get(i-1) + result.get(level-1).get(i));
            }
            result.add(curLevel);
        }
        return result;
    }
}