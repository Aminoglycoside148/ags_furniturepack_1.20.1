package com.aminoglycoside.ags_furniturepack.block;

import com.aminoglycoside.ags_furniturepack.AGSFurnitureMod;
import com.aminoglycoside.ags_furniturepack.block.modded_block_classes.FourDirectionalConnectedBlock;
import com.aminoglycoside.ags_furniturepack.block.modded_interfaces.AddonHitboxGeometryCollection;
import com.aminoglycoside.ags_furniturepack.item.ModItems;
import com.aminoglycoside.ags_furniturepack.tags.ModTags;
import net.boat.industrialhellscape.block.modded_block_classes.ConnectedBlocks.ConnectedFurnitureBlock;
import net.boat.industrialhellscape.block.modded_block_classes.ContainerBlocks.ConnectedContainerBlock;
import net.boat.industrialhellscape.block.modded_block_classes.ContainerBlocks.TwoBlockContainerMultiBlock;
import net.boat.industrialhellscape.block.modded_block_classes.PlacedFacingBlocks.SimpleFacingBlock;
import net.boat.industrialhellscape.block.modded_block_classes.PlacedFacingBlocks.SittableFacingBlock;
import net.boat.industrialhellscape.block.modded_block_classes.RailingBlocks.RailingBlock;
import net.boat.industrialhellscape.block.modded_block_classes.RailingBlocks.StairRailingBlock;
import net.boat.industrialhellscape.block.modded_interfaces.HitboxGeometryCollection;
import net.boat.industrialhellscape.block.modded_logic_enums.MultiBlockPlacementDirection;
import net.boat.industrialhellscape.sound.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AGSFurnitureMod.MODID);

