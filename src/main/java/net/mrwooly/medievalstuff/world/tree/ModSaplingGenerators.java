package net.mrwooly.medievalstuff.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator LUMISHROOM = new SaplingGenerator(MedievalStuff.MOD_ID + ":lumishroom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LUMISHROOM), Optional.empty());
}
