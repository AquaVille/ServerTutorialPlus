package nl.martenm.servertutorialplus.helpers.dataholders;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Created by Marten on 19-3-2017.
 */
public class FireWorkInfo {

    @Getter
    private final Location location;
    @Getter
    private FireworkMeta fireworkMeta;

    public FireWorkInfo(Location location, FireworkMeta fireworkMeta){
        this.fireworkMeta = fireworkMeta;
        this.location = location;
    }
}
