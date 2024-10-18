package net.mrwooly.medievalstuff.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.entity.custom.JellyEntity;

public class ModEntities {
    public static final EntityType<JellyEntity> JELLY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "jelly"),
            EntityType.Builder.create(JellyEntity::new, SpawnGroup.CREATURE).dimensions(0.8f, 1f).build());

    public static void registerModEntities() {
        MedievalStuff.LOGGER.info("Registering Mod Entities for " + MedievalStuff.MOD_ID);
    }
}
