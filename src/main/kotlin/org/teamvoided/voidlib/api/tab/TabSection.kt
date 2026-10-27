package org.teamvoided.voidlib.api.tab

import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.flag.FeatureFlagSet
import net.minecraft.world.item.CreativeModeTab.*
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStackLinkedSet
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import kotlin.math.ceil

data class TabSection(
    val id: ResourceLocation,
    val backgroundTexture: ResourceLocation,
    private val name: Component,
    private val generator: DisplayItemsGenerator,
) {

    private var rows: Int = 0
    private var sectionContents: Set<ItemStack> = ItemStackLinkedSet.createTypeAndComponentsSet()
    private var searchContents: Set<ItemStack> = ItemStackLinkedSet.createTypeAndComponentsSet()

    fun rows() = rows

    fun getDisplayName(): Component = name

    fun contents(): Set<ItemStack> = sectionContents

    fun searchContents(): Set<ItemStack> = searchContents

    fun buildContents(params: ItemDisplayParameters) {
        val output = SectionOutputBuilder(this, params.enabledFeatures)
        generator.accept(params, output)
        sectionContents = output.sectionContents
        searchContents = output.searchContents
        rows = ceil(searchContents.size / 9.0).toInt()
    }


    class Builder(val id: ResourceLocation) {

        private var generator: DisplayItemsGenerator = EMPTY_GENERATOR
        private var name: Component = Component.empty()
        private var backgroundTexture: ResourceLocation = DEFAULT_BACKGROUND

        fun name(name: Component): Builder {
            this.name = name
            return this
        }

        fun backgroundTexture(textureId: ResourceLocation): Builder {
            backgroundTexture = textureId
            return this
        }

        fun displayItems(generator: DisplayItemsGenerator): Builder {
            this.generator = generator
            return this
        }

        fun build(): TabSection {
            return TabSection(id, backgroundTexture, name, generator)
        }

    }

    class SectionOutputBuilder(val section: TabSection, val features: FeatureFlagSet) : Output {

        val sectionContents: MutableSet<ItemStack> = ItemStackLinkedSet.createTypeAndComponentsSet()
        val searchContents: MutableSet<ItemStack> = ItemStackLinkedSet.createTypeAndComponentsSet()

        override fun accept(stack: ItemStack, visibility: TabVisibility) {
            require(stack.count == 1) { "Stack(${stack.getDisplayName().string}) size must be exactly 1" }

            check(!(sectionContents.contains(stack) && visibility != TabVisibility.SEARCH_TAB_ONLY)) {
                "Accidentally adding the same item stack twice ${stack.getDisplayName().string} to a Tab Section: ${section.getDisplayName().string}"
            }

            if (stack.item.isEnabled(features)) when (visibility) {
                TabVisibility.PARENT_AND_SEARCH_TABS -> {
                    sectionContents.add(stack)
                    searchContents.add(stack)
                }

                TabVisibility.PARENT_TAB_ONLY -> sectionContents.add(stack)
                TabVisibility.SEARCH_TAB_ONLY -> searchContents.add(stack)
            }
        }

    }

    companion object {

        private val EMPTY_GENERATOR = DisplayItemsGenerator { _, _ -> }

        val DEFAULT_BACKGROUND = id("container/creative_inventory/section/blank")

        fun builder(id: ResourceLocation): Builder {
            return Builder(id)
        }

        fun build(id: ResourceLocation, fn: Builder.() -> Unit): TabSection {
            val builder = builder(id)
            fn.invoke(builder)
            return builder.build()
        }

    }
}