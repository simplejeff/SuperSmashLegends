package jmhao.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * MapParser to parse the map file into 2d arrays
 * @author jmhao
 *
 */
public class MapParser {
  File myFile;
  int[][] playerArray;
  int[][] mapArray;
  int width, length;
  public MapParser(File file) {
    myFile = file;
  }
  /**
   * getter for playerArray
   * @return playerArray
   */
  public int[][] getPlayer() {
    return playerArray;
  }
  /**
   * getter for mapArray
   * @return mapArray
   */
  public int[][] getMap() {
    return mapArray;
  }
  /**
   * scans its file and converts it into the arrays
   * @throws ParseGameException
   * @throws IOException
   */
  public void scanFile() throws ParseGameException, IOException {
    try(BufferedReader scanner = new BufferedReader(new FileReader(myFile))) {
      String line;
      if((line = scanner.readLine()) != null) {
        String[] first = line.split("\\s+");
        if(first.length != 2) {
          scanner.close();
          throw new ParseGameException("Invalid format for size on first line");
        }
        width = Integer.parseInt(first[0]);
        length = Integer.parseInt(first[1]);
      } else {
        scanner.close();
        throw new ParseGameException("Not enough information in file, line : " + 0);
      }
      playerArray = new int[length][width];
      mapArray = new int[length][width];
      String[] tileStart;
      if((line = scanner.readLine()) != null) {
        tileStart = line.split("\\s+");
      } else {
        scanner.close();
        throw new ParseGameException("Not enough information in file, line : " + 1);
      }
      if(tileStart.length < 1 || !tileStart[0].equals("START")) {
        scanner.close();
        throw new ParseGameException("Invalid start for tileArray");
      }
      for(int i = 0; i < length; i++) {
        String[] row = scanner.readLine().split("\\.");
        int rowLen = row.length;
        if(rowLen != width) {
          scanner.close();
          throw new ParseGameException("Invalid length for tileArray parsing map line " + i);
        }
        for(int j = 0; j < width; j++) {
          mapArray[i][j] = Integer.parseInt(row[j]);
        }
      }
      String[] tileEnd = scanner.readLine().split("\\s+");
      if(tileEnd.length < 1 || !tileEnd[0].equals("END")) {
        scanner.close();
        throw new ParseGameException("Invalid end for tileArray");
      }
      String[] playerStart = scanner.readLine().split("\\s+");
      if(playerStart.length < 1 || !playerStart[0].equals("START")) {
        scanner.close();
        throw new ParseGameException("Invalid start for playerArray");
      }
      for(int i = 0; i < length; i++) {
        String[] row = scanner.readLine().split("\\.");
        int rowLen = row.length;
        if(rowLen != width) {
          scanner.close();
          throw new ParseGameException("Invalid length for playerArray parsing map line " + i);
        }
        for(int j = 0; j < width; j++) {
          playerArray[i][j] = Integer.parseInt(row[j]);
        }
      }
      String[] playerEnd = scanner.readLine().split("\\s+");
      if(playerEnd.length < 1 || !playerEnd[0].equals("END")) {
        scanner.close();
        throw new ParseGameException("Invalid end for playerArray");
      }
      scanner.close();
    }
  }
}
