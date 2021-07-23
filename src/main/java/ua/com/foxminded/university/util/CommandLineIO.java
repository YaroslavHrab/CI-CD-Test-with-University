package ua.com.foxminded.university.util;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class CommandLineIO implements IInputOutput {

    private Scanner scanner;

    public CommandLineIO() {
        
        scanner = new Scanner(System.in);
    }

    public CommandLineIO(InputStream stream) {
        scanner = new Scanner(stream);
    }

    @Override
    public void print(String input) {
        System.out.println(input);
    }

    @Override
    public String readLine(String decs) {
        return scanner.nextLine();
    }
}
