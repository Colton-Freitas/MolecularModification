package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

public class MMPickaxe extends MMToolBase {

    public MMPickaxe() {
        super("item_mm_pickaxe", "pickaxe");
        breakableMats.put(Material.ROCK.toString(), Material.ROCK);
        breakableMats.put(Material.GLASS.toString(), Material.GLASS);
        breakableMats.put(Material.IRON.toString(), Material.IRON);
        breakableMats.put(Material.PISTON.toString(), Material.PISTON);
        breakableMats.put(Material.ANVIL.toString(), Material.ANVIL);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        float current = super.getStrVsBlock(stack, state);
        if(state.getBlock().getHarvestLevel(state) >= Blocks.OBSIDIAN.getHarvestLevel(Blocks.OBSIDIAN.getDefaultState()) &&
                canHarvestBlock(state, stack))
            return current * 5;
        return current;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return ((NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0) - NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 0)) /
                (double)NBTHelper.getInt(stack, TagLib.MM_DURABILITY_MAX, 0));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getDurabilityForDisplay(stack) != 0;
    }


}
