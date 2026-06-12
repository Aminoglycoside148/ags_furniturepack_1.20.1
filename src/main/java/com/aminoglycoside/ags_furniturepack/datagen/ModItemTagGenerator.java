package com.aminoglycoside.ags_furniturepack.datagen;

import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, AGSFurnitureMod.MODID, existingFileHelper);
    }
    public static final TagKey<Item> PICKAXES = TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("minecraft", "pickaxes"));
    public static final TagKey<Item> WRENCHES = TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("forge", "wrenches"));
    public static final TagKey<Item> WRENCH = TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("forge", "tools/wrench"));
    public static final TagKey<Item> TOOLS = TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("forge", "tools"));

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider pProvider) {
        //---------- EXTERNAL TAG REGISTRATION ----------
    }
}