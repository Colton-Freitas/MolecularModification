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
import ossium_team.mmod.common.block.tile.TileMolecularCraftingTableEnergy;
import ossium_team.mmod.handler.ModGuiHandler;

import javax.annotation.Nullable;

public class BlockMolecularCraftingTableEnergy extends BaseBlock implements ITileEntityProvider {

    public BlockMolecularCraftingTableEnergy() {
        super(Material.GLASS, "block_molecular_crafting_table_energy");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMolecularCraftingTableEnergy();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && !playerIn.isSneaking())
        {
            playerIn.openGui(MolecularModification.instance, ModGuiHandler.MOLECULAR_CRAFTING_TABLE_ENERGY_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(!worldIn.isRemote)
        {
            TileMolecularCraftingTableEnergy tile = (TileMolecularCraftingTableEnergy) worldIn.getTileEntity(pos);
            if(tile != null) {
                IItemHandlerModifiable handler = tile.getItemHandler();
                for (int i = 0; i < tile.getSizeInventory(); i++) {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i)));
                }
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
}
