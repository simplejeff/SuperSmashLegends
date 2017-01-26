package jmhao.engine.Sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClipPlayer {
  private static HashMap<String, File> mp3Files = new HashMap<String, File>();
  private static InputStream in;
  private static Clip clip = null;
  public static void addClip(String s, File m) {
    mp3Files.put(s,  m);
  }
  public static void playClip(String s) {
    System.out.println(mp3Files.toString());
    if(mp3Files.containsKey(s)) {
      try {
        if(clip != null && clip.isActive()) {
          clip.stop();
        }
        if(in != null) {
          in.close();
        }
        System.out.println("lets go make a clip");
        clip = AudioSystem.getClip();
        System.out.println("file : " + clip.toString());
        System.out.println(mp3Files.get(s).toString());
        in = new BufferedInputStream(new FileInputStream(mp3Files.get(s)));
        AudioInputStream stream = AudioSystem.getAudioInputStream(in);
        System.out.println("we have the stream");    
        clip = AudioSystem.getClip();
            clip.open(stream);
            System.out.println("opening stream");
            System.out.println(clip.toString());
            clip.start();
            System.out.println(clip.toString());
      } catch (FileNotFoundException e) {
      } catch (UnsupportedAudioFileException e) {
      } catch (IOException e) {
      } catch (LineUnavailableException e) {
      }
    }
  }
}
