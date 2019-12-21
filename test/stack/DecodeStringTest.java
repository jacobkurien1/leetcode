package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecodeStringTest {

   @Test
    void decodeString() {
       DecodeString decodeString = new DecodeString();

        String actual = decodeString.decodeString("2[abc]3[cd]ef");
        Assertions.assertEquals("abcabccdcdcdef", actual);

       actual = decodeString.decodeString("20[abc]3[cd]ef");
       Assertions.assertEquals("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabccdcdcdef", actual);
    }
}