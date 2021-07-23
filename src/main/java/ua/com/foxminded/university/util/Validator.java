package ua.com.foxminded.university.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

    private final static Logger log = LoggerFactory.getLogger(Validator.class);

    private Validator() {
    }

    public static void checkIfObjectNull(Object object) {
        try {
            Objects.requireNonNull(object, "Object should not be null");
        } catch (NullPointerException e) {
            log.error("{}", "Object should not be null", e);
            throw new RuntimeException("Object should not be null", e);
        }
        
    }
}
