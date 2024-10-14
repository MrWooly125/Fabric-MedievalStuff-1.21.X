package net.mrwooly.medievalstuff;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.mrwooly.medievalstuff.block.ModBlocks;
import net.mrwooly.medievalstuff.entity.ModEntities;
import net.mrwooly.medievalstuff.entity.custom.JellyEntity;
import net.mrwooly.medievalstuff.item.ModItemGroups;
import net.mrwooly.medievalstuff.item.ModItems;
import net.mrwooly.medievalstuff.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedievalStuff implements ModInitializer {
	public static final String MOD_ID = "medievalstuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModEntities.registerModEntities();

		ModWorldGeneration.generateModWorldGeneration();

		registerStrippables();
		registerFlammables();

		FabricDefaultAttributeRegistry.register(ModEntities.JELLY, JellyEntity.createJellyAttributes());
	}

	private static void registerStrippables() {
		StrippableBlockRegistry.register(ModBlocks.LUMISHROOM_LOG, ModBlocks.STRIPPED_LUMISHROOM_LOG);
		StrippableBlockRegistry.register(ModBlocks.LUMISHROOM_WOOD, ModBlocks.STRIPPED_LUMISHROOM_WOOD);
	}

	private static void registerFlammables() {
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_LUMISHROOM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_LUMISHROOM_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_PLANKS, 10, 7);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_CAP, 15, 10);
	}
}