package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        PersonManager personManager = new PersonManager(emf);


        personManager.addPerson(new Person("Alice", 25));
        personManager.addPerson(new Person("Bob", 30));
        personManager.addPerson(new Person("Charlie", 35));


        List<Person> persons = personManager.getAllPersons();
        for (Person person : persons) {
            System.out.println("Имя: " + person.getName() + ", Возраст: " + person.getAge());
        }

        personManager.close();
        emf.close();
    }
}