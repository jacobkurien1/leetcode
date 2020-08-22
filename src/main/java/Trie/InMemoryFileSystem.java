package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InMemoryFileSystem {
    TreeNode root;
    public InMemoryFileSystem() {
        root = new TreeNode("");
    }

    //Running time is O(pathlength + numberOfFilesORDirectories)
    public List<String> ls(String path) {
        TreeNode curr = root;
        String[] pathParts = path.split("/");
        for(int i = 1; i<pathParts.length; i++){
            //Assumption: curr is never null
            curr = curr.hm.get(pathParts[i]);
        }
        List<String> ret = new ArrayList<>();
        if(curr.hm != null){
            for(String key: curr.hm.keySet()){
                ret.add(key);
            }
        } else{
            ret.add(curr.name);
        }
        Collections.sort(ret);
        return ret;
    }

    //Running time is O(n)
    public void mkdir(String path) {
        mkPathAndReturnLastNode(path, false);
    }

    TreeNode mkPathAndReturnLastNode(String path, boolean isFile){
        TreeNode curr = root;
        String[] pathParts = path.split("/");
        for(int i = 1; i<pathParts.length; i++){
            if(!curr.hm.containsKey(pathParts[i])){
                curr.hm.put(pathParts[i], ((isFile && i == pathParts.length-1)?new TreeNode(pathParts[i], ""):
                        new TreeNode(pathParts[i])));
            }
            curr = curr.hm.get(pathParts[i]);
        }
        return curr;
    }

    //Running time is O(n)
    public void addContentToFile(String filePath, String content) {
        TreeNode curr = mkPathAndReturnLastNode(filePath, true);
        curr.content.append(content);
    }

    //Running time is O(n)
    public String readContentFromFile(String filePath) {
        //Assumption: mkPath does not create a directory/file for this case as paths are valid
        TreeNode curr = mkPathAndReturnLastNode(filePath, false);
        return curr.getContent();
    }

    class TreeNode {
        String name;
        StringBuilder content;
        HashMap<String, TreeNode> hm;
        public TreeNode(String name){
            this.name = name;
            hm = new HashMap<>();
        }

        public TreeNode(String name, String data){
            this.name = name;
            this.content = new StringBuilder();
            this.content.append(data);
        }

        public String getContent(){
            return content.toString();
        }
    }
}
