package ossium_team.mmod.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.oredict.OreDictionary;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.shape.DurabilityCoreUltima;
import ossium_team.mmod.common.shape.EfficiencyCore;
import ossium_team.mmod.common.shape.SelfRepairShape;

import java.util.Arrays;

public class DebugItem extends BaseItem {

    public DebugItem() {
        super("debug_item");
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            DurabilityCoreUltima shape = new DurabilityCoreUltima();
            Block [][][] blocks = shape.getShape();
            BlockPos start = pos.up();
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks[i].length; j++) {
                    for (int k = 0; k < blocks[i][j].length; k++) {
                        BlockPos current = start.add(i, j, k);
                        worldIn.setBlockState(current, blocks[i][j][k].getDefaultState());
                    }
                }
            }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote && !playerIn.isSneaking()) {
            worldIn.setLightFor(EnumSkyBlock.SKY, playerIn.getPosition(), 14);
        } else if (!worldIn.isRemote) {

        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
