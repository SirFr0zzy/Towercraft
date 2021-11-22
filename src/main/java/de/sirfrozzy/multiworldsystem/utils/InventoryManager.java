package de.sirfrozzy.multiworldsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public static Inventory getGameRuleInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§6§lWorldSettings§4§5§b§d§f§0§e");
        World world = player.getWorld();

        ItemStack placeholder = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").getItem();
        for (int slots = 0; slots < 27; slots ++) {
            inventory.setItem(slots, placeholder);
        }

        ItemStack mobSpawning = new ItemBuilder(Material.CHICKEN_SPAWN_EGG, "§f§lMob Spawning", 1, world.getGameRuleValue(GameRule.DO_MOB_SPAWNING)).getItem();
        ItemStack daylightCicle = new ItemBuilder(Material.DAYLIGHT_DETECTOR, "§f§lDaylight Cycle", 1, world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)).getItem();
        ItemStack fireTick = new ItemBuilder(Material.FLINT_AND_STEEL, "§f§lFire Tick", 1, world.getGameRuleValue(GameRule.DO_FIRE_TICK)).getItem();
        ItemStack weatherCycle = new ItemBuilder(Material.WATER_BUCKET, "§f§lWeather Cycle", 1, world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE)).getItem();
        ItemStack mobgriefing = new ItemBuilder(Material.TNT, "§f§lMob Grief", 1, world.getGameRuleValue(GameRule.MOB_GRIEFING)).getItem();
        ItemStack time = new ItemBuilder(Material.CLOCK, "§f§lTime", 1, false).getItem();
        ItemStack weather = new ItemBuilder(Material.LIGHTNING_ROD, "§f§lWeather", 1, false).getItem();

        inventory.setItem(10, mobSpawning);
        inventory.setItem(11, daylightCicle);
        inventory.setItem(12, fireTick);
        inventory.setItem(13, weatherCycle);
        inventory.setItem(14, mobgriefing);
        inventory.setItem(15, time);
        inventory.setItem(16, weather);

         return inventory;
    }

    public static Inventory getTimeInventory() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lTime§1§2§c§5§b§f§a");
        inventory.setItem(0, new ItemBuilder(Material.ORANGE_CANDLE).setGlowing(false).setName("§6§lSunrise").getItem());
        inventory.setItem(1, new ItemBuilder(Material.RED_CANDLE).setGlowing(false).setName("§6§lDay").getItem());
        inventory.setItem(2, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setGlowing(false).setName(" ").getItem());
        inventory.setItem(3, new ItemBuilder(Material.YELLOW_CANDLE).setGlowing(false).setName("§6§lSunset").getItem());
        inventory.setItem(4, new ItemBuilder(Material.LIGHT_BLUE_CANDLE).setGlowing(false).setName("§6§lMidnight").getItem());
        return inventory;
    }

    public static Inventory getWeatherInventory(Player player) {
        World world = player.getWorld();
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lWeather§1§c§3§4§f§a§8");
        inventory.setItem(0, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").getItem());
        inventory.setItem(1, new ItemBuilder(Material.GREEN_CANDLE).setName("§f§lClear").setGlowing(!world.isThundering() && !world.hasStorm()).getItem());
        inventory.setItem(2, new ItemBuilder(Material.BLUE_CANDLE).setName("§f§lRain").setGlowing(world.hasStorm()).getItem());
        inventory.setItem(3, new ItemBuilder(Material.BROWN_CANDLE).setName("§f§lThunderstorm").setGlowing(world.isThundering()).getItem());
        inventory.setItem(4, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").getItem());
        return inventory;
    }

}
