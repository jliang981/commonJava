package com.step.jliang.inaction

/**
 * @author jliang
 */
Book gina = new Book('Groovy in Action')
assert gina.getTitle() == 'Groovy in Action'
assert getTitleBackwards(gina) == 'Groovy in Action'

String getTitleBackwards(book) {
    title = book.getTitle()
    return title.reverse()
}