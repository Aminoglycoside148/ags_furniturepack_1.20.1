package com.aminoglycoside.ags_furniturepack.block.modded_block_classes;

import com.aminoglycoside.ags_furniturepack.block.modded_interfaces.FourDirectionalConnectedModelCapability;
import net.boat.industrialhellscape.block.modded_block_state_properties.DynamicConnectionState;
import net.boat.industrialhellscape.block.modded_interfaces.ConnectedModelCapability;
import net.boat.industrialhellscape.block.modded_interfaces.RotationHelper;
import net.boat.industrialhellscape.block.modded_interfaces.ToolUseCapability;
import net.boat.industrialhellscape.block.modded_logic_enums.MultiBlockPlacementDirection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class FourDirectionalConnectedBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST  = BlockStateProperties.EAST;
    public static final BooleanProperty WEST  = BlockStateProperties.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public TagKey<Block> BlockSetFamily; //To determine other blocks aside from its own can this block connect to

    private final VoxelShape SHAPE_NOCONNECTION;

    private final VoxelShape SHAPE_NORTH;
    private final VoxelShape SHAPE_SOUTH;
    private final VoxelShape SHAPE_EAST;
    private final VoxelShape SHAPE_WEST;

    private final VoxelShape SHAPE_NORTHSOUTH;
    private final VoxelShape SHAPE_EASTWEST;

    private final VoxelShape SHAPE_NORTHEAST;
    private final VoxelShape SHAPE_EASTSOUTH;
    private final VoxelShape SHAPE_SOUTHWEST;
    private final VoxelShape SHAPE_WESTNORTH;

    private final VoxelShape SHAPE_ALLBUTNORTH;
    private final VoxelShape SHAPE_ALLBUTSOUTH;
    private final VoxelShape SHAPE_ALLBUTEAST;
    private final VoxelShape SHAPE_ALLBUTWEST;

    private final VoxelShape SHAPE_ALLCONNECTION;

    public FourDirectionalConnectedBlock(Properties pProperties, TagKey<Block> inputCompatibleBlockSet, VoxelShape soloShape, VoxelShape oneConnectionShape, VoxelShape lineConnectionShape, VoxelShape cornerConnectionShape, VoxelShape threeConnectionShape, VoxelShape centerShape) {
        //When registering this block, pass in:
        // Properties,
        // Block Tag for related blocks that this block can physically connect to

        super(pProperties);

        //Define the Voxelshape hitboxes for each state

        SHAPE_NOCONNECTION = soloShape;

        SHAPE_NORTH = oneConnectionShape;
        SHAPE_SOUTH = RotationHelper.rotateVoxelCardinal(Direction.SOUTH, oneConnectionShape);
        SHAPE_EAST = RotationHelper.rotateVoxelCardinal(Direction.EAST, oneConnectionShape);
        SHAPE_WEST = RotationHelper.rotateVoxelCardinal(Direction.WEST, oneConnectionShape);

        SHAPE_NORTHSOUTH = lineConnectionShape;
        SHAPE_EASTWEST = RotationHelper.rotateVoxelCardinal(Direction.EAST, lineConnectionShape);

        SHAPE_NORTHEAST = cornerConnectionShape;
        SHAPE_EASTSOUTH = RotationHelper.rotateVoxelCardinal(Direction.EAST, cornerConnectionShape);
        SHAPE_SOUTHWEST = RotationHelper.rotateVoxelCardinal(Direction.SOUTH, cornerConnectionShape);
        SHAPE_WESTNORTH = RotationHelper.rotateVoxelCardinal(Direction.WEST, cornerConnectionShape);

        SHAPE_ALLBUTNORTH = threeConnectionShape;
        SHAPE_ALLBUTSOUTH = RotationHelper.rotateVoxelCardinal(Direction.SOUTH, threeConnectionShape);
        SHAPE_ALLBUTEAST = RotationHelper.rotateVoxelCardinal(Direction.EAST, threeConnectionShape);
        SHAPE_ALLBUTWEST = RotationHelper.rotateVoxelCardinal(Direction.WEST, threeConnectionShape);

        SHAPE_ALLCONNECTION = centerShape;

        //To determine other blocks aside from its own can this block connect to
        this.BlockSetFamily = inputCompatibleBlockSet;


        //Default state is Solo/unconnected, facing North, no waterlogging
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(WATERLOGGED, false)
        );
    }

    //---------- USE INTERACT, HITBOXES, PLACEMENT, AND BLOCK UPDATE METHODS HANDLED BY INTERFACES ---------


    @Override
    public @Nonnull VoxelShape getShape(BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        //See this mod's ConnectedModelCapability interface to view the following method.
        return FourDirectionalConnectedModelCapability.makeConnectedHitboxes(
                pState,
                SHAPE_NOCONNECTION,
                SHAPE_NORTH, SHAPE_SOUTH,SHAPE_EAST,SHAPE_WEST,
                SHAPE_NORTHSOUTH,SHAPE_EASTWEST,
                SHAPE_NORTHEAST,SHAPE_EASTSOUTH,SHAPE_SOUTHWEST,SHAPE_WESTNORTH
                ,SHAPE_ALLBUTNORTH,SHAPE_ALLBUTSOUTH,SHAPE_ALLBUTEAST,SHAPE_ALLBUTWEST,
                SHAPE_ALLCONNECTION
                );
    }
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        //See this mod's ConnectedModelCapability interface to view the following method.
        return FourDirectionalConnectedModelCapability.placeTheConnectableBlock(this, pContext, BlockSetFamily);
    }
    public void neighborChanged(@Nonnull BlockState state, Level level, @Nonnull BlockPos positionClicked, @Nonnull Block block, @Nonnull BlockPos fromPos, boolean isMoving) {
        //See this mod's ConnectedModelCapability interface to view the following method.
        FourDirectionalConnectedModelCapability.whenConnectedNeighborUpdated(state,level,positionClicked,block,fromPos,BlockSetFamily);
    }
    //---------- END OF METHODS HANDLED BY INTERFACE ----------

    @Override
    public @Nonnull RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, SOUTH, EAST, WEST, WATERLOGGED); //Block's blockstates; its NSEW orientation, its connection type defined
    }
}
