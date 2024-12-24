package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.mrwooly.medievalstuff.item.ModItems;

public class LifeGivingTotemItem extends Item {
    public LifeGivingTotemItem(Settings settings) {
        super(settings);
    }
    float heal;

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 36000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int useTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
        if (useTicks == 200) {
            double userX = user.getX();
            double userY = user.getY();
            double userZ = user.getZ();
            SoundEvent soundEvent = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            SoundCategory soundCategory = SoundCategory.PLAYERS;
            world.playSound(null, userX, userY, userZ, soundEvent, soundCategory, 0.75F, 2F);
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int useTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float healthBooster = user.getMaxHealth() / 2 - user.getHealth() / 2;
        int fixedUseTicks = Math.min(useTicks, 200);

        heal = 1F + (float) fixedUseTicks / 40 + healthBooster / 3;
        user.heal(heal);


        Hand hand = user.getActiveHand();
        if (user.getStackInHand(hand).isOf(ModItems.CHARGED_LIFE_GIVING_TOTEM)) {
            if (user.getStackInHand(hand).getDamage() == 15) {
                ItemStack itemStack = this.getDefaultStack();
                ItemStack newItemStack = itemStack.copyComponentsToNewStack(ModItems.DISCHARGED_LIFE_GIVING_TOTEM, 1);
                if (user instanceof PlayerEntity player) {
                    if (!player.getInventory().insertStack(newItemStack)) {
                        player.dropItem(newItemStack, false);
                    }
                }
            }
        }
        stack.damage(1, user, EquipmentSlot.MAINHAND);

        if (user instanceof PlayerEntity player) {
            int cooldownTime = 100 + fixedUseTicks;
            player.getItemCooldownManager().set(this, cooldownTime);
        }
    }
}
