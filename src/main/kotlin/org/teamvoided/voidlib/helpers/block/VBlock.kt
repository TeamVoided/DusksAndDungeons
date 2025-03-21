package org.teamvoided.voidlib.helpers.block

import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block

class VBlock(block:Block) : Block(copy(block))
