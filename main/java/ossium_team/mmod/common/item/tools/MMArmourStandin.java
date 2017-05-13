package ossium_team.mmod.common.item.tools;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MMArmourStandin extends MMToolBase {

    public MMArmourStandin(String name) {
        super(name, "");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote && !stack.hasTagCompound()) {
            baseNBT(stack, null, null, null, null, null, null, null, null);
        }
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return 1.0f;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        return false;
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.PASS;
    }
}
