package net.mrwooly.medievalstuff.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.block.ModBlocks;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUMISHROOM = registerKey("lumishroom");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<Block> registryEntryLookup = context.getRegistryLookup(RegistryKeys.BLOCK);
        BlockPredicate blockPredicate = BlockPredicate.matchingBlocks(
          ModBlocks.LUMISHROOM
        );

        register(context, LUMISHROOM,
                Feature.HUGE_FUNGUS,
                new HugeFungusFeatureConfig(
                Blocks.GRASS_BLOCK.getDefaultState(),
                ModBlocks.LUMISHROOM_LOG.getDefaultState(),
                ModBlocks.LUMISHROOM_CAP.getDefaultState(),
                Blocks.SHROOMLIGHT.getDefaultState(),
                blockPredicate,
                        false
                )
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MedievalStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
