import java.util.*;

public class DungeonEscape {
    public String[] up, down, east, west;
    int WIDTH, HEIGHT;

    public boolean[][] visited;
	public int exitTime(String[] up, String[] down, String[] east, String[] west, int startLevel, int startEasting) {
        HEIGHT = up.length;
        WIDTH = up[0].length();

        visited = new boolean[HEIGHT][WIDTH];
        this.up = up;
        this.down = down;
        this.east = east;
        this.west = west;

        SortedSet<DungeonNode> graph = new TreeSet<DungeonNode>();
        DungeonNode node = new DungeonNode(0, startEasting, startLevel);
        graph.add(node);

        while (!graph.isEmpty()) {
            node = graph.first();
            graph.remove(node);

            if(node.level == -1) return node.time;
            if (isLevelFilledWithWater(node.level, node.time)) continue;


            visited[node.level][node.easting] = true;


            // Create possible paths
            List<DungeonNode> possibleWays = new ArrayList<DungeonNode>();
            possibleWays.add(new DungeonNode(getCostUpFrom(node), node.easting, node.level - 1));
            possibleWays.add(new DungeonNode(getCostEastFrom(node), node.easting + 1, node.level));
            possibleWays.add(new DungeonNode(getCostWestFrom(node), node.easting - 1, node.level));
            possibleWays.add(new DungeonNode(getCostDownFrom(node), node.easting, node.level + 1));

            for (DungeonNode possibleNode : possibleWays) {
                if (possibleNode.time != Integer.MAX_VALUE
                        && (possibleNode.level == -1 || !visited[possibleNode.level][possibleNode.easting])) {
                    possibleNode.time =  possibleNode.time + node.time;
                    graph.add(possibleNode);
                }
            }

        }

		return -1;
	}

    int getCostEastFrom(DungeonNode node) {
        if (isLevelFilledWithWater(node.level, node.time)) return Integer.MAX_VALUE;
        if (node.easting >= WIDTH - 1) return Integer.MAX_VALUE;
        return getCostWithFood(node.easting, east[node.level]);
    }

    int getCostWestFrom(DungeonNode node) {
        if (isLevelFilledWithWater(node.level, node.time)) return Integer.MAX_VALUE;
        if (node.easting == 0) return Integer.MAX_VALUE;
        return getCostWithFood(node.easting, west[node.level]);
    }

    int getCostDownFrom(DungeonNode node) {
        if (isLevelFilledWithWater(node.level + 1, node.time)) return Integer.MAX_VALUE;
        if (node.level >= HEIGHT - 1) return Integer.MAX_VALUE;
        return getCostWithFood(node.easting, down[node.level]);
    }

    int getCostUpFrom(DungeonNode node) {
        if (isLevelFilledWithWater(node.level - 1, node.time)) return Integer.MAX_VALUE;
        return getCostWithFood(node.easting, up[node.level]);
    }

    int getCostWithFood(int i, String vals) {
        char val = vals.charAt(i);
        if (val < '0' || val > '9') return Integer.MAX_VALUE;
        return val - '0';
    }

    boolean isLevelFilledWithWater(int level, int curTime) {
        int filledLevels = (curTime+1)/WIDTH;
        if (HEIGHT - level > filledLevels ) return false;
        return true;
    }

    // set row to infintity
//    public void setRowToInfinity(int level) {
//        for (int i = 0; i < up[0].length) {
//            west[level][i] = Float.MAX_VALUE;
//            east[level][i] = Float.MAX_VALUE;
//            down[level][i] = Float.MAX_VALUE;
//
//            if (level < down.length -1)
//                down[level][i + 1] = Float.MAX_VALUE;
//
//            if (level > 0)
//                up[level-1][i] = Float.MAX_VALUE;
//        }
//    }
}








class DungeonNode implements Comparable {
    public int time = 0;
    public int easting = 0;
    public int level = 0;

    DungeonNode(int time, int easting, int level) {
        this.time = time;
        this.easting = easting;
        this.level = level;
    }

    @Override
    public int compareTo(Object o) {
        DungeonNode that = (DungeonNode) o;
        if (this.time > that.time) return 1;
        if (this.time < that.time) return -1;

        if (this.level > that.level) return -1;
        if (this.level < that.level) return 1;

        if (this.easting > that.easting) return -1;
        if (this.easting < that.easting) return 1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        DungeonNode that = (DungeonNode) o;
        if (easting != that.easting) return false;
        if (level != that.level) return false;
        if (time != that.time) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + easting;
        result = 31 * result + level;
        return result;
    }
}
