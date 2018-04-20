import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageShower extends JFrame {
    public static void main(String[] args) {
        new ImageShower();
    }

    public ImageShower() {
        this.setTitle("Picture Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        ImageIcon pic = new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02a.png");
        panel1.add(new JLabel(pic));
        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }
}