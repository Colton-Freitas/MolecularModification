package ossium_team.mmod.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

public abstract class MMReinforcedTool extends MMToolBase implements IToolNBTInstanciation{

    public MMReinforcedTool(String name, String toolClass) {
        super(name, toolClass);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if(!worldIn.isRemote) {
            NBTHelper.setInt(stack, TagLib.MM_DURABILITY, NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 1) -1);
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote && NBTHelper.getInt(stack, TagLib.MM_DURABILITY, 1) == 0)
            stack.shrink(1);
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
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
