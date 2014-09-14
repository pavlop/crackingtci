import javafx.beans.binding.When;

import java.util.*;

public class Egalitarianism3Easy {
    class Link {
        int a, b;

        @Override
        public boolean equals(Object o) {
            Link node = (Link) o;
            if (a == node.a && b == node.b) return true;
            if (a == node.b && b == node.a) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return a + b;
        }

        Link(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
	
	public int maxCities(int n, int[] a, int[] b, int[] len) {

        if (n == 1) return 1;
		int[][] distances = new int[n][n];

        for (int i = 0; i <n; i++ )
            for (int j = 0; j < n; j++ )
                distances[i][j] = 10_000;

        for (int i = 0; i < n-1; i++) {
            distances[a[i] - 1][b[i] - 1] = len[i];
            distances[b[i] - 1][a[i] - 1] = len[i];
        }
//        printArrays(distances);
//        System.out.println();

        for (int repeantJustInCaseSeveralTimes = 0;repeantJustInCaseSeveralTimes<n;repeantJustInCaseSeveralTimes++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);

//        printArrays(distances);
        //System.out.println(Arrays.deepToString(distances));

        Set<Integer> uniqueDisits = new HashSet<Integer>();
        for (int i = 0; i < n; i++ )
            for (int j = 0; j < n; j++ )
                uniqueDisits.add(distances[i][j]);


        int maxResult = 0;
        for (Integer dist: uniqueDisits) {
            Set<Link> links = getLinksWithDistance(dist, distances);
            Set<Integer> nodes = getNodesWithDistance(dist, distances);
            maxResult = Math.max(maxResult, getMaxAllConnected(links, nodes));
        }
        return maxResult;
	}

    Set<Link> getLinksWithDistance(int dist,  int[][] distances) {
        Set<Link> res = new HashSet<>();
        for (int i = 0; i < distances.length; i++)
            for (int j = i+1; j < distances.length; j++)
                if (dist == distances[i][j]) res.add(new Link(i, j));
        return res;
    }

    Set<Integer> getNodesWithDistance (int dist,  int[][] distances) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < distances.length; i++) {
            for (int j = i+1; j < distances.length; j++) {
                if (dist == distances[i][j]) {
                    res.add(i); res.add(j);
                }
            }
        }
        return res;
    }

    int getMaxAllConnected(Set<Link>  links, Set<Integer> nodes) {
        int maxCaonnected = 0;
        for (Integer startingNode : nodes) {
            Set<Integer> alradyConnected = new HashSet<>();
            alradyConnected.add(startingNode);

            Set<Integer> candidates = getAllLinkedNodes(links, startingNode);
            while(!candidates.isEmpty()) {
                Integer candidate = candidates.iterator().next();
                candidates.remove(candidate);
                if (isConnectedWithAll(alradyConnected, links, candidate)) {
                    alradyConnected.add(candidate);
                    candidates.addAll(getAllLinkedNodes(links, candidate));
                }
            }
            maxCaonnected = Math.max(maxCaonnected, alradyConnected.size());
        }
        return maxCaonnected;
    }

    Set<Integer> getAllLinkedNodes(Set<Link> links, Integer node) {
        Set<Integer> res = new HashSet<>();
        for (Link link:links) {
            if(link.a == node) res.add(link.b);
            if(link.b == node) res.add(link.a);
        }
        return res;
    }

    boolean isConnectedWithAll(Set<Integer> alradyConnected, Set<Link> links, Integer candidate) {
        for (int alreadyConnected: alradyConnected) {
            if(!links.contains(new Link(alreadyConnected, candidate))) return false;
        }
        return true;
    }
//
//    List<Integer> indexesOf(int num, int[] a) {
//        List<Integer> res = new ArrayList<Integer>();
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == num) res.add(i);
//        }
//        return res;
//    }

    public void printArrays(int[] ... arrays) {

        for (int[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) System.out.format("%05d ", arr[i]);
            System.out.println();
        }
    }
}
