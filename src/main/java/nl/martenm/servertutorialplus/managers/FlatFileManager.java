package nl.martenm.servertutorialplus.managers;

import nl.martenm.servertutorialplus.ServerTutorialPlus;
import nl.martenm.servertutorialplus.helpers.dataholders.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Created by Marten on 7-6-2017.
 */
@SuppressWarnings("ALL")
public class FlatFileManager{

    public static PlayerData getPlayerData(ServerTutorialPlus plugin, UUID uuid){
        File hostlocation = new File(plugin.getDataFolder() + "/playerdata");
        hostlocation.mkdirs();

        File file = new File(plugin.getDataFolder() + "/playerdata/" + uuid + ".json");
        if(file.exists()){
            try{
                FileReader reader = new FileReader(file.getPath());
                PlayerData obj = ServerTutorialPlus.getGson().fromJson(reader, PlayerData.class);
                reader.close();
                return obj;
            } catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        else{
            //Nothing we only get data that exists ;p
        }
        return null;
    }

    public static void setPlayerData(ServerTutorialPlus plugin, Player player, PlayerData data){
        if(data == null) return;
        new BukkitRunnable(){
            @Override
            public void run() {
                plugin.getLogger().info("Restoring player status for player: " + player.getName());
                player.setWalkSpeed(data.getWalkspeed());
                player.setAllowFlight(data.isAllowedFlight());
                player.setFlying(data.isFlying());
                player.setFlySpeed(data.getFlyspeed());
                player.teleport(data.getLocation());
                player.setGameMode(data.getGamemode());
            }
        }.runTask(plugin);
    }

    public static void deleteFile(ServerTutorialPlus plugin, UUID uuid){
        File file = new File(plugin.getDataFolder() + "/playerdata/" + uuid + ".json");
        if(file.exists()){
            file.delete();
        } else{
            System.out.println("[Server Tutorial Plus] Error, file not found.");
        }
    }

    public static void saveJson(ServerTutorialPlus plugin, PlayerData info){
        File hostlocation = new File(plugin.getDataFolder() + "/playerdata");
        hostlocation.mkdirs();

        File file = new File(plugin.getDataFolder() + "/playerdata/" + info.getUuid() + ".json");

        String json = ServerTutorialPlus.getGson().toJson(info);
        FileWriter writer = null;
        try{
            writer = new FileWriter(file);
            writer.write(json);
            System.out.println("[Server Tutorial Plus] A player left while in the tutorial. Old data has been saved.");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(writer != null){
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
