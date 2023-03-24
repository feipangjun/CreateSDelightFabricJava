package phoupraw.mcmod.createsdelight.api;

import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import com.simibubi.create.content.contraptions.components.saw.CuttingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.tag.TagKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;
public final class LambdasC {

    @Contract(pure = true)
    public static @NotNull Predicate<FluidVariant> matching(FluidIngredient predicate) {
        return v -> predicate.test(new FluidStack(v, Long.MAX_VALUE));
    }

    /**
     如果配方是{@link CuttingRecipe}或{@link CuttingBoardRecipe}，则匹配工具，否则返回{@code false}
     @param tool 工具物品，例如小刀、斧头等。
     */
    @Contract(pure = true)
    public static @NotNull Predicate<Recipe<?>> matchingTool(ItemStack tool) {
        return recipe -> {
            if (recipe instanceof CuttingRecipe cutting) return cutting.getIngredients().get(1).test(tool);
            if (recipe instanceof CuttingBoardRecipe cutting) return cutting.getTool().test(tool);
            return false;
        };
    }

    @Contract(pure = true)
    public static <T extends ProcessingRecipe<?>> @NotNull UnaryOperator<ProcessingRecipeBuilder<T>> addingStep(Ingredient ingredient) {
        return r -> r.require(ingredient);
    }

    @Contract(pure = true)
    public static <T extends ProcessingRecipe<?>> @NotNull UnaryOperator<ProcessingRecipeBuilder<T>> addingStep(ItemConvertible item) {
        return r -> r.require(item);
    }

    @Contract(pure = true)
    public static <T extends ProcessingRecipe<?>> @NotNull UnaryOperator<ProcessingRecipeBuilder<T>> addingStep(TagKey<Item> tag) {
        return r -> r.require(tag);
    }

    @Contract(pure = true)
    public static <T extends ProcessingRecipe<?>> @NotNull UnaryOperator<ProcessingRecipeBuilder<T>> addingStep(FluidIngredient fluidIngredient) {
        return r -> r.require(fluidIngredient);
    }

    @Contract(pure = true)
    public static <T extends ProcessingRecipe<?>> @NotNull UnaryOperator<ProcessingRecipeBuilder<T>> addingStep(Fluid fluid, long amount) {
        return r -> r.require(fluid, amount);
    }

    private LambdasC() {}
}