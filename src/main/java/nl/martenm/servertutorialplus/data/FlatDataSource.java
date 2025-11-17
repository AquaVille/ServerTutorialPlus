package nl.martenm.servertutorialplus.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import nl.martenm.servertutorialplus.ServerTutorialPlus;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author MartenM
 * @since 24-12-2017.
 */
public class FlatDataSource implements DataSource {

    private ServerTutorialPlus plugin;
    public FlatDataSource(ServerTutorialPlus plugin){
        this.plugin = plugin;
    }

    @Override
    public List<String> getPlayedTutorials(UUID uuid) {
        File hostlocation = new File(plugin.getDataFolder() + "/data/playerdata");
        hostlocation.mkdirs();

        File file = new File(plugin.getDataFolder() + "/data/playerdata/" + uuid + ".json");
        if(file.exists()){
            JsonParser parser = new JsonParser();
            JsonObject data = null;
            FileReader reader = null;

            try{
                reader = new FileReader(file.getPath());
                Object obj = parser.parse(reader);
                data = (JsonObject) obj;
            } catch (Exception ex){
                ex.printStackTrace();

            } finally {
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return (List<String>) data.get("tutorials");
        }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addPlayedTutorial(UUID uuid, String id) {
        List<String> played = getPlayedTutorials(uuid);
        played.add(id);

        File hostlocation = new File(plugin.getDataFolder() + "/data/playerdata/");
        hostlocation.mkdirs();

        JsonObject data = new JsonObject();
        data.add("tutorials", new Gson().toJsonTree(played));

        File file = new File(plugin.getDataFolder() + "/data/playerdata/" + uuid + ".json");

        FileWriter writer = null;
        try{
            writer = new FileWriter(file);
            writer.write(data.getAsString());
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
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

        return true;
    }

    @Override
    public boolean removePlayedTutorial(UUID uuid, String id) {
        List<String> played = getPlayedTutorials(uuid);
        played.remove(id);

        File hostlocation = new File(plugin.getDataFolder() + "/data/playerdata/");
        hostlocation.mkdirs();

        JsonObject data = new JsonObject();
        data.add("tutorials", new Gson().toJsonTree(played));

        File file = new File(plugin.getDataFolder() + "/data/playerdata/" + uuid + ".json");

        FileWriter writer = null;
        try{
            writer = new FileWriter(file);
            writer.write(data.getAsString());
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
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

        return true;
    }

    @Override
    public boolean hasPlayedTutorial(UUID uuid, String id) {
        return getPlayedTutorials(uuid).contains(id);
    }
}
