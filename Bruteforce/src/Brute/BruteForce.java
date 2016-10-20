package Brute;

/**
 * Created by Michael on 06.10.2015.
 * Description: bruteforce to hack password on apache server
 */

import java.util.Arrays;

public class BruteForce {

    private char[] chaeSetValue;
    private char[] currentPassValue;

    public BruteForce(char[] valuesSet, int currentPassLen) {
        chaeSetValue = valuesSet;
        currentPassValue = new char[currentPassLen];
        Arrays.fill(currentPassValue, chaeSetValue[0]);
    }

    public void increment()
    {
        int index = currentPassValue.length - 1;
        while(index >= 0)
        {
            if (currentPassValue[index] == chaeSetValue[chaeSetValue.length-1])
            {
                if (index == 0)
                {
                    currentPassValue = new char[currentPassValue.length + 1];
                    Arrays.fill(currentPassValue, chaeSetValue[0]);
                    break;
                }
                else
                {
                    currentPassValue[index] = chaeSetValue[0];
                    index--;
                }
            }
            else
            {
                currentPassValue[index] = chaeSetValue[Arrays.binarySearch(chaeSetValue, currentPassValue[index]) + 1];
                break;
            }
        }
    }

    @Override
    public String toString()
    {
        return String.valueOf(currentPassValue);
    }
}
