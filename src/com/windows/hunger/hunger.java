package com.windows.hunger;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class hunger extends JavaPlugin implements Listener {

	HashMap<Player, Integer> a = new HashMap<Player, Integer>();
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a배고픔 회복 차단 플러그인 활성화");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c배고픔 회복 차단 플러그인 비활성화");
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			a.put(player, player.getFoodLevel());
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onRespawn(PlayerRespawnEvent event) {
		final Player player = (Player)event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				int b = a.get(player).intValue();
				player.setFoodLevel(b);
			}
		}, 10L);
	}
}
