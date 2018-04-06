/*
Given a text file containing fruit store inventory, print the top three fruit names with the most quantity.

Example input file format (assume a single space delimiter):
$ cat fruits.txt
apple 5
orange 10
pineapple 2
apple 1
strawberry 3

Program output:

$ program fruits.txt
orange 10
apple 6
strawberry 3

We will be evaluating on feature correctness, code abstraction/design, performance, and error handling.
Once you’re done, reply to our email and send us your project as a .zip file or provide a link to it on GitHub.
Please include a README that explains how to set up and run your project.

README.txt:
1. There should be Java installed on your computer.

2. Copy directory "myFruitInventoryProject" to any place on your computer
where Java is runnable.

3. Open Command Line Terminal.

4. Go to the project folder:
$ cd "/... path to folder ... /myFruitInventoryProject"

5. Make sure you have data file fruits.txt in this folder.

6. Now it's time to run the program! Put these commands into terminal:

$ javac FruitInventory.java
$ java FruitInventory fruits.txt


Helper:
"$ javac FruitInventory.java" - Java compiles the project
"$ java FruitInventory fruits.txt" - Java runs compiled project with input file "fruits.txt"
*/


import java.util.*;

public class FruitInventory {

    public static void main(String[] args) {

        String[] input = {"apple 5", "orange 10", "pineaaple 2", "apple 1", "strawberry 3"};

        TreeMap<String, Integer> myMap = new TreeMap<>();
        for(int i=0;i<input.length;i++){
            if(myMap.containsKey(input[i].split(" ")[0])) {
                myMap.put(input[i].split(" ")[0], myMap.get(input[i].split(" ")[0]) + Integer.valueOf(input[i].split(" ")[1]));
            } else {
                myMap.put(input[i].split(" ")[0], Integer.valueOf(input[i].split(" ")[1]));
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
        Collections.reverse(myList);

        for(int i=0;i<3;i++) System.out.println(myList.get(i).getKey() + " " + myList.get(i).getValue());

        cellComplete();

    }

    private static List<Integer> cellComplete() {
        return new ArrayList<Integer>();
    }

}
