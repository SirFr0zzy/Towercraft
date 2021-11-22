package de.sirfrozzy.multiworldsystem.command;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import de.sirfrozzy.multiworldsystem.utils.InventoryManager;
import de.sirfrozzy.multiworldsystem.utils.MultiWorld;
import de.sirfrozzy.multiworldsystem.utils.WorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class World_Command implements CommandExecutor {

    private final MultiWorldSystem plugin = MultiWorldSystem.getInstance();
    private final WorldHandler worldHandler = MultiWorldSystem.getWorldHandler();
    private final File worldFile = new File(MultiWorldSystem.getInstance().getDataFolder(), "worlds.yml");

    private void noPermission(Player player) {
        player.sendMessage("§4Dazu hast du keine Rechte!");
    }

    private void sendUsage(Player player) {

        if (!player.hasPermission("mvs.seeUsage")) {
            noPermission(player);
            return;
        }
        player.sendMessage("§7========§6Befehle§7========");
        player.sendMessage("§a/mvs list");
        player.sendMessage("§a/mvs setSpawn");
        player.sendMessage("§a/mvs info (name)");
        player.sendMessage("§a/mvs create (name) (type)");
        player.sendMessage("§a/mvs delete (name)");
        player.sendMessage("§a/mvs import (name)");
        player.sendMessage("§a/mvs tp (name)");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        if (args.length == 0) {
            if(!player.hasPermission("mvs.gui")) {
                noPermission(player);
                return false;
            }
            player.openInventory(InventoryManager.getGameRuleInventory(player));
            return false;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {


                if (!player.hasPermission("mvs.list")) {
                    noPermission(player);
                    return false;
                }

                player.sendMessage("§7========§cWelten§7========");
                for (MultiWorld world : worldHandler.getWorldList()) {
                    if (world.isLoaded())
                        player.sendMessage("§a" + world.getName());
                    else
                        player.sendMessage("§c" + world.getName());
                }
                player.sendMessage("§7========§cWelten§7========");

            } else if (args[0].equalsIgnoreCase("setSpawn")) {
                if (!player.hasPermission("mvs.setSpawn")) {
                    noPermission(player);
                    return false;
                }
                worldHandler.setSpawnLocation(player.getLocation());
                player.sendMessage("§aSpawn Position wurde auf §bX:" + player.getLocation().getBlockX() + " Y:" + player.getLocation().getBlockY() + " Z:" + player.getLocation().getBlockZ() + " §agesetzt!");
            } else if (args[0].equalsIgnoreCase("info")) {
                if (!player.hasPermission("mvs.info")) {
                    noPermission(player);
                    return false;
                }
                String worldName = player.getWorld().getName();

                player.sendMessage("§7==========§6Info§7==========");
                player.sendMessage("§eID: §f" + cfg.getString(worldName + ".id"));
                player.sendMessage("§eName: §f" + cfg.getString(worldName + ".name"));
                player.sendMessage("§eErsteller: §f" + cfg.getString(worldName + ".creator"));
                player.sendMessage("§eTyp: §f" + cfg.getString(worldName + ".type"));
                player.sendMessage("§eDatum: §f" + cfg.getString(worldName + ".date"));
                player.sendMessage("§eSpawn: §fX:" + cfg.getString(worldName + ".spawn.SpawnX") + " Y:" + cfg.getString(worldName + ".spawn.SpawnY") + " Z:" + cfg.getString(worldName + ".spawn.SpawnZ"));
                player.sendMessage("§7==========§6Info§7==========");


            } else
                sendUsage(player);

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("info")) {
                if (!player.hasPermission("mvs.info")) {
                    noPermission(player);
                    return false;
                }
                String worldName = args[1];
                boolean exists = false;

                for (MultiWorld world : worldHandler.getWorldList()) {
                    if (Objects.equals(worldName, world.getName())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    player.sendMessage("§cDiese Welt existiert nicht!");
                    return false;
                }

                player.sendMessage("§7==========§6Info§7==========");
                player.sendMessage("§eID: §f" + cfg.getString(worldName + ".id"));
                player.sendMessage("§eName: §f" + cfg.getString(worldName + ".name"));
                player.sendMessage("§eErsteller: §f" + cfg.getString(worldName + ".creator"));
                player.sendMessage("§eTyp: §f" + cfg.getString(worldName + ".type"));
                player.sendMessage("§eDatum: §f" + cfg.getString(worldName + ".date"));
                player.sendMessage("§eSpawn: §fX:" + cfg.getString(worldName + ".spawn.SpawnX") + " Y:" + cfg.getString(worldName + ".spawn.SpawnY") + " Z:" + cfg.getString(worldName + ".spawn.SpawnZ"));
                player.sendMessage("§7==========§6Info§7==========");


            } else if (args[0].equalsIgnoreCase("tp")) {
                if (!player.hasPermission("mvs.teleport")) {
                    noPermission(player);
                    return false;
                }
                World w = Bukkit.getWorld(args[1]);
                if (w == null) {
                    player.sendMessage("§cDiese Welt existiert nicht!");
                    return false;
                }
                player.teleport(worldHandler.getSpawnLocation(args[1]));
            } else if (args[0].equalsIgnoreCase("delete")) {

                boolean b1 = player.hasPermission("mvs.delete.all");
                boolean b2 = player.hasPermission("mvs.delete." + args[1]);

                if (!(b1 || b2 || (b1 && b2))) {
                    noPermission(player);
                    return false;
                }

                MultiWorld world = worldHandler.getWorld(args[1]);

                if (world == worldHandler.getWorld(player.getWorld().getName())) {
                    player.sendMessage("§cDu kannst nicht die Welt löschen in der du dich befindest!");
                    return false;
                }

                if (world == null) {
                    player.sendMessage("§cDiese Welt existiert nicht!");
                    return false;
                }

                worldHandler.deleteWorld(world);
                player.sendMessage("§aDie Welt '" + args[1] + "' wurde gelöscht!");
            } else if(args[0].equalsIgnoreCase("import")) {

                if (!player.hasPermission("mvs.import")) {
                    noPermission(player);
                    return false;
                }

                if (!(new File(System.getProperty("user.dir") + "/" + args[1])).exists()) {
                    player.sendMessage("§cDiese Welt existiert nicht!");
                    return false;
                }
                player.sendMessage("§7Importiere die Welt '" + args[1] + "' ...");
                String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
                String name = args[1];
                String uuid = UUID.randomUUID().toString();
                Bukkit.createWorld(new WorldCreator(args[1]));
                World bukkitWorld = Bukkit.getWorld(args[1]);
                MultiWorld world = new MultiWorld(uuid, player.getName(), args[1], "none", date, bukkitWorld.getSpawnLocation(), bukkitWorld != null);

                cfg.set(name + ".id", uuid.toString());
                cfg.set(name + ".creator", player.getName());
                cfg.set(name + ".name", name);
                cfg.set(name + ".type", "none");
                cfg.set(name + ".date", date);
                cfg.set(name + ".spawn.SpawnX", bukkitWorld.getSpawnLocation().getX());
                cfg.set(name + ".spawn.SpawnY", bukkitWorld.getSpawnLocation().getY());
                cfg.set(name + ".spawn.SpawnZ", bukkitWorld.getSpawnLocation().getZ());
                cfg.set(name + ".load", Bukkit.getWorld(name) != null);
                try {
                    cfg.save(worldFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage("§aFertig!");

            } else
                sendUsage(player);
        } else if (args.length == 3) {
            if (!args[0].equalsIgnoreCase("create")) {
                sendUsage(player);
                return false;
            }
            if (!player.hasPermission("mvs.create")) {
                noPermission(player);
                return false;
            }
            String type = args[2];
            String name = args[1];

            type = type.toLowerCase();
            if (!(type.equals("flat") || type.equals("water") || type.equals("void") || type.equals("normal") || type.equals("amplified") || type.equals("nether")  || type.equals("end"))) {
                player.sendMessage("§cUngültiger Welt Typ! Nutze: flat, water, void, normal, amplified, nether, end");
                return false;
            }
            if (cfg.getStringList("worlds").contains(name)) {
                player.sendMessage("§cDiese Welt existiert bereits!");
                return false;
            }
            player.sendMessage("§7Welt wird erstellt...");
            worldHandler.createWorld(args[1], player.getName(), args[2]);
            player.sendMessage("§aFeritg!");
        }

        return false;
    }

}
