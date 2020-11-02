package ru.pinkgoosik.mods.figurkiechpochmak.figurki;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiniGoosik extends Figurka{

    public MiniGoosik(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.getItem()== Items.CAKE){
            if (!world.isClient) {
                player.sendMessage(new LiteralText("<MiniGoosik> вкусна"), false);
                world.playSound(null,pos,SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1, 1);
                if (!player.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
