package me.marenji.sudohsmp;

import org.bukkit.plugin.java.JavaPlugin;

import me.marenji.sudohsmp.commands.SetMaxHealthAllCommand;
import me.marenji.sudohsmp.commands.SetMaxHealthCommand;
import me.marenji.sudohsmp.listeners.DeathListener;
import me.marenji.sudohsmp.listeners.GoldenFoodListener;
import me.marenji.sudohsmp.listeners.JoinListener;
import me.marenji.sudohsmp.listeners.RespawnListener;

public class Main extends JavaPlugin {
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    new SetMaxHealthCommand(this);
    new SetMaxHealthAllCommand(this);
    new JoinListener(this);
    new DeathListener(this);
    new GoldenFoodListener(this);
    new RespawnListener(this);
  }

}
