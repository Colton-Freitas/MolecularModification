package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

public class MMShovel extends MMToolBase {

    public MMShovel() {
        super("item_mm_shovel", "shovel");
        breakableMats.put(Material.GROUND.toString(), Material.GROUND);
        breakableMats.put(Material.GRASS.toString(), Material.GRASS);
        breakableMats.put(Material.CLAY.toString(), Material.CLAY);
        breakableMats.put(Material.CRAFTED_SNOW.toString(), Material.CRAFTED_SNOW);
        breakableMats.put(Material.SNOW.toString(), Material.SNOW);
        breakableMats.put(Material.SAND.toString(), Material.SAND);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (canHarvestBlock(state, stack))
            return super.getStrVsBlock(stack, state) * .5f;
        else
            return super.getStrVsBlock(stack, state);
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
