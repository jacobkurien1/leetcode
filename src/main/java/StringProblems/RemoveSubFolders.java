package main.java.StringProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.

If a folder[i] is located within another folder[j], it is called a sub-folder of it.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.



Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]


Constraints:

1 <= folder.length <= 4 * 10^4
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'
folder[i] always starts with character '/'
Each folder name is unique.
 */
/*
Running time is O(n* (logn + m^2) where n is the total number of elements and m is the length of the folder path.
Space needed is O(1) if the space of sort is ignored. else O(n) for a merge sort
 */
public class RemoveSubFolders {
    public List<String> removeSubfolders(String[] folders) {
        List<String> mainFolders = new ArrayList<>();
        Arrays.sort(folders);
        for(String folder: folders){
            if(mainFolders.isEmpty() || !isSubfolder(mainFolders.get(mainFolders.size()-1), folder)){
                mainFolders.add(folder);
            }
        }
        return mainFolders;
    }

    boolean isSubfolder(String folder, String test){
        String[] folderSplit = test.split(folder, 2);
        return folderSplit.length == 2 &&
                folderSplit[0].equals("") &&
                folderSplit[1].length() >=1 &&
                folderSplit[1].charAt(0) == '/';
    }
}
