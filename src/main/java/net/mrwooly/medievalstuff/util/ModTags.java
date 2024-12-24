package net.mrwooly.medievalstuff.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_SILVER_TOOL = createTag("needs_silver_tool");
        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = createTag("incorrect_for_silver_tool");

        public static final TagKey<Block> NEEDS_RITUAL_DAGGER_TOOL = createTag("needs_ritual_dagger_tool");
        public static final TagKey<Block> INCORRECT_FOR_RITUAL_DAGGER_TOOL = createTag("incorrect_for_ritual_dagger_tool");

        public static final TagKey<Block> NEEDS_WIND_TOOL = createTag("needs_wind_tool");
        public static final TagKey<Block> INCORRECT_FOR_WIND_TOOL = createTag("incorrect_for_wind_tool");

        public static final TagKey<Block> NEEDS_ENDERIUM_TOOL = createTag("needs_enderium_tool");
        public static final TagKey<Block> INCORRECT_FOR_ENDERIUM_TOOL = createTag("incorrect_for_enderium_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }
}
