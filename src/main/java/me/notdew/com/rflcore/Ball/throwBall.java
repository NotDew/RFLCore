package me.notdew.com.rflcore.Ball;

import me.notdew.com.rflcore.RFLCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static me.notdew.com.rflcore.Ball.dropEvent.getTotalExperience;

public class throwBall implements Listener {
    public static HashMap<Snowball, Slime> assign = new HashMap<>();
    public static HashMap<Player, Location> ogloc = new HashMap<>();
    public static HashMap<Snowball, Player> ballp = new HashMap<>();

    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
        if (!(e.getPlayer().getInventory().getItemInHand().getType() == Material.SNOWBALL)) return;
        e.setCancelled(true);

        Snowball s = p.launchProjectile(Snowball.class);
        Vector v = p.getLocation().getDirection();
        Slime as = (Slime) p.getWorld().spawn(s.getLocation(), Slime.class, a -> a.setInvisible(true));
        as.setInvulnerable(true);
        as.setMaxHealth(100);
        as.setHealth(as.getMaxHealth());
        as.setInvisible(true);
        as.setSize(2);
        s.addPassenger(as);
        assign.put(s, as);
        ballCam(p, s);

        int str = 1;
        if (getTotalExperience(p) >= 40.1) {str = 4;
        } else if (getTotalExperience(p) >= 27.1) {str = 3;
        } else if (getTotalExperience(p) >= 16.1) {str = 2;
        } else if (getTotalExperience(p) >= 7.1) {str = 1;}


        s.setVelocity(p.getLocation().getDirection().multiply((str * .25 )+ 0.5));
        s.setVelocity(s.getVelocity().multiply(new Vector(1,(((double)dropEvent.lvl.get(p)) / 8) + 1, 1)));

        new BukkitRunnable(){
            @Override
            public void run(){
                as.setInvulnerable(false);
            }

        }.runTaskLater(RFLCore.instance, 5);
    }
    public static Vector rotateVectorAroundY(Vector vector, double degrees) {
        double rad = Math.toRadians(degrees);

        double currentX = vector.getX();
        double currentZ = vector.getZ();

        double cosine = Math.cos(rad);
        double sine = Math.sin(rad);

        return new Vector((cosine * currentX - sine * currentZ), vector.getY(), (sine * currentX + cosine * currentZ));
    }

    public void ballCam(Player p, Snowball s) {
        if (ballCam.inballcam.contains(p)) {
            ogloc.put(p, p.getLocation());
            ballp.put(s, p);
            p.setGameMode(GameMode.SPECTATOR);
            s.addPassenger(p);
        } else {
            return;
        }
    }
}

