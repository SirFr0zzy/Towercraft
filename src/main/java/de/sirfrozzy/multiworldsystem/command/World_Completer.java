package de.sirfrozzy.multiworldsystem.command;

import de.sirfrozzy.multiworldsystem.MultiWorldSystem;
import de.sirfrozzy.multiworldsystem.utils.WorldHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class World_Completer implements TabCompleter {

    private final WorldHandler worldHandler = MultiWorldSystem.getWorldHandler();
    private final File worldFile = new File(MultiWorldSystem.getInstance().getDataFolder(), "worlds.yml");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return new ArrayList<>();
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(worldFile);
        Player player = (Player) sender;
        if (!player.hasPermission("mvs.seeUsage")) {
            return new ArrayList<>();
        }
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("list");
            completions.add("info");
            completions.add("load");
            completions.add("tp");
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("info")) {
                return cfg.getStringList("worlds");
            }
        }

        return completions;
    }


}
