package net.mrwooly.medievalstuff.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;

public class ModEntityModelLayers {
    public static final EntityModelLayer JELLY =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "main");

    public static final EntityModelLayer MAGIC_SOUL_FIRE_CHARGE_TIER_1 =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "magic_soul_fire_charge_tier_1"), "main");

    public static final EntityModelLayer MAGIC_SOUL_FIRE_CHARGE_TIER_3 =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "magic_soul_fire_charge_tier_3"), "main");
}
