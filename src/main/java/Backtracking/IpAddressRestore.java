package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/restore-ip-addresses
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
 */
/*
Running time is O(n^3) as the firstdot can be placed in n-2 places and second dot can be placed in n-3 places and 3rd dot in n-4 places
Space needed is O(n)
 */
public class IpAddressRestore {
    public List<String> restoreIpAddresses(String s) {
        List<String> allIps = new ArrayList<>();
        restoreIpUtil(s, 0, 0, new StringBuilder(), -1, allIps);
        return allIps;
    }

    void restoreIpUtil(String s, int index, int dotCount, StringBuilder sb, int lastInteger, List<String> allIps){
        if(dotCount>3){
            return;
        }
        if(index==s.length()){
            if(dotCount == 3 && lastInteger != -1){
                allIps.add(sb.toString());
            }
            return;
        }
        if(lastInteger == 0){
            return; // handles 0001 case
        }
        int integerPart = ((lastInteger == -1)?0:lastInteger*10)+ // handles "54." case
                Integer.parseInt(Character.toString(s.charAt(index)));
        if(integerPart<0 || integerPart>255){
            return;
        }
        sb.append(Character.toString(s.charAt(index)));
        restoreIpUtil(s, index+1, dotCount, sb, integerPart, allIps);
        sb.setLength(sb.length()-1);
        sb.append(Character.toString(s.charAt(index)) + ".");
        restoreIpUtil(s, index+1, dotCount+1, sb, -1, allIps);
        sb.setLength(sb.length()-2);
    }
}
