package nl.martenm.servertutorialplus.helpers.dataholders;

import lombok.Getter;
import org.bukkit.Sound;

/**
 * Created by Marten on 5-3-2017.
 */
public class PlayerSound {

    public PlayerSound(Sound sound, float pitch, float volume){
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
    }

    @Getter
    private Sound sound;

    @Getter
    private float pitch;

    @Getter
    private float volume;

}
