package phoupraw.mcmod.createsdelight.block;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.foundation.utility.BlockHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import phoupraw.mcmod.createsdelight.registry.MyStatusEffects;

import java.util.List;
public class JellyBeansBlock extends Block {
    public static final VoxelShape SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 1.0, 12.0);

    public JellyBeansBlock() {
        this(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE).sounds(BlockSoundGroup.WOOL));
    }

    public JellyBeansBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        JellyBeansCakeBlock.eat(world, pos, state, player, 1, 0.5f, List.of(Pair.of(new StatusEffectInstance(MyStatusEffects.SATIATION, 1, 0), 1f)));
        world.removeBlock(pos, false);
        return ActionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return BlockHelper.hasBlockSolidSide(world.getBlockState(pos.down()), world, pos.down(), Direction.UP);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
