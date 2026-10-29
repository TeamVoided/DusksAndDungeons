package org.teamvoided.dusks_and_dungeons.client.config

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.isDev

class DnDClientConfig : Config(id("client")) {

    @RequiresAction(Action.RESTART)
    var enableDFDebugRenderer = isDev()

}