package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.mrwooly.medievalstuff.item.ModItems;

public class WeightlessDaggerTier2tem extends DaggerItem {
    private final RegistryEntry<StatusEffect> effect;

    public WeightlessDaggerTier2tem(ToolMaterial toolMaterial, Settings settings, RegistryEntry<StatusEffect> effect) {
        super(toolMaterial, settings);
        this.effect = effect;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(effect, 75, 1), attacker);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 150, 0, true , true));
            user.getStackInHand(Hand.MAIN_HAND).damage(1, ((ServerWorld) world), ((ServerPlayerEntity) user),
                    item -> user.sendEquipmentBreakStatus(ModItems.WEIGHTLESS_DAGGER, EquipmentSlot.MAINHAND));
            user.damage(user.getDamageSources().magic(), 1);
        }
        user.getItemCooldownManager().set(this, 300);
        return super.use(world, user, hand);
    }
}
