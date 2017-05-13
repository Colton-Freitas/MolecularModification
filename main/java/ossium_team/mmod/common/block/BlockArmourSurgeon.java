package ossium_team.mmod.common.block;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ossium_team.mmod.common.block.tile.TileArmourSurgeon;

import javax.annotation.Nullable;

public class BlockArmourSurgeon extends BaseBlock implements ITileEntityProvider {

    public BlockArmourSurgeon() {
        super(Material.ROCK, "block_armour_surgeon");
        this.setHarvestLevel("pickaxe", 0);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileArmourSurgeon();
    }
}
