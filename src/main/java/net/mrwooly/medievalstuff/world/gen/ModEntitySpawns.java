package net.mrwooly.medievalstuff.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.mrwooly.medievalstuff.entity.ModEntities;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                SpawnGroup.CREATURE, ModEntities.JELLY, 100,  2, 6);
        SpawnRestriction.register(ModEntities.JELLY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
    }
}
