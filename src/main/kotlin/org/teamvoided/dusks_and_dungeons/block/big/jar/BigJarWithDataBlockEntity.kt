//package org.teamvoided.dusks_and_dungeons.block.big.jar
//
//import net.minecraft.core.BlockPos
//import net.minecraft.core.HolderLookup
//import net.minecraft.core.component.DataComponentMap
//import net.minecraft.core.component.DataComponents
//import net.minecraft.nbt.CompoundTag
//import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
//import net.minecraft.resources.ResourceKey
//import net.minecraft.world.RandomizableContainer
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.item.ItemStack
//import net.minecraft.world.item.component.ItemContainerContents
//import net.minecraft.world.level.block.entity.BlockEntity
//import net.minecraft.world.level.block.entity.BlockEntityType
//import net.minecraft.world.level.block.state.BlockState
//import net.minecraft.world.level.storage.loot.LootTable
//import net.minecraft.world.ticks.ContainerSingleItem.BlockContainerSingleItem
//import org.teamvoided.dusks_and_dungeons.recipe.UseOnBlockRecipe.Companion.asItemOrEmpty
//
//class BigJarWithDataBlockEntity(blockPos: BlockPos, blockState: BlockState) :
//    BlockEntity(BlockEntityType.DECORATED_POT, blockPos, blockState), RandomizableContainer, BlockContainerSingleItem {
//    private var item: ItemStack
//    private var lootTable: ResourceKey<LootTable>? = null
//    private var lootTableSeed: Long = 0
//
//    init {
//        this.item = ItemStack.EMPTY
//    }
//
//    override fun saveAdditional(compoundTag: CompoundTag, provider: HolderLookup.Provider) {
//        super.saveAdditional(compoundTag, provider)
//        if (!this.trySaveLootTable(compoundTag) && !this.item.isEmpty) {
//            compoundTag.put(TAG_ITEM, this.item.save(provider))
//        }
//    }
//
//    override fun loadAdditional(compoundTag: CompoundTag, provider: HolderLookup.Provider) {
//        super.loadAdditional(compoundTag, provider)
//        if (!this.tryLoadLootTable(compoundTag)) {
//            if (compoundTag.contains(TAG_ITEM, 10)) {
//                this.item = ItemStack.parse(provider, compoundTag.getCompound(TAG_ITEM)).orElse(ItemStack.EMPTY)
//            } else {
//                this.item = ItemStack.EMPTY
//            }
//        }
//    }
//
//    val updatePacket: ClientboundBlockEntityDataPacket
//        get() = ClientboundBlockEntityDataPacket.create(this)
//
//    override fun getUpdateTag(provider: HolderLookup.Provider?): CompoundTag {
//        return this.saveCustomOnly(provider)
//    }
//
//    fun setFromItem(itemStack: ItemStack?) {
//        this.applyComponentsFromItemStack(itemStack)
//    }
//
//    val jarAsItem: ItemStack
//        get() {
//            val itemStack = blockState.asItemOrEmpty().item.defaultInstance
//            itemStack.applyComponents(this.collectComponents())
//            return itemStack
//        }
//
//    override fun getLootTable(): ResourceKey<LootTable>? = this.lootTable
//
//    override fun setLootTable(resourceKey: ResourceKey<LootTable>?) {
//        this.lootTable = resourceKey
//    }
//
//    override fun getLootTableSeed(): Long = this.lootTableSeed
//
//    override fun setLootTableSeed(l: Long) {
//        this.lootTableSeed = l
//    }
//
//    override fun collectImplicitComponents(builder: DataComponentMap.Builder) {
//        super.collectImplicitComponents(builder)
//        builder.set(
//            DataComponents.CONTAINER,
//            ItemContainerContents.fromItems(listOf(this.item))
//        )
//    }
//
//    override fun applyImplicitComponents(dataComponentInput: DataComponentInput) {
//        super.applyImplicitComponents(dataComponentInput)
//        this.item = (dataComponentInput.getOrDefault(
//            DataComponents.CONTAINER,
//            ItemContainerContents.EMPTY
//        ) as ItemContainerContents).copyOne()
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun removeComponentsFromTag(compoundTag: CompoundTag) {
//        super.removeComponentsFromTag(compoundTag)
//        compoundTag.remove(TAG_ITEM)
//    }
//
//    override fun getTheItem(): ItemStack {
//        this.unpackLootTable(null as Player?)
//        return this.item
//    }
//
//    override fun splitTheItem(i: Int): ItemStack {
//        this.unpackLootTable(null as Player?)
//        val itemStack = this.item.split(i)
//        if (this.item.isEmpty) {
//            this.item = ItemStack.EMPTY
//        }
//
//        return itemStack
//    }
//
//    override fun setTheItem(itemStack: ItemStack) {
//        this.unpackLootTable(null as Player?)
//        this.item = itemStack
//    }
//
//    override fun getContainerBlockEntity(): BlockEntity = this
//
//    companion object {
//        const val TAG_ITEM: String = "item"
//        //const val TAG_BIOME: String = "biome"
//        //const val TAG_POTION: String = "potion"
//    }
//}
