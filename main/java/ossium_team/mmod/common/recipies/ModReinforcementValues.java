package ossium_team.mmod.common.recipies;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ossium_team.mmod.common.item.ModItems;

import java.util.HashMap;

public class ModReinforcementValues {

    private static HashMap<String, Integer> hardness = new HashMap<String, Integer>();
    private static HashMap<String, Integer> flexibility = new HashMap<String, Integer>();
    private static HashMap<String, Integer> efficiency = new HashMap<String, Integer>();

    public static void init() {
        initHardness();
        initFlexibility();
        initEfficiency();
    }


    private static void initEfficiency() {
        efficiency.put(Items.DIAMOND.toString(), 1000);
        efficiency.put(Items.BONE.toString(), 50);
        efficiency.put(Items.BLAZE_ROD.toString(), 200);
        efficiency.put(Items.BRICK.toString(), 200);
        efficiency.put(Items.CLAY_BALL.toString(), 25);
        efficiency.put(Items.COAL.toString(), 50);
        efficiency.put(Items.EMERALD.toString(), 750);
        efficiency.put(Items.ENDER_PEARL.toString(), 50);
        efficiency.put(Items.BLAZE_POWDER.toString(), 10);
        efficiency.put(Items.FLINT.toString(), 150);
        efficiency.put(Items.GOLD_INGOT.toString(), 75);
        efficiency.put(Items.IRON_INGOT.toString(), 250);
        efficiency.put(Items.LEATHER.toString(), 50);
        efficiency.put(Items.NETHERBRICK.toString(), 200);
        efficiency.put(Items.QUARTZ.toString(), 250);
        efficiency.put(Items.STICK.toString(), 10);
        efficiency.put(Items.REDSTONE.toString(), 500);
        efficiency.put("ingotCopper", 300);
        efficiency.put("ingotTin", 225);
        efficiency.put("ingotBronze", 300);
        efficiency.put("ingotSilver", 300);
        efficiency.put("ingotLead", 250);
        efficiency.put("ingotSteel", 350);
        efficiency.put("ingotElectrum", 450);
        efficiency.put(ModItems.itemStrengthenedIronIngot.toString(), 300);
        efficiency.put(ModItems.itemStrengthenedDiamond.toString(), 1500);
        efficiency.put(ModItems.itemConductiveIron.toString(), 450);
    }

    private static void initHardness(){
        hardness.put(Items.DIAMOND.toString(), 500);
        hardness.put(Items.BONE.toString(), 50);
        hardness.put(Items.BLAZE_ROD.toString(), 100);
        hardness.put(Items.BRICK.toString(), 150);
        hardness.put(Items.CLAY_BALL.toString(), 25);
        hardness.put(Items.COAL.toString(), 75);
        hardness.put(Items.EMERALD.toString(), 400);
        hardness.put(Items.ENDER_PEARL.toString(), 1500);
        hardness.put(Items.BLAZE_POWDER.toString(), 10);
        hardness.put(Items.FLINT.toString(), 150);
        hardness.put(Items.GOLD_INGOT.toString(), 75);
        hardness.put(Items.IRON_INGOT.toString(), 250);
        hardness.put(Items.LEATHER.toString(), 50);
        hardness.put(Items.NETHERBRICK.toString(), 200);
        hardness.put(Items.QUARTZ.toString(), 250);
        hardness.put(Items.STICK.toString(), 10);
        hardness.put(Items.REDSTONE.toString(), 50);
        hardness.put("ingotCopper", 200);
        hardness.put("ingotTin", 250);
        hardness.put("ingotBronze", 300);
        hardness.put("ingotSteel", 350);
        hardness.put("ingotSilver", 250);
        hardness.put("ingotLead", 300);
        hardness.put("ingotElectrum", 150);
        hardness.put(ModItems.itemStrengthenedIronIngot.toString(), 300);
        hardness.put(ModItems.itemStrengthenedDiamond.toString(), 1500);
        hardness.put(ModItems.itemConductiveIron.toString(), 300);
    }

    private static void initFlexibility() {
        flexibility.put(Items.DIAMOND.toString(), 50);
        flexibility.put(Items.BONE.toString(), 250);
        flexibility.put(Items.BLAZE_ROD.toString(), 300);
        flexibility.put(Items.BRICK.toString(), 50);
        flexibility.put(Items.CLAY_BALL.toString(), 300);
        flexibility.put(Items.COAL.toString(), 50);
        flexibility.put(Items.EMERALD.toString(), 50);
        flexibility.put(Items.ENDER_PEARL.toString(), 1000);
        flexibility.put(Items.BLAZE_POWDER.toString(), 250);
        flexibility.put(Items.FLINT.toString(), 50);
        flexibility.put(Items.GOLD_INGOT.toString(), 150);
        flexibility.put(Items.IRON_INGOT.toString(), 100);
        flexibility.put(Items.LEATHER.toString(), 250);
        flexibility.put(Items.NETHERBRICK.toString(), 50);
        flexibility.put(Items.QUARTZ.toString(), 50);
        flexibility.put(Items.STICK.toString(), 300);
        flexibility.put(Items.REDSTONE.toString(), 50);
        flexibility.put("ingotCopper", 125);
        flexibility.put("ingotTin", 100);
        flexibility.put("ingotBronze", 75);
        flexibility.put("ingotSteel", 75);
        flexibility.put("ingotSilver", 100);
        flexibility.put("ingotLead", 50);
        flexibility.put("ingotElectrum", 125);
        flexibility.put(ModItems.itemStrengthenedIronIngot.toString(), 50);
        flexibility.put(ModItems.itemStrengthenedDiamond.toString(), 25);
        flexibility.put(ModItems.itemConductiveIron.toString(), 50);
    }

    public static int getHardnessValue(ItemStack stackIn) {
        if(OreDictionary.getOreIDs(stackIn).length != 0 && hardness.get(stackIn.getItem().toString()) == null) {
            int[] ids = OreDictionary.getOreIDs(stackIn);
            return hardness.get(OreDictionary.getOreName(ids[0])) != null ? hardness.get(OreDictionary.getOreName(ids[0])) : 0;
        }

        return hardness.get(stackIn.getItem().toString()) != null ? hardness.get(stackIn.getItem().toString()) : 0;
    }

    public static int getFlexibilityValue(ItemStack stackIn) {

        if(OreDictionary.getOreIDs(stackIn).length != 0 && flexibility.get(stackIn.getItem().toString()) == null) {
            int[] ids = OreDictionary.getOreIDs(stackIn);
            return flexibility.get(OreDictionary.getOreName(ids[0])) != null ? flexibility.get(OreDictionary.getOreName(ids[0])) : 0;
        }

        return flexibility.get(stackIn.getItem().toString()) != null ? flexibility.get(stackIn.getItem().toString()) : 0;
    }

    public static int getEfficiencyValue(ItemStack stackIn) {

        if(OreDictionary.getOreIDs(stackIn).length != 0 && efficiency.get(stackIn.getItem().toString()) == null) {
            int[] ids = OreDictionary.getOreIDs(stackIn);
            return efficiency.get(OreDictionary.getOreName(ids[0])) != null ? efficiency.get(OreDictionary.getOreName(ids[0])) : 0;
        }

        return efficiency.get(stackIn.getItem().toString()) != null ? efficiency.get(stackIn.getItem().toString()) : 0;
    }
}
