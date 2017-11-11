package com.step.jliang.enumerated;

/**
 * Created by jliang on 17/5/1.
 */
public class EnumImplement {
    public static void main(String[] args) {
        friut f = friut.APPLE;
        f.getp();
        System.out.println(f);
        Food food = Food.Appetizer.SALAD;
        for (Course c : Course.values()) {
            System.out.println(c.get(0));
        }
    }
}

enum friut implements Food3 {
    APPLE(1, "1") {
        @Override
        public void getp() {
            System.out.println("apple");
        }
    },
    ORIANG(2, "2"),
    BANANA(3, "3");

    //  int id;
    // String des;
    //String ad;
    friut(int id, String des) {
        //     this.id = id;
        //   this.des = des;
    }

    public  void getp() {

    }
}

interface Food3 {
    void getp();
}

interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }

    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
}

enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    Food get(int i) {
        if (i >= values.length) {
            return null;
        }
        return values[i];
    }
}
