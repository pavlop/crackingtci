package crackinginterview.datastructures.x3.StacksAndQues;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

/**
 * Created by pavlop on 4/2/14.
 * An animal shelter holds only dogs and cats, and operates on a strictly
 * "first in, first out" basis. People must adopt either the "oldest" (based on arrival time)
 * of all animals at the shelter, or they can select whether
 * they would prefer a dog or a cat (and will receive the oldest animal of that type).
 * They cannot select which specific animal they would like.
 * Create the data structures to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog and dequeueCat.
 * Youmay use the built-in L inkedL ist data structure.
 */
public class DogsAndCatsShelter {

    private Map<AnimalType, LinkedList<Animal>> ques = new HashMap<AnimalType, LinkedList<Animal>>();
    private LinkedList<Animal> globalQueue = new LinkedList<Animal>();

    public void enqueAny(Animal animal) {
        if (ques.containsKey(animal.type)) {
            ques.get(animal.type).push(animal);
        } else {
            LinkedList<Animal> newPetsList= new LinkedList<Animal>();
            newPetsList.push(animal);
            ques.put(animal.type, newPetsList);
        }
        globalQueue.push(animal);
    }

    public Animal dequeAny () {
        Animal toRemove =  globalQueue.pop();
        dequeAnimalByType(toRemove.type);
        return toRemove;
    }

    public Animal dequeAnimalByType (AnimalType type) {
        if(ques.containsKey(type)) {
            Animal toRemove = ques.get(type).pop();
            globalQueue.remove(toRemove);
            return toRemove;

        } else {
            return null;
        }
    }


    @Test
    public void dotest() {
        Animal cat = new Animal(AnimalType.CAT, "Murka");
        Animal dog = new Animal(AnimalType.DOG, "Sharik");
        DogsAndCatsShelter shalter = new DogsAndCatsShelter();
        shalter.enqueAny(cat);
        assertEquals(new Animal(AnimalType.CAT, "Murka"), shalter.dequeAnimalByType(AnimalType.CAT));
        shalter.enqueAny(dog);
        shalter.enqueAny(cat);
        assertEquals(new Animal(AnimalType.CAT, "Murka"), shalter.dequeAny());
        assertEquals(new Animal(AnimalType.DOG, "Sharik"), shalter.dequeAny());
    }


}

class Animal {
    public AnimalType type;
    public String name;

    public Animal (AnimalType t, String name) {
        this.type = t;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (name != null ? !name.equals(animal.name) : animal.name != null) return false;
        if (type != animal.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}

enum AnimalType {CAT, DOG}
