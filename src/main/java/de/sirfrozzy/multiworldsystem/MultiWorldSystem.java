package de.sirfrozzy.multiworldsystem;

import de.sirfrozzy.multiworldsystem.chunkGenerators.FlatGenerator;
import de.sirfrozzy.multiworldsystem.command.World_Command;
import de.sirfrozzy.multiworldsystem.command.World_Completer;
import de.sirfrozzy.multiworldsystem.events.MainClickEvent;
import de.sirfrozzy.multiworldsystem.events.TimeClickEvent;
import de.sirfrozzy.multiworldsystem.events.WeatherClickEvent;
import de.sirfrozzy.multiworldsystem.utils.WorldHandler;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiWorldSystem extends JavaPlugin {

    private static MultiWorldSystem instance;
    private static WorldHandler worldHandler;

    @Override
    public void onEnable() {
        instance = this;
        worldHandler = new WorldHandler();
        getCommand("multiworldsystem").setExecutor(new World_Command());
        getCommand("multiworldsystem").setTabCompleter(new World_Completer());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MainClickEvent(), this);
        pm.registerEvents(new TimeClickEvent(), this);
        pm.registerEvents(new WeatherClickEvent(), this);

    }

    @Override
    public void onDisable() {

    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new FlatGenerator(id);
    }

    public static MultiWorldSystem getInstance() {
        return instance;
    }
    public static WorldHandler getWorldHandler() {
        return worldHandler;
    }

}
