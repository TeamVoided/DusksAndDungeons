package org.teamvoided.dusk_autumn.compat

import com.fizzware.dramaticdoors.fabric.compat.Compats
import com.fizzware.dramaticdoors.fabric.compat.DDCompatAdvancement
import com.fizzware.dramaticdoors.fabric.compat.DDCompatRecipe
import com.fizzware.dramaticdoors.fabric.fabric.FabricUtils
import com.fizzware.dramaticdoors.fabric.fabric.datagen.DDModelProvider
import com.fizzware.dramaticdoors.fabric.registry.DDRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockSetType
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.log
import org.teamvoided.dusk_autumn.block.DnDWoodTypes
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.id

object DramaticDoorsCompat {
    fun id(path: String) = Identifier.of("dramaticdoors", path)

    fun initCompat() {
        log.info("Initializing DramaticDoors Compat")
        Compats.modChecker = FabricUtils.INSTANCE //(ender)  if this is not here we get a crash

        makeDnDDoors(DnDBlocks.BLUE_DOOR, DnDWoodTypes.CASCADE_BLOCK_SET_TYPE)
        makeDnDDoors(DnDBlocks.CASCADE_DOOR, DnDWoodTypes.CASCADE_BLOCK_SET_TYPE)
    }

    fun datagen(gen: BlockStateModelGenerator) {
        gen.createDoors(DnDBlocks.BLUE_DOOR)
        gen.createDoors(DnDBlocks.CASCADE_DOOR)
    }


    private fun makeDnDDoors(
        block: Block, blockSetType: BlockSetType,
        name: String = block.id.path, includeShort: Boolean = true, isWood: Boolean = true
    ) {
        makeDoorBlocks("dnd_$name", block, blockSetType, includeShort)
        makeRecipes("dnd_$name", block.id, isWood)
    }

    private fun makeDoorBlocks(name: String, block: Block, blockSetType: BlockSetType, includeShort: Boolean = true) =
        DDRegistry.registerDoorBlockAndItem("tall_$name", "short_$name", block, blockSetType, includeShort)

    private fun makeRecipes(name: String, block: Identifier, isWood: Boolean = true) {
        DDCompatAdvancement.createRecipeAdvancement("short_$name", block)
        DDCompatAdvancement.createRecipeAdvancement("tall_$name", block)

        DDCompatRecipe.createShortDoorRecipe("short_$name", block, isWood)
        DDCompatRecipe.createTallDoorRecipe("tall_$name", block, "tall_wooden_door")
    }

    private fun BlockStateModelGenerator.createDoors(block: Block, name: String = block.id.path) {
        this.createTallDoor(Registries.BLOCK.get(id("tall_dnd_$name")))
        this.createShortDoor(Registries.BLOCK.get(id("short_dnd_$name")))
    }

    private fun BlockStateModelGenerator.createTallDoor(doorBlock: Block) {
        val textureMapping = DDModelProvider.tallDoor(doorBlock)
        val resourceLocation1 = Models.DOOR_BOTTOM_LEFT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation5 = DDModelProvider.DOOR_MIDDLE_LEFT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation6 =
            DDModelProvider.DOOR_MIDDLE_LEFT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation7 = DDModelProvider.DOOR_MIDDLE_RIGHT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation8 =
            DDModelProvider.DOOR_MIDDLE_RIGHT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation9 = Models.DOOR_TOP_LEFT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation10 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation11 = Models.DOOR_TOP_RIGHT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation12 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        this.blockStateCollector.accept(
            DDModelProvider.createTallDoor(
                doorBlock,
                resourceLocation1,
                resourceLocation2,
                resourceLocation3,
                resourceLocation4,
                resourceLocation5,
                resourceLocation6,
                resourceLocation7,
                resourceLocation8,
                resourceLocation9,
                resourceLocation10,
                resourceLocation11,
                resourceLocation12
            )
        )
        this.registerItemModel(doorBlock.asItem())
    }

    private fun BlockStateModelGenerator.createShortDoor(doorBlock: Block) {
        val textureMapping = DDModelProvider.shortDoor(doorBlock)
        val resourceLocation = DDModelProvider.DOOR_SHORT_LEFT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation2 =
            DDModelProvider.DOOR_SHORT_LEFT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation3 = DDModelProvider.DOOR_SHORT_RIGHT.upload(doorBlock, textureMapping, this.modelCollector)
        val resourceLocation4 =
            DDModelProvider.DOOR_SHORT_RIGHT_OPEN.upload(doorBlock, textureMapping, this.modelCollector)
        this.blockStateCollector.accept(
            DDModelProvider.createShortDoor(
                doorBlock, resourceLocation, resourceLocation2, resourceLocation3, resourceLocation4
            )
        )
        this.registerItemModel(doorBlock.asItem())
    }
}
