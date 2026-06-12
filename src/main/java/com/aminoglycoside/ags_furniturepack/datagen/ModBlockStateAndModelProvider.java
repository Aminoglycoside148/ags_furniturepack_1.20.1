package com.aminoglycoside.ags_furniturepack.datagen;

import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import com.aminoglycoside.ags_furniturepack.block.ModBlocks;
import net.boat.industrialhellscape.block.modded_block_state_properties.TwoBlockMultiBlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateAndModelProvider extends BlockStateProvider {
    public static final EnumProperty<TwoBlockMultiBlockState> HALF = EnumProperty.create("half", TwoBlockMultiBlockState.class);
    public static final DirectionProperty FACING = BlockStateProperties.VERTICAL_DIRECTION;
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty ALT_STATE = BooleanProperty.create("alt_state");
    public static final EnumProperty<AttachFace> SURFACE_MOUNT = BlockStateProperties.ATTACH_FACE;

    public ModBlockStateAndModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AGSFurnitureMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Method naming convention ("SBI", "SI", etc.):
        //"S" - For State. Generates block State
        //"B" - For Block. Generates block Model (if no existing one is available).
        //"I" - For Item. Generates Item Model

        //---------- BLOCK ASSET GENERATION LIST ----------
        genHorizontalSBI(ModBlocks.ADDON_FURNISHINGS.get(), build3FaceTexturesBlockModel("addon_furnishings","furniture_category_block", "addon_furnishings_north", "addon_furnishings_west", "addon_furnishings_up"));


        GenFacingModelledSI(ModBlocks.CHAIR_BASIC_ARMCHAIR.get(),"basic_theme");
        GenFacingModelledSI(ModBlocks.CHAIR_BASIC_ARMCHAIR_BLACK.get(),"basic_theme");
        GenFacingModelledSI(ModBlocks.CHAIR_BASIC.get(),"basic_theme");
        GenFacingModelledSI(ModBlocks.CHAIR_DINER_RED.get(),"diner");
        GenFacingModelledSI(ModBlocks.CHAIR_FOLDING_GRAY.get(),"folding");

        genI(ModBlocks.GLASS_RAILING.get(),"railing");
        genCustomI(ModBlocks.GLASS_STAIR_RAILING.get(),"railing","glass_stair_rail_left");
    }
    //---------- END OF BLOCK ASSET GENERATION LIST ----------

    //---------- CUSTOM BLOCK MODEL GENERATORS ----------

    private ModelFile build3FaceTexturesBlockModel(String blockName, String folderName, String frontAndBack, String leftAndRight, String topAndBottom) {
        //Builds a textured model that uses three texture .pngs for all 6 faces.
        return build6FaceTexturesBlockModel(blockName, folderName, frontAndBack, frontAndBack, leftAndRight,leftAndRight,topAndBottom,topAndBottom);
    }

    private ModelFile build6FaceTexturesBlockModel(String blockName, String folderName, String front, String back, String left, String right, String top, String bottom) {
        //Builds a textured model that uses six texture .pngs for all 6 faces.
        return models().cube(
                blockName,
                modLoc( "block/" + folderName+ "/" + bottom), //bottom
                modLoc("block/" + folderName+ "/" + top), //top
                modLoc("block/" + folderName+ "/" + front), //front
                modLoc("block/" + folderName+ "/" + back), //back
                modLoc("block/"  + folderName+ "/" + left), //left
                modLoc("block/"  + folderName+ "/" + right) //right
        ).texture("particle", modLoc("block/" + folderName +"/" + top));
    }

    private ModelFile buildAntiCullBlock(String blockName, String folderName, String side, String top, String renderType) {
        return models()
                .withExistingParent(blockName, modLoc("block/anticull_template"))
                .texture("side", modLoc("block/" + folderName + "/" + side)) //side
                .texture("top", modLoc("block/" + folderName + "/" + top)) //top
                .texture("bottom", modLoc("block/" + folderName + "/" + side)) //bottom
                .renderType(renderType);
    }

    private ModelFile buildSimpleBlockWithRenderType(String blockName, String folderName, String renderType) {
        //Builds a textured model that uses six texture .pngs for all 6 faces.

        return models().cubeAll(
                blockName,
                modLoc( "block/" + folderName+ "/" + blockName)
        ).texture("particle", modLoc("block/" + folderName +"/" + blockName)).renderType(renderType);
    }

    private ModelFile buildCubeColumn(String modelName, String folderName, String sideTexture, String endTexture) {
        String basePath = "block/"; //generated/resources/assets/models/blocks

        return models()
                .withExistingParent(basePath + modelName, mcLoc("block/cube_column"))
                .texture("side", AGSFurnitureMod.MODID + ":" + basePath + (folderName+(folderName.isEmpty() ? "":"/")) + sideTexture)
                .texture("end", AGSFurnitureMod.MODID + ":" + basePath + (folderName+(folderName.isEmpty() ? "":"/")) + endTexture);
    }

    //---------- END OF CUSTOM BLOCK MODEL GENERATOR METHODS ----------

    //---------- SBI ASSET GENERATOR METHODS ----------
    // STATE, BLOCK MODEL, AND/OR ITEM MODEL GENERATION

    private void genSimpleBlockWithRenderTypeSBI(Block block, String folderName, String renderType) {
        //For blocks like Vesselglass
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        simpleBlock(block, buildSimpleBlockWithRenderType(stringName, folderName, renderType));

        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genAntiCullBLock(Block block, String folderName, String side, String top, String renderType) {
        //For Strut blocks. A side and top texture are required to accomodate Catwalk Struts
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        simpleBlock(block, buildAntiCullBlock(stringName, folderName, side, top, renderType));

        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }


    private void genStairsWithRenderTypeSBI(Block block, String folderName, String sideTexture, String bottomTexture, String topTexture, String renderType) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String blockPath = "block/" + folderName+(folderName.isEmpty() ? "":"/");


        ResourceLocation bottomTextureLoc = new ResourceLocation(AGSFurnitureMod.MODID,blockPath+bottomTexture );
        ResourceLocation sideTextureLoc = new ResourceLocation(AGSFurnitureMod.MODID,blockPath+sideTexture );
        ResourceLocation topTextureLoc = new ResourceLocation(AGSFurnitureMod.MODID,blockPath+topTexture );

        stairsBlockWithRenderType((StairBlock) block, sideTextureLoc,bottomTextureLoc,topTextureLoc,renderType);

        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genSimpleSlabsSBI(Block block, Block parentBlock) {
        //For Rockrete slabs which have a homogenous texture and solid model
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        slabBlock(((SlabBlock) block), blockTexture(parentBlock), blockTexture(parentBlock));

        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }
    private void genSlabsWithCustomDoubleSBI(Block block, Block parentBlock, String folderName, String doubleSlabName, String sideTexture, String endTexture, String nameStringToReplace, String nameStringReplacement ) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String parentStringName = BuiltInRegistries.BLOCK.getKey(parentBlock).getPath();
        String blockPath = "block/" + folderName+(folderName.isEmpty() ? "":"/");
        String generatedBlockPath = "block/";

        //build custom double-slab model
        buildCubeColumn(doubleSlabName, folderName, sideTexture, endTexture);

        ResourceLocation doubleSlabLoc = new ResourceLocation(AGSFurnitureMod.MODID,generatedBlockPath + doubleSlabName );
        ResourceLocation textureLoc = new ResourceLocation(AGSFurnitureMod.MODID,blockPath + parentStringName.replace(nameStringToReplace,nameStringReplacement) );

        slabBlock(((SlabBlock) block), doubleSlabLoc, textureLoc);

        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genSlabBlockSBI(Block block, String folderName, String sideTextureName, String topTextureName, String bottomTextureName, String renderType) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        ResourceLocation side = modLoc("block/" + folderName + "/" + sideTextureName);
        ResourceLocation top = modLoc("block/" + folderName + "/" + topTextureName);
        ResourceLocation bottom =  modLoc("block/" + folderName + "/" + bottomTextureName);

        ModelFile slab = models()
                .withExistingParent(stringName, modLoc("block/anticull_slab"))
                .texture("side", side) //side
                .texture("top", top) //top
                .texture("bottom", bottom) //bottom
                .renderType(renderType);

        //models().slab(stringName, side, bottom, top).texture("particle", side).renderType(renderType);
        ModelFile slabTop = models()
                .withExistingParent(stringName+"_top", modLoc("block/anticull_slab_top"))
                .texture("side", side) //side
                .texture("top", top) //top
                .texture("bottom", bottom) //bottom
                .renderType(renderType);

        //models().slabTop(stringName + "_top", side, bottom, top).texture("particle", side).renderType(renderType);

        ModelFile slabDouble = models().getExistingFile(modLoc("block/"+stringName.replace("_slab","")));

        getVariantBuilder(block).forAllStates(state -> {
            SlabType type = state.getValue(SlabBlock.TYPE);
            return ConfiguredModel.builder()
                    .modelFile(
                            switch (type) {
                                case TOP -> slabTop;
                                case BOTTOM -> slab;
                                default -> slabDouble;
                            }
                    ).build();
        });

        itemModels().getBuilder(stringName)
                .parent(slab)
                .renderType(renderType);
    }

    private void genFolderedSI(Block block, String folderName) { //STATES AND ITEM MODEL ONLY
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;
        getVariantBuilder(block)
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc(existingModelPath)))
                .addModel();
        //Generate Item Model
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genFolderedSBI(Block block, String subfolder) { //STATES, BLOCK MODEL, ITEM MODEL
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        simpleBlockWithItem(block,
                models().withExistingParent(stringName, mcLoc("block/cube_all"))
                        .texture("all", modLoc("block/" + subfolder+(subfolder.isEmpty() ? "":"/") + stringName)));
    }

    private void genAttachedSI(Block block, String folderName) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(FaceAttachedHorizontalDirectionalBlock.FACING);
            AttachFace face = state.getValue(FaceAttachedHorizontalDirectionalBlock.FACE);
            simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));

            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName)))
                    .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                    .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                    .build();

        }, BlockStateProperties.WATERLOGGED);
    }

    private void genAttachedSBI(Block block, String folderName, ModelFile model) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(FaceAttachedHorizontalDirectionalBlock.FACING);
            AttachFace face = state.getValue(FaceAttachedHorizontalDirectionalBlock.FACE);
            simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                    .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                    .build();

        }, BlockStateProperties.WATERLOGGED);
    }

    private void genHorizontalSBI(Block block, ModelFile model) {
        horizontalBlock(block, model);

        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genTrapdoorSBI(Block block, String renderType) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+stringName;
        trapdoorBlockWithRenderType(((TrapDoorBlock) block), modLoc("block/doors/" + stringName), true, renderType);

        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath+"_bottom")));
    }

    private void genDoorSBI(Block block, String renderType) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+stringName;
        doorBlockWithRenderType(((DoorBlock) block), modLoc("block/doors/"+stringName+"_bottom"), modLoc("block/doors/"+stringName+"_top"), renderType);
    }

    private void genSimpleSBI(Block block, ModelFile model) {
        simpleBlock(block, model);

        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String existingModelPath = "block/"+stringName;
        simpleBlockItem(block, models().getExistingFile(modLoc(existingModelPath)));
    }

    private void genFolderedToggleBlockSBI(Block block, String textureSubFolder, String existingBaseModelSubFolder, Boolean makeBaseModel, Boolean makeAltModel, String nameStringToReplace, String nameStringReplacement, String renderType) {

        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        String baseModelPath = "block/"+stringName;
        String altStringName = stringName.replace(nameStringToReplace,nameStringReplacement);
        String altModelPath = "block/"+altStringName;


        String pathToTexture = "block/" + (textureSubFolder+(textureSubFolder.isEmpty() ? "":"/"));
        String texturePath =  pathToTexture + stringName;
        String altTexturePath = pathToTexture + stringName.replace(nameStringToReplace,nameStringReplacement);

        //GENERATE BASE MODEL (DEFAULT STATE), optional if not already present
        if(makeBaseModel) {
            models().withExistingParent(stringName, mcLoc("block/cube_all"))
                    .texture("all", modLoc(texturePath)).renderType(renderType);
        }

        //GENERATE ROTATED MODEL (ALT TEXTURE STATE)
        if(makeAltModel) {
            models().withExistingParent(altStringName, mcLoc("block/cube_all"))
                    .texture("all", modLoc(altTexturePath)).renderType(renderType); //Generate model of alt-texture block in generated models/block folder
        }

        //GENERATE BLOCKSTATES
        getVariantBuilder(block)
                .forAllStates(state -> {
                    boolean altTexture = state.getValue(ALT_STATE);
                    String modelToUse = (altTexture) ? altModelPath : baseModelPath;

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc(modelToUse)))
                            .build();
                });
        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(baseModelPath)));
    }

    private void genFolderedDirToggleBlockSBI(Block block, String textureSubFolder, String existingBaseModelSubFolder, Boolean makeBaseModel, Boolean makeAltModel, String textureName, String altTextureName) {

        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String pathToName = "block/"+existingBaseModelSubFolder+(existingBaseModelSubFolder.isEmpty() ? "":"/");

        String baseModelPath = pathToName+stringName;
        String altModelPath = pathToName+stringName+"_alt_texture";

        String pathToTexture = "block/" + (textureSubFolder+(textureSubFolder.isEmpty() ? "":"/"));
        String texturePath =  pathToTexture + textureName;
        String altTexturePath = pathToTexture + altTextureName;

        //GENERATE BASE MODEL (DEFAULT STATE), optional if not already present
        if(makeBaseModel) {
            models().withExistingParent(stringName, mcLoc("block/cube_all"))
                    .texture("all", modLoc(texturePath));
        }

        //GENERATE ROTATED MODEL (ALT TEXTURE STATE)
        if(makeAltModel) {
            models().withExistingParent(altModelPath, mcLoc("block/cube_all"))
                    .texture("all", modLoc(altTexturePath)); //Generate model of alt-texture block in generated models/block folder
        }

        //GENERATE BLOCKSTATES
        getVariantBuilder(block)
                .forAllStates(state -> {
                    boolean altTexture = state.getValue(ALT_STATE);
                    String modelToUse = (altTexture) ? altModelPath : baseModelPath;

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc(modelToUse)))
                            .build();
                });
        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(baseModelPath)));
    }

    private void GenFacingModelledSI(Block block, String folderName) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String modelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction horizontalFacing = state.getValue(HORIZONTAL_FACING);

                    int yRot = switch (horizontalFacing) {
                        case SOUTH -> 180;
                        case WEST  -> 270;
                        case EAST  -> 90;
                        default -> 0; //NORTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc("block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName)))
                            .rotationY(yRot)
                            .build();
                }, BlockStateProperties.WATERLOGGED);

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(modelPath)));
    }

    private void GenFacingPoweredSI(Block block, String folderName) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String unpoweredModelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;
        String poweredModelPath = unpoweredModelPath+"_on";

        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction horizontalFacing = state.getValue(HORIZONTAL_FACING);
                    Boolean powered = state.getValue(POWERED);
                    String modelToUse = powered? poweredModelPath : unpoweredModelPath;

                    int yRot = switch (horizontalFacing   ) {
                        case SOUTH -> 180;
                        case WEST  -> 270;
                        case EAST  -> 90;
                        default -> 0; //NORTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc(modelToUse)))
                            .rotationY(yRot)
                            .build();
                }, BlockStateProperties.WATERLOGGED);

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(unpoweredModelPath)));
    }

    private void genWaterloggableSI(Block block, String folderName) {
        //No placement rotation necessary for these blocks
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String modelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        getVariantBuilder(block)
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc(modelPath)))
                .addModel();

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(modelPath)));
    }

    private void genWAllVsCeilingSI(Block block, String folderName, String ceilingModelKey, String wallModelKey) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String blockName = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;
        String ceilingOrFloorModel = blockName+"_"+ceilingModelKey;
        String wallModel = blockName+"_"+wallModelKey;

        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction horizontalFacing = state.getValue(HORIZONTAL_FACING);
                    AttachFace surfaceAttached = state.getValue(SURFACE_MOUNT);
                    String modelToUse = (surfaceAttached == AttachFace.CEILING || surfaceAttached == AttachFace.FLOOR)? ceilingOrFloorModel : wallModel;

                    int yRot = switch (horizontalFacing   ) {
                        case SOUTH -> 180;
                        case WEST  -> 270;
                        case EAST  -> 90;
                        default -> 0; //NORTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc(modelToUse)))
                            .rotationY(yRot)
                            .build();
                }, BlockStateProperties.WATERLOGGED);

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(wallModel)));

    }


    private void TwoBlockMultiBlock(Block block, String folderName) {
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String blockName = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;
        String positiveBlockName = blockName+"_positive";
        String negativeBlockName = blockName+"_negative";

        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction horizontalFacing = state.getValue(HORIZONTAL_FACING);
                    TwoBlockMultiBlockState half = state.getValue(HALF);
                    String modelToUse = (half == TwoBlockMultiBlockState.POSITIVE)? positiveBlockName : negativeBlockName;

                    int yRot = switch (horizontalFacing   ) {
                        case SOUTH -> 180;
                        case WEST  -> 270;
                        case EAST  -> 90;
                        default -> 0; //NORTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(modLoc(modelToUse)))
                            .rotationY(yRot)
                            .build();
                }, BlockStateProperties.WATERLOGGED);

    }

    private void genI(Block block, String folderName) {
        //Only generate the item model for this block. Block states and block models are already written.
        String stringName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String modelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(modelPath)));
    }
    private void genCustomI(Block block, String folderName, String stringName) {
        //Only generate the item model for this block. Block states and block models are already written.
        String modelPath = "block/"+folderName+(folderName.isEmpty() ? "":"/")+stringName;

        //GENERATE ITEM MODEL
        simpleBlockItem(block, models().getExistingFile(modLoc(modelPath)));
    }

    //---------- END OF SBI ASSET GENERATOR METHODS ----------
}
