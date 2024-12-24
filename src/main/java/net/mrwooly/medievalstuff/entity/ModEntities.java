package net.mrwooly.medievalstuff.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.entity.custom.JellyEntity;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier1ProjectileEntity;
import net.mrwooly.medievalstuff.entity.custom.MagicSoulFireChargeTier3ProjectileEntity;

public class ModEntities {
    public static final EntityType<JellyEntity> JELLY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "jelly"),
            EntityType.Builder.create(JellyEntity::new, SpawnGroup.CREATURE).dimensions(0.8F, 1F).build());

    public static final EntityType<MagicSoulFireChargeTier1ProjectileEntity> MAGIC_SOUL_FIRE_CHARGE_TIER_1 = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "magic_soul_fire_charge_tier_1"),
            EntityType.Builder.<MagicSoulFireChargeTier1ProjectileEntity>create
                    (MagicSoulFireChargeTier1ProjectileEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).build());

    public static final EntityType<MagicSoulFireChargeTier3ProjectileEntity> MAGIC_SOUL_FIRE_CHARGE_TIER_3 = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "magic_soul_fire_charge_tier_3"),
            EntityType.Builder.<MagicSoulFireChargeTier3ProjectileEntity>create
                    (MagicSoulFireChargeTier3ProjectileEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).build());

    public static void registerModEntities() {
        MedievalStuff.LOGGER.info("Registering Mod Entities for " + MedievalStuff.MOD_ID);
    }
}
