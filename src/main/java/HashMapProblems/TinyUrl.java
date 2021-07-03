package main.java.HashMapProblems;

import java.util.HashMap;
import java.util.Random;

/*
https://leetcode.com/problems/encode-and-decode-tinyurl/
Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work.
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
/*
Running time is O(1) for both encode and decode
Space needed is O(n) to save all the mappings
 */
public class TinyUrl {
    HashMap<String, String> urlMap = new HashMap<>();
    HashMap<String, String> shortUrlMap = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(!urlMap.containsKey(longUrl)){
            String shortUrlPart = "";
            do{
                shortUrlPart = getRandom(7);
            } while(shortUrlMap.containsKey(shortUrlPart));
            urlMap.put(longUrl, shortUrlPart);
            shortUrlMap.put(shortUrlPart, longUrl);
        }

        return "http://tinyurl.com/" + urlMap.get(longUrl);
    }

    String getRandom(int n){
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            int val = rnd.nextInt(62);
            sb.append(getRandomChar(val));
        }
        return sb.toString();
    }

    String getRandomChar(int val){
        if(val<10){
            return Integer.toString(val);
        } else if(val>=10 && val< 36){
            return Character.toString((char)(val-10+'a'));
        } else {
            return Character.toString((char)(val-36+'A'));
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String shortUrlPart = shortUrl.substring(shortUrl.length() - 7);
        if (shortUrlMap.containsKey(shortUrlPart)) {
            return shortUrlMap.get(shortUrlPart);
        }
        return "";
    }
}
