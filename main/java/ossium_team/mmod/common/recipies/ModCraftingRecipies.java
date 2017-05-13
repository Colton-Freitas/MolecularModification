package ossium_team.mmod.common.recipies;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ossium_team.mmod.common.block.ModBlocks;
import ossium_team.mmod.common.item.ModItems;

public class ModCraftingRecipies {

    public static void init() {
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemConcaveMould),
                " B ", "BCB", "C C", 'B', Items.BRICK, 'C', Items.CLAY_BALL);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemConvexMould),
                "C C", "BCB", " B ", 'C', Items.CLAY_BALL, 'B', Items.BRICK);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.meltingTable),
                "S S", "SLS", "HHH", 'S', Blocks.STONE, 'L', Items.LAVA_BUCKET, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.strengtheningTable),
                "SLS", "SCS", "HHH", 'S', Blocks.STONE, 'L', ModItems.itemEnderGlazedConvexLens, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.reinforcingTable),
                "SGS", "SLS", "HCH", 'S', Blocks.STONE, 'G', Blocks.GLASS, 'L', ModItems.itemEnderGlazedConvexLens, 'H', Blocks.STONE_SLAB,
                'C', Blocks.CRAFTING_TABLE);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.molecularCraftingTable),
                "SCS", "SCS", "HHH", 'S', Blocks.STONE, 'C', Blocks.CRAFTING_TABLE, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockMoleculizer),
                "SSS", "SCL", "HHH", 'S', Blocks.STONE, 'C', Blocks.CHEST, 'L', ModItems.itemEnderGlazedConvexLens, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.molecularCraftingTableEnergy),
                "CGC", "VCV", "ITI", 'C', ModItems.itemEnderGlazedConcaveLens, 'G', Blocks.GLOWSTONE, 'V', ModItems.itemEnderGlazedConvexLens,
                'I', ModItems.itemStrengthenedIronIngot, 'T', ModBlocks.molecularCraftingTable);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemUpgradeCore),
                "IOI", "ODO", "IOI", 'I', ModItems.itemStrengthenedIronIngot, 'O', Blocks.OBSIDIAN, 'D', ModItems.itemStrengthenedDiamond);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.examinationTable),
                "SLS", "S S", "HHH", 'S', Blocks.STONE, 'L', ModItems.itemConcaveLens, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.armourApplier),
                "SSS", "SUL", "HHH", 'S', Blocks.STONE, 'U', ModItems.itemUpgradeCore, 'L', ModItems.itemEnderGlazedConvexLens, 'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.armourSurgeon),
                "SSS", "SAI", "HHH", 'S', Blocks.STONE, 'A', new ItemStack(Items.GOLDEN_APPLE, 1, 1), 'I', Items.IRON_SWORD,
                'H', Blocks.STONE_SLAB);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.strengthenedDiamond),
                "DDD", "DDD", "DDD", 'D', ModItems.itemStrengthenedDiamond);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.strengthenedIron),
                "III", "III", "III", 'I', ModItems.itemStrengthenedIronIngot);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemConductiveIron, 4),
                "DID", "IDI", "DID", 'I', ModItems.itemStrengthenedIronIngot, 'D', Items.REDSTONE);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.conductiveIron),
                "III", "III", "III", 'I', ModItems.itemConductiveIron);
    }

}
