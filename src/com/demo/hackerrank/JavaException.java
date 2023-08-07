package com.demo.hackerrank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaException {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(System.in);
            int x = scan.nextInt();
            int y = scan.nextInt();

            System.out.println(x/y);
        }
        catch (InputMismatchException e){
            System.out.println(e.getClass().getName());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
