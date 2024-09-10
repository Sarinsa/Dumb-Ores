package com.sarinsa.tomfoolery.common.event;

import com.sarinsa.tomfoolery.common.capability.CapabilityHelper;
import com.sarinsa.tomfoolery.common.core.registry.TomEffects;
import com.sarinsa.tomfoolery.common.core.registry.TomEntities;
import com.sarinsa.tomfoolery.common.entity.living.Buffcat;
import com.sarinsa.tomfoolery.common.item.CoolGlassesItem;
import com.sarinsa.tomfoolery.common.network.NetworkHelper;
import com.sarinsa.tomfoolery.common.util.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPotionEffectExpire(MobEffectEvent.Expired event) {
        LivingEntity livingEntity = event.getEntity();

        if (event.getEffectInstance() == null)
            return;

        updateEntityCactusAttract(event.getEffectInstance().getEffect(), livingEntity, false);
    }

    @SubscribeEvent
    public void onPotionRemoved(MobEffectEvent.Remove event) {
        LivingEntity livingEntity = event.getEntity();

        if (event.getEffectInstance() == null || !(livingEntity instanceof ServerPlayer))
            return;

        updateEntityCactusAttract(event.getEffectInstance().getEffect(), livingEntity, false);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPotionEffectAdded(MobEffectEvent.Added event) {
        LivingEntity livingEntity = event.getEntity();

        updateEntityCactusAttract(event.getEffectInstance().getEffect(), livingEntity, true);
    }

    @SubscribeEvent
    public void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            Level level = livingEntity.getCommandSenderWorld();

            if (level.isLoaded(livingEntity.blockPosition())) {
                if (!level.isClientSide) {
                    ServerLevel serverLevel = (ServerLevel) level;

                    for (ServerPlayer playerEntity : serverLevel.players()) {
                        NetworkHelper.updateEntityCactusAttract(playerEntity, livingEntity);
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerUpdate(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CoolGlassesItem glasses) {
                double range = glasses.getRange();

                Vec3 eyePosition = player.getEyePosition(1.0F);
                Vec3 viewVector = player.getViewVector(1.0F);
                Vec3 vector3d = eyePosition.add(viewVector.x * range, viewVector.y * range, viewVector.z * range);
                BlockHitResult result = player.level().clip(new ClipContext(eyePosition, vector3d, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

                glasses.gaze(player, player.level(), result);
            }
        }
    }

    @SubscribeEvent
    public void onCatInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Cat cat) {
            if (cat.hasEffect(MobEffects.DAMAGE_BOOST)) {
                if (event.getItemStack().getItem() == Items.GOLDEN_APPLE) {
                    event.setCancellationResult(InteractionResult.CONSUME);

                    if (event.getLevel() instanceof ServerLevel serverLevel) {
                        Buffcat buffcat = TomEntities.BUFFCAT.get().spawn(serverLevel, null, event.getEntity(), cat.blockPosition(), MobSpawnType.TRIGGERED, true, false);

                        if (buffcat != null) {
                            buffcat.readAdditionalSaveData(cat.saveWithoutId(new CompoundTag()));
                        }

                        cat.discard();
                    }
                    RandomSource random = event.getLevel().getRandom();
                    event.getLevel().playSound(event.getEntity(), cat.blockPosition(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.NEUTRAL, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F );
                }
            }
        }
    }

    private static void updateEntityCactusAttract(MobEffect effect, LivingEntity livingEntity, boolean marked) {
        if (effect == TomEffects.CACTUS_ATTRACTION.get()) {
            NBTHelper.markEntityCactusAttr(livingEntity, marked);
        }
    }
}
