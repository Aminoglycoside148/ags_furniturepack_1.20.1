package com.aminoglycoside.ags_furniturepack.datagen;

import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import com.aminoglycoside.ags_furniturepack.block.ModBlocks;
import com.aminoglycoside.ags_furniturepack.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AGSFurnitureMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.TEST_DESK)
                .add(
                        ModBlocks.TEST_DESK.get()
                );

        this.tag(ModTags.Blocks.DRAWERS)
                .add(
                        ModBlocks.DRAWER_MODERN_WHITE.get(),
                        ModBlocks.DRAWER_MODERN_BLACK.get(),
                        ModBlocks.DESK_MODERN_WHITE.get(),
                        ModBlocks.DESK_MODERN_BLACK.get(),
                        ModBlocks.TABLE_FOLDING_GRAY.get()
                );

        this.tag(ModTags.Blocks.TABLES)
                .add(
                        ModBlocks.TABLE_BASIC.get(),
                        ModBlocks.TABLE_DINER_RED.get()
                );
    }
}