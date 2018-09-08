package me.askingg.spawners;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.golems.Main;

public class EmeraldGolem implements Listener {

	Random random = new Random();

	@EventHandler
	public void emeraldPlace(BlockPlaceEvent event) {

		Player player = (Player) event.getPlayer();
		Block block = event.getBlock();
		Location location = block.getLocation();
		String world = block.getWorld().getName().toString();
		File locationFile = new File("plugins/Golems/Locations", world + " - " + location.getBlockX() + "-"
				+ location.getBlockY() + "-" + location.getBlockZ() + ".yml");

		if (block.getType().equals(Material.SPAWNER)) {
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
					.equals(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"))) {

				if (!(locationFile.exists())) {
					try {

						locationFile.createNewFile();
						FileConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationFile);
						locationConfig.set("Spawner Type", "Emerald");
						locationConfig.save(locationFile);
						Bukkit.getConsoleSender()
								.sendMessage(Main.colorCodes(Main.prefix
										+ "&aSuccessfully&f created a new &aEmerald Golem Spawner&f location &8(&a" + world
										+ " &8-&a " + location.getBlockX() + "&8-&a" + location.getBlockY() + "&8-&a"
										+ location.getBlockZ() + "&8)"));

					} catch (IOException e) {
					}
				}

				if (locationFile.exists()) {

					CreatureSpawner spawner = (CreatureSpawner) block.getState();

					spawner.setSpawnedType(EntityType.IRON_GOLEM);
					spawner.update();

				}

			}
		}
	}

	@EventHandler
	public void emeraldBreak(BlockBreakEvent event) {

		Block block = event.getBlock();
		Location location = block.getLocation();
		String world = block.getWorld().getName().toString();
		File locationFile = new File("plugins/Golems/Locations", world + " - " + location.getBlockX() + "-"
				+ location.getBlockY() + "-" + location.getBlockZ() + ".yml");
		FileConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationFile);

		if (block.getType().equals(Material.SPAWNER)) {
			if (locationFile.exists()) {
				if (locationConfig.getString("Spawner Type").equals("Emerald")) {

					ItemStack emeraldGolem = new ItemStack(Material.SPAWNER);
					ItemMeta emeraldGolemMeta = emeraldGolem.getItemMeta();
					emeraldGolemMeta.setDisplayName(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"));
					emeraldGolem.setItemMeta(emeraldGolemMeta);
					event.setDropItems(false);
					block.getWorld().dropItem(location, emeraldGolem);
					locationFile.delete();

				}
			}
		}
	}

	@EventHandler
	public void emeraldSpawn(SpawnerSpawnEvent event) {

		CreatureSpawner spawner = (CreatureSpawner) event.getSpawner().getBlock().getState();
		Location location = spawner.getLocation();
		String world = spawner.getWorld().getName().toString();
		File locationFile = new File("plugins/Golems/Locations", world + " - " + location.getBlockX() + "-"
				+ location.getBlockY() + "-" + location.getBlockZ() + ".yml");
		FileConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationFile);

		if (locationFile.exists()) {
			if (locationConfig.getString("Spawner Type").equals("Emerald")) {

				event.getEntity().setCustomName(Main.colorCodes("&aEmerald Golem"));
				spawner.setSpawnedType(EntityType.IRON_GOLEM);
				spawner.update();

			}
		}
	}

	@EventHandler
	public void emeraldDeath(EntityDeathEvent event) {

		Entity mob = event.getEntity();
		Location location = mob.getLocation();

		if (mob.getName().equals(Main.colorCodes("&aEmerald Golem"))) {

			event.getDrops().clear();
			Integer emeraldAmount = random.nextInt(3);
			ItemStack emerald = new ItemStack(Material.EMERALD);
			emerald.setAmount(emeraldAmount + 3);
			event.getEntity().getWorld().dropItem(location, emerald);

		}
	}
}
