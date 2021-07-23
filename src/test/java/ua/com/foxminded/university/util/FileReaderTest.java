package ua.com.foxminded.university.util;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.university.util.FileReader;

public class FileReaderTest {
    
    private FileReader fileReader = new FileReader();

    @Test
    public void readDataFromFile_mustReadOneLineFromFile() {
        String testFileName = "testFile.txt";
        String expectedResult = "test text";
        
        assertEquals(expectedResult, fileReader.readFile(testFileName).stream().collect(joining(" ")));
    }
}
