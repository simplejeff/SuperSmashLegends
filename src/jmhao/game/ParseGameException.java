package jmhao.game;

@SuppressWarnings("serial")
/**
 * ParseGameException handles parsing exceptions of map files
 * @author jmhao
 *
 */
public class ParseGameException extends Exception {
  public ParseGameException(){}
  public ParseGameException(String message) {
    super(message);
  }
}
