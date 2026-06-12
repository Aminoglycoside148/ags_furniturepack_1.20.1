package com.aminoglycoside.ags_furniturepack.tags;

import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {
        //MAIN BLOCK CATEGORIES
        //FURNITURE CATEGORIES
        public static final TagKey<Block> BASIC_FURNITURE_CATEGORY = tag("basic_furniture_category");
        public static final TagKey<Block> ALL_FURNITURE_BLOCKS = tag("all_furniture_blocks");

        //CONNECTED BLOCKSET FAMILIES
        public static final TagKey<Block> TEST_DESK = tag("test_desk");
        public static final TagKey<Block> DRAWERS = tag("drawers");
        public static final TagKey<Block> TABLES = tag("tables");

        //---------- METHODS ----------
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(AGSFurnitureMod.MODID, name));
        }
        private static TagKey<Block> moddedTag(String modNameSpace, String name) {
            return BlockTags.create(new ResourceLocation(modNameSpace, name));
        }
    }

    public static class Items {
        //For ungenerated recipes dictating recycling back into vanilla materials (Slabs are NOT smeltable. NOT INCLUDED HERE)

        //public static final TagKey<Item> VESSELPLATE_SMELTABLE_ITEM = tag("vesselplate_smeltable_item");
        //---------- METHODS ----------
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AGSFurnitureMod.MODID, name));
        }
        private static TagKey<Item> moddedTag(String modNameSpace, String name) {
            return ItemTags.create(new ResourceLocation(modNameSpace, name));
        }
    }
}