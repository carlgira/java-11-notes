package com.carlgira.exceptions;

public class Main {

    /**
     * This will cut the Exception printing.
     */
    public static void one() throws Exception {
        try{
            throw new Exception("bad");
        } finally {
            return;
        }
    }

    /**
     * It will print 1 and 2, finally block always executes.
     */
    public static void two(){
        try{
            System.out.println("1");
            return;
        }
        catch (Exception e){
        }
        finally {
            System.out.println("2");
        }
        System.out.println("3");
    }

    public static void three(){
    }

    public static void main(String[] args) throws Exception {
        two();
    }
}
