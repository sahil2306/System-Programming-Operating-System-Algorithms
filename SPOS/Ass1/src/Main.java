import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        pass1 first = new pass1();

        //Code File Input
        first.parseFile("input4.asm");

        //Pass1 All Outputs
        System.out.println(first.getIC());
        System.out.println(first.getSymbolTable());
        System.out.println(first.getLiteralTable());
        System.out.println(first.getPoolTable());
    }
}
