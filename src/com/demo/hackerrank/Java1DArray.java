package com.demo.hackerrank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Java1DArray {
    public static void main(String[] args) throws FileNotFoundException {
//    		Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner(new FileReader("Java1DArray_input04.txt"));
        Scanner scan_output = new Scanner(new FileReader("Java1DArray_output04.txt"));
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }
             
            int [] game_new = Arrays.copyOf(game, game.length);
            String str_output = scan_output.nextLine();
            String canWin = (canWin(leap, game)) ? "YES" : "NO";
            if(!str_output.equalsIgnoreCase(canWin)) {
            	System.out.println("n : " + n + ", Leap : " + leap);
            	System.out.println("canWin: " + canWin + " , TestCase: " + str_output); 
            	for (int i = 0; i < game_new.length; i++) {
  					System.out.print(game_new[i] + " ");
  				}
              System.out.println();
            }
            
//           System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
    /*
    * 
   	n : 3, Leap : 0
	canWin: NO , TestCase: YES
	0 0 0   (YES)
	
	n : 3, Leap : 1
	canWin: YES , TestCase: NO
	0 0 1 
       
    * */
    public static boolean canWin(int leap, int[] game) {    	
    	return mySolution(leap, game);    	
    	//return isSolvable(leap, game, 0);    	
    }
    
    public static boolean isSolvable(int leap, int[] game, int i) {
    	if(i >= game.length) return true;
    	if (i < 0 || game[i] == 1) return false;
    	game[i] = 1;
    	
    	return isSolvable(leap, game, i + leap) ||
    			isSolvable(leap, game, i + 1) || 
    			isSolvable(leap, game, i - 1);
    }
    
    public static boolean mySolution(int leap, int[] game) {
    	boolean result = false;
    	LinkedList<Integer> queue = new LinkedList<>(); 
    	int arrLen = game.length;
    	for (int i = 0; i < arrLen; i++) {
    		if(game[i] == 1) {
				break;
			}
    		if((i + leap) < arrLen) {
				if(game[i + leap] == 0) {
					queue.offer(i + leap);
				}								
    		}
    		else {
    			result = true;
    			break;
    		}
		}
    	while(!queue.isEmpty()) {
    		int val = queue.poll();
    		for (int i = val; i < arrLen; i++) {
				if(i < arrLen) {
					if(game[i] == 1) {
						break;
					}
					else if(game[i] == 0) {
						if((i + leap) == (arrLen - 1) && game[i + leap] == 0) {
							result = true;
							return result;
						}
						else if((i + leap) < (arrLen - 1) && game[i + leap] == 0) {
							if(leap > 0) {
								queue.offer(i + leap);
							}
						}
						else if((i + leap) > (arrLen - 1)) {
							result = true;
							return result;
						}						
					}
					game[i] = 1;
				}					
			}
			for (int i = val - 1; i > val - leap; i--) {
				if(i >= 0) {
					if(game[i] == 1) {
						break;
					}
					else if(game[i] == 0) {
						if(leap > 0 && (i + leap) < arrLen && game[i + leap] == 0) {
							queue.offer(i + leap);
						}
					}
					game[i] = 1;	
				}
			}
    	}
    	return result;
    }
}
