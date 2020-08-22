package StringProblems;

/*
https://leetcode.com/problems/validate-ip-address/
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::)
to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
/*
Running time is O(n)
Space needed is O(n) to store all the parts
 */
public class ValidIp {
    public String validIPAddress(String IP) {
        String[] ip4Parts = IP.split("\\.", -1);
        String[] ip6Parts = IP.split(":", -1);

        if(ip4Parts.length == 4 && isIp4(ip4Parts)){
            return "IPv4";
        }
        if(ip6Parts.length == 8 && isIp6(ip6Parts)){
            return "IPv6";
        }
        return "Neither";
    }
    boolean isIp6(String[] parts){
        for(String part:parts){
            if(part.length() == 0 || part.length()>4){
                return false;
            }
            part = part.toUpperCase();
            for(int i = 0; i<part.length(); i++){
                if((part.charAt(i)>='0' && part.charAt(i)<='9') ||
                        (part.charAt(i)>='A' && part.charAt(i)<='F')){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    boolean isIp4(String[] parts){
        for(String part:parts){
            int val =0;
            if(part.length() == 0){
                return false;
            }
            for(int i = 0;i<part.length();i++){
                if(i==0 && part.charAt(i)=='0' && part.length()!=1){
                    System.out.println("1");
                    return false;
                }
                if(part.charAt(i)<'0' || part.charAt(i)>'9'){
                    System.out.println("2");
                    return false;
                }
                val = val*10 + (int)(part.charAt(i) - '0');
                if(val>=256){
                    System.out.println("3");
                    return false;
                }
            }
        }
        return true;
    }
}
