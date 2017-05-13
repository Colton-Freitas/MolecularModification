package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class FortuneShape implements IMolecularShapeTestable{

    private Block[][] shape;

    public FortuneShape() {
        shape = new Block[3][3];

        shape[0][0] = Blocks.LAPIS_BLOCK;
        shape[0][2] = Blocks.LAPIS_BLOCK;
        shape[2][0] = Blocks.LAPIS_BLOCK;
        shape[2][2] = Blocks.LAPIS_BLOCK;
        shape[0][1] = Blocks.DIAMOND_BLOCK;
        shape[1][0] = Blocks.DIAMOND_BLOCK;
        shape[1][2] = Blocks.DIAMOND_BLOCK;
        shape[2][1] = Blocks.DIAMOND_BLOCK;
        shape[1][1] = Blocks.EMERALD_BLOCK;
    }

    @Override
    public int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            Block[][] current = new Block[3][3];
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    current[j][k] = blocks[i][3 + j][3 + k];
                }
            if (areEqual(shape, current))
                count++;
        }

        for (int i = 0; i < 9; i++) {
            Block[][] current = new Block[3][3];
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    current[j][k] = blocks[3 + k][3 + j][i];
                }
            }
            if (areEqual(shape, current))
                count++;
        }
        return new int[] {0, 0, 0, 0, 0, count > 3 ? 3 : count, 0};
    }

    private boolean areEqual(Block[][] initialShape, Block[][] blocks) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!initialShape[i][j].equals(blocks[i][j]))
                    return false;
            }
        }
        return true;
    }

    public Block[][] getShape() {
        return shape;
    }
}
