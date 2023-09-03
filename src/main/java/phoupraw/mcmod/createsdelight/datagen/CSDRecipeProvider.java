package phoupraw.mcmod.createsdelight.datagen;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.EmptyingRecipe;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import io.github.tropheusj.milk.Milk;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import phoupraw.mcmod.createsdelight.registry.CSDFluids;
import phoupraw.mcmod.createsdelight.registry.CSDIdentifiers;
import phoupraw.mcmod.createsdelight.registry.CSDItems;

import java.util.function.Consumer;

public final class CSDRecipeProvider extends FabricRecipeProvider {


    public CSDRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerStonecuttingRecipe(exporter, RecipeCategory.FOOD, AllItems.BAR_OF_CHOCOLATE.get(), CSDItems.CHOCOLATE_BLOCK, 3);
        new ShapedRecipeJsonBuilder(RecipeCategory.FOOD, CSDItems.CHOCOLATE_BLOCK, 3)
          .input('A', AllItems.BAR_OF_CHOCOLATE.get())
          .pattern("AAA")
          .pattern("AAA")
          .pattern("AAA")
          .criterion("stupidMojang", conditionsFromItem(AllItems.BAR_OF_CHOCOLATE.get()))
          .offerTo(exporter, CSDIdentifiers.CHOCOLATE_BLOCK.withPrefixedPath("crafting/"));
        new ProcessingRecipeBuilder<>(EmptyingRecipe::new, CSDIdentifiers.EGG_LIQUID)
          .require(Items.EGG)
          .output(CSDItems.EGG_SHELL)
          .output(CSDFluids.EGG_LIQUID, FluidConstants.BOTTLE / 2)
          .build(exporter);
        new ProcessingRecipeBuilder<>(CompactingRecipe::new, CSDIdentifiers.CREAM_BLOCK)
          .require(Milk.STILL_MILK, FluidConstants.BLOCK)
          .output(CSDItems.CREAM_BLOCK)
          .build(exporter);
    }


}
