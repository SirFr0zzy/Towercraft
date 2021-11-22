package de.sirfrozzy.multiworldsystem.events;

import de.sirfrozzy.multiworldsystem.utils.InventoryManager;
import de.sirfrozzy.multiworldsystem.utils.ItemBuilder;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainClickEvent implements Listener {

    @EventHandler
    public void handle(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§6§lWorldSettings§4§5§b§d§f§0§e")) {
            event.setCancelled(true);
            World world = player.getWorld();
            switch (event.getSlot()) {
                case 10:
                    if (world.getGameRuleValue(GameRule.DO_MOB_SPAWNING))
                        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    else
                        world.setGameRule(GameRule.DO_MOB_SPAWNING, true);

                    ItemStack mobSpawning = new ItemBuilder(Material.CHICKEN_SPAWN_EGG, "§f§lMob Spawning", 1, world.getGameRuleValue(GameRule.DO_MOB_SPAWNING)).getItem();
                    inventory.setItem(10, mobSpawning);
                    break;
                case 11:
                    if (world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE))
                        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    else
                        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

                    ItemStack daylightCycle = new ItemBuilder(Material.DAYLIGHT_DETECTOR, "§f§lDaylight Cycle", 1, world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)).getItem();
                    inventory.setItem(11, daylightCycle);
                    break;
                case 12:
                    if (world.getGameRuleValue(GameRule.DO_FIRE_TICK))
                        world.setGameRule(GameRule.DO_FIRE_TICK, false);
                    else
                        world.setGameRule(GameRule.DO_FIRE_TICK, true);

                    ItemStack fireTick = new ItemBuilder(Material.FLINT_AND_STEEL, "§f§lFire Tick", 1, world.getGameRuleValue(GameRule.DO_FIRE_TICK)).getItem();
                    inventory.setItem(12, fireTick);
                    break;
                case 13:
                    if (world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE))
                        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                    else
                        world.setGameRule(GameRule.DO_WEATHER_CYCLE, true);

                    ItemStack weatherCycle = new ItemBuilder(Material.WATER_BUCKET, "§f§lWeather Cycle", 1, world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE)).getItem();
                    inventory.setItem(13, weatherCycle);
                    break;
                case 14:
                    if (world.getGameRuleValue(GameRule.MOB_GRIEFING))
                        world.setGameRule(GameRule.MOB_GRIEFING, false);
                    else
                        world.setGameRule(GameRule.MOB_GRIEFING, true);

                    ItemStack mobGriefing = new ItemBuilder(Material.TNT, "§f§fMob Griefing", 1, world.getGameRuleValue(GameRule.MOB_GRIEFING)).getItem();
                    inventory.setItem(14, mobGriefing);
                    break;
                case 15:
                    player.openInventory(InventoryManager.getTimeInventory());
                    break;
                case 16:
                    player.openInventory(InventoryManager.getWeatherInventory(player));
                    break;
                default:
            }
            return;
        }

    }

}
