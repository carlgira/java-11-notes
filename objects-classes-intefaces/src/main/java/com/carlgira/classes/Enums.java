package com.carlgira.classes;

public class Enums {
    public static void main(String[] args) {

        Normal normal = Normal.ONE;
    }
}

enum Normale  {
    ONE, TWO;
}



enum Normal {
    ONE {

        Integer sum(){
            return 1;
        }
    }, TWO {

        Integer sum(){
            return 1;
        }
    }, THREE  {

        Integer sum(){
            return 1;
        }
    };

    // new Normal();

    abstract Integer sum();
}

enum OtherEnum {
    ONE(1), TWO(2);
    Integer age;

    OtherEnum(Integer age){
    }
}

enum Sail {
    TALL(1) {
        @Override
        protected int getHeight() {
            return 0;
        }
    },

    SHORT(2) {
        protected int getHeight() {return 2;}
    };

    protected abstract int getHeight();

    Sail(Integer e){
    }
}



class Penguin {


    enum Baby {EGG}

    static class Chick {
        enum Baby {EGG}

        public static void main(String[] args) {
            Penguin.Baby b = Penguin.Baby.EGG;
        }
    }

    public static void main(String[] args) {
        boolean match = false;
        Baby egg = Baby.EGG;
        switch (egg) {
            case EGG:
                match = true;
        }

        Chick o = new Chick();
    }

}