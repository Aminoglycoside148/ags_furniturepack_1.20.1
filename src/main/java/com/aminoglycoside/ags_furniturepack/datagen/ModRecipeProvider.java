package com.aminoglycoside.ags_furniturepack.datagen;
import com.aminoglycoside.ags_furniturepack.block.ModBlocks;
import net.boat.industrialhellscape.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    //---------- END OF DATAGEN VARIABLES ----------
    private static final List<ItemLike> ADDON_FURNITURE_CATEGORIES = List.of(
            ModBlocks.ADDON_FURNISHINGS.get().asItem()
    );

    private static final List<ItemLike> ADDON_FURNITURE = List.of(
            ModBlocks.CHAIR_BASIC.get().asItem(),
            ModBlocks.CHAIR_BASIC_ARMCHAIR.get().asItem(),
            ModBlocks.CHAIR_BASIC_ARMCHAIR_BLACK.get().asItem(),
            ModBlocks.TABLE_BASIC.get().asItem(),
            ModBlocks.DRAWER_MODERN_WHITE.get().asItem(),
            ModBlocks.DESK_MODERN_WHITE.get().asItem(),
            ModBlocks.DRAWER_MODERN_WHITE_TALL.get().asItem(),
            ModBlocks.DRAWER_MODERN_BLACK.get().asItem(),
            ModBlocks.DESK_MODERN_BLACK.get().asItem(),
            ModBlocks.DRAWER_MODERN_BLACK_TALL.get().asItem(),
            ModBlocks.CHAIR_DINER_RED.get().asItem(),
            ModBlocks.TABLE_DINER_RED.get().asItem(),
            ModBlocks.CHAIR_FOLDING_GRAY.get().asItem(),
            ModBlocks.TABLE_FOLDING_GRAY.get().asItem(),

            ModBlocks.GLASS_STAIR_RAILING.get().asItem(),
            ModBlocks.GLASS_RAILING.get().asItem()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oneIngredientStonecutsToMany(ADDON_FURNITURE_CATEGORIES, "furniture_categories", ForgeRegistries.BLOCKS.getValue(new ResourceLocation("industrialhellscape", "ihea_furniture_kit")).asItem(), pWriter);
        oneIngredientStonecutsToMany(ADDON_FURNITURE, "addon_furniture", ModBlocks.ADDON_FURNISHINGS.get().asItem(), pWriter);


        //
// Slabs
//        slabRecipe(ModBlocks.BLUE_ROCKRETE_SLAB.get(), Ingredient.of(ModBlocks.BLUE_ROCKRETE.get()), "blue_rockrete_slab_craft", pWriter);
//        slabRecipe(ModBlocks.RED_ROCKRETE_SLAB.get(), Ingredient.of(ModBlocks.RED_ROCKRETE.get()), "red_rockrete_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GREEN_ROCKRETE_SLAB.get(), Ingredient.of(ModBlocks.GREEN_ROCKRETE.get()),"green_rockrete_slab_craft", pWriter);
//        slabRecipe(ModBlocks.YELLOW_ROCKRETE_SLAB.get(), Ingredient.of(ModBlocks.YELLOW_ROCKRETE.get()),"yellow_rockrete_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GRAY_ROCKRETE_SLAB.get(), Ingredient.of(ModBlocks.GRAY_ROCKRETE.get()),"gray_rockrete_slab_craft", pWriter);
//
//        slabRecipe(ModBlocks.STRUT_SLAB.get(), Ingredient.of(ModBlocks.STRUT.get()), "strut_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GRAY_STRUT_SLAB.get(), Ingredient.of(ModBlocks.GRAY_STRUT.get()), "gray_strut_slab_craft", pWriter);
//        slabRecipe(ModBlocks.RUSTY_STRUT_SLAB.get(), Ingredient.of(ModBlocks.RUSTY_STRUT.get()), "rusty_strut_slab_craft", pWriter);
//
//        slabRecipe(ModBlocks.CATWALK_STRUT_SLAB.get(), Ingredient.of(ModBlocks.CATWALK_STRUT.get()), "catwalk_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GRAY_CATWALK_STRUT_SLAB.get(), Ingredient.of(ModBlocks.GRAY_CATWALK_STRUT.get()), "gray_catwalk_slab_craft",  pWriter);
//        slabRecipe(ModBlocks.RUSTY_CATWALK_STRUT_SLAB.get(), Ingredient.of(ModBlocks.RUSTY_CATWALK_STRUT.get()),"rusty_catwalk_slab_craft", pWriter);
//
//        slabRecipe(ModBlocks.SMOOTH_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_VESSELPLATE_TILE.get()), "smooth_vesselplate_slab_craft", pWriter);
//        slabRecipe(ModBlocks.VESSELPLATE_SHEETING_SLAB.get(), Ingredient.of(ModBlocks.VESSELPLATE_SHEETING.get()), "vesselplate_sheeting_slab_craft", pWriter);
//        slabRecipe(ModBlocks.RIVETED_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.RIVETED_VESSELPLATE_PANEL.get()), "riveted_vesselplate_slab_craft", pWriter);
//
//        slabRecipe(ModBlocks.SMOOTH_GRAY_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_GRAY_VESSELPLATE_TILE.get()),"smooth_gray_vesselplate_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GRAY_VESSELPLATE_SHEETING_SLAB.get(), Ingredient.of(ModBlocks.GRAY_VESSELPLATE_SHEETING.get()),"gray_vesselplate_sheeting_slab_craft", pWriter);
//        slabRecipe(ModBlocks.GRAY_RIVETED_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.GRAY_RIVETED_VESSELPLATE_PANEL.get()),"gray_riveted_vesselplate_slab_craft", pWriter);
//
//        slabRecipe(ModBlocks.SMOOTH_RUSTY_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_RUSTY_VESSELPLATE_TILE.get()),"smooth_rusty_vesselplate_slab_craft", pWriter);
//        slabRecipe(ModBlocks.RUSTY_VESSELPLATE_SHEETING_SLAB.get(), Ingredient.of(ModBlocks.RUSTY_VESSELPLATE_SHEETING.get()),"rusty_vesselplate_sheeting_slab_craft", pWriter);
//        slabRecipe(ModBlocks.RUSTY_RIVETED_VESSELPLATE_SLAB.get(), Ingredient.of(ModBlocks.RUSTY_RIVETED_VESSELPLATE_PANEL.get()),"rusty_riveted_vesselplate_slab_craft", pWriter);

        //---------- END OF INTERCHANGEABLE STONECUTTER CRAFTING ----------
    }

    //---------- RECIPE GENERATION METHODS ----------

    protected static void oneIngredientStonecutsToMany(List<ItemLike> stonecutOutputList, String materialType, Item itemIngredient, Consumer<FinishedRecipe> pWriter){
        for (int i = 0; i < stonecutOutputList.size(); i++) {
            String itemName = stonecutOutputList.get(i).toString().replaceAll("[^a-zA-Z]+","_");
            stonecutting(
                    Ingredient.of(itemIngredient), //Input
                    RecipeCategory.BUILDING_BLOCKS,
                    stonecutOutputList.get(i),1)
                    .unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                    .save(pWriter, new ResourceLocation("industrialhellscape", materialType + "_stonecut_" + itemName+i));
        }
    }

    protected static void iterativeRecipes(List<ItemLike> stonecutOutputList, String materialType, TagKey<Item> smeltableItemTag, Consumer<FinishedRecipe> pWriter) {
        for (int i = 0; i < stonecutOutputList.size(); i++) {
            String itemName = stonecutOutputList.get(i).toString().replaceAll("[^a-zA-Z]+","_");
            if (itemName.contains("_slab")  ) {
                stonecutting(
                        Ingredient.of(smeltableItemTag),
                        RecipeCategory.BUILDING_BLOCKS,
                        stonecutOutputList.get(i),2) //2x output for slab output
                        .unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                        .save(pWriter, new ResourceLocation("industrialhellscape", materialType + "_stonecut_" + itemName+i));
            } else

                stonecutting(
                        Ingredient.of(smeltableItemTag),
                        RecipeCategory.BUILDING_BLOCKS,
                        stonecutOutputList.get(i),1)
                        .unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                        .save(pWriter, new ResourceLocation("industrialhellscape", materialType + "_stonecut_" + itemName+i));
        }
    }

    protected static void OneIngredientShapelessRecipe(Block outputBlock, int outputAmount, Ingredient inputIngredient, String recipeName, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputBlock, outputAmount)
                .requires(ModItems.INHELL_HAVEN_DEVICE.get())
                .requires(inputIngredient)
                .unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                .save(pWriter, new ResourceLocation("industrialhellscape", recipeName));
    }

    protected static void slabRecipe(Block outputBlock, Ingredient inputIngredient, String recipeName, Consumer<FinishedRecipe> pWriter) {
        //Create Door recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, outputBlock,6)
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")

                .define('A', inputIngredient)

                .unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                .save(pWriter, new ResourceLocation("industrialhellscape", recipeName));
    }

    protected static SingleItemRecipeBuilder stonecutting(Ingredient pIngredients, RecipeCategory pCategory, ItemLike pResult, Integer amount) {
        return new SingleItemRecipeBuilder(pCategory, RecipeSerializer.STONECUTTER, pIngredients, pResult, amount);
    }

    protected static void recipeSmeltAndBlast(Consumer<FinishedRecipe> pFinishedRecipeConsumer, TagKey<Item> tagOfInputIngredients, RecipeCategory pCategory, ItemLike outputIngredient, int smeltingTime, int blastingTime, String pGroup) {
        recipeSmelting(pFinishedRecipeConsumer, tagOfInputIngredients, pCategory, outputIngredient, smeltingTime, pGroup, "smelt_recycle");
        recipeBlasting(pFinishedRecipeConsumer, tagOfInputIngredients, pCategory, outputIngredient, blastingTime, pGroup, "blast_recycle");
    }

    protected static void recipeSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, TagKey<Item> tagOfInputIngredients, RecipeCategory pCategory, ItemLike outputIngredient, int pCookingTime, String pGroup, String pRecipeName) {
        cookingRecipe(pFinishedRecipeConsumer, tagOfInputIngredients, RecipeSerializer.SMELTING_RECIPE, pCategory, outputIngredient, pCookingTime, pGroup, pRecipeName);
    }

    protected static void recipeBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, TagKey<Item> tagOfInputIngredients, RecipeCategory pCategory, ItemLike outputIngredient, int pCookingTime, String pGroup, String pRecipeName) {
        cookingRecipe(pFinishedRecipeConsumer, tagOfInputIngredients, RecipeSerializer.BLASTING_RECIPE, pCategory, outputIngredient, pCookingTime, pGroup, pRecipeName);
    }
    protected static void cookingRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, TagKey<Item> tagOfIngredients, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, RecipeCategory pCategory, ItemLike pResult, int pCookingTime, String pGroup, String pRecipeName) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(tagOfIngredients), pCategory, pResult, 0, pCookingTime, pCookingSerializer)
                .group(pGroup).unlockedBy(getHasName(ModItems.INHELL_HAVEN_DEVICE.get()), has(ModItems.INHELL_HAVEN_DEVICE.get()))
                .save(pFinishedRecipeConsumer, pGroup + "_" + pRecipeName);
    }

    //---------- END OF RECIPE GENERATION METHODS ----------
}