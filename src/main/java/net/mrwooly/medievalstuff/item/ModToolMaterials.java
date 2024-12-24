package net.mrwooly.medievalstuff.item;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.mrwooly.medievalstuff.util.ModTags;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    SILVER(ModTags.Blocks.INCORRECT_FOR_SILVER_TOOL,
            325, 6.5F, 2.0F, 16, () -> Ingredient.ofItems(ModItems.SILVER_INGOT)),

    RITUAL_DAGGER(ModTags.Blocks.INCORRECT_FOR_RITUAL_DAGGER_TOOL,
            256, 6.0F, 2.5F, 10, () -> Ingredient.ofItems(ModItems.SILVER_INGOT)),

    WIND(ModTags.Blocks.INCORRECT_FOR_WIND_TOOL,
            512, 6.5F, 2.0F, 15, () -> Ingredient.ofItems(Items.BREEZE_ROD)),

    ENDERIUM(ModTags.Blocks.INCORRECT_FOR_ENDERIUM_TOOL,
            2048, 9.5F, 3.5F, 20, () -> Ingredient.ofItems(ModItems.ENDERIUM_ALLOY));


    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(
            final TagKey<Block> inverseTag,
            final int itemDurability,
            final float miningSpeed,
            final float attackDamage,
            final int enchantability,
            final Supplier<Ingredient> repairIngredient
    ) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
