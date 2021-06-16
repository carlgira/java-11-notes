package com.carlgira.classes;

import java.lang.reflect.Field;

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

        Object asd = null;

        class InnerLocal {
            private Integer number1;
            InnerLocal(){

                number1 = param + number + staticNumber; // Fields used them as final or effectively final
                Object aq = asd;
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
                int n = number + staticNumber;

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

class One {
    enum Baby {EGG}
    static class Two {
        private Integer one;
        enum Baby {EGG}
        final static class Three {
            enum Baby {EGG}
            public static void main(String[] args) {
                One.Baby b = One.Baby.EGG;
                Two.Baby c = Two.Baby.EGG;
                Baby e = Baby.EGG;
            }
        }
    }

    public static void main(String[] args) {
        Baby b = Baby.EGG;
        Two.Baby c = Two.Baby.EGG;
        Two.Three.Baby e = Two.Three.Baby.EGG;

    }
}



