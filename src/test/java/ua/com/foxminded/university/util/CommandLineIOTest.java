package ua.com.foxminded.university.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.university.util.CommandLineIO;

public class CommandLineIOTest {

    CommandLineIO commandLineInterfaceInstance;

    @Test
    public void readLine_shouldReadUserTextFromConsole() {
        String testedText = "hello";
        InputStream stream = new ByteArrayInputStream(testedText.getBytes());
        commandLineInterfaceInstance = new CommandLineIO(stream);

        assertEquals(testedText, commandLineInterfaceInstance.readLine(""));
    }

    @Test
    public void print_shouldPrintGivenText() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commandLineInterfaceInstance = new CommandLineIO();

        commandLineInterfaceInstance.print("hello");

        assertEquals("hello", outContent.toString().replaceAll("\\r|\\n", ""));
    }
}
