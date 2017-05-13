package ossium_team.mmod.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import ossium_team.mmod.MolecularModification;
import ossium_team.mmod.common.block.tile.TileBlockMoleculizer;
import ossium_team.mmod.handler.ModGuiHandler;

import javax.annotation.Nullable;

public class BlockBlockMoleculizer extends BaseBlock implements ITileEntityProvider {

    public BlockBlockMoleculizer () {
        super(Material.GLASS, "block_block_moleculizer");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBlockMoleculizer();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && !playerIn.isSneaking()) {
            playerIn.openGui(MolecularModification.instance, ModGuiHandler.BLOCK_MOLECULIZER_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) != null) {
            IItemHandlerModifiable handler = ((TileBlockMoleculizer)worldIn.getTileEntity(pos)).getItemHandler();
            for (int i = 0; i < handler.getSlots(); i++)
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i)));
        }
        super.breakBlock(worldIn, pos, state);
    }
}
