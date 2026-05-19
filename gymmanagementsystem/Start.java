import gui.MemberGUI;

public class Start {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MemberGUI().setVisible(true);
        });
    }
}