/*
        Run: ZipChallenge/src/main/java/Zip.java
        ->Stdout: "Enter data:"
        Put Your Data, example:
        Enter data:
        [94200,94299] [94226,94399] [94226,94398] [94133,94133] [94130,94160]

        hit "Enter", get result:
        [94130,94160] [94200,94399]


        Challenge description:

        BACKGROUND
        Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes.
        For example if the ranges are:

        [94133,94133] [94200,94299] [94600,94699]

        Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

        Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

        PROBLEM
        Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

        NOTES
        - The ranges above are just examples, your implementation should work for any set of arbitrary ranges
        - Ranges may be provided in arbitrary order
        - Ranges may or may not overlap
        - Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

        EXAMPLES:
        If the input = [94133,94133] [94200,94299] [94600,94699]
        Then the output should be = [94133,94133] [94200,94299] [94600,94699]

        If the input = [94133,94133] [94200,94299] [94226,94399]
        Then the output should be = [94133,94133] [94200,94399]

        Evaluation Guidelines:
        Your work will be evaluated against the following criteria:
        - Successful implementation
        - Efficiency of the implementation
        - Design choices and overall code organization
        - Code quality and best practices
*/


import java.util.*;

public class Zip {

    public static void main(String[] args) {

        class ZipPairElement {
            int begin;
            int end;
            ZipPairElement(int a, int b) {
                this.begin=a;
                this.end=b;
            }
        }

        Comparator<ZipPairElement> newComparator = new Comparator<ZipPairElement>() {
            @Override
            public int compare(ZipPairElement o1, ZipPairElement o2) {
                if(o1.begin < o2.begin) return -1;
                if(o1.begin > o2.begin) return 1;
                if(o1.begin == o2.begin) {
                    if(o1.end < o2.end) return -1;
                    if(o1.end == o2.end) return 0;
                    if(o1.end > o2.end) return 1;
                }
                return 0;
            }
        };

        String input = "[94200,94299] [94226,94399] [94226,94398] [94133,94133] [94130,94160]";

        ArrayList<ZipPairElement> myList = new ArrayList<ZipPairElement>();

        for(String s : input.split(" ")) {
            myList.add(new ZipPairElement(Integer.valueOf(s.substring(1,6)), Integer.valueOf(s.substring(7,12))));
        }

        Collections.sort(myList, newComparator);

        for(int i=0;i<myList.size()-1;i++) {
            if(myList.get(i+1).begin <= myList.get(i).end) {
                if(myList.get(i+1).end > myList.get(i).end) {
                    myList.get(i).end = myList.get(i+1).end;
                }
                myList.remove(i+1);
                i--;
            }
        }

        for(ZipPairElement z : myList) {
            System.out.println("[" + z.begin + "," + z.end + "]");
        }
    }
}
