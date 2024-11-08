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

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MAGIC_GEMS = createTag("magic_gems");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }
}
