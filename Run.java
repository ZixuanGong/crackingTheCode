import java.util.*;

public class Run {
	public static int[][] rot90deg(int[][] img) {
	    // assume clockwise, in-place
	
	    // check NxN
	    if (img == null || img.length == 0 
	        || img.length != img[0].length)
	        return null;
	
	    int n = img.length;
	    int n_layer = n/2;
	    int tmp;
	
	    for (int j = 0; j < n_layer; j++) {
	        for (int i = j; i < n-1-j; i++) {
	            tmp = img[j][i];    // top[i]
	            img[j][i] = img[n-1-i][j];    // top[i] = left[i]
	            img[n-1-i][j] = img[n-1-j][n-1-i];  // left[i] = bottom[i]
	            img[n-1-j][n-1-i] = img[i][n-1-j];  // bottom[i] = right[i]
	            img[i][n-1-j] = tmp;
	        }
	    }
	
	    return img;
	}
	
	
    public static void main(String[] args) {
		int[][] ret = rot90deg(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
		System.out.println(Arrays.deepToString(ret));
		assert Arrays.deepEquals(ret, new int[][]{{7,4,1},{8,5,2},{9,6,3}});
		ret = rot90deg(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
		System.out.println(Arrays.deepToString(ret));
		assert Arrays.deepEquals(ret, new int[][]{{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}});
		/*
		1  2  3  4
		5  6  7  8
		9  10 11 12
		13 14 15 16
		13  9  5  1
		14  10 6  2
		15  11 7  3
		16  12 8  4
		*/
    }
}
