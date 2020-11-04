package ru.pinkgoosik.mods.figurkiechpochmak.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.pinkgoosik.mods.figurkiechpochmak.entity.BouncyMiniBombEntity;

public class BouncyMiniBomb extends Item {
    public BouncyMiniBomb(Settings settings){
        super(settings);
    }
    static float playerYawHoo;
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        playerYawHoo = user.yaw;
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 40);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            BouncyMiniBombEntity bouncyMiniBombEntity = new BouncyMiniBombEntity(world, user);
            bouncyMiniBombEntity.setItem(itemStack);
            bouncyMiniBombEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 0.5F, 0.4F);
            world.spawnEntity(bouncyMiniBombEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
    public static float getPlayerYaw(){
        return playerYawHoo;
    }
}
