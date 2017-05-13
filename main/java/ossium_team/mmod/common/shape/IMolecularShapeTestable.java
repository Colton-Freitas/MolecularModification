package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface IMolecularShapeTestable {

    int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair);


}
