package ru.pinkgoosik.mods.figurkiechpochmak;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import ru.pinkgoosik.mods.figurkiechpochmak.entity.BouncyMiniBombEntity;
import ru.pinkgoosik.mods.figurkiechpochmak.entity.MiniBombEntity;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniGoosik;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniSomik;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniSpace;
import ru.pinkgoosik.mods.figurkiechpochmak.figurki.MiniTetsa;
import ru.pinkgoosik.mods.figurkiechpochmak.item.BouncyMiniBomb;
import ru.pinkgoosik.mods.figurkiechpochmak.item.MiniBomb;


public class FigurkiEchpochmak implements ModInitializer {

	public static final Block MINI_SOMIK = new MiniSomik(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Block MINI_TETSA = new MiniTetsa(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Block MINI_GOOSIK = new MiniGoosik(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Block MINI_SPACE = new MiniSpace(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
	public static final Item MINI_BOMB = new MiniBomb(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(16));
	public static final Item BOUNCY_MINI_BOMB = new BouncyMiniBomb(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(16));
	public static final EntityType<MiniBombEntity> BOMB = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("figurkiechpochmak", "mini_bomb"),
			FabricEntityTypeBuilder.<MiniBombEntity>create(SpawnGroup.CREATURE, MiniBombEntity::new).build()
	);
	public static final EntityType<BouncyMiniBombEntity> BOUNCY_BOMB = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("figurkiechpochmak", "bouncy_mini_bomb"),
			FabricEntityTypeBuilder.<BouncyMiniBombEntity>create(SpawnGroup.CREATURE, BouncyMiniBombEntity::new).build()
	);

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_somik"), MINI_SOMIK);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_somik"), new BlockItem(MINI_SOMIK, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_tetsa"), MINI_TETSA);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_tetsa"), new BlockItem(MINI_TETSA, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_goosik"), MINI_GOOSIK);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_goosik"), new BlockItem(MINI_GOOSIK, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("figurkiechpochmak", "mini_space"), MINI_SPACE);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_space"), new BlockItem(MINI_SPACE, new Item.Settings()));

		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "mini_bomb"), MINI_BOMB);
		Registry.register(Registry.ITEM, new Identifier("figurkiechpochmak", "bouncy_mini_bomb"), BOUNCY_MINI_BOMB);
		DispenserBlock.registerBehavior(FigurkiEchpochmak.MINI_BOMB, new ProjectileDispenserBehavior() {
			@Override
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				return Util.make(new MiniBombEntity(world, position.getX(), position.getY(), position.getZ()), (snowballEntity) -> {
					snowballEntity.setItem(stack);
				});
			}
		});
		DispenserBlock.registerBehavior(FigurkiEchpochmak.BOUNCY_MINI_BOMB, new ProjectileDispenserBehavior() {
			@Override
			protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
				return Util.make(new BouncyMiniBombEntity(world, position.getX(), position.getY(), position.getZ()), (snowballEntity) -> {
					snowballEntity.setItem(stack);
				});
			}
		});


	}
}
