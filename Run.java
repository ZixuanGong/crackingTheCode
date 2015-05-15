import java.util.*;

public class Run {
	public static boolean hasUniqChar (String str) {
	
	    if (str == null || str.length() == 0)
	        return true;
	
	    int let[] = new int[26];
	
	    for (char c: str.toCharArray()) {
	        c = Character.toUpperCase(c);
	
	        int i = c - 'A';
	        if (let[i] == 0)
	            let[i] = 1;
	        else
	            return false;
	    }
	
	    return true;
	}
	
	
    public static void main(String[] args) {
		assert hasUniqChar("string") == true;
		assert hasUniqChar("apple") == false;
		assert hasUniqChar("") == true;
    }
}
