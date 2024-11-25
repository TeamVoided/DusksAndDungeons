package org.teamvoided.dusks_and_dungeons.util.datagen

@JvmField
var OVERRIDE_MODE = false

fun inOverrideMode(fn: () -> Unit) {
    OVERRIDE_MODE = true
    fn()
    OVERRIDE_MODE = false
}