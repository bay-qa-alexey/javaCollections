

import java.util.*;

public class amazon {

    public static void main(String[] args) {

        String input = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
        String[] excluded = {"and", "he", "the", "to", "is", "Jack", "Jill"};

        String[] arr = input.split("[^A-Za-z0-9]");

        HashMap<String, Integer> myMap = new HashMap<>();

        for(int i=0;i<arr.length;i++) {
            myMap.put(arr[i].toLowerCase(), (myMap.containsKey(arr[i].toLowerCase()))? myMap.get(arr[i].toLowerCase()) + 1 : 1);
        }

        myMap.remove("");
        for(String s : excluded) {
            myMap.remove(s.toLowerCase());
        }

        List<Map.Entry<String, Integer>> myList = new LinkedList<Map.Entry<String, Integer>>(myMap.entrySet());

        myList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                return (m1.getValue() > m2.getValue()) ? -1 : 1;
            }
        });

        int max = myList.get(0).getValue();

        for(int i=0;i<myList.size();i++) {
            if(myList.get(i).getValue() != max) {
                myList.remove(i);
                i--;
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(myMap);
        System.out.println(myList);

    }
}
