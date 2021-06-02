package com.hsae.kotlindemo

class User {
    var name = "mike"

    var home = "yanchen"

    get() {
        return field + "nb"
    }

    set(value) {
        field = "cute" +value
    }


}