/*
 The Problem:
 Reconciliation is a term Addepar uses for a set of correctness and consistency measures applied to the data we receive and use in financial calculations.
 One of the most common reconciliation checks is called unit reconciliation, which answers the question, "does the transaction history add up to the number of shares the bank says I have?".
 For example, if the bank said I had 100 shares of Apple at the end of yesterday, and I bought 20 shares of Apple today, then we expect the bank to report 120 shares at the end of today.
 This surprisingly isn't always the case! The bank may send incomplete data, we may be parsing it incorrectly, or there may be events like corporate actions or trade settlement lag that cause an inconsistency.
 Unit reconciliation is very important, because numbers that don't add up shouldn't be trusted for any metrics.
 The Input:
 recon.in has three sections:
 D0-POS describes the positions in the account at the end of Day 0. Each record is a space-separated pair of Symbol and Shares. For example "AAPL 10" means 10 shares of AAPL were held at the end of Day 0, and "Cash 122.16" means we had $122.16 in the account at the end of Day 0.
 D1-TRN describes the transactions that occurred in the account on Day 1. Each record is space-separated collection of four items: Symbol, Transaction Code, Shares, and Total Value. For example, the record "AAPL BY 10 6123.21" means 10 shares of AAPL were bought for a total cost of $6123.21.
 D1-POS describes the positions in the account at the end of Day 1, and has the same format as D0-POS.
 The Output:
 The objective is to write a program that prints an alphabetically ordered, space-separated record for each position that fails unit reconciliation. For example, "AAPL 10" means that the reported shares of AAPL in D1-POS is 10 higher than expected based on the transactions.
 The outputs must be printed in alphabetical order.

 Sample input:
 recon.in
 --------
 D0-POS
 AAPL 100
 GOOG 200
 Cash 10

 D1-TRN
 AAPL SL 50 30000
 GOOG BY 10 10000

 D1-POS
 AAPL 50
 GOOG 220
 Cash 20000

 Sample output:
 recon.out
 ---------
 Cash -10
 GOOG 10
*/

package main.java.Algorithms.Collections;

import java.util.Map;
import java.util.TreeMap;

public class BankTransactionReconciliation {

    public static void main(String[] args) {

        String[] input = {
                "D0-POS",
                "AAPL 100",
                "GOOG 200",
                "Cash 10",
                "",
                "D1-TRN",
                "AAPL SL 50 30000",
                "GOOG BY 10 10000",
                "",
                "D1-POS",
                "AAPL 50",
                "GOOG 220",
                "Cash 20000"
        };

        TreeMap<String, Integer> myMap = new TreeMap<>();
        int flag=0;
        for(String str : input) {
            if(str.equals("D0-POS")) {flag=1;}
            if(str.equals("D1-TRN")) {flag=2;}
            if(str.equals("D1-POS")) {flag=3;}
            if(!str.equals("") && !str.equals("D0-POS") && !str.equals("D1-TRN") && !str.equals("D1-POS")) {
                if(flag == 1) {
                    myMap.put(str.split(" ")[0], Integer.valueOf(str.split(" ")[1]));
                }
                if(flag == 2) {
                    if(str.split(" ")[1].equals("SL")) {
                        myMap.put(str.split(" ")[0], myMap.get(str.split(" ")[0]) - Integer.valueOf(str.split(" ")[2]));
                        myMap.put("Cash", myMap.get("Cash") + Integer.valueOf(str.split(" ")[3]));
                    } else {
                        myMap.put(str.split(" ")[0], myMap.get(str.split(" ")[0]) + Integer.valueOf(str.split(" ")[2]));
                        myMap.put("Cash", myMap.get("Cash") - Integer.valueOf(str.split(" ")[3]));
                    }
                }
                if(flag == 3) {
                    myMap.put(str.split(" ")[0], myMap.get(str.split(" ")[0]) - Integer.valueOf(str.split(" ")[1]));
                }
            }

        }

        for(Map.Entry<String, Integer> i : myMap.entrySet()){
            if(i.getValue() !=0) System.out.println(i.getKey() + " " + i.getValue());
        }

    }

}
