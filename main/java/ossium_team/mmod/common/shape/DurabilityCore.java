package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import ossium_team.mmod.common.block.ModBlocks;

import java.util.Arrays;

public class DurabilityCore implements IMolecularShapeTestable {

    private Block[][][] shape = new Block[5][5][5];

    public DurabilityCore() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[i][0][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[i][4][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[0][i][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[4][i][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[i][j][0] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shape[i][j][4] = Blocks.PISTON;
            }
        }


        shape[1][1][1] = ModBlocks.strengthenedIron;
        shape[3][1][1] = ModBlocks.strengthenedIron;
        shape[1][1][3] = ModBlocks.strengthenedIron;
        shape[3][1][3] = ModBlocks.strengthenedIron;
        shape[1][3][1] = ModBlocks.strengthenedIron;
        shape[3][3][1] = ModBlocks.strengthenedIron;
        shape[1][3][3] = ModBlocks.strengthenedIron;
        shape[3][3][3] = ModBlocks.strengthenedIron;
        shape[2][1][2] = Blocks.OBSIDIAN;
        shape[1][2][2] = Blocks.OBSIDIAN;
        shape[2][2][1] = Blocks.OBSIDIAN;
        shape[3][2][2] = Blocks.OBSIDIAN;
        shape[2][2][3] = Blocks.OBSIDIAN;
        shape[2][3][2] = Blocks.OBSIDIAN;
        shape[2][2][2] = ModBlocks.strengthenedDiamond;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(shape[i][j][k] == null)
                        shape[i][j][k] = ModBlocks.strengthenedIron;
                }
            }
        }
    }

    @Override
    public int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair) {
        Block[][][] subShape = new Block[5][5][5];
        for (int i = 2; i < 7; i++) {
            for (int j = 2; j < 7; j++) {
                for (int k = 2; k < 7; k++) {
                    subShape[i - 2][j - 2][k - 2] = blocks[i][j][k];
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(!shape[i][j][k].equals(subShape[i][j][k]))
                        return new int[7];
                }
            }
        }

        return new int[] {(int)(baseHardness * 1.5), 0, 0, 0, 0, 0, 0};
    }

    public Block[][][] getShape() {
        return shape;
    }
}
