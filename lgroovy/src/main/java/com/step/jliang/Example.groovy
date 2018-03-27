package com.step.jliang
/**
 * @author jliang
 */
class Example {
    static def x = 5

    static def abc(a) {
        println(x)
        println(x +
                x)
    }

    def fun() {
        getX()
    }

    static void main(String[] args) {
        int a = 3;
        abc(a);
        String c = '''Hello Triple" aa
                "Multiple lines''';
        getX()
        def clos = { param -> println "Hello ${param}" }
        clos.call([param: 12])

        def lst = [11, 12, 13, 14];
        lst.each { println it }

        lst.each { println it }
        println("The list will only display those numbers which are divisible by 2")
        lst.each { num -> if (num % 2 == 0) println num }
    }
}
