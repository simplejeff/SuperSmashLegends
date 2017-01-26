package jmhao.game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.media.Media;

import javax.imageio.ImageIO;

import cs195n.CS195NLevelReader;
import cs195n.CS195NLevelReader.InvalidLevelException;
import cs195n.LevelData;
import cs195n.Vec2f;
import cs195n.Vec2i;
import jmhao.engine.Application;
import jmhao.engine.Sound.ClipPlayer;
import jmhao.game.UIElements.GameAnimation;
import jmhao.game.UIElements.GameSprite;

/**
 * TacGame class to instantiate game
 * @author jmhao
 *
 */
public class Final {
  public static Image spritesPng;
  public static Image facesPng;
  public static Image platformPng;
  public static Image bgPng;
  public static Image borderPng;
  public static LevelData ld;
  public static Image logoPng;
  public static Font leagueFont;
  
  public static GameSprite spriteFaces;
  public static GameSprite spriteBlink;
  public static GameSprite spriteBraumQ;
  public static GameSprite spriteBullet;
  public static GameSprite spriteEffects;
  public static GameSprite spriteEzrealQ;
  public static GameSprite spriteYasuoQ;
  public static GameSprite spriteMeleePunch;
  public static GameSprite spriteTeemoQ;
  public static GameSprite sprites;
  
  public static GameSprite spriteEzrealCrouch;
  public static GameSprite spriteTeemoCrouch;
  public static GameSprite spriteYasuoCrouch;
  public static GameSprite spriteBraumCrouch;
  public static GameSprite spriteCrouch;
  
  public static GameSprite spritePunch;
  public static GameSprite spriteEzrealPunch;
  public static GameSprite spriteYasuoPunch;
  public static GameSprite spriteBraumPunch;
  
  public static GameSprite spriteShoot;
  public static GameSprite spriteTeemoShoot;
  
  public static GameSprite spriteStand;
  public static GameSprite spriteEzrealStand;
  public static GameSprite spriteTeemoStand;
  public static GameSprite spriteYasuoStand;
  public static GameSprite spriteBraumStand;
  
