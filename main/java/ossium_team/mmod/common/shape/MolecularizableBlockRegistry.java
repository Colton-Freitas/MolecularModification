package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.block.ModBlocks;

import java.util.ArrayList;
import java.util.HashMap;

public class MolecularizableBlockRegistry {

    private static HashMap<String, Integer> hardness = new HashMap<String, Integer>();
    private static HashMap<String, Integer> efficiency = new HashMap<String, Integer>();
    private static ArrayList<IMolecularShapeTestable> shapes = new ArrayList<IMolecularShapeTestable>();

    public static void init() {
        initHardness();
        initEfficiency();
        initShapes();
    }

    private static void initHardness() {
        hardness.put(new ItemStack(Blocks.OBSIDIAN).getItem().toString(), 1000);
        hardness.put(new ItemStack(Blocks.NETHER_BRICK).getItem().toString(), 500);
        hardness.put(new ItemStack(Blocks.HARDENED_CLAY).getItem().toString(), 100);
        hardness.put(new ItemStack(Blocks.LAPIS_BLOCK).getItem().toString(), 100);
        hardness.put(new ItemStack(Blocks.ANVIL).getItem().toString(), 1000);
        hardness.put(new ItemStack(Blocks.BONE_BLOCK).getItem().toString(), 100);
        hardness.put(new ItemStack(Blocks.BOOKSHELF).getItem().toString(), 150);
        hardness.put(new ItemStack(Blocks.BRICK_BLOCK).getItem().toString(), 200);
        hardness.put(new ItemStack(Blocks.CLAY).getItem().toString(), 50);
        hardness.put(new ItemStack(Blocks.COAL_BLOCK).getItem().toString(), 250);
        hardness.put(new ItemStack(Blocks.COBBLESTONE).getItem().toString(), 400);
        hardness.put(new ItemStack(Blocks.DIAMOND_BLOCK).getItem().toString(), 1000);
        hardness.put(new ItemStack(Blocks.EMERALD_BLOCK).getItem().toString(), 900);
        hardness.put(new ItemStack(Blocks.END_BRICKS).getItem().toString(), 500);
        hardness.put(new ItemStack(Blocks.END_STONE).getItem().toString(), 400);
        hardness.put(new ItemStack(Blocks.GOLD_BLOCK).getItem().toString(), 300);
        hardness.put(new ItemStack(Blocks.IRON_BLOCK).getItem().toString(), 750);
        hardness.put(new ItemStack(Blocks.NETHERRACK).getItem().toString(), 450);
        hardness.put(new ItemStack(Blocks.QUARTZ_BLOCK).getItem().toString(), 550);
        hardness.put(new ItemStack(Blocks.REDSTONE_BLOCK).getItem().toString(), 50);
        hardness.put(new ItemStack(ModBlocks.strengthenedDiamond).getItem().toString(), 1500);
        hardness.put(new ItemStack(ModBlocks.strengthenedIron).getItem().toString(), 1000);
        hardness.put(new ItemStack(ModBlocks.conductiveIron).getItem().toString(), 1000);

        for (int i = 0; i < 6; i++)
            hardness.put(new ItemStack(Blocks.PLANKS, 1, i).getItem().toString(), 100);
        for (int i = 0; i < 7; i++)
            hardness.put(new ItemStack(Blocks.STONE, 1, i).getItem().toString(), 500);
        for (int i = 0; i < 4; i++)
            hardness.put(new ItemStack(Blocks.LOG, 1, i).getItem().toString(), 200);
        for (int i = 0; i < 2; i++) {
            hardness.put(new ItemStack(Blocks.LOG2, 1, i).getItem().toString(), 200);
        }
    }

