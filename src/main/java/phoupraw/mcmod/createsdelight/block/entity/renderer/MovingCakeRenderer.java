package phoupraw.mcmod.createsdelight.block.entity.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import phoupraw.mcmod.createsdelight.block.entity.MovingCakeBlockEntity;

public class MovingCakeRenderer implements BlockEntityRenderer<MovingCakeBlockEntity> {

    public MovingCakeRenderer(BlockEntityRendererFactory.Context context) {}

    @Override
    public void render(MovingCakeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }

}
