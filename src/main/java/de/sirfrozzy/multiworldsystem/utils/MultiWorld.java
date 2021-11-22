package de.sirfrozzy.multiworldsystem.utils;

import org.bukkit.Location;

import java.util.UUID;

public class MultiWorld {



    private final String id;
    private final String creator;
    private final String name;
    private final String type;
    private final String creationDate;
    private Location spawn;
    private Boolean loaded;


    public MultiWorld(String id, String creator, String name, String type, String creationDate, Location spawn, Boolean loaded) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.type = type;
        this.creationDate = creationDate;
        this.spawn = spawn;
        this.loaded = loaded;
    }


    public String getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Boolean isLoaded() {
        return loaded;
    }

    public void setLoad(Boolean loaded) {
        this.loaded = loaded;
    }

    public Location getSpawnpoint() {
        return spawn;
    }

    public void setSpawnpoint(Location spawnpoint) {
        this.spawn = spawnpoint;
    }



}
