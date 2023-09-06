package phoupraw.mcmod.createsdelight.block.entity;

import com.simibubi.create.foundation.blockEntity.SyncedBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.createsdelight.cake.CakeIngredient;
import phoupraw.mcmod.createsdelight.cake.VoxelCake;
import phoupraw.mcmod.createsdelight.registry.CSDBlockEntityTypes;

public class InProdBlockEntity extends SyncedBlockEntity {

    public CakeIngredient cakeIngredient;
    public BlockPos origin;
    public @Nullable BlockPos relative;
    public int edgeLen;
    public CakeOvenBlockEntity oven;
    @Nullable protected VoxelCake voxelCake;

    public InProdBlockEntity(BlockPos pos, BlockState state) {this(CSDBlockEntityTypes.IN_PROD_CAKE, pos, state);}

    public InProdBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setVoxelCake(VoxelCake.of(nbt.getCompound("voxelCake")));
        if (nbt.contains("relative", NbtElement.COMPOUND_TYPE)) {
            relative = NbtHelper.toBlockPos(nbt.getCompound("relative"));
        } else {
            relative = null;
        }
        edgeLen = nbt.getByte("edgeLen");
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (getVoxelCake() != null) {
            nbt.put("voxelCake", getVoxelCake().toNbt());
        }
        if (relative != null) {
            nbt.put("relative", NbtHelper.fromBlockPos(relative));
        }
        nbt.putByte("edgeLen", (byte) edgeLen);
    }
    public @Nullable VoxelCake getVoxelCake() {
        return voxelCake;
    }
    public void setVoxelCake(@Nullable VoxelCake voxelCake) {
        this.voxelCake = voxelCake;
        sendData();
    }
}
