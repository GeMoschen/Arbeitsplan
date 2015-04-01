package de.gemo.arbeitsplan.exceptions;

public class PersonAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -8596124320759300802L;
    private static final String TEXT = "Person '%s' already exists!";

    public PersonAlreadyExistsException(String name) {
        super(String.format(TEXT, name));
    }
}
