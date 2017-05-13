package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class DurabilityCoreUltima implements IMolecularShapeTestable {

    Block[][][] shape = new Block[9][9][9];

    public  DurabilityCoreUltima() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[i][0][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[i][8][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[0][i][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[8][i][j] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[i][j][0] = Blocks.PISTON;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                shape[i][j][8] = Blocks.PISTON;
            }
        }

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[i][1][j] = Blocks.OBSIDIAN;
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[i][7][j] = Blocks.OBSIDIAN;
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[1][i][j] = Blocks.OBSIDIAN;
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[7][i][j] = Blocks.OBSIDIAN;
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[i][j][1] = Blocks.OBSIDIAN;
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                shape[i][j][7] = Blocks.OBSIDIAN;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (shape[i][j][k] == null)
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
                    if(shape[i][j][k] == null)
                        continue;
                    if(!shape[i][j][k].equals(blocks[i][j][k]))
                        return new int[7];
                }
            }
        }
        return new int[] {baseHardness * 6, 0, -18, 0, 0, 0, 0};
    }

    public Block[][][] getShape() {
        return shape;
    }
}
