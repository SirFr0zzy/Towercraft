package de.sirfrozzy.multiworldsystem.chunkGenerators;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class FlatGenerator extends ChunkGenerator {

    MultiWorldSystem instance;

    int max;
    Material surface = Material.GRASS_BLOCK;
    Material fill = Material.DIRT;
    Material bottom = Material.BEDROCK;

    public FlatGenerator(String template) {
        this.instance = MultiWorldSystem.getInstance();
        this.max = 32;
    }

    //ChunkData chunk, int max, Material bottom, Material surface, Material fill
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = 0;
                while (y <= this.max) {
                    if (y == 0) {
                        chunk.setBlock(x, y, z, this.bottom);
                    } else if (y == this.max) {
                        chunk.setBlock(x, y, z, this.surface);
                    } else {
                        chunk.setBlock(x, y, z, this.fill);
                    }
                    y++;
                }
            }
        }
        return chunk;
    }
}
