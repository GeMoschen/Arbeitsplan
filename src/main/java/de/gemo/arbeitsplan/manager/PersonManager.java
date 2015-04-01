package de.gemo.arbeitsplan.manager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.gemo.arbeitsplan.exceptions.PersonAlreadyExistsException;
import de.gemo.arbeitsplan.exceptions.PersonNotFoundException;
import de.gemo.arbeitsplan.units.Person;

public class PersonManager {
    private static Set<Person> PERSONS;

    static {
        PERSONS = Collections.synchronizedSet(new HashSet<Person>());
    }

    public static boolean replace(Person personToReplace, Person newPerson) throws PersonNotFoundException, PersonAlreadyExistsException {
        return remove(personToReplace) && add(newPerson);
    }

    public static boolean add(Person person) throws PersonAlreadyExistsException {
        if (!PERSONS.add(person)) {
            throw new PersonAlreadyExistsException(person.getName());
        }
        return true;
    }

    public static boolean has(Person person) {
        return PERSONS.contains(person);
    }

    public static boolean remove(Person person) throws PersonNotFoundException {
        if (!PERSONS.remove(person)) {
            throw new PersonNotFoundException(person.getName());
        }
        return true;
    }

    public static Person get(String name) throws PersonNotFoundException {
        for (Person person : PERSONS) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        throw new PersonNotFoundException(name);
    }

    public static int getSize() {
        return PERSONS.size();
    }

    public static Set<Person> getAll() {
        return Collections.unmodifiableSet(PERSONS);
    }

}
