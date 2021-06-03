package com.carlgira;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * Serializable class
    * Marker that class can be serializable
    * All the fields must be serializable otherwise will fail. (if Zero would not be serializble, serialization of one would fail)
 */
class Zero implements Serializable{

}
class One implements Serializable{
    private String one;
    private static String two;
    private Zero zero;

}

/**
 * Serializable class.
    * serialVersionUID: Identify the version of the object and make sure the serialization would work
    * transient: Keyword to indicate not to serialize a field.
    * writeObject: Custom method to serialize an object
    * readObject: Custom method to de-serialize an object. (it's important to read the fields in the same order they are written)
    * serialPersistentFields: White list of fields to serialize.
 */
class Two implements Serializable {
    public static final long serialVersionUID = 1L;
    private String user;
    private transient String author;


    public Two(String user,String author){
        this.user=user;
        this.author=author;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.author);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        this.author = (String)in.readObject();
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}

class Person implements Serializable {
    private String name;
    private String gender;
    private double height;

    private static final ObjectStreamField[] serialPersistentFields  =
            {new ObjectStreamField("name", String.class),
            new ObjectStreamField("height", double.class)};
}

/**
 * Final
 * final class: Class can not be extended.
 * final field: Can only be initialized once.
 * final method: Method can not be override by a sub-class
 */
final class Three {
    private final Integer one = 5;
    public final void sum(){

    }
}

/**
 * Builder pattern
     * Private constructor
     * Validate input by input
     * The Builder class always returns the an instance of the class.
 */
class BankAccount {

    public static class Builder {

        private long accountNumber; //This is important, so we'll pass it to the constructor.
        private String owner;
        private double interestRate;

        public Builder(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Builder withOwner(String owner){
            this.owner = owner;

            return this;  //By returning the builder each time, we can create a fluent interface.
        }

        public Builder atRate(double interestRate){
            this.interestRate = interestRate;

            return this;
        }

        public BankAccount build(){
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            BankAccount account = new BankAccount();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            account.accountNumber = this.accountNumber;
            account.owner = this.owner;
            account.interestRate = this.interestRate;

            return account;
        }
    }


    private long accountNumber; //This is important, so we'll pass it to the constructor.
    private String owner;
    private double interestRate;

    private BankAccount() {
        //Constructor is now private.
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount.Builder(1234L)
                .withOwner("Marge")
                .atRate(2.5)
                .build();
    }

}

/**
 * Cloneable
     * Interface to create the method clone() and create a copy of object
 */
class Four implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

/**
 * Immutable
     * Class where it's not possible to change it's internal state.
 * Class Protection
    * Use final fields when possible
    * Make sure to return cloned values so outside object cant modify values
    * Do not use methods that can be overridden in constructors
    * Do not log sensitive information (on normal log or in Exceptions)
    * Create MODULES of not correlated code on application
 */
class Five {
    private final Integer credidCard;
    private final Integer two;
    private final Four four;

    public Five(Integer credidCard, Integer two) {
        this.credidCard = credidCard;
        this.two = two;
        this.four = new Four();

        calculate();
    }

    Integer sum(){
        try{
            System.out.println(credidCard); // Do not do.
        }
        catch (Exception e){
            System.out.println(credidCard); // Do not do.
        }

        return Integer.sum(credidCard, two);
    }

    final void calculate(){

    }

    public Four getFour() throws CloneNotSupportedException {
        return (Four) four.clone();
    }
}

/**
 * AccessController
 */
class Six {

    public static void main(String[] args) {
        AccessController.doPrivileged(new PrivilegedAction<Integer>() {
            @Override
            public Integer run() {
                return null;
            }
        });
    }
}

/**
 * Math
    * Avoid overflows with addExact
    * Avoid Aritmethic Exceptions checing for  isFinite or isNan.
 */
class Seven {
    public static void main(String[] args) {
        Math.addExact(1, 2); // A
        Double.isFinite(1/Double.MIN_VALUE);
        Double.isNaN(Math.sqrt(-2));
    }
}

public class Main {

}
