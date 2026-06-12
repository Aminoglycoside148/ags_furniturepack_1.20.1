package com.aminoglycoside.ags_furniturepack.block.modded_interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface FourDirectionalConnectedModelCapability {

    //---------- PROPERTIES NECESSARY FOR INTERFACE ----------
    BooleanProperty NORTH = BlockStateProperties.NORTH;
    BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    BooleanProperty WEST = BlockStateProperties.WEST;
    BooleanProperty EAST = BlockStateProperties.EAST;
   BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED; //Waterlogging is true or false
    //---------- END OF PROPERTIES  ----------

    //---------- METHODS FOR CONNECTED FURNITURE BLOCKS ----------
    //Shared methods for (currently) two block classes that implement connective model capability
    static BlockState placeTheConnectableBlock(Block block, BlockPlaceContext pContext, TagKey<Block> BlockSetFamily) {
        Level level = pContext.getLevel();
        BlockState state = block.defaultBlockState();
        BlockPos positionClicked = pContext.getClickedPos(); //Get the position when player places new block
        FluidState fluidstate = level.getFluidState(pContext.getClickedPos());

        return state
            .setValue(NORTH, hasConnectableBlock(level.getBlockState(pContext.getClickedPos().north()), block, BlockSetFamily))
            .setValue(SOUTH, hasConnectableBlock(level.getBlockState(pContext.getClickedPos().south()), block, BlockSetFamily))
            .setValue(EAST, hasConnectableBlock(level.getBlockState(pContext.getClickedPos().east()), block, BlockSetFamily))
            .setValue(WEST, hasConnectableBlock(level.getBlockState(pContext.getClickedPos().west()), block, BlockSetFamily));
    }

    static void whenConnectedNeighborUpdated(BlockState state, Level level, BlockPos positionClicked, Block block, BlockPos fromPos, TagKey<Block> BlockSetFamily) {
        if (!level.isClientSide) {
            if (state.getValue(WATERLOGGED)) {
                level.scheduleTick(positionClicked, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }

            BlockState newState = state
                    .setValue(NORTH,  hasConnectableBlockFromState(level.getBlockState(positionClicked.north()), state, BlockSetFamily))
                    .setValue(SOUTH,  hasConnectableBlockFromState(level.getBlockState(positionClicked.south()), state, BlockSetFamily))
                    .setValue(EAST,  hasConnectableBlockFromState(level.getBlockState(positionClicked.east()), state, BlockSetFamily))
                    .setValue(WEST,  hasConnectableBlockFromState(level.getBlockState(positionClicked.west()), state, BlockSetFamily));

            if (!newState.equals(state)) {
                level.setBlock(positionClicked, newState, 3);
            }
        }
        if (level.isClientSide) return;
    }

    static VoxelShape makeConnectedHitboxes(BlockState pState,
                                            VoxelShape SHAPE_NOCONNECTION,
                                            VoxelShape SHAPE_NORTH, VoxelShape SHAPE_SOUTH,VoxelShape SHAPE_EAST,VoxelShape SHAPE_WEST,
                                            VoxelShape SHAPE_NORTHSOUTH,VoxelShape SHAPE_EASTWEST,
                                            VoxelShape SHAPE_NORTHEAST,VoxelShape SHAPE_EASTSOUTH,VoxelShape SHAPE_SOUTHWEST,VoxelShape SHAPE_WESTNORTH,
                                            VoxelShape SHAPE_ALLBUTNORTH,VoxelShape SHAPE_ALLBUTSOUTH,VoxelShape SHAPE_ALLBUTEAST,VoxelShape SHAPE_ALLBUTWEST,
                                            VoxelShape SHAPE_ALLCONNECTION) {

        //AI wrote this part. think its kinda clever way to deal with this
        int key = (pState.getValue(NORTH) ? 8 : 0) | (pState.getValue(SOUTH) ? 4 : 0) | (pState.getValue(EAST) ? 2 : 0) | (pState.getValue(WEST) ? 1 : 0);

        return switch (key) {
            case 0b0001 -> SHAPE_WEST;    //  W
            case 0b0010 -> SHAPE_EAST;    //  E
            case 0b0011 -> SHAPE_EASTWEST;   //  EW
            case 0b0100 -> SHAPE_SOUTH;    //  S
            case 0b0101 -> SHAPE_SOUTHWEST;   //  SW
            case 0b0110 -> SHAPE_EASTSOUTH;   //  SE
            case 0b0111 -> SHAPE_ALLBUTNORTH;  //  SEW
            case 0b1000 -> SHAPE_NORTH;    //  N
            case 0b1001 -> SHAPE_WESTNORTH;   //  NW
            case 0b1010 -> SHAPE_NORTHEAST;   //  NE
            case 0b1011 -> SHAPE_ALLBUTSOUTH;  //  NEW
            case 0b1100 -> SHAPE_NORTHSOUTH;   //  NS
            case 0b1101 -> SHAPE_ALLBUTEAST;  //  NSW
            case 0b1110 -> SHAPE_ALLBUTWEST;  //  NSE
            case 0b1111 -> SHAPE_ALLCONNECTION; //  NSEW
            default     -> SHAPE_NOCONNECTION; //  0000, no connections
        };
    }

    //---------- METHODS FOR CONNECTED FURNITURE BLOCKS ----------
    //The following methods find the block-state adjacent to the position clicked (this should correspond with a block being placed next to similar neighbors).
    //The block-state of the neighboring block can also be used to identify what block that is too.

    static Boolean hasConnectableBlock(BlockState TargetBlock, Block self, TagKey<Block> ofBlockSetFamilyTag){
        return TargetBlock.is(self) || TargetBlock.is(ofBlockSetFamilyTag);
    }

    static Boolean hasConnectableBlockFromState(BlockState TargetBlock, BlockState self, TagKey<Block> ofBlockSetFamilyTag){
        return TargetBlock.is(self.getBlock()) || TargetBlock.is(ofBlockSetFamilyTag);
    }
    //----------
}
