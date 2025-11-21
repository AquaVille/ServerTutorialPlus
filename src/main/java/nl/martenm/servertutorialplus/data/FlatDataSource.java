package nl.martenm.servertutorialplus.data;

import nl.martenm.servertutorialplus.ServerTutorialPlus;
import nl.martenm.servertutorialplus.helpers.dataholders.PlayerData;
import nl.martenm.servertutorialplus.managers.FlatFileManager;

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
        PlayerData data = FlatFileManager.getPlayerData(plugin, uuid);
        if (data != null) {
            return data.getTutorials();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean addPlayedTutorial(UUID uuid, String id) {
        PlayerData data = FlatFileManager.getPlayerData(plugin, uuid);
        if (data != null) {
            data.addTutorial(id);
            FlatFileManager.saveJson(plugin, data);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePlayedTutorial(UUID uuid, String id) {
        PlayerData data = FlatFileManager.getPlayerData(plugin, uuid);
        if (data != null) {
            data.removeTutorial(id);
            FlatFileManager.saveJson(plugin, data);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPlayedTutorial(UUID uuid, String id) {
        return getPlayedTutorials(uuid).contains(id);
    }
}
