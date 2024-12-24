package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.List;

public class CustomSweepWeaponItem extends SwordItem {
    public CustomSweepWeaponItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    private static ToolComponent createToolComponent() {
        return new ToolComponent(
                List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5f)), 1.0F, 2
        );
    }

    public static AttributeModifiersComponent createAttributeModifiersComponent(ToolMaterial material, float baseAttackDamage, float baseAttackDistance, float baseAttackSpeed, float baseMiningDistance) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(
                                BASE_ATTACK_DISTANCE_MODIFIER_FOR_CUSTOM_SWEEP_WEAPON_ITEM, baseAttackDistance, EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID, baseAttackSpeed, EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(
                                BASE_MINING_DISTANCE_MODIFIER_FOR_CUSTOM_SWEEP_WEAPON_ITEM, baseMiningDistance, EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                ).build();
    }

    public static final Identifier BASE_ATTACK_DISTANCE_MODIFIER_FOR_CUSTOM_SWEEP_WEAPON_ITEM = Identifier.ofVanilla("base_attack_damage_modifier");
    public static final Identifier BASE_MINING_DISTANCE_MODIFIER_FOR_CUSTOM_SWEEP_WEAPON_ITEM = Identifier.ofVanilla("base_attack_mining_modifier");
}
