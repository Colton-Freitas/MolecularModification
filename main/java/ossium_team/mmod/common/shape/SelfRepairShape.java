package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import ossium_team.mmod.common.block.ModBlocks;

public class SelfRepairShape implements IMolecularShapeTestable {

    private Block[][][] shape = new Block[9][9][9];

    public SelfRepairShape() {
        shape[4][8][4] = ModBlocks.molecularCraftingTableEnergy;
        shape[4][8][3] = ModBlocks.conductiveIron;
        shape[3][8][4] = ModBlocks.conductiveIron;
        shape[5][8][4] = ModBlocks.conductiveIron;
        shape[4][8][5] = ModBlocks.conductiveIron;

        //Top square
        for (int i = 2; i < 7; i++) {
            shape[i][8][2] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[2][8][i] = ModBlocks.conductiveIron;
        }
        for (int i = 6; i > 1; i--) {
            shape[i][8][6] = ModBlocks.conductiveIron;
        }
        for (int i = 6; i > 1; i--) {
            shape[6][8][i] = ModBlocks.conductiveIron;
        }

        //bottom Square
        for (int i = 2; i < 7; i++) {
            shape[i][0][2] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[2][0][i] = ModBlocks.conductiveIron;
        }
        for (int i = 6; i > 1; i--) {
            shape[i][0][6] = ModBlocks.conductiveIron;
        }
        for (int i = 6; i > 1; i--) {
            shape[6][0][i] = ModBlocks.conductiveIron;
        }

        //Four pillars up
        for (int i = 1; i < 8; i++) {
            shape[2][i][2] = ModBlocks.conductiveIron;
        }
        for (int i = 1; i < 8; i++) {
            shape[2][i][6] = ModBlocks.conductiveIron;
        }
        for (int i = 1; i < 8; i++) {
            shape[6][i][2] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 8; i++) {
            shape[6][i][6] = ModBlocks.conductiveIron;
        }

        //
        for (int i = 2; i < 7; i++) {
            shape[0][i][2] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[0][2][i] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[0][6][i] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[0][i][6] = ModBlocks.conductiveIron;
        }

        for (int i = 2; i < 7; i++) {
            shape[2][i][0] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[i][2][0] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[i][6][0] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[6][i][0] = ModBlocks.conductiveIron;
        }

        for (int i = 2; i < 7; i++) {
            shape[2][i][8] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[i][2][8] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[6][i][8] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[i][6][8] = ModBlocks.conductiveIron;
        }

        for (int i = 2; i < 7; i++) {
            shape[8][i][2] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[8][2][i] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[8][6][i] = ModBlocks.conductiveIron;
        }
        for (int i = 2; i < 7; i++) {
            shape[8][i][6] = ModBlocks.conductiveIron;
        }

        for (int i = 0; i < 9; i++) {
            shape[2][2][i] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[2][6][i] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[6][2][i] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[6][6][i] = ModBlocks.conductiveIron;
        }

        for (int i = 0; i < 9; i++) {
            shape[i][2][2] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[i][6][2] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[i][2][6] = ModBlocks.conductiveIron;
        }
        for (int i = 0; i < 9; i++) {
            shape[i][6][6] = ModBlocks.conductiveIron;
        }



        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if(shape[i][j][k] == null)
                        shape[i][j][k] = Blocks.AIR;
                }
            }
        }
    }

    @Override
    public int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if(shape[i][j][k].equals(Blocks.AIR))
                        continue;
                    if (!shape[i][j][k].equals(blocks[i][j][k]))
                        return new int[7];
                }
            }
        }

        return new int[] {0, 0, 0, 0, 0, 0, 1};
    }

    public Block[][][] getShape() {
        return shape;
    }
}
