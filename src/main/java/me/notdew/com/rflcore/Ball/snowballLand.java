package me.notdew.com.rflcore.Ball;

import me.notdew.com.rflcore.RFLCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class snowballLand implements Listener {

    @EventHandler
    public void onLand(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball) {
            if (throwBall.assign.containsKey(e.getEntity())) {
                if (throwBall.ballp.containsKey(e.getEntity())) {
                    Player p = throwBall.ballp.get(e.getEntity());
                    p.teleport(throwBall.ogloc.get(p));
                    throwBall.ogloc.remove(p);
                    p.setGameMode(GameMode.SURVIVAL);
                    throwBall.ballp.remove(e.getEntity());

                }


                throwBall.assign.get(e.getEntity()).remove();
            }
        }
    }
    @EventHandler
    public void hitSnowball(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof Slime)) return;
        Player p = (Player)e.getDamager();
        Bukkit.broadcastMessage("CAUGHT! By:" + ((Player) e.getDamager()).getDisplayName());
        e.setDamage(0);
        e.setCancelled(true);
        ((Slime) e.getEntity()).setHealth(1);
        ((Slime) e.getEntity()).setInvulnerable(true);
        ((Slime)e.getEntity()).remove();
        p.setGlowing(true);
        new BukkitRunnable(){
            @Override
            public void run(){
                p.setGlowing(false);
            }

        }.runTaskLater(RFLCore.instance, 20*5);

    }

}
