package com.example.demo.hack;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@RestController

public class SoundController {
    private Clip clip;

    @GetMapping("/play")
    public String playSound() {
        try {
            if (clip == null || !clip.isRunning()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("/Users/aidaikydykbekova/Downloads/preryivistyiy-zvuk.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return "Failed to play sound";
        }
        return "Sound is playing";
    }

    @GetMapping("/stop")
    public String stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        return "Sound stopped";
    }
}

