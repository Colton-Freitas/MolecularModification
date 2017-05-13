package ossium_team.mmod.common.dimension.chunkGenerator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MolecularCraftingChunkGenerator implements IChunkGenerator {

    private final World worldObj;
    private Random rand;
    private Biome[] biomesForGeneration;

    private List<Biome.SpawnListEntry> mobs = Lists.newArrayList();

    public MolecularCraftingChunkGenerator(World world) {
        this.worldObj = world;
        this.rand = new Random(world.getSeed());

    }


    @Nullable
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
        return null;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return ImmutableList.of();
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public Chunk provideChunk(int x, int z) {
        return null;
    }
}