//add blocks like
//public static final RegistryObject <Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
//    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    //public static final RegistryObject <Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> ADDON_FURNISHINGS = registerBlock("addon_furnishings",
            () -> new SimpleFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHAIR_BASIC_ARMCHAIR = registerBlock("chair_basic_armchair",
            () -> new SittableFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    AddonHitboxGeometryCollection.ARMCHAIR_1_SHAPE()
            )
    );

    public static final RegistryObject<Block> CHAIR_BASIC_ARMCHAIR_BLACK = registerBlock("chair_basic_armchair_black",
            () -> new SittableFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    AddonHitboxGeometryCollection.ARMCHAIR_1_SHAPE()
            )
    );

    public static final RegistryObject<Block> CHAIR_BASIC = registerBlock("chair_basic",
            () -> new SittableFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    AddonHitboxGeometryCollection.CHAIR_BASIC_SHAPE()
            )
    );

    public static final RegistryObject<Block> DRAWER_MODERN_WHITE_TALL = registerBlock("drawer_modern_white_tall",
            () -> new TwoBlockContainerMultiBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion()
                    , MultiBlockPlacementDirection.VERTICAL
                    , 54
                    , ModSounds.METAL_BOX_OPEN.get()
                    , ModSounds.METAL_BOX_CLOSE.get()
            )
    );

    public static final RegistryObject<Block> DRAWER_MODERN_BLACK_TALL = registerBlock("drawer_modern_black_tall",
            () -> new TwoBlockContainerMultiBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion()
                    , MultiBlockPlacementDirection.VERTICAL
                    , 54
                    , ModSounds.METAL_BOX_OPEN.get()
                    , ModSounds.METAL_BOX_CLOSE.get()
            )
    );

    public static final RegistryObject<Block> DRAWER_MODERN_WHITE = registerBlock("drawer_modern_white",
            () -> new ConnectedContainerBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    18,
                    ModTags.Blocks.DRAWERS,
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    SoundEvents.BARREL_OPEN,
                    SoundEvents.BARREL_CLOSE,
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );

    public static final RegistryObject<Block> DRAWER_MODERN_BLACK = registerBlock("drawer_modern_black",
            () -> new ConnectedContainerBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    18,
                    ModTags.Blocks.DRAWERS,
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    HitboxGeometryCollection.FULL_BLOCK(),
                    SoundEvents.BARREL_OPEN,
                    SoundEvents.BARREL_CLOSE,
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );

    public static final RegistryObject<Block> DESK_MODERN_WHITE = registerBlock("desk_modern_white",
            () -> new ConnectedFurnitureBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.DRAWERS,
                    AddonHitboxGeometryCollection.DESK_MODERN_SOLO_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_LEFT_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_MIDDLE_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_RIGHT_SHAPE(),
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );

    public static final RegistryObject<Block> DESK_MODERN_BLACK = registerBlock("desk_modern_black",
            () -> new ConnectedFurnitureBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.DRAWERS,
                    AddonHitboxGeometryCollection.DESK_MODERN_SOLO_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_LEFT_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_MIDDLE_SHAPE(),
                    AddonHitboxGeometryCollection.DESK_MODERN_RIGHT_SHAPE(),
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );


    public static final RegistryObject<Block> TEST_DESK = registerBlock("test_desk",
            () -> new ConnectedFurnitureBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.DRAWERS,
                    HitboxGeometryCollection.DESK_SOLO_SHAPE(),
                    HitboxGeometryCollection.DESK_LEFT_SHAPE(),
                    HitboxGeometryCollection.DESK_MIDDLE_SHAPE(),
                    HitboxGeometryCollection.DESK_RIGHT_SHAPE(),
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );

    public static final RegistryObject<Block> TABLE_BASIC = registerBlock("table_basic",
            () -> new FourDirectionalConnectedBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.TABLES,
                    AddonHitboxGeometryCollection.TABLE_BASIC_SOLO_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_BASIC_END_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_BASIC_CENTER_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_BASIC_CORNER_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_BASIC_CENTER_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_BASIC_CENTER_SHAPE())
    );

    public static final RegistryObject<Block> TABLE_DINER_RED = registerBlock("table_diner_red",
            () -> new FourDirectionalConnectedBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.TABLES,
                    AddonHitboxGeometryCollection.TABLE_DINER_SOLO_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_DINER_END_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_DINER_LANE_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_DINER_CORNER_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_DINER_T_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_DINER_CENTER_SHAPE())
    );

    public static final RegistryObject<Block> CHAIR_DINER_RED = registerBlock("chair_diner_red",
            () -> new SittableFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    AddonHitboxGeometryCollection.CHAIR_DINER_SHAPE()
            )
    );

    public static final RegistryObject<Block> CHAIR_FOLDING_GRAY = registerBlock("chair_folding_gray",
            () -> new SittableFacingBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    AddonHitboxGeometryCollection.CHAIR_FOLDING_SHAPE()
            )
    );


    public static final RegistryObject<Block> TABLE_FOLDING_GRAY = registerBlock("table_folding_gray",
            () -> new ConnectedFurnitureBlock(BlockBehaviour
                    .Properties.copy(Blocks.OAK_PLANKS)
                    .noOcclusion(),
                    ModTags.Blocks.DRAWERS,
                    AddonHitboxGeometryCollection.TABLE_FOLDING_SOLO_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_FOLDING_LEFT_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_FOLDING_MIDDLE_SHAPE(),
                    AddonHitboxGeometryCollection.TABLE_FOLDING_RIGHT_SHAPE(),
                    MultiBlockPlacementDirection.HORIZONTAL
            )
    );

    public static final RegistryObject<Block> GLASS_RAILING = registerBlock("glass_railing",
            () -> new RailingBlock(BlockBehaviour
                    .Properties.copy(Blocks.GLASS)
                    .noOcclusion()
            )
    );
    public static final RegistryObject<Block> GLASS_STAIR_RAILING = registerBlock("glass_stair_railing",
            () -> new StairRailingBlock(BlockBehaviour
                    .Properties.copy(Blocks.GLASS)
                    .noOcclusion()
            )
    );


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())
        {
        });
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }



}

