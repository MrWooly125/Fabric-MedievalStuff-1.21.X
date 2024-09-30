package net.mrwooly.medievalstuff.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly.medievalstuff.MedievalStuff;

public class ModItems {
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new Item.Settings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));

    public static final Item PIECE_OF_JELLY = registerItem("piece_of_jelly", new Item(new Item.Settings().food(ModFoodComponents.PIECE_OF_JELLY)));
    public static final Item JAR_OF_JELLY = registerItem("jar_of_jelly", new Item(new Item.Settings().food(ModFoodComponents.JAR_OF_JELLY)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MedievalStuff.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MedievalStuff.LOGGER.info("Registering Mod Items for " + MedievalStuff.MOD_ID);
    }
}
