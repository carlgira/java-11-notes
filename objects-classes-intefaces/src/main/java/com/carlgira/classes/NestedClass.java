package com.carlgira.classes;

public class NestedClass {

}

class Outer {

    private Integer number;
    private static Integer staticNumber;

    public Outer(){

    }

    public Outer(Integer one){

    }

    /**
     * Inner static class
     */
    public static class InnerStatic {

        InnerStatic(){

        }
    }

    /**
     * Inner member class
     */
    public class InnerMember {

        InnerMember(){
        }
    }


    public void createInnerMember(){
        InnerMember innerMember = new InnerMember(); // Only from inside the outer class instance context.
        InnerMember innerMember1 = new Outer().new InnerMember();

        Outer temp = new Outer();
        InnerMember innerMember2 = temp.new InnerMember();
    }

    public static void createInnerStatic(){
        InnerStatic innerStatic1 = new InnerStatic(); // Only from inside of outer class.
        InnerStatic innerStatic = new Outer.InnerStatic();
    }

    public void createLocalClass(Integer param){
        class InnerLocal {
            private Integer number;
            InnerLocal(){
                number = param + number + staticNumber; // Fields used them as final or effectively final
            }
        }

        InnerLocal innerLocal = new InnerLocal();
    }

    interface InnerInterface {
        void sum();
    }

    abstract class InnerAbstract{
        abstract void sum();
    }

    public void createAnonynousClass(){
        InnerInterface innerInterface = new InnerInterface() {
            @Override
            public void sum() {

            }
        };

        InnerAbstract innerAbstract = new InnerAbstract() {
            @Override
            void sum() {

            }
        };
    }

    public static void main(String[] args) {

    }
}
