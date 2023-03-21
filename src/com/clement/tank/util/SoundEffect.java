package com.clement.tank.util;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static void play(String src){
        //通过文件路径,播放音效
        AudioClip audioClip=new AudioClip(SoundEffect.class.getResource(src).toString());
        audioClip.play();
    }
}
