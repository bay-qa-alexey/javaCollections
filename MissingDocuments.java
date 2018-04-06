/*
Mike is a loan officer. He scanned a pile of documents from his desk for different mortgage applications.
Some of the loan applications are missing required documents.
Write a program to help him organize the documents, and determine which ones are missing.

Every document contains metadata, including:
- Document name (“fileName”),
- Document owner (“owner”),
- Document type (“docType”),
- Loan application ID (“applicationId”),
- Content length (“contentLength”)

Your task is to find all the document types that are missing for each loan application.
Every loan application must include every document type.
Since you are new to Mike’s work, you don’t know how many document types there are in total.
You should figure that out from the pile yourself.

Below is an example of a standard test case. There are few more test cases with inputs and outputs hidden.

Input (stdin):
A comma delimited file with the first line as field names. You can assume the input is valid.

Input Example:
fileName,owner,docType,applicationId,contentLength
bank_statement_123,TonyStark,bank_statement,1,1000
tax_document_1,TonyStark,tax_return,1,6001
tax_document_2,SteveRogers,tax_return,2,2000
document_423,ThorOdinson,tax_return,3,1500
medical_report_1,ThorOdinson,medical_history,3,15000
prescription_1,StephenStrange,medical_history,4,200
steven_asset,StephenStrange,bank_statement,4,4000

Output(stdout):
- Print out two lines for each document type. The first line is document type. The second line are the application IDs that are missing for this type of document.
- Delimit the application IDs by one space.
- Sort document types in alphabet order. Sort application IDs in numerical order.
- Print only document types when they are missing for at least one loan application.

Output Example:
bank_statement
2 3
medical_history
1 2
tax_return
4
*/

import java.util.*;

public class MissingDocuments {

    public static void main(String[] args) {

        String[] input = {
                "fileName,owner,docType,applicationId,contentLength",
                "bank_statement_123,TonyStark,bank_statement,1,1000",
                "tax_document_1,TonyStark,tax_return,1,6001",
                "tax_document_2,SteveRogers,tax_return,2,2000",
                "document_423,ThorOdinson,tax_return,3,1500",
                "medical_report_1,ThorOdinson,medical_history,3,15000",
                "prescription_1,StephenStrange,medical_history,4,200",
                "steven_asset,StephenStrange,bank_statement,4,4000"
        };

        int numDocType=0, numId=0;
        for(int i=0;i<input[0].split(",").length;i++) {
            if(input[0].split(",")[i].equals("docType")) {numDocType = i;}
            if(input[0].split(",")[i].equals("applicationId")) {numId = i;}
        }

        TreeSet<Integer> mySet = new TreeSet<>();
        TreeMap<String, TreeSet<Integer>> myMap = new TreeMap<>();
        for(int i=1;i<input.length;i++) {
            if(!myMap.containsKey(input[i].split(",")[numDocType])) {
                myMap.put(input[i].split(",")[numDocType], new TreeSet<Integer>());
            }
            myMap.get(input[i].split(",")[numDocType]).add(Integer.valueOf(input[i].split(",")[numId]));
            mySet.add(Integer.valueOf(input[i].split(",")[numId]));
        }

        String tmp;
        for(Map.Entry<String, TreeSet<Integer>> i : myMap.entrySet()) {
            if(!i.getValue().isEmpty()) {
                System.out.println(i.getKey());
                tmp = "";
                for(Integer ii : mySet) {
                    if(!i.getValue().contains(ii)) {
                        tmp += ii + " ";
                    }
                }
                System.out.println(tmp.trim());
            }
        }
    }
}
