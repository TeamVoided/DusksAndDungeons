package org.teamvoided.voidlib.helpers.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy

class VBlock(block: Block) : Block(ofFullCopy(block))
