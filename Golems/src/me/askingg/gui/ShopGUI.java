package me.askingg.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.golems.Main;
import net.milkbowl.vault.economy.EconomyResponse;

public class ShopGUI implements Listener {

	public static List<Player> shopping = new ArrayList<>();

	public static Inventory shop(Player player) {

		ItemStack coal = new ItemStack(Material.SPAWNER);
		ItemMeta coalMeta = coal.getItemMeta();
		coalMeta.setDisplayName(Main.colorCodes("&3&l» &8Coal Golem Spawner &3&l«"));
		List<String> coalLore = new ArrayList<>();
		coalLore.add(Main.colorCodes("&fPrice &3&l» &a$10"));
		coalMeta.setLore(coalLore);
		coal.setItemMeta(coalMeta);
		ItemStack iron = new ItemStack(Material.SPAWNER);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName(Main.colorCodes("&3&l» &7Iron Golem Spawner &3&l«"));
		List<String> ironLore = new ArrayList<>();
		ironLore.add(Main.colorCodes("&fPrice &3&l» &a$20"));
		ironMeta.setLore(ironLore);
		iron.setItemMeta(ironMeta);
		ItemStack gold = new ItemStack(Material.SPAWNER);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName(Main.colorCodes("&3&l» &6Gold Golem Spawner &3&l«"));
		List<String> goldLore = new ArrayList<>();
		goldLore.add(Main.colorCodes("&fPrice &3&l» &a$30"));
		goldMeta.setLore(goldLore);
		gold.setItemMeta(goldMeta);
		ItemStack diamond = new ItemStack(Material.SPAWNER);
		ItemMeta diamondMeta = diamond.getItemMeta();
		diamondMeta.setDisplayName(Main.colorCodes("&3&l» &bDiamond Golem Spawner &3&l«"));
		List<String> diamondLore = new ArrayList<>();
		diamondLore.add(Main.colorCodes("&fPrice &3&l» &a$40"));
		diamondMeta.setLore(diamondLore);
		diamond.setItemMeta(diamondMeta);
		ItemStack emerald = new ItemStack(Material.SPAWNER);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"));
		List<String> emeraldLore = new ArrayList<>();
		emeraldLore.add(Main.colorCodes("&fPrice &3&l» &a$50"));
		emeraldMeta.setLore(emeraldLore);
		emerald.setItemMeta(emeraldMeta);

		Inventory shop = Bukkit.createInventory(null, 27, Main.colorCodes("Golem Spawners Shop"));

		shop.setItem(10, coal);
		shop.setItem(11, iron);
		shop.setItem(12, gold);
		shop.setItem(13, diamond);
		shop.setItem(14, emerald);

		return shop;

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void shopClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (shopping.contains(player)) {

			event.setCancelled(true);
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.colorCodes("&3&l» &8Coal Golem Spawner &3&l«"))) {
				EconomyResponse r = Main.getEconomy().withdrawPlayer(player.getName(), 10);
				if (r.transactionSuccess()) {
					ItemStack coalGolem = new ItemStack(Material.SPAWNER);
					ItemMeta coalGolemMeta = coalGolem.getItemMeta();
					coalGolemMeta.setDisplayName(Main.colorCodes("&3&l» &8Coal Golem Spawner &3&l«"));
					coalGolem.setItemMeta(coalGolemMeta);

					player.getInventory().addItem(coalGolem);
					player.sendMessage(Main.colorCodes(Main.prefix + "&fPurchased a &8Coal Golem Spawner"));
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.colorCodes("&3&l» &7Iron Golem Spawner &3&l«"))) {
				EconomyResponse r = Main.getEconomy().withdrawPlayer(player.getName(), 20);
				if (r.transactionSuccess()) {
					ItemStack ironGolem = new ItemStack(Material.SPAWNER);
					ItemMeta ironGolemMeta = ironGolem.getItemMeta();
					ironGolemMeta.setDisplayName(Main.colorCodes("&3&l» &7Iron Golem Spawner &3&l«"));
					ironGolem.setItemMeta(ironGolemMeta);

					player.getInventory().addItem(ironGolem);
					player.sendMessage(Main.colorCodes(Main.prefix + "&fPurchased a &7Iron Golem Spawner"));
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.colorCodes("&3&l» &6Gold Golem Spawner &3&l«"))) {
				EconomyResponse r = Main.getEconomy().withdrawPlayer(player.getName(), 30);
				if (r.transactionSuccess()) {
					ItemStack goldGolem = new ItemStack(Material.SPAWNER);
					ItemMeta goldGolemMeta = goldGolem.getItemMeta();
					goldGolemMeta.setDisplayName(Main.colorCodes("&3&l» &6Gold Golem Spawner &3&l«"));
					goldGolem.setItemMeta(goldGolemMeta);

					player.getInventory().addItem(goldGolem);
					player.sendMessage(Main.colorCodes(Main.prefix + "&fPurchased a &6Gold Golem Spawner"));
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.colorCodes("&3&l» &bDiamond Golem Spawner &3&l«"))) {
				EconomyResponse r = Main.getEconomy().withdrawPlayer(player.getName(), 40);
				if (r.transactionSuccess()) {
					ItemStack diamondGolem = new ItemStack(Material.SPAWNER);
					ItemMeta diamondGolemMeta = diamondGolem.getItemMeta();
					diamondGolemMeta.setDisplayName(Main.colorCodes("&3&l» &bDiamond Golem Spawner &3&l«"));
					diamondGolem.setItemMeta(diamondGolemMeta);

					player.getInventory().addItem(diamondGolem);
					player.sendMessage(Main.colorCodes(Main.prefix + "&fPurchased a &bDiamond Golem Spawner"));
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"))) {
				EconomyResponse r = Main.getEconomy().withdrawPlayer(player.getName(), 50);
				if (r.transactionSuccess()) {
					ItemStack emeraldGolem = new ItemStack(Material.SPAWNER);
					ItemMeta emeraldGolemMeta = emeraldGolem.getItemMeta();
					emeraldGolemMeta.setDisplayName(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"));
					emeraldGolem.setItemMeta(emeraldGolemMeta);

					player.getInventory().addItem(emeraldGolem);
					player.sendMessage(Main.colorCodes(Main.prefix + "&fPurchased a &aEmerald Golem Spawner"));
				}
			}
		}
	}

	@EventHandler
	public void shopClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if (shopping.contains(player)) {

			shopping.remove(player);

		}
	}
}
