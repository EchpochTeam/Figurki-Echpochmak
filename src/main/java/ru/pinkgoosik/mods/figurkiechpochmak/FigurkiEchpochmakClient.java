package ru.pinkgoosik.mods.figurkiechpochmak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import ru.pinkgoosik.mods.figurkiechpochmak.entity.MiniBombEntity;

public class FigurkiEchpochmakClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_SOMIK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_TETSA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FigurkiEchpochmak.MINI_GOOSIK, RenderLayer.getCutout());
        EntityRendererRegistry.INSTANCE.register(FigurkiEchpochmak.BOMB, (dispatcher, context) -> new FlyingItemEntityRenderer<MiniBombEntity>(dispatcher, context.getItemRenderer()));
    }

}
