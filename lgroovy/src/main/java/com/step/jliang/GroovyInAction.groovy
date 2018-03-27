package com.step.jliang

/**
 * @author jliang
 */
class GroovyInAction extends GroovyTestCase {

    void testBiBao() {
        def classes = [String, List];
        println(classes.'package'.name)
    }
}
