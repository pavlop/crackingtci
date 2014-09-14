package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class WordLadderTimeLimit2 {
    String start, end;
    Set<String> dict;
    Map<String, Integer> shortestPathsToWord = new HashMap<String, Integer>();
    int globalMinimumPath = Integer.MAX_VALUE;
    List<LinkedHashSet<String>> globalRes = new LinkedList<LinkedHashSet<String>>();

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        this.start = start; this.end = end; this.dict = dict;
        findLadders(new LinkedHashSet<String>(Arrays.asList(new String[]{start})), start);

        List<List<String>> result = new ArrayList<List<String>>();
        for (LinkedHashSet<String> path:globalRes) {
            if (path.size() == globalMinimumPath) result.add(new ArrayList<>(path));
        }
        return result;
    }

    public void findLadders(LinkedHashSet<String> curRes, String lastElement) {
        if(lastElement.equals(end)) {
            globalRes.add(curRes);
            globalMinimumPath = Math.min(globalMinimumPath, curRes.size());
            return;
        }

        if(curRes.size() >= globalMinimumPath) return;

        Set<String> candidates = getNotVisistedCandidates(lastElement, curRes);
//        filterNotVisitedCandidates(candidates, curRes);
        filterAndUpdatetShortestPaths(candidates, curRes.size());

        for (String cand:candidates) {
            LinkedHashSet<String> newRes = new LinkedHashSet<String>(curRes);
            newRes.add(cand);
            findLadders(newRes, cand);
        }
    }

    public Set<String> getNotVisistedCandidates(String word, Set<String> visited) {
        Set<String> candidates = new HashSet<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c<='z'; c++) {
                StringBuilder newStrB = new StringBuilder(word);
                newStrB.replace(i, i+1, ""+c);
                String newStr = newStrB.toString();
                if(dict.contains(newStr) && !visited.contains(newStr)) {
                    candidates.add(newStr);
                }
            }
        }
        return candidates;
//        for (String cand:dict) {
//            if (isOneCharDiff(cand, word)) {
//                candidates.add(cand);
//            }
//        }
    }


//    public void filterNotVisitedCandidates(Set<String> candidates, List<String> curRes) {
//        for (String visited:curRes) {
//            if(candidates.contains(visited)) candidates.remove(visited);
//        }
//    }

    public void filterAndUpdatetShortestPaths(Set<String> candidates, int curPath) {
        for (Iterator<String> it = candidates.iterator(); it.hasNext(); ) {
            String word = it.next();
            if(!shortestPathsToWord.containsKey(word)) {
                shortestPathsToWord.put(word, curPath);
            } else {
                int existingPaths = shortestPathsToWord.get(word);
                if (curPath > existingPaths) {
                    it.remove();
                } else {
                    shortestPathsToWord.put(word, curPath);
                }
            }
        }
    }



    @Test
    public void test() {
        Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList(new String[]{"hot","cog","dot","dog","hit","lot","log"}));
        Assert.assertEquals(5, findLadders("hit", "cog", dict));
    }
}
