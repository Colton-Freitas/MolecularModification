package ossium_team.mmod.common.dimension;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import ossium_team.mmod.common.dimension.chunkGenerator.MolecularCraftingChunkGenerator;

public class MolecularCraftingWorldProvider extends WorldProvider {

    @Override
    public String getSaveFolder() {
        return "MolecularCrafting";
    }

    public IChunkGenerator createChunkGenerator() {
        return new MolecularCraftingChunkGenerator(world);
    }

    @Override
    public DimensionType getDimensionType() {
        return null;
    }
}
