package phoupraw.mcmod.createsdelight;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class CSDMixinConfigPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public @Nullable String getRefMapperConfig() {
        return null;
    }

    ///**
    // @see MixinBlocks
    // */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return !FabricLoader.getInstance().isModLoaded("immersive_weathering") || !mixinClassName.equals("phoupraw.mcmod.createsdelight.mixin.MixinBlocks");
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public @Nullable List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

}
