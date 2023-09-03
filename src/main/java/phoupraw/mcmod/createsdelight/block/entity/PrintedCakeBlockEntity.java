package phoupraw.mcmod.createsdelight.block.entity;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.createsdelight.cake.CakeIngredient;
import phoupraw.mcmod.createsdelight.cake.VoxelCake;
import phoupraw.mcmod.createsdelight.registry.CDRegistries;
import phoupraw.mcmod.createsdelight.registry.CSDBlockEntityTypes;

import java.util.*;

public class PrintedCakeBlockEntity extends SmartBlockEntity implements Nameable {

    public static final Comparator<BlockBox> BLOCK_BOX_COMPARATOR = Comparator
      .comparingInt(BlockBox::getMinY)
      .reversed()
      .thenComparingInt(BlockBox::getMinX)
      .thenComparingInt(BlockBox::getMinZ);

    public static @Nullable VoxelCake nbt2content(NbtCompound blockEntityTag) {
        if (blockEntityTag.contains("predefined", NbtElement.STRING_TYPE)) {
            Identifier id = new Identifier(blockEntityTag.getString("predefined"));
            return CDRegistries.PREDEFINED_CAKE.get(id);
        }
        return VoxelCake.of(blockEntityTag.getCompound("voxelCake"));
    }

    public @Nullable Identifier predefined;
    private @Nullable VoxelCake voxelCake;
    private @Nullable Text customName;
    public final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);

    public PrintedCakeBlockEntity(BlockPos pos, BlockState state) {
        this(CSDBlockEntityTypes.PRINTED_CAKE, pos, state);
    }

    public PrintedCakeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Text getName() {
        return Objects.requireNonNullElse(getCustomName(), getCachedState().getBlock().getName());
    }

    @Nullable
    @Override
    public Text getCustomName() {
        return customName;
    }

    public void setCustomName(@Nullable Text customName) {
        this.customName = customName;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {

    }

    @Override
    protected void write(NbtCompound tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        Identifier predefined = this.predefined;
        if (predefined != null) {
            tag.putString("predefined", predefined.toString());
        } else if (getVoxelCake() != null) {
            tag.put("voxelCake", getVoxelCake().toNbt());
        }
        if (getCustomName() != null) {
            tag.putString("CustomName", Text.Serializer.toJson(getCustomName()));
        }
    }

    @Override
    protected void read(NbtCompound tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        if (tag.contains("CustomName", NbtElement.STRING_TYPE)) {
            setCustomName(Text.Serializer.fromJson(tag.getString("CustomName")));
        } else {
            setCustomName(null);
        }
        setVoxelCake(nbt2content(tag));
        shapes.clear();
        World world1 = getWorld();
        if (world1 == null) return;
        world1.updateListeners(getPos(), getCachedState(), getCachedState(), Block.REDRAW_ON_MAIN_THREAD);
    }

    public @Nullable VoxelCake getVoxelCake() {
        return voxelCake;
    }

    public void setVoxelCake(@Nullable VoxelCake voxelCake) {
        this.voxelCake = voxelCake;
        if (voxelCake == null) return;
        for (CakeIngredient key : voxelCake.getContent().keySet()) {
            if (voxelCake.getContent().get(key) instanceof List<BlockBox> list) {
                list.sort(BLOCK_BOX_COMPARATOR);
            }
        }
        sendData();
    }

}
