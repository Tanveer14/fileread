package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class practice {
    public static void main(String[] args) throws FileNotFoundException {
        File file=new File("my.txt");
        Scanner scan=new Scanner(file);
        PrintWriter printWriter=new PrintWriter(file);
        printWriter.println("my name is tanveer");
        printWriter.close();

        while(scan.hasNext())
        {
            System.out.println(scan.next());
        }
    }
}
