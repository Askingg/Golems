package me.askingg.golems;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.askingg.commands.SpawnerCommands;
import me.askingg.gui.ShopGUI;
import me.askingg.spawners.CoalGolem;
import me.askingg.spawners.DiamondGolem;
import me.askingg.spawners.EmeraldGolem;
import me.askingg.spawners.GoldGolem;
import me.askingg.spawners.IronGolem;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	@SuppressWarnings("unused")
	private SpawnerCommands spawnerCommands;
	public static Economy econ = null;
	public static String prefix = "&8(&cGolems&8) &3&l» ";

	public void onEnable() {

		createFolders();
		if (!(setupEconomy())) getServer().getPluginManager().disablePlugin(this);
		Bukkit.getConsoleSender().sendMessage(colorCodes(prefix + "&fPlugin &asuccessfully &floaded"));
		getServer().getPluginManager().registerEvents(new ShopGUI(), this);
		getServer().getPluginManager().registerEvents(new CoalGolem(), this);
		getServer().getPluginManager().registerEvents(new IronGolem(), this);
		getServer().getPluginManager().registerEvents(new GoldGolem(), this);
		getServer().getPluginManager().registerEvents(new DiamondGolem(), this);
		getServer().getPluginManager().registerEvents(new EmeraldGolem(), this);
		spawnerCommands = new SpawnerCommands(this);

	}

	public static String colorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	private void createFolders() {

		File golemsFolder = new File("plugins/Golems");
		if (!(golemsFolder.exists())) {
			golemsFolder.mkdirs();
		}
		File locationsFolder = new File("plugins/Golems/Locations");
		if (!(locationsFolder.exists())) {
			locationsFolder.mkdirs();
		}
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    public static Economy getEconomy() {
        return econ;
    }
}
