package de.sirfrozzy.multiworldsystem.utils;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import de.sirfrozzy.multiworldsystem.chunkGenerators.FlatGenerator;
import de.sirfrozzy.multiworldsystem.chunkGenerators.VoidGenerator;
import de.sirfrozzy.multiworldsystem.chunkGenerators.WaterGenerator;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorldHandler {

    private final List<MultiWorld> worldList;
    private final File worldFile = new File(MultiWorldSystem.getInstance().getDataFolder(), "worlds.yml");

    public List<MultiWorld> getWorldList() {
        return worldList;
    }

    public WorldHandler() {
        this.worldList = new ArrayList<>();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        if (!cfg.isSet("worlds")) {
            List<String> list = new ArrayList<>();
            cfg.set("worlds", list);
            try {
                cfg.save(worldFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!cfg.isSet("world")) {
            UUID uuid = UUID.randomUUID();

            List<String> worlds = cfg.getStringList("worlds");
            if (!worlds.contains("world"))
                worlds.add("world");
            cfg.set("worlds", worlds);

            World world = Bukkit.getWorld("world");

            cfg.set("world.id", uuid.toString());
            cfg.set("world.creator", "server");
            cfg.set("world.name", "world");
            cfg.set("world.type", "normal");
            cfg.set("world.date", "none");
            cfg.set("world.spawn.SpawnX", world.getSpawnLocation().getX());
            cfg.set("world.spawn.SpawnY", world.getSpawnLocation().getY());
            cfg.set("world.spawn.SpawnZ", world.getSpawnLocation().getZ());
            cfg.set("world.load", Bukkit.getWorld("world") != null);
            try {
                cfg.save(worldFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            worldList.add(new MultiWorld(uuid.toString(), "server", "world", "normal", "none", world.getSpawnLocation(), Bukkit.getWorld("world") != null));
        }

        if (!cfg.isSet("world_nether") && Bukkit.getWorld("world_nether") != null) {
            UUID uuid = UUID.randomUUID();

            List<String> worlds = cfg.getStringList("worlds");
            if (!worlds.contains("world_nether"))
                worlds.add("world_nether");
            cfg.set("worlds", worlds);
            World world = Bukkit.getWorld("world_nether");

            cfg.set("world_nether.id", uuid.toString());
            cfg.set("world_nether.creator", "server");
            cfg.set("world_nether.name", "world_nether");
            cfg.set("world_nether.type", "nether");
            cfg.set("world_nether.spawn.SpawnX", world.getSpawnLocation().getX());
            cfg.set("world_nether.spawn.SpawnY", world.getSpawnLocation().getY());
            cfg.set("world_nether.spawn.SpawnZ", world.getSpawnLocation().getZ());
            cfg.set("world_nether.load", Bukkit.getWorld("world_nether") != null);
            try {
                cfg.save(worldFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            worldList.add(new MultiWorld(uuid.toString(), "server", "world_nether", "nether", "none", world.getSpawnLocation(), Bukkit.getWorld("world_nether") != null));
        }

        if (!cfg.isSet("world_the_end") && Bukkit.getWorld("world_the_end") != null) {
            UUID uuid = UUID.randomUUID();

            List<String> worlds = cfg.getStringList("worlds");
            if (!worlds.contains("world_the_end"))
                worlds.add("world_the_end");
            cfg.set("worlds", worlds);

            World world = Bukkit.getWorld("world_the_end");

            cfg.set("world_the_end.id", uuid.toString());
            cfg.set("world_the_end.creator", "server");
            cfg.set("world_the_end.name", "world_the_end");
            cfg.set("world_the_end.type", "end");
            cfg.set("world_the_end.date", "none");
            cfg.set("world_the_end.spawn.SpawnX", world.getSpawnLocation().getX());
            cfg.set("world_the_end.spawn.SpawnY", world.getSpawnLocation().getY());
            cfg.set("world_the_end.spawn.SpawnZ", world.getSpawnLocation().getZ());
            cfg.set("world_the_end.load", Bukkit.getWorld("world_the_end") != null);
            try {
                cfg.save(worldFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            worldList.add(new MultiWorld(uuid.toString(), "server", "world_the_end", "end", "none", world.getSpawnLocation(), Bukkit.getWorld("world_the_end") != null));
        }



        for (String worlds : cfg.getStringList("worlds")) {
            Location location = new Location(Bukkit.getWorld(worlds), cfg.getInt(worlds + "spawn.SpawnX"), cfg.getInt(worlds + ".spawn.SpawnX"), cfg.getInt(worlds + ".spawn.SpawnX"));
            MultiWorld world = new MultiWorld(cfg.getString(worlds + ".uuid"), cfg.getString(worlds + ".creator"), cfg.getString(worlds + ".name"), cfg.getString(worlds + ".type"), cfg.getString(worlds + ".date"), location, cfg.getBoolean(worlds + ".load"));
            worldList.add(world);
            Bukkit.createWorld(new WorldCreator(worlds));
        }
    }

    public Location getSpawnLocation(String name) {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        World world = Bukkit.getWorld(name);
        int x = cfg.getInt(name + ".spawn.SpawnX");
        int y = cfg.getInt(name + ".spawn.SpawnY");
        int z = cfg.getInt(name + ".spawn.SpawnZ");
        return new Location(world, x, y, z);
    }

    public void setSpawnLocation(Location location) {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);

        cfg.set(location.getWorld().getName() + ".spawn.SpawnX", location.getBlockX());
        cfg.set(location.getWorld().getName() + ".spawn.SpawnY", location.getBlockY());
        cfg.set(location.getWorld().getName() + ".spawn.SpawnZ", location.getBlockZ());

        try {
            cfg.save(worldFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createWorld(String name, String creator, String type) {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        UUID uuid = UUID.randomUUID();
        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());

        List<String> worlds = cfg.getStringList("worlds");
        worlds.add(name);
        cfg.set("worlds", worlds);

        WorldCreator wc = new WorldCreator(name);
        switch (type) {
            case "flat":
                wc.generator(new FlatGenerator("flat"));
                wc.type(WorldType.FLAT);
                break;
            case "water":
                wc.generator(new WaterGenerator("water"));
                break;
            case "void":
                wc.generator(new VoidGenerator());
                break;
            case "normal":
                wc.type(WorldType.NORMAL);
                break;
            case "amplified":
                wc.type(WorldType.AMPLIFIED);
                break;
            case "nether":
                wc.environment(World.Environment.NETHER);
                break;
            case "end":
                wc.environment(World.Environment.THE_END);
        }

        World world = wc.createWorld();
        assert world != null;
        world.setTime(6000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setDifficulty(Difficulty.EASY);

        cfg.set(name + ".id", uuid.toString());
        cfg.set(name + ".creator", creator);
        cfg.set(name + ".name", name);
        cfg.set(name + ".type", type);
        cfg.set(name + ".date", date);
        cfg.set(name + ".spawn.SpawnX", world.getSpawnLocation().getX());
        cfg.set(name + ".spawn.SpawnY", world.getSpawnLocation().getY());
        cfg.set(name + ".spawn.SpawnZ", world.getSpawnLocation().getZ());
        cfg.set(name + ".load", Bukkit.getWorld(name) != null);
        try {
            cfg.save(worldFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        worldList.add(new MultiWorld(uuid.toString(), creator, name, type, name, world.getSpawnLocation(), Bukkit.getWorld(name) != null));
    }

    public void loadWorld(String name) {
        Bukkit.createWorld(new WorldCreator(name));
    }

    public void unload(String name) {
        worldList.remove(getWorld(name));
        Bukkit.unloadWorld(name, true);
    }

    public void deleteWorld(MultiWorld world) {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        String name = world.getName();
        Bukkit.unloadWorld(name, false);
        List<String> worlds = cfg.getStringList("worlds");
        worlds.remove(world.getName());
        cfg.set("worlds", worlds);
        cfg.set("tata", null);
        worldList.remove(world);
        deleteFolder(new File(name));
        try {
            cfg.save(worldFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultiWorld getWorld(String name) {
        for (MultiWorld world : worldList) {
            if (world.getName().equalsIgnoreCase(name))
                return world;
        }
        return null;
    }

    private boolean deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory())
                    deleteFolder(file);
                else
                    file.delete();
            }
        }
        folder.delete();
        return folder.delete();
    }

}
