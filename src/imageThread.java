import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;

import sun.awt.windows.ThemeReader;


class imageThread extends Thread {
	
	//�̹����� ������.
	//ó�� �������̹��� ���� �� �̹����� �Ͽ� �̹����� ���´�. 
	JButton change[];
	String chagepath;
	imageThread(JButton [] J, String name) {
		change = J;
		chagepath = name;
	}


	public void run() {
		try {
			Thread.sleep(5000); //5�� �ڿ� �̹������� ��� ���� �ٲ��.
			for (int i = 0; i < change.length; i++) {
				change[i].setIcon(new ImageIcon(chagepath));
				change[i].setBackground(Color.black); //Start�κ� ���
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}