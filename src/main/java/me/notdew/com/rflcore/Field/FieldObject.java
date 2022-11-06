package me.notdew.com.rflcore.Field;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import me.notdew.com.rflcore.RFLCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FieldObject {
    CuboidRegion region;

    Player p;

    String e1 = "";
    String e2 = "";
    String dir;
    String name;

    public CuboidRegion getRegion() {return region;}


    public CuboidRegion getAway() {
        if (e1.equals("away") || e2.equals("away")) {
            if (e1.equals("away")) {
                return getE1();
            } else {
                return getE2();
            }
        }
        return null;
    }
    public CuboidRegion getHome() {
        if (e1.equals("home") || e2.equals("home")) {
            if (e1.equals("home")) {
                return getE1();
            } else {
                return getE2();
            }
        }
        return null;
    }



    public FieldObject(Region region, Player p, String n) {
        this.region = region.getBoundingBox();
        this.p = p;
        p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Stand in the AWAY endzone and then do /field away");

        getDir();
        Bukkit.broadcastMessage(getE2() + " | " + getE1());
        this.name = n;

    }


    public void inAway() {

        if (!(getE1().getBoundingBox().contains(p.getLocation().getBlockX(), getE1().getMinimumY(),  p.getLocation().getBlockZ()) || getE2().getBoundingBox().contains(p.getLocation().getBlockX(), getE2().getMinimumY(), p.getLocation().getBlockZ()))) {
            p.sendMessage();
            p.sendMessage(ChatColor.RED + "No EndZone Found. Move and Try Again.");
            return;
        } else {
            p.sendMessage(ChatColor.GREEN + "Successfully Set EndZones!");
        }
        if (getE1().getBoundingBox().contains(p.getLocation().getBlockX(), p.getLocation().getBlockZ())) {
            e1 = "away";
            e2 = "home";
        } else {
            e1 = "home";
            e2 = "away";
        }

    }
    public String getDirection() {return dir;}

    public void getDir() {
        float yaw = p.getLocation().getYaw();
        yaw = (yaw % 360 + 360) % 360;
        if (yaw >= 315 || yaw < 45) {
            dir = "S";
        }if (yaw < 135) {
            dir = "W";
        } else if (yaw < 225) {
            dir = "N";
        } else if (yaw < 315) {
            dir = "E";
        }
    }

    public CuboidRegion getE1() {
        BlockVector3 max = BlockVector3.at(region.getMinimumPoint().getX()+10, region.getMinimumY(), region.getMaximumPoint().getZ());
        CuboidRegion r = new CuboidRegion(max, region.getMinimumPoint());
        return r;
    }
    public CuboidRegion getE2() {
        BlockVector3 max2 = BlockVector3.at(region.getMaximumPoint().getX()-10, region.getMinimumY(), region.getMinimumPoint().getZ());
        CuboidRegion r2 = new CuboidRegion(max2, region.getMaximumPoint());
        return r2;
    }
    public String getName() {
        return name;
    }


    public FieldObject(Object[] raw) {
        this.e1 = (String) raw[0];
        this.e2 = (String) raw[1];
        this.dir = (String) raw[2];
        region = new CuboidRegion(BlockVector3.at((int)raw[3], (int)raw[4], (int)raw[5]), BlockVector3.at((int)raw[6], (int)raw[7], (int)raw[8]));
        this.name = (String) raw[9];
        FieldCommand.fieldnames.put(name, this);
    }


    public ArrayList<Object> serialize() {
        ArrayList<Object> map = new ArrayList<Object>();
        map.add( e1);
        map.add( e2);
        map.add( dir);
        map.add( region.getMinimumPoint().getBlockX());
        map.add( region.getMinimumPoint().getBlockY());
        map.add( region.getMinimumPoint().getBlockZ());
        map.add( region.getMaximumPoint().getBlockX());
        map.add(region.getMaximumPoint().getBlockY());
        map.add( region.getMaximumPoint().getBlockZ());
        map.add( name);
        System.out.println(map.toString());
        return map;
    }

}
