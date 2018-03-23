
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageSu {

    private Icon[] ia;

    public ImageSu() {
        initImage();
    }

    private void initImage() {
        ia = new ImageIcon[12];
        ia[0] = new ImageIcon(getClass().getResource("/number/0.png"));
        for (int i = 1; i < 12; i++) {
            ia[i] = new ImageIcon(getClass().getResource("/number/" + String.valueOf((int) Math.pow(2, i)) + ".png"));
        }
    }

    public Icon[] getIa() {
        return ia;
    }

    public void setIa(Icon[] ia) {
        this.ia = ia;
    }

}
