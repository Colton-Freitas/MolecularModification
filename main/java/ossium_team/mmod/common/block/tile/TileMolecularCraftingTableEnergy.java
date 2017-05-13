package ossium_team.mmod.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.item.tools.IToolNBTInstanciation;
import ossium_team.mmod.common.item.tools.MMArmourStandin;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.recipies.ModMolecularCraftingTableRecipe;
import ossium_team.mmod.helper.NBTHelper;

public class TileMolecularCraftingTableEnergy extends BaseModTile {

    public TileMolecularCraftingTableEnergy() {
        invSize = 12;
        itemHandler = new ItemStackHandler(12);
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            ItemStack[] inputItems = new ItemStack[9];
            for (int i = 0; i < inputItems.length; i++)
                inputItems[i] = itemHandler.getStackInSlot(i);
            if(ModMolecularCraftingTableRecipe.isValidRecipe(inputItems) != null &&
                    !itemHandler.getStackInSlot(11).isEmpty() && itemHandler.getStackInSlot(9).isEmpty()) {
                if(NBTHelper.hasNBT(itemHandler.getStackInSlot(11))) {
                    operationTicks++;
                    operationTicks %= 200;
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = false;
                    if (operationTicks == 199) {
                        itemHandler.setStackInSlot(9, finalizeRecipe(ModMolecularCraftingTableRecipe.isValidRecipe(inputItems).getRecipeReturn()));
                    }
                }
            }
            else
            {
                operationTicks = 0;
                if(!hasClientUpdated) {
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = true;
                }
            }
            if (!itemHandler.getStackInSlot(10).isEmpty() && world.canSeeSky(pos.up()) && world.isDaytime()) {
                ItemStack tool = itemHandler.getStackInSlot(10);
                if(NBTHelper.getInt(tool, TagLib.MM_DURABILITY, 0) != NBTHelper.getInt(tool, TagLib.MM_DURABILITY_MAX, 0)) {
                    NBTHelper.setInt(tool, TagLib.MM_DURABILITY, NBTHelper.getInt(tool, TagLib.MM_DURABILITY, 0) + 30);
                    if(NBTHelper.getInt(tool, TagLib.MM_DURABILITY, 0) > NBTHelper.getInt(tool, TagLib.MM_DURABILITY_MAX, 0))
                        NBTHelper.setInt(tool, TagLib.MM_DURABILITY, NBTHelper.getInt(tool, TagLib.MM_DURABILITY_MAX, 0));
                }
            }
        }
    }

    private ItemStack finalizeRecipe(ItemStack recipeReturn) {
        ItemStack ret = recipeReturn;
        if(ret.getItem().equals(ModItems.itemReinforcedPickaxe))
            ret = new ItemStack(ModItems.itemMMPickaxe);
        else if (ret.getItem().equals(ModItems.itemReinforcedAxe))
            ret = new ItemStack(ModItems.itemMMAxe);
        else if (ret.getItem().equals(ModItems.itemReinforcedShovel))
            ret = new ItemStack(ModItems.itemMMShovel);
        else if (ret.getItem().equals(ModItems.reinforcedHead))
            ret = new ItemStack(ModItems.itemArmourHead);
        else if (ret.getItem().equals(ModItems.reinforcedBody))
            ret = new ItemStack(ModItems.itemArmourChest);
        else if (ret.getItem().equals(ModItems.reinforcedLegs))
            ret = new ItemStack(ModItems.itemArmourLegs);
        else if (ret.getItem().equals(ModItems.reinforcedBoots))
            ret = new ItemStack(ModItems.itemArmourBoots);
        else if (ret.getItem().equals(ModItems.itemReinforcedSword))
            ret = new ItemStack(ModItems.itemMMSword);

        int handleHardness = 0;
        int handleFlexibility = 0;
        int baseHardness = 0;
        int baseEfficiency = 0;

        for (int i = 0; i < 9; i++) {
            ItemStack current = itemHandler.getStackInSlot(i);
            if (!current.isEmpty()) {
                if (NBTHelper.verify(current, TagLib.ITEM_REINFORCEMENT_FLEXIBILITY)) {
                    handleHardness += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_HARDNESS, 0);
                    handleFlexibility += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_FLEXIBILITY, 0);
                } else {
                    baseEfficiency += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_EFFICIENCY, 0);
                    baseHardness += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_HARDNESS, 0);
                }
            }
        }
        ItemStack upgradeCore = itemHandler.getStackInSlot(11);

        double flexibilityMod = handleFlexibility > handleHardness ? (double) handleHardness / handleFlexibility : (double) handleFlexibility / handleHardness;
        baseHardness += NBTHelper.getInt(upgradeCore, TagLib.MM_HARDNESS, 0);
        baseEfficiency += NBTHelper.getInt(upgradeCore, TagLib.MM_EFFICIENCY, 0);

        if (ret.getItem() instanceof IToolNBTInstanciation && !(ret.getItem() instanceof MMArmourStandin)) {
            ret = ((IToolNBTInstanciation) ret.getItem()).baseNBT(ret, (int) (baseHardness * flexibilityMod), (int) (baseEfficiency * flexibilityMod / 2.5),
                    2 + NBTHelper.getInt(upgradeCore, TagLib.MM_HARVEST, 0), 0,
                    NBTHelper.getInt(upgradeCore, TagLib.MM_MAX_RANGE, 0), NBTHelper.getInt(upgradeCore, TagLib.MM_SILK, 0), NBTHelper.getInt(upgradeCore, TagLib.MM_FORTUNE, 0),
                    NBTHelper.getInt(upgradeCore, TagLib.MM_SELF_REPAIR, 0));
            for (int i = 0; i < 9; i++) {
                if (!itemHandler.getStackInSlot(i).isEmpty())
                    itemHandler.getStackInSlot(i).shrink(1);
            }
            itemHandler.getStackInSlot(11).shrink(1);

            return ret;
        }
        else if (ret.getItem() instanceof MMArmourStandin) {
            ret = ((MMArmourStandin) ret.getItem()).baseNBT(ret, baseHardness, baseEfficiency, 0, 0, 0, 0, 0, 0);
            for (int i = 0; i < 9; i++) {
                if (!itemHandler.getStackInSlot(i).isEmpty())
                    itemHandler.getStackInSlot(i).shrink(1);
            }
            itemHandler.getStackInSlot(11).shrink(1);

            return ret;
        }
        return ItemStack.EMPTY;
    }

}
