import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        pass1 first = new pass1();
        pass2 sec = new pass2();

        //Code File Input
        first.parseFile("input4.asm");

        //Pass1 All Outputs
        System.out.println(first.getIC());
        first.getSymbolTable();
        first.getLiteralTable();
        first.getPoolTable();

        //Second Pass
        System.out.println(sec.getMachineCode());
    }
}
