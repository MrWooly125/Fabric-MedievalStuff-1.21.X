package net.mrwooly.medievalstuff.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PIECE_OF_JELLY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.10f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30), 0.50f).snack().build();
    public static final FoodComponent JAR_OF_JELLY = new FoodComponent.Builder().nutrition(8).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 150), 0.80f).build();
}
