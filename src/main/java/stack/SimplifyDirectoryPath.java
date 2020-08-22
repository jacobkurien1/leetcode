package stack;

import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/simplify-path/
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names.
The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.



Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
 */
/*
Running time is O(n) where n is the steps in the input string
Space needed is O(n) for the stack
 */
public class SimplifyDirectoryPath {
    public String simplifyPath(String path) {
        Deque<String> dq = new LinkedList<>();
        String[] pathParts = path.split("/");
        for(String pathPart : pathParts){
            if(pathPart.equals("..")){
                dq.pollFirst(); // assumption path is valid
            } else if (pathPart.equals("") || pathPart.equals(".")){}
            else {
                dq.addFirst(pathPart);
            }
        }

        if(dq.isEmpty()){
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while(!dq.isEmpty()){
            sb.append("/" + dq.pollLast());
        }
        return sb.toString();
    }
}
