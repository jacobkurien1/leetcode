package BitManipulation;

/*
https://leetcode.com/problems/utf-8-validation/
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data.
This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
 */
/*
The rules can be summarized as:
1. A valid UTF-8 character can be 1 - 4 bytes long.
2. For a 1-byte character, the first bit is a 0, followed by its unicode.
3. For an n-bytes character, the first n-bits are all ones, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
4. The input given would be an array of integers containing the data.
    We have to return if the data in the array represents a valid UTF-8 encoding.
    The important thing to note here is that the array doesn't contain data for just a single character.
    As can be seen from the first example, the array can contain data for multiple characters all of which can be valid
    UTF-8 characters and hence the charset represented by the array is valid.
5. Another important thing to note is that the integers in the array can be larger than 255 as well.
    The highest number that can be represented by 8 bits is 255.
    So, what do we do if an integer in the array is say, 476?
    According to the Note in the problem before the examples, we only have to consider the 8 least significant bits of each integer in the array.
Running time is O(N)
Space Requirement is O(1)
 */
public class Utf8Validation {
    public boolean validUtf8(int[] data) {
        int i =0;
        while(i<data.length){
            if(data[i]>255){
                data[i] &= 255; //255 ->(11111111)
            }
            if((data[i]&128) == 0) {//first bit is 0 so its 1 byte with unicode
                i++;
            } else {
                int ones = howMany1sInMSB(data[i]);
                if(ones > 4 || ones <=1){
                    return false; //one utf-8 char cannot take more than 4bytes;
                }
                if(!checkIndividualUtf8(data, i+1, ones-1+i)){
                    return false;
                }
                i += ones;
            }
        }
        return true;
    }
    int howMany1sInMSB(int val){
        int numOf1s = 0;
        while(val>0){
            if((val&128) == 0){
                break;
            }
            numOf1s++;
            val  = val<<1;
        }
        return numOf1s;
    }

    boolean checkIndividualUtf8(int[] data, int st, int end){
        if(st >= data.length || end >= data.length){
            return false;
        }
        for(int i = st; i<=end; i++){
            if((data[i]&192) != 128){ //192 ->(11000000), 128 (10000000)
                return false;
            }
        }
        return true;
    }
}
