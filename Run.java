import java.util.*;

public class Run {
	public static String compress (String s) {
	    // scan s, auto return if ret.length > s.length
	
	    if (s.isEmpty() || s == null)
	        return s;
	
	    int i = 0, j = 0;
	    int cnt = 0;
	    StringBuilder ret = new StringBuilder();
	
	    while (j < s.length()) {
	        if (s.charAt(j) == s.charAt(i)) {
	            cnt++;
	        } else {
	            ret.append(s.charAt(i));
	            ret.append(cnt);
	            if (ret.length() >= s.length())
	                return s;
	            i = j;
	            cnt = 1;
	        }
	        j++;
	    }
	
	    ret.append(s.charAt(i));
	    ret.append(cnt);
	    if (ret.length() >= s.length())
	                return s;
	    return ret.toString();
	}
	
	
    public static void main(String[] args) {
		System.err.println("aabcccccaaa"+" -> "+compress("aabcccccaaa")+" | " +"a2b1c5a3");
		assert compress("aabcccccaaa").equals("a2b1c5a3");
		
		System.err.println("aaaaa"+" -> "+compress("aaaaa")+" | " +"a5");
		assert compress("aaaaa").equals("a5");
		
		System.err.println("aaaaab"+" -> "+compress("aaaaab")+" | " +"a5b1");
		assert compress("aaaaab").equals("a5b1");
		
		System.err.println("aaab"+" -> "+compress("aaab")+" | " +"aaab");
		assert compress("aaab").equals("aaab");
		
		System.err.println("abcd"+" -> "+compress("abcd")+" | " +"abcd");
		assert compress("abcd").equals("abcd");
		
		System.err.println("aabb"+" -> "+compress("aabb")+" | " +"aabb");
		assert compress("aabb").equals("aabb");
		
		System.err.println(""+" -> "+compress("")+" | " +"");
		assert compress("").equals("");
		
    }
}
