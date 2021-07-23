package ua.com.foxminded.university.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileReader {

    private final Logger log = LoggerFactory.getLogger(FileReader.class);
    
    public List<String> readFile(String fileName) {

        if (fileName == null) {
            log.error("{}", "FileName should not be null");
            throw new IllegalArgumentException("FileName should not be null");
        }

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            log.error("{}", String.format("File '%s' was not found", fileName));
            throw new IllegalArgumentException(String.format("File '%s' was not found", fileName));
        }

        return new BufferedReader(new InputStreamReader(inputStream))
            .lines()
            .parallel()
            .collect(Collectors.toList());
    }
}
