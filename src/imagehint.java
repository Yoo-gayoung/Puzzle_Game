import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.JButton;


class imagehint extends Thread {
	
	//�̹����� ��Ʈ�� �ϴ°����� J��ư��� �̹������� ��θ� �޾Ƽ� 
	//�� ��ư�鿡 �̹����� �ٽ� �ѹ� setting���ش� 
	JButton change[];
	String chagepath[];
	imagehint(JButton [] J, String name[]) {
		change = J;
		chagepath = name;
	}


	public void run() {
		for (int i = 0; i < change.length; i++) {
			change[i].setIcon(new ImageIcon(chagepath[i]));
			change[i].setBackground(Color.pink); //��Ʈ back ground
		}
	}
}
