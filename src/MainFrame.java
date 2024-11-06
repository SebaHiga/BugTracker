import javax.swing.*;

public class MainFrame extends JFrame{
    public void mostrar() {
        JOptionPane.showMessageDialog(this, "Mensaje de error",
                "titulo ventana error", JOptionPane.ERROR_MESSAGE);
    }
}
