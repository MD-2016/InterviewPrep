import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class AnimalShelter {

    static class Animal {
        String type;
        Date dateArrived;
    }

    static class Dog extends Animal {
        Dog()
        {
            super();
            type = "dog";
        }
    }

    static class Cat extends Animal {
        Cat()
        {
            super();
            type = "cat";
        }
    }

    /*
     * Time is n size queue but all operations are O(1)
     * Space is O(n)
     */
    static class mainAnimalShelter {
        Queue<Dog> dogs;
        Queue<Cat> cats;

        public mainAnimalShelter()
        {
            dogs = new LinkedList<Dog>();
            cats = new LinkedList<Cat>();
        }

        public void enqueue(Animal animal) throws Exception
        {
            boolean isCat = animal.type.equals("cat");
            boolean isDog = animal.type.equals("dog");

            if((!isCat) && (!isDog))
            {
                throw new Exception("Unknown animal");
            }

            animal.dateArrived = new Date();
            if(isCat)
            {
                if(animal instanceof Cat)
                {
                    cats.offer((Cat)animal);
                }
            }

            if(isDog)
            {
                if(animal instanceof Dog)
                {
                    dogs.offer((Dog)animal);
                }
            }

        }

        public Animal dequeueAny() throws Exception
        {
            boolean noCat = cats.isEmpty();
            boolean noDog = dogs.isEmpty();

            if(noCat && noDog)
            {
                throw new Exception("No more animals");
            }

            if(noCat)
            {
                return dogs.poll();
            }

            else if(noDog)
            {
                return cats.poll();
            }

            else
            {
                return cats.peek().dateArrived.compareTo(dogs.peek().dateArrived) < 0 ? cats.poll() : dogs.poll();
            }
        }

        public Cat dequeueCat() throws Exception
        {
            boolean noCat = cats.isEmpty();
            if(noCat)
            {
                throw new Exception("no cats left");
            }
            return cats.poll();
        }

        public Dog dequeueDog() throws Exception
        {
            boolean noDog = dogs.isEmpty();
            if(noDog)
            {
                throw new Exception("no dogs left");
            }
            return dogs.poll();
        }
    }

    /*
     * Book approach
     */
    abstract static class Animal2{
        private int order;
        protected String name;
        public Animal2(String n){name = n;}
        public void setOrder(int ord) {order = ord;}
        public int getOrder(){return order;}

        public boolean isOlderThan(Animal2 a)
        {
            return this.order < a.getOrder();
        }
    }

    static class AnimalQueue {
        LinkedList<Dog2> dogs = new LinkedList<>();
        LinkedList<Cat2> cats = new LinkedList<>();
        private int order = 0;

        public void enqueue(Animal2 a)
        {
            a.setOrder(order);
            order++;

            if(a instanceof Dog2)
            {
                dogs.addLast((Dog2) a);
            }
            else if(a instanceof Cat2)
            {
                cats.addLast((Cat2) a);
            }
        }

        public Animal2 dequeueAny() {
            if (dogs.size() == 0) {
                return dequeueCats();
            } else if (cats.size() == 0) {
                return dequeueDogs();
            }
            Dog2 dog = dogs.peek();
            Cat2 cat = cats.peek();
            if (dog.isOlderThan(cat)) {
                return dogs.poll();
            } else {
                return cats.poll();
            }
        }

        public Dog2 dequeueDogs()
        {
            return dogs.poll();
        }

        public Cat2 dequeueCats()
        {
            return cats.poll();
        }

        public Animal2 peek() {
            if (dogs.size() == 0) {
                return cats.peek();
            } else if (cats.size() == 0) {
                return dogs.peek();
            }
            Dog2 dog = dogs.peek();
            Cat2 cat = cats.peek();
            if (dog.isOlderThan(cat)) {
                return dog;
            } else {
                return cat;
            }
        }

        public int size() {
            return dogs.size() + cats.size();
        }

        public Dog2 peekDogs() {
            return dogs.peek();
        }

        public Cat2 peekCats() {
            return cats.peek();
        }

    }

    public static class Dog2 extends Animal2 {
        public Dog2(String n){super(n);}
    }

    public static class Cat2 extends Animal2 {
        public Cat2(String n){super(n);}
    }

    public static void main(String[] args)
    {

    }

}