  public static GameSprite spriteWalk;
  public static GameSprite spriteEzrealWalk;
  public static GameSprite spriteTeemoWalk;
  public static GameSprite spriteYasuoWalk;
  public static GameSprite spriteBraumWalk;

  
  public static void main(String[] args) {
    try {
      bgPng = ImageIO.read(new File("res/nogamenolife.png"));
      logoPng = ImageIO.read(new File("res/SPICYMEATBALL.png"));
      platformPng = ImageIO.read(new File("res/bg.png"));
      leagueFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/beaufortforlol-regular.otf"));
      leagueFont = leagueFont.deriveFont(Font.PLAIN,12);
      GraphicsEnvironment ge =
          GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(leagueFont);
      /*GraphicsEnvironment ge = 
          GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/beaufortforlol-regular.otf")));
      InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("res/beaufortforlol-regular.otf");
      leagueFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(12f);*/
     // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      //register the font
     // ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\custom_font.ttf")));
      File f = new File("res/m3.nlf");
      ld = CS195NLevelReader.readLevel(f);
      spritesPng = ImageIO.read(new File("res/sprites.png"));
      borderPng = ImageIO.read(new File("res/fullBorder.png"));
      Image mySprites = ImageIO.read(new File("res/faces2.png"));
      spriteFaces = new GameSprite(4, mySprites);
      spriteFaces.setDimensions(new Vec2f(250f, 250f));
      spriteFaces.setImageDimensions(new Vec2i(150, 150));
      GameAnimation teemo = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation ezreal = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation yasuo = new GameAnimation(new Vec2f(0, 1), new Vec2f(0, 0));
      GameAnimation braum = new GameAnimation(new Vec2f(1, 1), new Vec2f(0, 0));
      spriteFaces.addAnimation(teemo, 0);
      spriteFaces.addAnimation(ezreal, 1);
      spriteFaces.addAnimation(yasuo, 2);
      spriteFaces.addAnimation(braum, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/blink.png"));
      spriteBlink = new GameSprite(4, mySprites);
      spriteBlink.setDimensions(new Vec2f(110f, 220f));
      spriteBlink.setImageDimensions(new Vec2i(44, 88));
      GameAnimation spriteBlink1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteBlink2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteBlink3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteBlink4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBlink.addAnimation(spriteBlink1, 0);
      spriteBlink.addAnimation(spriteBlink2, 1);
      spriteBlink.addAnimation(spriteBlink3, 2);
      spriteBlink.addAnimation(spriteBlink4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/braumQ.png"));
      spriteBraumQ = new GameSprite(4, mySprites);
      spriteBraumQ.setDimensions(new Vec2f(200f, 100f));
      spriteBraumQ.setImageDimensions(new Vec2i(100, 50));
      GameAnimation spriteBraumQ1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumQ2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumQ3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumQ4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBraumQ.addAnimation(spriteBraumQ1, 0);
      spriteBraumQ.addAnimation(spriteBraumQ2, 1);
      spriteBraumQ.addAnimation(spriteBraumQ3, 2);
      spriteBraumQ.addAnimation(spriteBraumQ4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/bullet.png"));
      spriteBullet = new GameSprite(4, mySprites);
      spriteBullet.setDimensions(new Vec2f(100f, 40f));
      spriteBullet.setImageDimensions(new Vec2i(50, 20));
      GameAnimation spriteBullet1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteBullet2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteBullet3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteBullet4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBullet.addAnimation(spriteBullet1, 0);
      spriteBullet.addAnimation(spriteBullet2, 1);
      spriteBullet.addAnimation(spriteBullet3, 2);
      spriteBullet.addAnimation(spriteBullet4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/effects.png"));
      spriteEffects = new GameSprite(12, mySprites);
      spriteEffects.setDimensions(new Vec2f(100f, 100f));
      spriteEffects.setImageDimensions(new Vec2i(50, 50));
      GameAnimation spriteEffects1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteEffects2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteEffects3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteEffects4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      GameAnimation spriteEffects5 = new GameAnimation(new Vec2f(0, 1), new Vec2f(0, 0));
      GameAnimation spriteEffects6 = new GameAnimation(new Vec2f(1, 1), new Vec2f(0, 0));
      GameAnimation spriteEffects7 = new GameAnimation(new Vec2f(2, 1), new Vec2f(0, 0));
      GameAnimation spriteEffects8 = new GameAnimation(new Vec2f(3, 1), new Vec2f(0, 0));
      GameAnimation spriteEffects9 = new GameAnimation(new Vec2f(0, 2), new Vec2f(0, 0));
      GameAnimation spriteEffects10 = new GameAnimation(new Vec2f(1, 2), new Vec2f(0, 0));
      GameAnimation spriteEffects11 = new GameAnimation(new Vec2f(2, 2), new Vec2f(0, 0));
      GameAnimation spriteEffects12 = new GameAnimation(new Vec2f(3, 2), new Vec2f(0, 0));   
      spriteEffects.addAnimation(spriteEffects1, 0);
      spriteEffects.addAnimation(spriteEffects2, 1);
      spriteEffects.addAnimation(spriteEffects3, 2);
      spriteEffects.addAnimation(spriteEffects4, 3);
      spriteEffects.addAnimation(spriteEffects5, 4);
      spriteEffects.addAnimation(spriteEffects6, 5);
      spriteEffects.addAnimation(spriteEffects7, 6);
      spriteEffects.addAnimation(spriteEffects8, 7);
      spriteEffects.addAnimation(spriteEffects9, 8);
      spriteEffects.addAnimation(spriteEffects10, 9);
      spriteEffects.addAnimation(spriteEffects11, 10);
      spriteEffects.addAnimation(spriteEffects12, 11);
      
      mySprites = ImageIO.read(new File("res/sprites/ezrealQ.png"));
      spriteEzrealQ = new GameSprite(4, mySprites);
      spriteEzrealQ.setDimensions(new Vec2f(100f, 40f));
      spriteEzrealQ.setImageDimensions(new Vec2i(50, 20));
      GameAnimation spriteEzrealQ1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealQ2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealQ3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealQ4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteEzrealQ.addAnimation(spriteEzrealQ1, 0);
      spriteEzrealQ.addAnimation(spriteEzrealQ2, 1);
      spriteEzrealQ.addAnimation(spriteEzrealQ3, 2);
      spriteEzrealQ.addAnimation(spriteEzrealQ4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/yasuoQ.png"));
      spriteYasuoQ = new GameSprite(4, mySprites);
      spriteYasuoQ.setDimensions(new Vec2f(100f, 200f));
      spriteYasuoQ.setImageDimensions(new Vec2i(80, 160));
      GameAnimation spriteYasuoQ1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoQ2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoQ3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoQ4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteYasuoQ.addAnimation(spriteYasuoQ1, 0);
      spriteYasuoQ.addAnimation(spriteYasuoQ2, 1);
      spriteYasuoQ.addAnimation(spriteYasuoQ3, 2);
      spriteYasuoQ.addAnimation(spriteYasuoQ4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/teemoQ.png"));
      spriteTeemoQ = new GameSprite(4, mySprites);
      spriteTeemoQ.setDimensions(new Vec2f(100f, 40f));
      spriteTeemoQ.setImageDimensions(new Vec2i(50, 20));
      GameAnimation spriteTeemoQ1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoQ2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoQ3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoQ4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteTeemoQ.addAnimation(spriteTeemoQ1, 0);
      spriteTeemoQ.addAnimation(spriteTeemoQ2, 1);
      spriteTeemoQ.addAnimation(spriteTeemoQ3, 2);
      spriteTeemoQ.addAnimation(spriteTeemoQ4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/meleePunch.png"));
      spriteMeleePunch = new GameSprite(4, mySprites);
      spriteMeleePunch.setDimensions(new Vec2f(100f, 50f));
      spriteMeleePunch.setImageDimensions(new Vec2i(100, 50));
      GameAnimation spriteMeleePunch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteMeleePunch2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteMeleePunch3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteMeleePunch4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteMeleePunch.addAnimation(spriteMeleePunch1, 0);
      spriteMeleePunch.addAnimation(spriteMeleePunch2, 1);
      spriteMeleePunch.addAnimation(spriteMeleePunch3, 2);
      spriteMeleePunch.addAnimation(spriteMeleePunch4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/crouchRight.png"));
      spriteCrouch = new GameSprite(1, mySprites);
      spriteCrouch.setDimensions(new Vec2f(100f, 150f));
      spriteCrouch.setImageDimensions(new Vec2i(40, 60));
      GameAnimation spriteCrouch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      spriteCrouch.addAnimation(spriteCrouch1, 0);
      
      mySprites = ImageIO.read(new File("res/sprites/BraumcrouchRight.png"));
      spriteBraumCrouch = new GameSprite(1, mySprites);
      spriteBraumCrouch.setDimensions(new Vec2f(100f, 170f));
      spriteBraumCrouch.setImageDimensions(new Vec2i(40, 68));
      GameAnimation spriteCrouch2 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      spriteBraumCrouch.addAnimation(spriteCrouch1, 0);
      mySprites = ImageIO.read(new File("res/sprites/EzrealcrouchRight.png"));
      spriteEzrealCrouch = new GameSprite(1, mySprites);
      spriteEzrealCrouch.setDimensions(new Vec2f(105f, 170f));
      spriteEzrealCrouch.setImageDimensions(new Vec2i(42, 68));
      GameAnimation spriteCrouch3 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      spriteEzrealCrouch.addAnimation(spriteCrouch1, 0);
      mySprites = ImageIO.read(new File("res/sprites/TeemocrouchRight.png"));
      spriteTeemoCrouch = new GameSprite(1, mySprites);
      spriteTeemoCrouch.setDimensions(new Vec2f(100f, 170f));
      spriteTeemoCrouch.setImageDimensions(new Vec2i(40, 68));
      GameAnimation spriteCrouch4 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      spriteTeemoCrouch.addAnimation(spriteCrouch1, 0);
      mySprites = ImageIO.read(new File("res/sprites/YasuocrouchRight.png"));
      spriteYasuoCrouch = new GameSprite(1, mySprites);
      spriteYasuoCrouch.setDimensions(new Vec2f(120f, 185f));
      spriteYasuoCrouch.setImageDimensions(new Vec2i(48, 74));
      GameAnimation spriteCrouch5 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      spriteYasuoCrouch.addAnimation(spriteCrouch1, 0);
      
      mySprites = ImageIO.read(new File("res/sprites/punch.png"));
      spritePunch = new GameSprite(4, mySprites);
      spritePunch.setDimensions(new Vec2f(100f, 200f));
      spritePunch.setImageDimensions(new Vec2i(40, 80));
      GameAnimation spritePunch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spritePunch2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spritePunch3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spritePunch4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spritePunch.addAnimation(spritePunch1, 0);
      spritePunch.addAnimation(spritePunch2, 1);
      spritePunch.addAnimation(spritePunch3, 2);
      spritePunch.addAnimation(spritePunch4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/ezrealPunch.png"));
      spriteEzrealPunch = new GameSprite(4, mySprites);
      spriteEzrealPunch.setDimensions(new Vec2f(130f, 220f));
      spriteEzrealPunch.setImageDimensions(new Vec2i(52, 88));
      GameAnimation spriteEzrealPunch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealPunch2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealPunch3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealPunch4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteEzrealPunch.addAnimation(spriteEzrealPunch1, 0);
      spriteEzrealPunch.addAnimation(spriteEzrealPunch2, 1);
      spriteEzrealPunch.addAnimation(spriteEzrealPunch3, 2);
      spriteEzrealPunch.addAnimation(spriteEzrealPunch4, 3);
      mySprites = ImageIO.read(new File("res/sprites/yasuoPunch.png"));
      spriteYasuoPunch = new GameSprite(4, mySprites);
      spriteYasuoPunch.setDimensions(new Vec2f(345f, 220f));
      spriteYasuoPunch.setImageDimensions(new Vec2i(138, 88));
      GameAnimation spriteYasuoPunch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoPunch2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoPunch3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoPunch4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteYasuoPunch.addAnimation(spriteYasuoPunch1, 0);
      spriteYasuoPunch.addAnimation(spriteYasuoPunch2, 1);
      spriteYasuoPunch.addAnimation(spriteYasuoPunch3, 2);
      spriteYasuoPunch.addAnimation(spriteYasuoPunch4, 3);
      mySprites = ImageIO.read(new File("res/sprites/braumPunch.png"));
      spriteBraumPunch = new GameSprite(4, mySprites);
      spriteBraumPunch.setDimensions(new Vec2f(150f, 200f));
      spriteBraumPunch.setImageDimensions(new Vec2i(60, 80));
      GameAnimation spriteBraumPunch1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumPunch2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumPunch3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumPunch4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBraumPunch.addAnimation(spriteBraumPunch1, 0);
      spriteBraumPunch.addAnimation(spriteBraumPunch2, 1);
      spriteBraumPunch.addAnimation(spriteBraumPunch3, 2);
      spriteBraumPunch.addAnimation(spriteBraumPunch4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/shoot.png"));
      spriteShoot = new GameSprite(4, mySprites);
      spriteShoot.setDimensions(new Vec2f(100f, 200f));
      spriteShoot.setImageDimensions(new Vec2i(40, 80));
      GameAnimation spriteShoot1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteShoot2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteShoot3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteShoot4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteShoot.addAnimation(spriteShoot1, 0);
      spriteShoot.addAnimation(spriteShoot2, 1);
      spriteShoot.addAnimation(spriteShoot3, 2);
      spriteShoot.addAnimation(spriteShoot4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/teemoShootRight.png"));
      spriteTeemoShoot = new GameSprite(4, mySprites);
      spriteTeemoShoot.setDimensions(new Vec2f(100f, 220f));
      spriteTeemoShoot.setImageDimensions(new Vec2i(40, 88));
      GameAnimation spriteTeemoShoot1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoShoot2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoShoot3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoShoot4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteTeemoShoot.addAnimation(spriteTeemoShoot1, 0);
      spriteTeemoShoot.addAnimation(spriteTeemoShoot2, 1);
      spriteTeemoShoot.addAnimation(spriteTeemoShoot3, 2);
      spriteTeemoShoot.addAnimation(spriteTeemoShoot4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/stand.png"));
      spriteStand = new GameSprite(4, mySprites);
      spriteStand.setDimensions(new Vec2f(100f, 200f));
      spriteStand.setImageDimensions(new Vec2i(40, 80));
      GameAnimation spriteStand1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteStand2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteStand3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteStand4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteStand.addAnimation(spriteStand1, 0);
      spriteStand.addAnimation(spriteStand2, 1);
      spriteStand.addAnimation(spriteStand3, 2);
      spriteStand.addAnimation(spriteStand4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/ezrealStandRight.png"));
      spriteEzrealStand = new GameSprite(4, mySprites);
      spriteEzrealStand.setDimensions(new Vec2f(110f, 220f));
      spriteEzrealStand.setImageDimensions(new Vec2i(44, 88));
      GameAnimation spriteStand5 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteStand6 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteStand7 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteStand8 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteEzrealStand.addAnimation(spriteStand5, 0);
      spriteEzrealStand.addAnimation(spriteStand6, 1);
      spriteEzrealStand.addAnimation(spriteStand7, 2);
      spriteEzrealStand.addAnimation(spriteStand8, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/braumStandRight.png"));
      spriteBraumStand = new GameSprite(4, mySprites);
      spriteBraumStand.setDimensions(new Vec2f(100f, 200f));
      spriteBraumStand.setImageDimensions(new Vec2i(40, 80));
      GameAnimation spriteStand9 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteStand10 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteStand11 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteStand12 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBraumStand.addAnimation(spriteStand9, 0);
      spriteBraumStand.addAnimation(spriteStand10, 1);
      spriteBraumStand.addAnimation(spriteStand11, 2);
      spriteBraumStand.addAnimation(spriteStand12, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/yasuoStandRight.png"));
      spriteYasuoStand = new GameSprite(4, mySprites);
      spriteYasuoStand.setDimensions(new Vec2f(150f, 240f));
      spriteYasuoStand.setImageDimensions(new Vec2i(60, 96));
      GameAnimation spriteStand13 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteStand14 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteStand15 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteStand16 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteYasuoStand.addAnimation(spriteStand13, 0);
      spriteYasuoStand.addAnimation(spriteStand14, 1);
      spriteYasuoStand.addAnimation(spriteStand15, 2);
      spriteYasuoStand.addAnimation(spriteStand16, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/teemoStandRight.png"));
      spriteTeemoStand = new GameSprite(4, mySprites);
      spriteTeemoStand.setDimensions(new Vec2f(100f, 220f));
      spriteTeemoStand.setImageDimensions(new Vec2i(40, 88));
      GameAnimation spriteStand17 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteStand18 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteStand19 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteStand20 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteTeemoStand.addAnimation(spriteStand17, 0);
      spriteTeemoStand.addAnimation(spriteStand18, 1);
      spriteTeemoStand.addAnimation(spriteStand19, 2);
      spriteTeemoStand.addAnimation(spriteStand20, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/walk.png"));
      spriteWalk = new GameSprite(4, mySprites);
      spriteWalk.setDimensions(new Vec2f(100f, 200f));
      spriteWalk.setImageDimensions(new Vec2i(40, 80));
      GameAnimation spriteWalk1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteWalk2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteWalk3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteWalk4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteWalk.addAnimation(spriteWalk1, 0);
      spriteWalk.addAnimation(spriteWalk2, 1);
      spriteWalk.addAnimation(spriteWalk3, 2);
      spriteWalk.addAnimation(spriteWalk4, 3);
      
      mySprites = ImageIO.read(new File("res/sprites/ezrealWalkRight.png"));
      spriteEzrealWalk = new GameSprite(4, mySprites);
      spriteEzrealWalk.setDimensions(new Vec2f(100f, 220f));
      spriteEzrealWalk.setImageDimensions(new Vec2i(40, 88));
      GameAnimation spriteEzrealWalk1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealWalk2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealWalk3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteEzrealWalk4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteEzrealWalk.addAnimation(spriteEzrealWalk1, 0);
      spriteEzrealWalk.addAnimation(spriteEzrealWalk2, 1);
      spriteEzrealWalk.addAnimation(spriteEzrealWalk3, 2);
      spriteEzrealWalk.addAnimation(spriteEzrealWalk4, 3);
      mySprites = ImageIO.read(new File("res/sprites/yasuoWalkRight.png"));
      spriteYasuoWalk = new GameSprite(4, mySprites);
      spriteYasuoWalk.setDimensions(new Vec2f(150f, 240f));
      spriteYasuoWalk.setImageDimensions(new Vec2i(60, 96));
      GameAnimation spriteYasuoWalk1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoWalk2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoWalk3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteYasuoWalk4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteYasuoWalk.addAnimation(spriteYasuoWalk1, 0);
      spriteYasuoWalk.addAnimation(spriteYasuoWalk2, 1);
      spriteYasuoWalk.addAnimation(spriteYasuoWalk3, 2);
      spriteYasuoWalk.addAnimation(spriteYasuoWalk4, 3);
      mySprites = ImageIO.read(new File("res/sprites/braumWalkRight.png"));
      spriteBraumWalk = new GameSprite(4, mySprites);
      spriteBraumWalk.setDimensions(new Vec2f(110f, 200f));
      spriteBraumWalk.setImageDimensions(new Vec2i(44, 80));
      GameAnimation spriteBraumWalk1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumWalk2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumWalk3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteBraumWalk4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteBraumWalk.addAnimation(spriteBraumWalk1, 0);
      spriteBraumWalk.addAnimation(spriteBraumWalk2, 1);
      spriteBraumWalk.addAnimation(spriteBraumWalk3, 2);
      spriteBraumWalk.addAnimation(spriteBraumWalk4, 3);
      mySprites = ImageIO.read(new File("res/sprites/teemoWalkRight.png"));
      spriteTeemoWalk = new GameSprite(4, mySprites);
      spriteTeemoWalk.setDimensions(new Vec2f(100f, 220f));
      spriteTeemoWalk.setImageDimensions(new Vec2i(40, 88));
      GameAnimation spriteTeemoWalk1 = new GameAnimation(new Vec2f(0, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoWalk2 = new GameAnimation(new Vec2f(1, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoWalk3 = new GameAnimation(new Vec2f(2, 0), new Vec2f(0, 0));
      GameAnimation spriteTeemoWalk4 = new GameAnimation(new Vec2f(3, 0), new Vec2f(0, 0));
      spriteTeemoWalk.addAnimation(spriteTeemoWalk1, 0);
      spriteTeemoWalk.addAnimation(spriteTeemoWalk2, 1);
      spriteTeemoWalk.addAnimation(spriteTeemoWalk3, 2);
      spriteTeemoWalk.addAnimation(spriteTeemoWalk4, 3);
      
      File music = new File("res/music/trndsttr.wav");
      ClipPlayer.addClip("trndsttr", music);
      music = new File("res/music/seeYou.wav");
      ClipPlayer.addClip("seeYou", music);
      music = new File("res/music/itGMa.wav");
      ClipPlayer.addClip("itGMa", music);
      music = new File("res/music/shelter.wav");
      ClipPlayer.addClip("shelter", music);
      music = new File("res/music/warriors.wav");
      ClipPlayer.addClip("warriors", music);
      music = new File("res/music/whispy.wav");
      ClipPlayer.addClip("whispy", music);
      music = new File("res/music/wonderfulLife.wav");
      ClipPlayer.addClip("wonderfulLife", music);
      Application a = new Application("Final",false);
      a.setScreen(new MenuScreen(a, "Welome to Super Smash Legends! Use up and down, and primary attack to select a character, Enter to start", 0, new Vec2i(960, 540), true));
      a.startup(); // begin processing events
      } catch (InvalidLevelException e) {
        System.out.println("Invalid leveldata file");
    } catch (IOException e){
      System.out.println("Invalid ImageIO file");
    } catch (FontFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
