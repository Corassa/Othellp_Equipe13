package display;

import javax.swing.JButton;
import jogo.Casa;

/**
 *
 * @author Marcelo Paglione
 */
public class ButtonCasa {

    private JButton button;
    private Casa casa;
    private boolean mouseListener;

    public ButtonCasa() {
        casa = new Casa();
        button = new JButton("");
        mouseListener = true;
    }

    public void setMouseListener(boolean mouseListener) {
        this.mouseListener = mouseListener;
    }

    public boolean getMouseListener() {
        return mouseListener;
    }

    public Casa getCasa() {
        return casa;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }
}
