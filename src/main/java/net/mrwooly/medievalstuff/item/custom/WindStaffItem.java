package net.mrwooly.medievalstuff.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import net.mrwooly.medievalstuff.item.ModItems;

import java.util.List;

public class WindStaffItem extends Item implements ProjectileItem {

    public WindStaffItem(Item.Settings settings) {
        super(settings);
    }
    int stateCounter = 0;

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 4.5, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(ATTACK_RANGE, 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(BLOCK_RANGE, 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).build();
    }
    public static final Identifier ATTACK_RANGE = Identifier.of("attack_range");
    public static final Identifier BLOCK_RANGE = Identifier.of("block_range");

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return super.canMine(state, world, pos, miner);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.postHit(stack, target, attacker);
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.BREEZE_ROD);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if(stateCounter == 1) {
                WindChargeEntity windChargeEntity1 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.5F, 3.5F);
                world.spawnEntity(windChargeEntity1);

                WindChargeEntity windChargeEntity2 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 4.5F);
                world.spawnEntity(windChargeEntity2);

                int chanceForAdditionalProjectile = MathHelper.nextInt(Random.createLocal(), 1, 4);
                if(chanceForAdditionalProjectile == 1) {

                    WindChargeEntity windChargeEntity3 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                    windChargeEntity3.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 4.5F);
                    world.spawnEntity(windChargeEntity3);

                user.getItemCooldownManager().set(this, 35);
                } else {

                    user.getItemCooldownManager().set(this, 30);
                }
                stateCounter = 2;
            }
            if(stateCounter == 0) {
                WindChargeEntity windChargeEntity1 = new WindChargeEntity(user, world, user.getPos().getX(), user.getEyePos().getY(), user.getPos().getZ());
                windChargeEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 2.5F);
                world.spawnEntity(windChargeEntity1);
                stateCounter = 1;
                user.getItemCooldownManager().set(this, 25);
            }
            if(stateCounter == 2) {
                stateCounter = 0;
            }
            user.getStackInHand(Hand.MAIN_HAND).damage(1, ((ServerWorld) world), ((ServerPlayerEntity) user),
                    item -> user.sendEquipmentBreakStatus(ModItems.WIND_STAFF, EquipmentSlot.MAINHAND));
        }

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_WIND_CHARGE_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        ItemStack itemStack = user.getStackInHand(hand);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    LivingEntity livingEntity;

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        Random random = world.getRandom();
        double x1 = random.nextTriangular(direction.getOffsetX(), 0.11485000000000001);
        double y1 = random.nextTriangular(direction.getOffsetY(), 0.11485000000000001);
        double z1 = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d1 = new Vec3d(x1, y1, z1);
        WindChargeEntity windChargeEntity1 = new WindChargeEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d1);
        windChargeEntity1.setVelocity(vec3d1);

        double x2 = random.nextTriangular(livingEntity.getPitch(), 0.11485000000000001);
        double y2 = random.nextTriangular(direction.getOffsetY(), 0.11485000000000001);
        double z2 = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d2 = new Vec3d(x2, y2, z2);
        WindChargeEntity windChargeEntity2 = new WindChargeEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d2);
        windChargeEntity2.setVelocity(vec3d2);

        double x3 = random.nextTriangular(direction.getOffsetX(), 0.11485000000000001);
        double y3 = random.nextTriangular(direction.getOffsetY(), 0.11485000000000001);
        double z3 = random.nextTriangular(direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d3 = new Vec3d(x3, y3, z3);
        WindChargeEntity windChargeEntity3 = new WindChargeEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d3);
        windChargeEntity3.setVelocity(vec3d3);
        return windChargeEntity3;
    }

    @Override
    public void initializeProjectile(ProjectileEntity entity, double x, double y, double z, float power, float uncertainty) {
    }
}