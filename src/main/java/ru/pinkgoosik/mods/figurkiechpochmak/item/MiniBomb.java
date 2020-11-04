package ru.pinkgoosik.mods.figurkiechpochmak.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.pinkgoosik.mods.figurkiechpochmak.entity.MiniBombEntity;

public class MiniBomb extends Item{

    public MiniBomb(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isFallFlying()) {
            ItemStack itemStack = user.getStackInHand(hand);
            if (!world.isClient) {
                world.spawnEntity(new FireworkRocketEntity(world, itemStack, user));
                if (!user.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
            return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
        }else{
            ItemStack itemStack = user.getStackInHand(hand);
            user.getItemCooldownManager().set(this, 30);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                MiniBombEntity miniBombEntity = new MiniBombEntity(world, user);
                miniBombEntity.setItem(itemStack);
                miniBombEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 0.5F, 0.4F);
                world.spawnEntity(miniBombEntity);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.abilities.creativeMode) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }

    }

}
