import java.util.*;

public class KiloManX {
    String[] damageChart;
    int[] bossHealth;

    Set<Set<Integer>> visitedWeapons = new HashSet<Set<Integer>>();

	
	public int leastShots(String[] damageChart, int[] bossHealth) {
        this.damageChart = damageChart;
        this.bossHealth = bossHealth;

        SortedSet<KiloNode> graph = new TreeSet<KiloNode>();
        KiloNode lastNode = new KiloNode();
        graph.add(lastNode);


         while (!graph.isEmpty()) {
            lastNode = graph.first();
            graph.remove(lastNode);
            if(visitedWeapons.contains(lastNode.weaponsCollected)) continue;
            visitedWeapons.add(lastNode.weaponsCollected);

            if (lastNode.weaponsCollected.size() == damageChart.length) {
                return lastNode.costToGetHere;
            }

            for (int bossIdx = 0; bossIdx < bossHealth.length; bossIdx++) {
                // boss already defeated as we already have their weapon
                if (lastNode.weaponsCollected.contains(bossIdx)) continue;

                // find best path to boss from current state
                KiloNode nextBoss = new KiloNode();
                nextBoss.costToGetHere = strikesToDefeatBossWithBestWeapon(bossIdx, lastNode.weaponsCollected,lastNode.costToGetHere);
                nextBoss.weaponsCollected = new HashSet<>(lastNode.weaponsCollected);
                nextBoss.weaponsCollected.add(bossIdx);
                graph.add(nextBoss);
            }
        }

        return  -1 ;//lastNode.costToGetHere;
	}

    public int strikesToDefeatBossWithBestWeapon(int bossIdx, Set<Integer> weapons, int curStrikes) {
        String curBossVulnerabilities = damageChart[bossIdx];
        int curBossHealth = bossHealth[bossIdx];

        int bestWeaponDmg = 1;
        for(int weaponIdx : weapons) {
            bestWeaponDmg = Math.max(bestWeaponDmg, curBossVulnerabilities.charAt(weaponIdx) - '0');
        }

        return curStrikes + (int)Math.ceil(1.0*curBossHealth/bestWeaponDmg);

    }

}

class KiloNode implements Comparable {
    public int costToGetHere = 0;
    public Set<Integer> weaponsCollected = new TreeSet<>();

    // Define a comparator that puts nodes with less shots on top
    @Override
    public int compareTo(Object o) {
        KiloNode thatNode = (KiloNode) o;
        if (costToGetHere < thatNode.costToGetHere) return -1;
        if (costToGetHere > thatNode.costToGetHere) return 1;

        if (weaponsCollected.containsAll(thatNode.weaponsCollected)) return 0;
        if (weaponsCollected.size() < thatNode.weaponsCollected.size()) return -1;
        if (weaponsCollected.size() > thatNode.weaponsCollected.size()) return 1;

        Iterator<Integer> thatIterator = thatNode.weaponsCollected.iterator();
        for (int thisW : weaponsCollected) {
            int thatW = thatIterator.next();
            if (thisW < thatW) return -1;
            if (thisW > thatW) return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KiloNode kiloNode = (KiloNode) o;

        if (costToGetHere != kiloNode.costToGetHere) return false;
        if (weaponsCollected != null ? !weaponsCollected.equals(kiloNode.weaponsCollected) : kiloNode.weaponsCollected != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = costToGetHere;
        result = 31 * result + (weaponsCollected != null ? weaponsCollected.hashCode() : 0);
        return result;
    }
}
