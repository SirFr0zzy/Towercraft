package de.sirfrozzy.multiworldsystem.chunkGenerators;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        return createChunkData(world);
    }

}