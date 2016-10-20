package Brute;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Michael on 07.10.2015.
 */
public class FileWorker {
    private void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }

    public ArrayList<String> read(String fileName) throws FileNotFoundException {
        ArrayList <String> sb = new ArrayList<>();

        exists(fileName);
        File file = new File(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.add(s);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb;
    }

    public static void write(String fileName, String text) {
        File file = new File(fileName);

        try {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
