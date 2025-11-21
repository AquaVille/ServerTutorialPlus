package nl.martenm.servertutorialplus.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;

/**
 * Represents a tutorial sign.
 * Created by Marten on 10-3-2017.
 */
public class TutorialSign {

    @Getter
    @Setter
    private Block block;
    @Getter
    @Setter
    private String ServerTutorialId;

    public TutorialSign(Block block, String serverTutorialId){
        this.block = block;
        this.ServerTutorialId = serverTutorialId;
    }
}
