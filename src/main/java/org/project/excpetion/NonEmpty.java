package org.project.excpetion;

public class NonEmpty extends IllegalArgumentException {
    public NonEmpty(String message) {
        super(message);
    }
}
