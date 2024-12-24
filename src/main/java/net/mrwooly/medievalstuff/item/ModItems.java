package net.mrwooly.medievalstuff.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.mrwooly.medievalstuff.MedievalStuff;
import net.mrwooly.medievalstuff.entity.ModEntities;
import net.mrwooly.medievalstuff.item.custom.*;

import java.util.List;

public class ModItems {
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new Item.Settings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));
    public static final Item JAR = registerItem("jar", new Item(new Item.Settings().maxCount(8)));

    public static final Item PIECE_OF_JELLY = registerItem("piece_of_jelly", new Item(new Item.Settings().food(ModFoodComponents.PIECE_OF_JELLY)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.medievalstuff.piece_of_jelly.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item JAR_OF_JELLY = registerItem("jar_of_jelly",
            new JarOfJellyItem(new Item.Settings().food(ModFoodComponents.JAR_OF_JELLY).recipeRemainder(ModItems.JAR).maxCount(8)));



    public static final Item SILVER_DAGGER = registerItem("silver_dagger",
            new CustomSweepWeaponItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(CustomSweepWeaponItem.
                            createAttributeModifiersComponent(
                                    ModToolMaterials.SILVER,
                                    1F,
                                    -0.75F,
                                    -2F,
                                    -0.75F))));

    public static final Item WEIGHTLESS_DAGGER = registerItem("weightless_dagger",
            new WeightlessDaggerItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(WeightlessDaggerItem.
                            createAttributeModifiersComponent(
                                    ModToolMaterials.SILVER,
                                    1F,
                                    -0.75F,
                                    -2F,
                                    -0.75F)), StatusEffects.LEVITATION));

    public static final Item WEIGHTLESS_DAGGER_TIER_2 = registerItem("weightless_dagger_tier_2",
            new WeightlessDaggerTier2Item(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(WeightlessDaggerTier2Item.
                            createAttributeModifiersComponent(
                                    ModToolMaterials.SILVER,
                                    1F,
                                    -0.75F,
                                    -1.9F,
                                    -0.75F)), StatusEffects.LEVITATION));



    public static final Item SILVER_SWORD = registerItem("silver_sword",
            new SwordItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SILVER, 3, -2.3f))));
    public static final Item SILVER_PICKAXE = registerItem("silver_pickaxe",
            new PickaxeItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SILVER, 1f, -2.7f))));
    public static final Item SILVER_AXE = registerItem("silver_axe",
            new AxeItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.SILVER, 6f, -3f))));
    public static final Item SILVER_SHOVEL = registerItem("silver_shovel",
            new ShovelItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.SILVER, 1.5f, -2.9f))));
    public static final Item SILVER_HOE = registerItem("silver_hoe",
            new HoeItem(ModToolMaterials.SILVER,
                    new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SILVER, -2.0f, -0.9f))));


    public static final Item SILVER_HELMET = registerItem("silver_helmet",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(16))));

    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(16))));

    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(16))));

    public static final Item SILVER_BOOTS = registerItem("silver_boots",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(16))));

    public static final Item CHARGED_LIFE_GIVING_TOTEM = registerItem("charged_life_giving_totem", new LifeGivingTotemItem(new Item.Settings()
            .maxCount(1).maxDamage(16).rarity(Rarity.UNCOMMON)));

    public static final Item DISCHARGED_LIFE_GIVING_TOTEM = registerItem("discharged_life_giving_totem", new Item(new Item.Settings()
            .maxCount(1)));

    public static final Item WIND_STAFF = registerItem("wind_staff", new WindStaffItem(
            new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .maxDamage(256)
                    .attributeModifiers(WindStaffItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, WindStaffItem.createToolComponent())));

    public static final Item STORM_STAFF = registerItem("storm_staff", new StormStaffItem(
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .maxDamage(512)
                    .attributeModifiers(StormStaffItem.createAttributeModifiersComponent())
                    .component(DataComponentTypes.TOOL, StormStaffItem.createToolComponent())));

    public static final Item SPEAR_OF_WIND = registerItem("spear_of_wind",
            new SpearOfWindItem(ModToolMaterials.WIND,
                    new Item.Settings().rarity(Rarity.UNCOMMON).attributeModifiers(SpearOfWindItem.createAttributeModifiersComponent(ModToolMaterials.SILVER))));


    public static final Item ENDERIUM_ALLOY = registerItem("enderium_alloy", new Item(new Item.Settings().rarity(Rarity.EPIC)));

    public static final Item ENDERIUM_BLADE = registerItem("enderium_blade",
            new EnderiumBladeItem(ModToolMaterials.ENDERIUM, new Item.Settings().rarity(Rarity.RARE)
                    .attributeModifiers(EnderiumBladeItem.createAttributeModifiersComponent(
                            ModToolMaterials.ENDERIUM,
                            2.0F,
                            -0.5F,
                            -2.2F,
                            -0.5F))));

    public static final Item RITUAL_DAGGER = registerItem("ritual_dagger", new RitualDaggerItem(ModToolMaterials.RITUAL_DAGGER,
            new Item.Settings().rarity(Rarity.UNCOMMON).attributeModifiers(
                    RitualDaggerItem.createAttributeModifiersComponent(
                            ModToolMaterials.RITUAL_DAGGER,
                            1.0F,
                            -1.0F,
                            -2.1F,
                            -1.0F
                    ))));

    public static final Item MAGIC_CANE = registerItem("magic_cane", new MagicCaneItem(new Item.Settings().maxDamage(128)));


   public static final Item JELLY_SPAWN_EGG = registerItem("jelly_spawn_egg",
           new SpawnEggItem(ModEntities.JELLY, 0x465ae0, 0x545978, new Item.Settings()));


   public static Item registerItem(String name, Item item) {
       return Registry.register(Registries.ITEM, Identifier.of(MedievalStuff.MOD_ID, name), item);
   }

   public static void registerModItems() {
       MedievalStuff.LOGGER.info("Registering Mod Items for " + MedievalStuff.MOD_ID);
   }
}
