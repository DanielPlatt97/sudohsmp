package me.marenji.sudohsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.marenji.sudohsmp.util.ChatHelper;
import me.marenji.sudohsmp.util.PermissionsHelper;
import me.marenji.sudohsmp.Main;
import me.marenji.sudohsmp.health.PlayerHealthManager;

public class SetMaxHealthAllCommand implements CommandExecutor {
  
  //@SuppressWarnings({"unused"})
  private Main plugin;
  private PlayerHealthManager healthManager;
  
  public SetMaxHealthAllCommand(Main plugin) {
    this.plugin = plugin;
    this.healthManager = new PlayerHealthManager(plugin);
    plugin.getCommand("maxhealthall").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ( !PermissionsHelper.isSenderAdmin(sender) ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("notadmin_message")
      ));
      return false;
    }
    
    // Getting command arguments
    String heartsString = null;
    if ( args.length >= 1) {
      heartsString = args[0];
    }
    
    // no arguments specified
    if ( heartsString == null ) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("maxhealthall_invalidargs_message")
      ));
      return false;
    }
    
    int hearts;
    try {
      hearts = Integer.parseInt(heartsString);
    } catch (Exception e) {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig().getString("maxhealthall_invalidargs_message")
      ));
      return false;
    }
    
    var success = healthManager.setMaxHeartsAllPlayers(hearts);
    if ( success ) {
      Bukkit.broadcastMessage(
          ChatHelper.chat(
              plugin.getConfig()
                  .getString("maxhealthall_setsuccess_message")
          )
      );
      return true;
    } else {
      sender.sendMessage(ChatHelper.chat(
          plugin.getConfig()
            .getString("maxhealth_invalidhearts_message")
      ));
      return false;
    }
  }
  
}