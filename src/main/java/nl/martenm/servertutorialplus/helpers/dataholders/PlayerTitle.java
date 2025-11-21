package nl.martenm.servertutorialplus.helpers.dataholders;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marten on 5-3-2017.
 */
public class PlayerTitle {

    public PlayerTitle(String title, String subtitle, int fadeIn, int time, int fadeOut){
        this.setTitle(title);
        this.setSubtitle(subtitle);
        this.setTime(time);
        this.setFadeIn(fadeIn);
        this.setFadeOut(fadeOut);
    }

    public PlayerTitle(){
        this.setTitle("");
        this.setSubtitle("");
        this.setFadeIn(20);
        this.setFadeOut(20);
        this.setTime(40);
    }

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String subtitle;
    @Getter
    @Setter
    private int time;
    @Getter
    @Setter
    private int fadeIn;
    @Getter
    @Setter
    private int fadeOut;
}
