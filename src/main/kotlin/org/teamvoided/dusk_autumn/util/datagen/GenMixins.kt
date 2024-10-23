package org.teamvoided.dusk_autumn.util.datagen

@JvmField
var OVERRIDE_MODE = false

fun inOverrideMode(fn:()->Unit){
    OVERRIDE_MODE = true
    fn()
    OVERRIDE_MODE = false
}