    private static void initEfficiency() {
        efficiency.put(new ItemStack(Blocks.OBSIDIAN).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.NETHER_BRICK).getItem().toString(), 250);
        efficiency.put(new ItemStack(Blocks.HARDENED_CLAY).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.LAPIS_BLOCK).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.ANVIL).getItem().toString(), 1000);
        efficiency.put(new ItemStack(Blocks.BONE_BLOCK).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.BOOKSHELF).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.BRICK_BLOCK).getItem().toString(), 200);
        efficiency.put(new ItemStack(Blocks.CLAY).getItem().toString(), 50);
        efficiency.put(new ItemStack(Blocks.COAL_BLOCK).getItem().toString(), 250);
        efficiency.put(new ItemStack(Blocks.COBBLESTONE).getItem().toString(), 200);
        efficiency.put(new ItemStack(Blocks.DIAMOND_BLOCK).getItem().toString(), 900);
        efficiency.put(new ItemStack(Blocks.EMERALD_BLOCK).getItem().toString(), 1000);
        efficiency.put(new ItemStack(Blocks.END_BRICKS).getItem().toString(), 400);
        efficiency.put(new ItemStack(Blocks.END_STONE).getItem().toString(), 500);
        efficiency.put(new ItemStack(Blocks.GOLD_BLOCK).getItem().toString(), 100);
        efficiency.put(new ItemStack(Blocks.IRON_BLOCK).getItem().toString(), 750);
        efficiency.put(new ItemStack(Blocks.NETHERRACK).getItem().toString(), 450);
        efficiency.put(new ItemStack(Blocks.QUARTZ_BLOCK).getItem().toString(), 550);
        efficiency.put(new ItemStack(Blocks.REDSTONE_BLOCK).getItem().toString(), 850);
        efficiency.put(new ItemStack(ModBlocks.strengthenedIron).getItem().toString(), 1000);
        efficiency.put(new ItemStack(ModBlocks.strengthenedDiamond).getItem().toString(), 1750);
        efficiency.put(new ItemStack(ModBlocks.conductiveIron).getItem().toString(), 1500);

        for (int i = 0; i < 6; i++)
            efficiency.put(new ItemStack(Blocks.PLANKS, 1, i).getItem().toString(), 100);
        for (int i = 0; i < 7; i++)
            efficiency.put(new ItemStack(Blocks.STONE, 1, i).getItem().toString(), 250);
        for (int i = 0; i < 4; i++)
            efficiency.put(new ItemStack(Blocks.LOG, 1, i).getItem().toString(), 200);
        for (int i = 0; i < 2; i++) {
            efficiency.put(new ItemStack(Blocks.LOG2, 1, i).getItem().toString(), 200);
        }
    }

    private static void initShapes() {
        shapes.add(new PistonShape());
        shapes.add(new HarvestShape());
        shapes.add(new SilkShape());
        shapes.add(new FortuneShape());
        shapes.add(new DurabilityCore());
        shapes.add(new DurabilityCoreUltima());
        shapes.add(new EfficiencyCore());
        shapes.add(new SelfRepairShape());
    }

    public static int getHardness(Block blockIn) {
        return hardness.get(new ItemStack(blockIn).getItem().toString()) != null ? hardness.get(new ItemStack(blockIn).getItem().toString()) : 0;
    }

    public static int getEfficiency(Block blockIn) {
        return efficiency.get(new ItemStack(blockIn).getItem().toString()) != null ? efficiency.get(new ItemStack(blockIn).getItem().toString()) : 0;
    }

    public static int[] getShapesEffect(Block[][][] blocksIn, int baseHardness, int baseEfficiency, int baseHarvest, int baseMaxRange, int silky, int fortune, int selfRepair) {
        int[] boost = new int[7];
        for(IMolecularShapeTestable shape : shapes)
            boost = addArray(shape.getShapeFromBlocks(blocksIn, baseHardness, baseEfficiency, baseMaxRange, baseHarvest, silky, fortune, selfRepair), boost);
        return boost;
    }

    private static int[] addArray(int[] x, int[] y) {
        int[] ret = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            ret[i] = x[i] + y[i];
        }
        return ret;
    }
}
