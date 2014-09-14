package crackinginterview.datastructures.x8.ObjectOrientedDesign;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Imagine you have a call center with three levels of employees:
 * respondent, manager, and director.
 * An incoming telephone call must be first allocated to a respondent who is free.
 * If the respondent can't handle the call, he or she must escalate the call to a manager.
 * If the manager is not free or notable to handle it,
 * then the call should be escalated to a director.
 * Design the classes and data structures for this problem.
 * Implement a method dispatchCaLL() which assigns a call to the first available employee.

 */
public class CallHandler {
    PriorityQueue<Emloee> availabilityQueue= new PriorityQueue<Emloee>(15, new EmployeesAvailabilityComparator());

    public CallHandler() {
        //initialization
        availabilityQueue.add(new Emloee(Level.director, "Larry"));
        availabilityQueue.add(new Emloee(Level.director, "Eric"));
        availabilityQueue.add(new Emloee(Level.director, "Sergey"));
        availabilityQueue.add(new Emloee(Level.manager, "Yen"));
        availabilityQueue.add(new Emloee(Level.manager, "Denis"));
        availabilityQueue.add(new Emloee(Level.respondent, "Pavel"));
        availabilityQueue.add(new Emloee(Level.respondent, "Pavlo"));
        availabilityQueue.add(new Emloee(Level.respondent, "Maria"));
        availabilityQueue.add(new Emloee(Level.respondent, "Alexey"));
    }

    public Emloee getAvailableEmploee() {
        Emloee availableEmploee = availabilityQueue.poll();
        return availableEmploee;
    }

}

// the bigger availability number - the more emploee is ready to accept a client
class EmployeesAvailabilityComparator implements Comparator<Emloee> {

    @Override
    public int compare(Emloee emloee, Emloee emloee2) {
        if (emloee.level.asNumber() > emloee2.level.asNumber()) return 1;
        if (emloee.level.asNumber() < emloee2.level.asNumber()) return -1;

        // chain is equal, compare smth elese
        return 0;
    }

}

class Emloee {

    public Level level;
    public String name;

    Emloee(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}

enum Level{
    respondent, manager,  director;
    public int asNumber () {
        if (this == director) return 3;
        if (this == manager) return 2;
        return 1;
    }
}
