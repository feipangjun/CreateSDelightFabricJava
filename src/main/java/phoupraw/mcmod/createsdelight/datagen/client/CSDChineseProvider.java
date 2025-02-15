package phoupraw.mcmod.createsdelight.datagen.client;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import phoupraw.mcmod.createsdelight.CreateSDelight;
import phoupraw.mcmod.createsdelight.misc.MadeVoxels;
import phoupraw.mcmod.createsdelight.registry.CSDBlocks;
import phoupraw.mcmod.createsdelight.registry.CSDIdentifiers;
import phoupraw.mcmod.createsdelight.registry.CSDItems;
import phoupraw.mcmod.createsdelight.registry.CSDStatusEffects;

public final class CSDChineseProvider extends FabricLanguageProvider {
    public CSDChineseProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }
    @Override
    public void generateTranslations(TranslationBuilder b) {
        b.add("modmenu.nameTranslation." + CreateSDelight.MOD_ID, "机械动力乐事");
        b.add("modmenu.descriptionTranslation." + CreateSDelight.MOD_ID,
          """
          添加了各式蛋糕和自定义蛋糕。
          """);
        b.add(CSDIdentifiers.ITEM_GROUP.toTranslationKey("itemGroup"), "机械动力乐事");
        b.add(CSDBlocks.VOXEL_MAKER, "体素蛋糕制作机");
        b.add(CSDBlocks.MADE_VOXEL, "体素蛋糕");
        b.add(CSDBlocks.CHOCOLATE_BLOCK, "巧克力块");
        b.add(CSDBlocks.WHEAT_CAKE_BASE_BLOCK, "全麦蛋糕胚块");
        b.add(CSDBlocks.BUTTER_BLOCK, "黄油块");
        b.add(CSDBlocks.CREAM, "奶油");
        b.add(CSDBlocks.APPLE_JAM, "苹果酱");
        b.add(CSDBlocks.WHEAT_PASTE, "全麦面糊");
        b.add(CSDIdentifiers.EGG_LIQUID.toTranslationKey("block"), "鸡蛋液");
        b.add(CSDItems.BUCKETED_EGG_LIQUID, "桶装鸡蛋液");
        b.add(CSDItems.BUCKETED_APPLE_JAM, "桶装苹果酱");
        b.add(CSDItems.BUCKETED_WHEAT_PASTE, "桶装全麦面糊");
        b.add(CSDItems.BUCKETED_CREAM, "桶装奶油");
        b.add(CSDItems.BUTTER_NUGGET, "黄油粒");
        b.add(CSDItems.BUTTER_INGOT, "黄油锭");
        b.add(CSDItems.EGG_SHELL, "鸡蛋壳");
        b.add(CSDItems.KELP_ASH, "海带灰烬");
        b.add(CSDStatusEffects.SATIATION, "饱食");
        b.add(MadeVoxels.getTranslationKey("brownie"), "巧克力饼干蛋糕");
        b.add(MadeVoxels.getTranslationKey("minecraft"), "谎言是个蛋糕");
    }
}
