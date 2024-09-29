package net.mrwooly.medievalstuff;

import net.fabricmc.api.ModInitializer;

import net.mrwooly.medievalstuff.item.ModItemGroups;
import net.mrwooly.medievalstuff.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedievalStuff implements ModInitializer {
	public static final String MOD_ID = "medievalstuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
	}
}