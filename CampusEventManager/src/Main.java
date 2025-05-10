public class Main {
  public static void main(String[] args) {
      Database.getInstance().loadAll();

      MainGUI m = new MainGUI();
      m.setVisible(true);
  }
}
