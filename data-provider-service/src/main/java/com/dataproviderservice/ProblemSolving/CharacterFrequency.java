package com.dataproviderservice.ProblemSolving;

import java.util.*;

public class CharacterFrequency {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter String ");

        String S = myObj.nextLine();
        int length=S.length();
        char str[]= S.toCharArray();
        int[] frequency=  calculateFrequency(S,length);
        System.out.println("Frequencies of the characters in the string are as below: ");
        System.out.println("Characters  frequencies");
        for(int i = 0; i <frequency.length; i++)
        {

            if(frequency[i]!=0 && str[i]!=' ')
                System.out.println(str[i] + "              " + frequency[i]);
        }
    }
    public static int[] calculateFrequency(String s, int size)
    {
        int[]frequency=new int[size];
        char str[]= s.toCharArray();
        for (int i=0;i<str.length;i++)
        {
            if(str[i]!='0')
            {
                frequency[i]=1;
                for (int j=i+1;j<size;j++)
                {
                    if(str[i]==str[j])
                    {
                        frequency[i]++;
                        str[j]='0';
                    }
                }
            }
        }
        return frequency;
    }
}
