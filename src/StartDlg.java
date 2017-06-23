import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


class StartDlg extends JDialog implements ActionListener{
	// ���� ���� â�� �׷�
	private ButtonGroup groupSetting;
	// Default�� â�� �׷�
	private ButtonGroup groupDefault;
	
	// ���� ����â�� ���� ��ư
	private JRadioButton jDelfault;
	private JRadioButton jCostomer;
	
	// Default�� â�� ���� ��ư
	private JRadioButton jEasy;
	private JRadioButton jNomar;
	private JRadioButton jHard;
	
	
	// Ok�� Cancel��ư
	private JButton jBtnOk;
	private JButton jBtnCancel;
	
	// ��Ʈ ���
	private Font myFont;
	

	// �� �ޱ�
	private int row_value;
	private int col_value;

	// üũ�� ���� �ڽ�
	private boolean check;

	// Ȯ�� ��ư Ŭ��
	private boolean click;

	private FindGame owner;

	private Font myFont(int font){
		this.myFont = new Font("Dialog", Font.BOLD, font);
		return myFont;
	}

	public StartDlg(JFrame owner){
		super(owner,"", false);
		this.owner = (FindGame)owner;
		this.init();
		this.setLayout();
		this.addListener();
		this.showJDlg();

	}
	private void init(){

		// �׷�
		this.groupSetting = new ButtonGroup();
		this.groupDefault = new ButtonGroup();
		
		// ���� â
		this.jDelfault = new JRadioButton("Default");
		// ��ư�� ���� ���·� ����.
		this.jDelfault.doClick();
		this.jCostomer = new JRadioButton("Costomer");
		
		// Default�� ��������� �����ϴ°�
		this.jEasy = new JRadioButton("4x4");
		this.jNomar = new JRadioButton("4x5");
		// �����ڸ� �̿��Ͽ� ��ư�� ���� ���·� ����.
		this.jHard = new JRadioButton("4x6", true);
		// ��ư��
		this.jBtnOk = new JButton("����");
		this.jBtnCancel = new JButton("���");
	
	}
	private void setLayout(){
		//���� ������ ���̾�α� ���̾ƿ� ��ġ���̴�. 
		JPanel big = new JPanel(new GridLayout(0, 1));
	
		JPanel pnlDefault = new JPanel(new GridLayout(0, 1));
		pnlDefault.setBorder(new TitledBorder("���� ũ�� ���� \n"));
		groupDefault.add(jEasy);
		groupDefault.add(jNomar);
		groupDefault.add(jHard);
		pnlDefault.add(jEasy);
		pnlDefault.add(jNomar);
		pnlDefault.add(jHard);
		big.add(pnlDefault);
		
		JPanel pnlBtn = new JPanel(); 
		pnlBtn.add(jBtnOk);
		pnlBtn.add(jBtnCancel);
		
		this.add(pnlBtn, BorderLayout.SOUTH);
		this.add(big,BorderLayout.CENTER);
		
	}
	//�� ���� ��ư�� ok, cancle�� ���� ��ư���� ����� �Ѵ� 
	private void addListener(){
	
		this.addWindowListener(new WindowAdapter(){
			public void WindowClosing(WindowEvent we){
				dispose();
		
			}
		});

		// ���� �̺�Ʈ ó��
		this.jDelfault.addActionListener(this);


//		 Ok�̺�Ʈ ó��(����, �ڸ�, Ȧ��, ¦��)
		this.jBtnOk.addActionListener(this);
		this.jBtnCancel.addActionListener(this);
		
		// ���߿� �� �̺�Ʈ ����
		this.jEasy.addActionListener(this);
		this.jNomar.addActionListener(this);
		this.jHard.addActionListener(this);
	}

	private void showJDlg(){
		this.pack();
		this.setLocation(200, 100);
		this.setVisible(true);
	}

	//�� ��ư���� ��������� getsource�� ���ؼ� ��ư�� ��ü�� id���� �����´� 
	public void actionPerformed(ActionEvent ae){
		Object o = ae.getSource();
				
		if(this.jDelfault.isSelected()) {

			this.jEasy.setEnabled(true);
			this.jNomar.setEnabled(true);
			this.jHard.setEnabled(true);

			//���̵��� ���� ���� ���� �̴�. 
			if(this.jEasy.isSelected()) {
				this.row_value = 4;
				this.col_value = 4;
			} else if(this.jNomar.isSelected()) {
				this.row_value = 4;
				this.col_value = 5;
			} else if(this.jHard.isSelected()) {
				this.row_value = 4;
				this.col_value = 6;
			}

			if(o == this.jBtnOk) { //Ok ��ư�� ������ ��. ������ ������ ���̾� �α�â �����.
				this.owner.setBtns(this.row_value, this.col_value, true);
				new FindGame(this, this.row_value, this.col_value);
				this.owner.dispose();
				this.dispose();
			}
		}else if(o == this.jBtnCancel){
			this.dispose();
		}

	}
}