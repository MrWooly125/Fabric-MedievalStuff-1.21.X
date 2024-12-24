package net.mrwooly.medievalstuff.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;

public class ModEffects {

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MedievalStuff.MOD_ID, name), statusEffect);
    }

    public static void registerEffects()  {
        MedievalStuff.LOGGER.info("Registering Mod Effects for " + MedievalStuff.MOD_ID);
    }
}
