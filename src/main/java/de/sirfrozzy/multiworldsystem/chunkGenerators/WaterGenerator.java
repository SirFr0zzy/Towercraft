package de.sirfrozzy.multiworldsystem.chunkGenerators;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class WaterGenerator extends ChunkGenerator {

    MultiWorldSystem instance;

    int max;
    Material fill = Material.LEGACY_WATER;
    Material layer2 = Material.SAND;
    Material layer1 = Material.DIRT;
    Material bottom = Material.BEDROCK;

    public WaterGenerator(String template) {
        this.instance = MultiWorldSystem.getInstance();
        this.max = 18;
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
                    } else if (y <= 5 && y >= 1) {
                        chunk.setBlock(x, y, z, this.layer1);
                    } else if (y <= 8 && y >= 6) {
                        chunk.setBlock(x, y, z, this.layer2);
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
