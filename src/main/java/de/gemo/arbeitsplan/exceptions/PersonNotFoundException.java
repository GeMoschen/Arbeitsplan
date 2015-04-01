package de.gemo.arbeitsplan.exceptions;

public class PersonNotFoundException extends Exception {

    private static final long serialVersionUID = -8596124320759300802L;
    private static final String TEXT = "Person '%s' not found!";

    public PersonNotFoundException(String name) {
        super(String.format(TEXT, name));
    }
}
