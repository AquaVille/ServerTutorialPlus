package nl.martenm.servertutorialplus.objects;

import lombok.Getter;
import lombok.Setter;
import nl.martenm.servertutorialplus.ServerTutorialPlus;
import nl.martenm.servertutorialplus.helpers.SpigotUtils;
import org.bukkit.Location;

import java.util.UUID;

/**
 * This class holds info about NPCs.
 * It also contains the UUIDs of the armorstands (holograms)
 * @author MartenM
 */
public class NPCInfo {

    private ServerTutorialPlus plugin;

    @Getter
    private String id;

    @Getter
    private UUID npcId;
    @Setter
    @Getter
    private UUID[] armorstandIDs;
    @Getter
    private String serverTutorialID;

    @Getter
    private Location location;

    public NPCInfo(ServerTutorialPlus plugin, String id, UUID npcId, UUID[] armorstandIDs, String serverTutorialID){
        this.plugin = plugin;
        this.id = id;
        this.npcId = npcId;
        this.armorstandIDs = armorstandIDs;
        this.serverTutorialID = serverTutorialID;

        try{
            location = SpigotUtils.getEntity(npcId).getLocation();
        } catch (Exception ex){
            System.out.println(ex.getStackTrace());
            //Welp what happend!
        }
    }

    public NPCInfo(ServerTutorialPlus plugin, String id, UUID npcId, UUID[] armorstandIDs, String serverTutorialID, Location location){
        this.plugin = plugin;
        this.id = id;
        this.npcId = npcId;
        this.armorstandIDs = armorstandIDs;
        this.serverTutorialID = serverTutorialID;

        this.location = location;
    }

}
