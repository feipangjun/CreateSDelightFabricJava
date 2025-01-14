package phoupraw.mcmod.createsdelight.registry;

import com.simibubi.create.AllBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import phoupraw.mcmod.createsdelight.block.MadeVoxelBlock;
import phoupraw.mcmod.createsdelight.block.ThickFluidBlock;
import phoupraw.mcmod.createsdelight.block.VoxelMakerBlock;
import phoupraw.mcmod.createsdelight.client.CSDClientModInitializer;
import phoupraw.mcmod.createsdelight.datagen.CSDBlockLootTableProvider;
import phoupraw.mcmod.createsdelight.datagen.CSDBlockTagProvider;
import phoupraw.mcmod.createsdelight.datagen.client.CSDChineseProvider;
import phoupraw.mcmod.createsdelight.datagen.client.CSDEnglishProvider;
import phoupraw.mcmod.createsdelight.datagen.client.CSDModelProvider;

/**
 方块编写流程：
 <ol>
 <li>若自定义方块，则在{@link phoupraw.mcmod.createsdelight.block}创建方块类，继承{@link Block}；推荐重载无参构造器。<br/>
 <li>在{@link CSDIdentifiers}创建{@link Identifier}。<br/>
 <li>在{@link CSDBlocks}创建方块<b>并注册</b>。<br/>
 <li>在{@link CSDItems}按照创建物品的流程创建{@link BlockItem}或自定义物品<b>并注册</b>。<br/>
 <li>在{@link CSDChineseProvider}和{@link CSDEnglishProvider}添加翻译。<br/>
 <li>在{@link CSDModelProvider}添加方块状态和物品模型。<br/>
 <li>在{@link CSDBlockLootTableProvider}添加战利品表。<br/>
 <li>在{@link CSDBlockTagProvider}添加标签。<br/>
 <li>运行数据生成器。<br/>
 <li>在{@code src/main/resources/assets/createsdelight/models/block}创建方块模型。<br/>
 <li>若贴图包含透明像素，需在{@link CSDClientModInitializer}添加{@link BlockRenderLayerMap}。<br/>
 <li>运行客户端，检查方块效果是否如预期。<br/>
 <li>在{@code ChangeLog.md}添加更新日志。<br/>
 <li>提交git。
 </ol>
 @see CSDBlockEntityTypes */
public final class CSDBlocks {
    public static final Block VOXEL_MAKER = register(CSDIdentifiers.VOXEL_MAKER, new VoxelMakerBlock(FabricBlockSettings.copyOf(AllBlocks.BRASS_CASING.get())));
    public static final Block MADE_VOXEL = register(CSDIdentifiers.MADE_VOXEL, new MadeVoxelBlock(FabricBlockSettings.create()
      .solid()
      .nonOpaque()
      .dynamicBounds()
      .breakInstantly()
      .sounds(BlockSoundGroup.WOOL)
      .mapColor(MapColor.SNOW)));
    //细雪
    public static final Block CREAM = new ThickFluidBlock(FabricBlockSettings.copyOf(Blocks.POWDER_SNOW).breakInstantly());
    public static final Block APPLE_JAM = register(CSDIdentifiers.APPLE_JAM, new ThickFluidBlock(FabricBlockSettings.copyOf(CREAM).mapColor(MapColor.RED)));
    public static final Block WHEAT_PASTE = register(CSDIdentifiers.WHEAT_PASTE, new ThickFluidBlock(FabricBlockSettings.copyOf(CREAM).mapColor(MapColor.SAND)));
    //固体
    public static final Block CHOCOLATE_BLOCK = new Block(FabricBlockSettings.create().hardness(1).mapColor(MapColor.BROWN));
    public static final Block WHEAT_CAKE_BASE_BLOCK = register(CSDIdentifiers.WHEAT_CAKE_BASE_BLOCK, new HayBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).mapColor(DyeColor.BROWN)));
    public static final Block BUTTER_BLOCK = register(CSDIdentifiers.BUTTER_BLOCK, new Block(FabricBlockSettings.create().hardness(1).mapColor(MapColor.YELLOW).sounds(BlockSoundGroup.CANDLE)));
    //TODO 糖块、苹果酱块、胡萝卜酱块、土豆泥块、甜浆果酱块、发光浆果块、甜菜汁块、
    static {
        //register(CSDIdentifiers.CAKE_OVEN, CAKE_OVEN);
        //register(CSDIdentifiers.PRINTED_CAKE, PRINTED_CAKE);
        register(CSDIdentifiers.CHOCOLATE_BLOCK, CHOCOLATE_BLOCK);
        register(CSDIdentifiers.CREAM, CREAM);
    }
    public static <T extends Block> T register(Identifier id, T block) {
        return Registry.register(Registries.BLOCK, id, block);
    }
    private CSDBlocks() {
    }
}
