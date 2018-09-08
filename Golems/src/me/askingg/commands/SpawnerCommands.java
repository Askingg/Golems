package me.askingg.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.golems.Main;
import me.askingg.gui.ShopGUI;

public class SpawnerCommands implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;

	public SpawnerCommands(Main plugin) {

		this.plugin = plugin;
		plugin.getCommand("golems").setExecutor(this);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length == 0) {

			player.sendMessage(Main.colorCodes("&8(&e/Golems&8) &3&l» &fView the help list"));
			player.sendMessage(Main.colorCodes("&8(&e/Golems Dev&8) &3&l» &fView the developer"));
			player.sendMessage(Main.colorCodes("&8(&e/Golems Shop&8) &3&l» &fOpen the spawner shop"));
			if (player.hasPermission("golems.list"))
				player.sendMessage(Main.colorCodes("&8(&e/Golems List&8) &3&l» &fView the golem types"));
			if (player.hasPermission("golems.give"))
				player.sendMessage(
						Main.colorCodes("&8(&e/Golems Give &6player type amount&8) &3&l» &fGive someone spawner(s)"));

		} else {
			if (args[0].equalsIgnoreCase("dev")) {
				player.sendMessage(Main.colorCodes(Main.prefix + "&fDeveloper &3&l» &aAskingg"));
			}
			if (args[0].equalsIgnoreCase("list")) {
				if (player.hasPermission("golems.list")) {
					player.sendMessage(
							Main.colorCodes(Main.prefix + "&aCoal&f, &aIron&f, &aGold&f, &aDiamond&f, &aEmerald"));
				} else {
					player.sendMessage(
							Main.colorCodes(Main.prefix + "&fSorry, but you do not have permission to do this"));
				}
			}
			if (args[0].equalsIgnoreCase("shop")) {
				player.openInventory(ShopGUI.shop(player));
				ShopGUI.shopping.add(player);
			}

			if (args[0].equalsIgnoreCase("give")) {
				if (args.length == 4) {
					if (player.hasPermission("golems.give")) {

						Player target = Bukkit.getPlayer(args[1]);
						Inventory inventory = target.getInventory();
						String type = args[2];
						Integer amount = Integer.parseInt(args[3]);

						if (type.equalsIgnoreCase("coal") || type.equalsIgnoreCase("iron")
								|| type.equalsIgnoreCase("gold") || type.equalsIgnoreCase("diamond")
								|| type.equalsIgnoreCase("emerald")) {

							ItemStack golemSpawner = new ItemStack(Material.SPAWNER);
							golemSpawner.setAmount(amount);
							ItemMeta golemMeta = golemSpawner.getItemMeta();
							if (type.equalsIgnoreCase("coal"))
								golemMeta.setDisplayName(Main.colorCodes("&3&l» &8Coal Golem Spawner &3&l«"));
							if (type.equalsIgnoreCase("iron"))
								golemMeta.setDisplayName(Main.colorCodes("&3&l» &7Iron Golem Spawner &3&l«"));
							if (type.equalsIgnoreCase("gold"))
								golemMeta.setDisplayName(Main.colorCodes("&3&l» &6Gold Golem Spawner &3&l«"));
							if (type.equalsIgnoreCase("diamond"))
								golemMeta.setDisplayName(Main.colorCodes("&3&l» &bDiamond Golem Spawner &3&l«"));
							if (type.equalsIgnoreCase("emerald"))
								golemMeta.setDisplayName(Main.colorCodes("&3&l» &aEmerald Golem Spawner &3&l«"));
							golemSpawner.setItemMeta(golemMeta);

							inventory.addItem(golemSpawner);
							if (amount == 1) {
								if (type.equalsIgnoreCase("coal"))
									target.sendMessage(
											Main.colorCodes(Main.prefix + "&fYou received a &8Coal Golem Spawner"));
								if (type.equalsIgnoreCase("iron"))
									target.sendMessage(
											Main.colorCodes(Main.prefix + "&fYou received a &7Iron Golem Spawner"));
								if (type.equalsIgnoreCase("gold"))
									target.sendMessage(
											Main.colorCodes(Main.prefix + "&fYou received a &6Gold Golem Spawner"));
								if (type.equalsIgnoreCase("diamond"))
									target.sendMessage(
											Main.colorCodes(Main.prefix + "&fYou received a &bDiamond Golem Spawner"));
								if (type.equalsIgnoreCase("emerald"))
									target.sendMessage(
											Main.colorCodes(Main.prefix + "&fYou received a &aEmerald Golem Spawner"));
							} else if (amount > 1) {
								if (type.equalsIgnoreCase("coal"))
									target.sendMessage(Main.colorCodes(
											Main.prefix + "&fYou received &a" + amount + " &8Coal Golem Spawners"));
								if (type.equalsIgnoreCase("iron"))
									target.sendMessage(Main.colorCodes(
											Main.prefix + "&fYou received &a" + amount + " &7Iron Golem Spawners"));
								if (type.equalsIgnoreCase("gold"))
									target.sendMessage(Main.colorCodes(
											Main.prefix + "&fYou received &a" + amount + " &6Gold Golem Spawners"));
								if (type.equalsIgnoreCase("diamond"))
									target.sendMessage(Main.colorCodes(
											Main.prefix + "&fYou received &a" + amount + " &bDiamond Golem Spawners"));
								if (type.equalsIgnoreCase("emerald"))
									target.sendMessage(Main.colorCodes(
											Main.prefix + "&fYou received &a" + amount + " &aEmerald Golem Spawners"));
							}

						} else {
							player.sendMessage(Main.colorCodes(Main.prefix + "&fSorry, but &c" + type
									+ "&f is not a valid spawner type. To view all available tiers execute &e/Golems List"));
						}
					} else {
						player.sendMessage(
								Main.colorCodes(Main.prefix + "&fSorry, but you do not have permission to do this"));
					}
				} else {
					player.sendMessage(
							Main.colorCodes(Main.prefix + "&fUsage &3&l» &e/Golems Give &6Player Tier Amount"));
				}
			}
		}

		return false;
	}
}
