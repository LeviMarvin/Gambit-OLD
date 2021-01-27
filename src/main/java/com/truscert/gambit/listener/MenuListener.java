package com.truscert.gambit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getWhoClicked ().getOpenInventory().getTitle().equals("Menu"))
            //To limit player get items in menu.
            e.setCancelled(true);

        if(e.getRawSlot()<0 || e.getRawSlot()>e.getInventory().getSize() || e.getInventory()==null)
            return;

        Player p = (Player)e.getWhoClicked();

        if(e.getRawSlot()==22){
            p.sendMessage("你点击了金锭!");
            p.closeInventory();
        } else {
            p.sendMessage("你没有点击金锭!");
            p.closeInventory();
        }
    }
}
