package nl.martenm.servertutorialplus.helpers.dataholders;

import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marten on 15-3-2017.
 */
public class PlayerData {

    @Getter
    private UUID uuid;

    @Getter
    private float flyspeed;

    @Getter
    private float walkspeed;

    @Getter
    private boolean isFlying;

    @Getter
    private boolean isAllowedFlight;

    @Getter
    private Location location;

    @Getter
    private GameMode gamemode;

    @Getter
    private List<String> tutorials;

    public PlayerData(Player player){
        this.uuid = player.getUniqueId();
        this.flyspeed = player.getFlySpeed();
        this.walkspeed = player.getWalkSpeed();
        this.isFlying = player.isFlying();
        this.isAllowedFlight = player.getAllowFlight();
        this.location = player.getLocation();
        this.gamemode = player.getGameMode();
        this.tutorials = new ArrayList<>();
    }


    public void restore(Player player) {
        player.setFlySpeed(getFlyspeed());
        player.setWalkSpeed(getWalkspeed());
        player.setAllowFlight(isAllowedFlight);
        player.setFlying(isFlying);
        player.setGameMode(getGamemode());
        player.teleport(getLocation());
    }

    public void addTutorial(String id) {
        tutorials.add(id);
    }

    public void removeTutorial(String id) {
        tutorials.remove(id);
    }
}
