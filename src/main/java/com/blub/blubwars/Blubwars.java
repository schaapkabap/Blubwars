package com.blub.blubwars;

import com.blub.blubwars.Listeners.ConnectListener;
import com.blub.blubwars.Listeners.GameListener;
import com.blub.blubwars.command.ArenaCommand;
import com.blub.blubwars.instance.Arena;
import com.blub.blubwars.manager.ArenaManager;
import com.blub.blubwars.manager.configManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blubwars extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        // Setup config
        configManager.setupConfig(this);

        // Creating arenaManager
        arenaManager = new ArenaManager(this);

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);

        // Register command
        getCommand("blubwars").setExecutor(new ArenaCommand(this));

        // Enable message
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Enabling the " + ChatColor.BOLD.toString() + ChatColor.LIGHT_PURPLE + "Blubwars" + ChatColor.RESET.toString() + ChatColor.BLUE + " plugin!");
    }

    @Override
    public void onDisable() {
        // Kicking all players from their games
        for (Arena arena : arenaManager.getArenas()){
            arena.reset();
        }
        // Disable message
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabled the " + ChatColor.BOLD.toString() + ChatColor.LIGHT_PURPLE + "Blubwars" + ChatColor.RESET.toString() + ChatColor.RED + " plugin!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Thank you for playing!");
    }

    public ArenaManager getArenaManager() { return arenaManager; }
}