package com.aminoglycoside.ags_furniturepack.datagen;


import com.aminoglycoside.ags_furniturepack.block.ModBlocks;
import net.boat.industrialhellscape.block.modded_block_classes.RailingBlocks.RailingBlock;
import net.boat.industrialhellscape.block.modded_block_classes.RailingBlocks.StairRailingBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //DEBUG BLOCKS
        this.dropSelf(ModBlocks.ADDON_FURNISHINGS.get());

        this.dropSelf(ModBlocks.TEST_DESK.get());
        this.dropSelf(ModBlocks.CHAIR_BASIC.get());
        this.dropSelf(ModBlocks.CHAIR_BASIC_ARMCHAIR.get());
        this.dropSelf(ModBlocks.CHAIR_BASIC_ARMCHAIR_BLACK.get());
        this.dropSelf(ModBlocks.TABLE_BASIC.get());
        this.dropSelf(ModBlocks.DRAWER_MODERN_WHITE.get());
        this.dropSelf(ModBlocks.DRAWER_MODERN_BLACK.get());
        this.dropSelf(ModBlocks.DESK_MODERN_WHITE.get());
        this.dropSelf(ModBlocks.DESK_MODERN_BLACK.get());
        this.dropSelf(ModBlocks.DRAWER_MODERN_WHITE_TALL.get());
        this.dropSelf(ModBlocks.DRAWER_MODERN_BLACK_TALL.get());
        this.dropSelf(ModBlocks.CHAIR_DINER_RED.get());
        this.dropSelf(ModBlocks.TABLE_DINER_RED.get());

        this.dropSelf(ModBlocks.CHAIR_FOLDING_GRAY.get());
        this.dropSelf(ModBlocks.TABLE_FOLDING_GRAY.get());

        this.add(ModBlocks.GLASS_STAIR_RAILING.get(),
                block -> createStairRailingDrops(ModBlocks.GLASS_STAIR_RAILING.get()));
        this.add(ModBlocks.GLASS_RAILING.get(),
                block -> createRailingDrops(ModBlocks.GLASS_RAILING.get()));
    }


    protected LootTable.Builder createRailingDrops(Block pBlock) {
        //It turns out, .withPool()s can be stacked together to form separate loot pools.
        //For example, the vanilla function createPotFlowerItemTable() in BlockLootSubProvider

        return LootTable.lootTable()

                //Separate Loot pool for NORTH_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(RailingBlock.NORTH_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))

                //Separate Loot pool for SOUTH_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(RailingBlock.SOUTH_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))

                //Separate Loot pool for EAST_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(RailingBlock.EAST_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))

                //Separate Loot pool for WEST_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(RailingBlock.WEST_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))

                ;
    }

    protected LootTable.Builder createStairRailingDrops(Block pBlock) {
        return LootTable.lootTable()

                //Separate Loot pool for RIGHT_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(StairRailingBlock.LEFT_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))

                //Separate Loot pool for LEFT_FENCE blockstate (0-1 item output)
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(StairRailingBlock.RIGHT_FENCE, true))) //when true
                        .add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock)
                                        .apply(List.of(1), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_))))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))
                ;
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}