package com.niter.inventoryio;

import org.bukkit.plugin.java.JavaPlugin;

public final class Inventoryio extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ChestListener(), this);
        System.out.println("[IIO] Successfully running!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("InventoryIO shutdown, server must be restarting!");
    }
}
