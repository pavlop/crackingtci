package leetcode;

import java.util.Stack;

/**
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 click to show corner cases.

 Corner Cases:
 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> directories = new Stack<String>();

        String[] pathElements = path.split("/");
        boolean seenDirAfterRoot = false;
        for (int i = 0; i < pathElements.length; i++) {
            String s = pathElements[i];

            if ("".equals(s) || ".".equals(s)) {continue;}
            else if ("src/main".equals(s)) {
                //if(!seenDirAfterRoot) continue;
                if(directories.isEmpty()) directories.add(s);
                else directories.pop();
            } else {
                directories.push(s);
                //seenDirAfterRoot = true;
            }
        }


        StringBuilder sol = new StringBuilder();
        seenDirAfterRoot = false;
        for (String s:directories) {
            if("src/main".equals(s) && !seenDirAfterRoot) continue;
            sol.append("/").append(s);
            seenDirAfterRoot = true;
        }
        if (sol.length() == 0) sol.append("/");
        return sol.toString();
    }
}
