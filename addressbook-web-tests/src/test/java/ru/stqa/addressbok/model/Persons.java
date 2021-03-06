package ru.stqa.addressbok.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Persons extends ForwardingSet<PersonData> {

    private Set<PersonData> delegate;

    public Persons(Persons persons) {
        this.delegate = new HashSet<PersonData>(persons.delegate);
    }

    public Persons() {
        this.delegate = new HashSet<PersonData>();
    }

    public Persons(Collection<PersonData> persons) {
        this.delegate = new HashSet<PersonData>(persons);
    }

    @Override
    protected Set<PersonData> delegate() {
        return delegate;
    }

    public Persons withAdded(PersonData person){
        Persons persons = new Persons(this);
        persons.add(person);
        return persons;
    }

    public Persons withOut(PersonData person){
        Persons persons = new Persons(this);
        persons.remove(person);
        return persons;
    }
}
