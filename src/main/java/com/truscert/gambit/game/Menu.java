package com.truscert.gambit.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
    private static Menu instance;
    public static Menu getMenu() {
        if (instance == null) instance = new Menu();
        return instance;
    }

    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 6*9, "§6Menu");
        ItemStack item_bk = new ItemStack(Material.DIAMOND);
        int[] arr = {17, 18, 26, 27, 35, 36};
        for (int i=0; i<=9; i++) {
            inv.setItem(i,item_bk);
        }

        for (int i=44; i<=53; i++) {
            inv.setItem(i,item_bk);
        }

        for (int i : arr) {
            inv.setItem(i,item_bk);
        }

        ItemStack item_button1 = new ItemStack(Material.GOLD_INGOT);
        ItemStack item_button2 = new ItemStack(Material.ANVIL);
        inv.setItem(22,item_button1);
        inv.setItem(31,item_button2);

        player.openInventory(inv);
    }
}
