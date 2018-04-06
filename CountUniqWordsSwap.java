/*
Given a string. Swap most occurs word with less one.

Input:
qw qw hello hello hello ttt ttt ttt ttt

Output:
ttt ttt hello hello hello qw qw qw qw
*/


import java.util.*;

public class CountUniqWordsSwap {

    public static void main(String[] args) {

        String input = "hello hello hello ttt ttt ttt ttt";
        String[] tokens = input.split(" ");
        HashMap<String, Integer> myMap = new HashMap<>();

        for(int i=0;i<tokens.length;i++) {
            if(myMap.containsKey(tokens[i])) {
                myMap.put(tokens[i], myMap.get(tokens[i]) + 1);
            } else {
                myMap.put(tokens[i], 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> myList = new ArrayList<Map.Entry<String, Integer>>(myMap.entrySet());

        Comparator<Map.Entry<String, Integer>> newComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() < o2.getValue()) return -1;
                return 1;
            }
        };

        Collections.sort(myList, newComparator);

        String min = myList.get(0).getKey();
        String max = myList.get(myList.size()-1).getKey();

        String result = "";
        for(int i=0;i<tokens.length;i++) {
            if (tokens[i].equals(min)) {
                tokens[i] = max;
            } else if (tokens[i].equals(max)) {
                tokens[i] = min;
            }
            result += " " + tokens[i];
        }

        System.out.println(input);
        System.out.println(result.trim());

    }
}
