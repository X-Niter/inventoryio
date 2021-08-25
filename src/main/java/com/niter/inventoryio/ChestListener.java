package com.niter.inventoryio;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.sound.SoundStop;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChestListener implements Listener {

    private static List<String> inChest = new ArrayList<String>();


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final HumanEntity p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            assert block != null;
            if (block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST)) {
                if (inChest.isEmpty()) {
                    inChest.add(p.getName());
                    System.out.println("[IIO DEBUG] " + inChest.toString() + " Opened & added to chest");
                    System.out.println("[IIO DEBUG] User inChest list of 0 " + inChest.get(0));
                    if (inChest.size() > 1) {
                        inChest.subList(1, inChest.size()).clear();
                    }

                /*if (inChest.get(0).toString().contains(e.getPlayer().getName().toString())) {
                    System.out.println("[IIO DEBUG] " + inChest.get(0) + ", " + e.getPlayer().getName());
                    Chest chest = (Chest) block.getState();
                    p.openInventory(chest.getInventory());
                } else
                    e.setCancelled(true);
                    System.out.println("[IIO DEBUG] " + e.getPlayer().getName() + " tried to open active chest");
                    e.getPlayer().sendMessage(inChest.toString() + " Is currently in chest, please wait!");*/
                }
                if (!inChest.get(0).contains(e.getPlayer().getName()) || !inChest.get(0).equals(e.getPlayer().getName())) {
                    if (inChest.size() > 1) {
                        inChest.subList(1, inChest.size()).clear();
                    }
                    e.setCancelled(true);
                    System.out.println("[IIO DEBUG] " + e.getPlayer().getName() + " tried to open active chest");
                    e.getPlayer().sendMessage(inChest.toString() + " Is currently in chest, please wait!");
                }
            }
        }
    }


    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();

        if (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest) {
            System.out.println("[IIO DEBUG]" + " Users stored for chest is currently -> " + inChest.toString());
            System.out.println("[IIO DEBUG]" + " Clearing the following users -> " + inChest.toString());
            inChest.clear();
            System.out.println("[IIO DEBUG] " +  e.getPlayer().getName() + " closed a chest");
            System.out.println("[IIO DEBUG]" + " Users left stored is currently -> " + inChest.toString());
        }

    }
}

