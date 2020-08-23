import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {

    static AnimalShelter.mainAnimalShelter as;
    static AnimalShelter.AnimalQueue aq;
    AnimalShelter.Dog dog1;
    AnimalShelter.Dog dog2;
    AnimalShelter.Dog dog3;
    AnimalShelter.Dog dog4;
    AnimalShelter.Dog dog5;
    AnimalShelter.Cat cat1;
    AnimalShelter.Cat cat2;
    AnimalShelter.Cat cat3;
    AnimalShelter.Cat cat4;
    AnimalShelter.Cat cat5;

    @BeforeEach
    void setUp() {
        as = new AnimalShelter.mainAnimalShelter();
        aq = new AnimalShelter.AnimalQueue();
        dog1 = new AnimalShelter.Dog();
        dog2 = new AnimalShelter.Dog();
        dog3 = new AnimalShelter.Dog();
        dog4 = new AnimalShelter.Dog();
        dog5 = new AnimalShelter.Dog();
        cat1 = new AnimalShelter.Cat();
        cat2 = new AnimalShelter.Cat();
        cat3 = new AnimalShelter.Cat();
        cat4 = new AnimalShelter.Cat();
        cat5 = new AnimalShelter.Cat();
    }

    @Test
    void test1() throws Exception {
        List<AnimalShelter.Animal> al = Arrays.asList(dog1,dog2,dog3,dog4,dog5,cat1,cat2,cat3,cat4,cat5);
        for(AnimalShelter.Animal animal: al)
        {
            as.enqueue(animal);
        }

        assertEquals(false,al.isEmpty());
        assertEquals(10, al.size());
        assertEquals(cat1, as.dequeueCat());
        assertEquals(dog1, as.dequeueDog());
        assertEquals(cat2, as.dequeueCat());
        assertEquals(dog2, as.dequeueDog());
        assertEquals(cat3, as.dequeueCat());
        assertEquals(dog3, as.dequeueDog());
        assertEquals(cat4, as.dequeueCat());
        assertEquals(dog4, as.dequeueDog());
        assertEquals(cat5, as.dequeueCat());
        assertEquals(dog5, as.dequeueAny());

    }

    @Test
    void test2()
    {
        assertEquals(true, aq.size() == 0);
        assertEquals(0, aq.size());
        aq.enqueue(new AnimalShelter.Cat2("Callie"));
        aq.enqueue(new AnimalShelter.Cat2("Kiki"));
        aq.enqueue(new AnimalShelter.Dog2("Fido"));
        aq.enqueue(new AnimalShelter.Dog2("Dora"));
        aq.enqueue(new AnimalShelter.Cat2("Kari"));
        aq.enqueue(new AnimalShelter.Dog2("Dexter"));
        aq.enqueue(new AnimalShelter.Dog2("Dobo"));
        aq.enqueue(new AnimalShelter.Cat2("Copa"));
        assertEquals(8, aq.size());
        assertEquals("Callie", aq.dequeueCats().name);
        assertEquals("Kiki", aq.dequeueCats().name);
        assertEquals("Fido", aq.dequeueDogs().name);
        assertEquals("Dora", aq.dequeueDogs().name);
        assertEquals("Kari", aq.dequeueCats().name);
        assertEquals("Dexter", aq.dequeueDogs().name);
        assertEquals("Dobo", aq.dequeueDogs().name);
        assertEquals("Copa", aq.dequeueAny().name);
        assertEquals(0, aq.size());
    }

    @AfterEach
    void tearDown() {
        as = null;
        dog1 = null;
        dog2 = null;
        dog3 = null;
        dog4 = null;
        dog5 = null;
        cat1 = null;
        cat2 = null;
        cat3 = null;
        cat4 = null;
        cat5 = null;
        aq = null;
        assertNull(aq);
        assertNull(as);
    }
}