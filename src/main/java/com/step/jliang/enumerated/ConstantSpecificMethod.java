package com.step.jliang.enumerated;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by jliang on 17/5/1.
 */
public class ConstantSpecificMethod {
    static void f1(EnumMethod e){
        System.out.println(e.getInfo());
    }

    public static void main(String[] args) {
        for(EnumMethod csm : EnumMethod.values())
            f1(csm);
    }
}

enum EnumMethod {
    DATE_TIME {
        String getInfo() {
            return
                    DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };
    abstract String getInfo();
    public static void main(String[] args) {
        for(EnumMethod csm : values())
            System.out.println(csm.getInfo());
    }
}
