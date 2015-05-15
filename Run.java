import java.util.*;

public class Run {
	public static boolean isPermutation (String s, String t) {
	    // suppose whitespace significant & case-sensitive
	    if (s == null || t == null
	        || s.length() != t.length())
	        return false;
	
	    HashMap<Character, Integer> map = new HashMap<>();
	    for (char c: s.toCharArray()) {
	        int cnt = map.containsKey(c) ? map.get(c) : 0;
	        map.put(c, cnt+1);
	    }
	
	    for (char c: t.toCharArray()) {
	        int cnt = map.containsKey(c) ? map.get(c) : 0;
	        if (cnt == 0)
	            return false;
	        else
	            map.put(c, cnt-1);
	    }
	    return true;
	}
	
	
    public static void main(String[] args) {
		assert isPermutation("a","a" ) == true;
		assert isPermutation(" ", " " ) == true;
		assert isPermutation("","abc" ) == false;
		assert isPermutation(" ","abc" ) == false;
		assert isPermutation(null,"abc" ) == false;
		assert isPermutation("","" ) == true;
		assert isPermutation("god","dog" ) == true;
		assert isPermutation("God","dog" ) == false;
		assert isPermutation("god ","dog" ) == false;
		assert isPermutation(" abc","abc" ) == false;
		assert isPermutation("qwerty","erwtqy" ) == true;
    }
}
