import javax.swing.*;

public class MainFrame extends JFrame{
    private JFrame mainFrame;

    MainFrame(){
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void displayLoginPane() {
        this.mainFrame.getContentPane().add(new PanelLogin());
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
}
