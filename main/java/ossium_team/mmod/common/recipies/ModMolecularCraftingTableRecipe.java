package ossium_team.mmod.common.recipies;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.ModItems;

public class ModMolecularCraftingTableRecipe {

    private static Item[] reinforcedPickaxeRecipeIron = new Item[] {
            ModItems.itemReinforcedIron, ModItems.itemReinforcedIron, ModItems.itemReinforcedIron, Items.AIR, ModItems.itemReinforcedStick, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedPickaxeRecipeDiamond = new Item[] {
            ModItems.itemReinforcedDiamond, ModItems.itemReinforcedDiamond, ModItems.itemReinforcedDiamond, Items.AIR, ModItems.itemReinforcedStick, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedShovelRecipeIron = new Item[] {
            Items.AIR, ModItems.itemReinforcedIron, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedShovelRecipeDiamond = new Item[] {
            Items.AIR, ModItems.itemReinforcedDiamond, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedAxeRecipeIron = new Item[] {
            Items.AIR, ModItems.itemReinforcedIron, ModItems.itemReinforcedIron, Items.AIR, ModItems.itemReinforcedStick, ModItems.itemReinforcedIron, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedAxeRecipeDiamond = new Item[] {
            Items.AIR, ModItems.itemReinforcedDiamond, ModItems.itemReinforcedDiamond, Items.AIR, ModItems.itemReinforcedStick, ModItems.itemReinforcedDiamond, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] strengthenedDiamondChestArmour = new Item[] {
            ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond
    };
    private static Item[] strengthenedDiamondHeadArmour = new Item[] {
            ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond, Items.AIR, Items.AIR, Items.AIR
    };
    private static Item[] strengthenedDiamondLegArmour = new Item[] {
            ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond
    };
    private static Item[] strengthenedDiamondBootsArmour = new Item[] {
            Items.AIR, Items.AIR, Items.AIR, ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond, ModItems.itemStrengthenedDiamond, Items.AIR, ModItems.itemStrengthenedDiamond
    };
    private static Item[] reinforcedSwordRecipeIron = new Item[] {
            Items.AIR, ModItems.itemReinforcedIron, Items.AIR, Items.AIR, ModItems.itemReinforcedIron, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };
    private static Item[] reinforcedSwordRecipeDiamond = new Item[] {
            Items.AIR, ModItems.itemReinforcedDiamond, Items.AIR, Items.AIR, ModItems.itemReinforcedDiamond, Items.AIR, Items.AIR, ModItems.itemReinforcedStick, Items.AIR
    };

    private ItemStack ret;

    private ModMolecularCraftingTableRecipe(ItemStack ret) {
        this.ret = ret;
    }

    public static ModMolecularCraftingTableRecipe isValidRecipe(ItemStack... inputStacks) {
        if(inputStacks.length != 9) {
            return null;
        }
        if(areEqual(inputStacks, reinforcedPickaxeRecipeDiamond) || areEqual(inputStacks, reinforcedPickaxeRecipeIron))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.itemReinforcedPickaxe));
        else if (areEqual(inputStacks, reinforcedAxeRecipeDiamond) || areEqual(inputStacks, reinforcedAxeRecipeIron))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.itemReinforcedAxe));
        else if (areEqual(inputStacks, reinforcedShovelRecipeDiamond) || areEqual(inputStacks, reinforcedShovelRecipeIron))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.itemReinforcedShovel));
        else if (areEqual(inputStacks, strengthenedDiamondHeadArmour))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.reinforcedHead));
        else if (areEqual(inputStacks, strengthenedDiamondChestArmour))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.reinforcedBody));
        else if (areEqual(inputStacks, strengthenedDiamondLegArmour))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.reinforcedLegs));
        else if (areEqual(inputStacks, strengthenedDiamondBootsArmour))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.reinforcedBoots));
        else if (areEqual(inputStacks, reinforcedSwordRecipeDiamond) || areEqual(inputStacks, reinforcedSwordRecipeIron))
            return new ModMolecularCraftingTableRecipe(new ItemStack(ModItems.itemReinforcedSword));
        return null;
    }


    private static boolean areEqual(ItemStack[] input, Item[] recipe) {
        Item[] inputItems = new Item[input.length];
        for (int i = 0; i < input.length; i++) {
            inputItems[i] = input[i].getItem();
        }

        for (int i = 0; i < recipe.length; i++) {
            if(inputItems[i] == null && recipe[i] == null)
                continue;
            if ((inputItems[i] == null && recipe[i] != null) || (inputItems[i] != null && recipe[i] == null)) {
                return false;
            }
            if(!inputItems[i].getRegistryName().equals(recipe[i].getRegistryName())) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getRecipeReturn() {
        return ret;
    }
}
