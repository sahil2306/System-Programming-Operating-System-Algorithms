import java.util.*;
import java.io.*;

class Main{
    private String s;
    public int i;
    double num;

    public static void main(String[] args) {
        s = "Hello Universe!" ;
        i = 5;
        num = 69.96;

        System.out.println("Welcome, " + s);

        if(num > 50) {
            System.out.println("num is greater than 50 & num = " + num);
        } 
        else if(num == 50) {
	        System.out.println("num is equal to 50 & num = " + num);
        }
        else {
            System.out.println("num is lesser than 50 & num = " + num);
        }

        for(int j = 0;j<i;++j) {
            System.out.println("j = " + j) ;
        }
    }
}