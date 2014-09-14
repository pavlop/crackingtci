import java.util.*;


/**

   /\   /\
  /  \ /  \
 | 01 | 02 |
  \  / \  /
   \/   \/
1,0 | 11 |  1,2
    \   /
      \/
  2,1    2,2



*/
public class RoboCourier {
    Set<Coordinate> field = new HashSet<Coordinate>();
    Set<CoodrinateAndDirection> visited = new HashSet<CoodrinateAndDirection>();

    public static final int PIVOT_TIME = 3;

	public int timeToDeliver(String[] path) {
        int curx = 0;
        int cury = 0;
        field.add(new Coordinate(curx, cury));
        Direction curDirection = Direction.EAST;

        // parse path
        for (String s:path) {
            for (char c:s.toCharArray()) {
                switch (c) {
                    case 'L':
                        curDirection = curDirection.turnLeft();
                        break;
                    case 'R':
                        curDirection = curDirection.turnRight();
                        break;
                    case 'F':
                        cury += curDirection.dy(curx);
                        curx += curDirection.dx();
                        field.add(new Coordinate(curx, cury));
                        break;
                }

            }
        }
        Coordinate target = new Coordinate(curx, cury);
        System.out.println("field" + field);
        System.out.println(target);


        SortedSet<RoboNode> graph = new TreeSet<RoboNode>();
        RoboNode node = new RoboNode(0, new Coordinate(0, 0), Direction.EAST);
        graph.add(node);

        while (!graph.isEmpty()) {
            node = graph.first();
            graph.remove(node);
            if (node.coordinate.equals(target)) return node.time;
            if(visited.contains(new CoodrinateAndDirection(node.coordinate, node.direction))) continue;;
            visited.add(new CoodrinateAndDirection(node.coordinate, node.direction));
            graph.addAll(getPossibleMovesFrom(node));
        }

        System.out.println("visited"+visited);
        return -1;


	}

    public Set<RoboNode> getPossibleMovesFrom(RoboNode baseNode) {
        Set<RoboNode> res = new HashSet<>();

        // Roatae at the same place right
        for (int i = 1; i <= 3; i ++) {
            RoboNode newNode = new RoboNode(baseNode.time + PIVOT_TIME*i, baseNode.coordinate, baseNode.direction.turnRight());
            res.add(newNode);
        }

        for (int i = 1; i <= 2; i ++) {
            RoboNode newNode = new RoboNode(baseNode.time + PIVOT_TIME*i, baseNode.coordinate, baseNode.direction.turnLeft());
            res.add(newNode);
        }

        //move forward from current node as far as possible
        RoboNode current = baseNode;
        for (int i = 1; ; i++) {
            int newX = current.coordinate.x + current.direction.dx();
            int newY = current.coordinate.y + current.direction.dy(current.coordinate.x);
            Coordinate newCoord = new Coordinate(newX, newY);
            if (field.contains(newCoord)) {
                int timeToGetFromBase = getTimeToMoveForward(i);
                RoboNode next = new RoboNode(baseNode.time + timeToGetFromBase,newCoord, baseNode.direction );
                res.add(next);
                current = next;
            } else {
                break;
            }
        }
        return res;
    }

    public int getTimeToMoveForward (int numberOfSteps) {
        int res = 0;
        for (int i = 1; i <= numberOfSteps; i++) {
            if (i == 1 || i == numberOfSteps) {
                res += 4;
                continue;
            } else {
                res += 2;
            }
        }
        return res;
    }

}


class CoodrinateAndDirection {
    public Coordinate coordinate;
    public Direction direction;

    CoodrinateAndDirection(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoodrinateAndDirection that = (CoodrinateAndDirection) o;

        if (!coordinate.equals(that.coordinate)) return false;
        if (direction != that.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coordinate.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CoodrinateAndDirection{" +
                "coordinate=" + coordinate +
                ", direction=" + direction +
                '}';
    }
}

class Coordinate {
    public int x;
    public int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Coordinate that = (Coordinate) o;
        if (x != that.x) return false;
        if (y != that.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + '}';
    }
}

class RoboNode implements Comparable {

    public int time = 0;
    Coordinate coordinate;
    Direction direction;

    RoboNode(int time, Coordinate coordinate, Direction direction) {
        this.time = time;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    @Override
    public int compareTo(Object o) {
        RoboNode that = (RoboNode) o;
        if (this.time > that.time) return 1;
        if (this.time < that.time) return -1;
        if (this.coordinate.y > that.coordinate.y) return -1;
        if (this.coordinate.y < that.coordinate.y) return 1;
        if (this.coordinate.x > that.coordinate.x) return -1;
        if (this.coordinate.x < that.coordinate.x) return 1;
        return  (this.direction.list().indexOf(this.direction) -that.direction.list().indexOf(that.direction)) ;
    }

    @Override
    public boolean equals(Object o) {
        RoboNode roboNode = (RoboNode) o;
        if (time != roboNode.time) return false;
        if (!coordinate.equals(roboNode.coordinate)) return false;
        if (direction != roboNode.direction) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + coordinate.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}


enum Direction {
    NORTH_WEST,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH_WEST,
    WEST;

    private static ArrayList<Direction> list;


    ArrayList<Direction> list() {
        if(list == null) list = new ArrayList<Direction>(Arrays.asList(this.values()));
       return list;
    }

    Direction turnLeft() {
        int curIdx = list().indexOf(this);
        if (curIdx == 0) return list().get(list().size() - 1);
        else return list.get(curIdx - 1);
    }

    Direction turnRight() {
        int curIdx = list().indexOf(this);
        if (curIdx == list().size() - 1) return list().get(0);
        else return list.get(curIdx + 1);
    }

    int dx() {
        //boolean isEven = x%2 == 0;
        switch(this) {
            case EAST:
                return 0;
            case SOUTH_EAST:
                return 1;
            case SOUTH_WEST:
                return 1;
            case WEST:
                return 0;
            case NORTH_WEST:
                return -1;
            case NORTH_EAST:
                return -1;
            default: //never
                throw new RuntimeException("WTF");

        }
    }

    int dy(int x) {
        boolean isEven = x%2 == 0;
        switch(this) {
            case EAST:
                return 1;
            case SOUTH_EAST:
                return isEven?0:1;
            case SOUTH_WEST:
                return isEven?-1:0;
            case WEST:
                return -1;
            case NORTH_WEST:
                return isEven?-1:0;
            case NORTH_EAST:
                return isEven?0:1;
            default: //never
                throw new RuntimeException("WTF");
        }
    }
}
