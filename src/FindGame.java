import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

// borer�� ����ϱ� ���ؼ� import ��Ŵ
import javax.swing.border.*;

//�̰����� 99�ʾȿ� ��� ī�带 ����� ¦�� ���ߴ� �����Դϴ�.
//hint�� ������ ī�带 ����� �����ֽ��ϴ�. �� hint�� ������ -20���� ���ϰ� �˴ϴ�.  
//���� �����������ԵǸ� ���̵��� ���� easy, normal, hard���� 
//4x4, 4x5, 4x6�������� ũ�⸦ ������ �� �ֽ��ϴ�.
//�ѹ� ���ߴ°��� �����Ҷ����� -5���� ���ϰ� �˴ϴ�. 

class FindGame extends JFrame implements ActionListener {
	// static final ���
	private static final String SCORE = "��";
	private static final String REMAIN = "��";
	private static final String TIMER = "��";
	private static final String VALUE = "0";

	// JLabel
	// ��ȭ�� ���� ������ ���� �ʿ�
	private JLabel jlblScore_Value;
	private JLabel jlblRemain_Value;
	private JLabel jlblTimer_Value;
	private JLabel jlblScore;
	private JLabel jlblRemain;
	private JLabel jlblTimer;
	private JLabel jname;

	TimeThread thread;
	
	
	
	private JButton hint;
	private JButton newgame;
	
	// JToggleButton
	private JButton[] coverBtns;
	// private JButton[] showBtns;
	public int btn_Item;

	// GridLayout
	private int row;
	private int col;

	// ��ư�� ���¸� ����

	private int[] btnstate;
	GameBtns btnpatten;
	private String[] RanOutput;
	int temp1 = -1, temp2 = -1;
	int nowremain;
	int nowscore;
	static int scores =0;
	
	// ������
	FindGame() {
		super("¦ ���߱� ���� ����");
		this.init();
		this.setLayout();
		this.setBtns(true);
		this.addListener();
		this.showJFrame();

	}

	FindGame(JDialog owner, int row, int col) {
		super("¦ ���߱� ���� ����");
		this.row = row;
		this.col = col;
		this.init();
		this.setLayout();
		//this.setBackground(Color.red);
		
		this.setBtns(this.row, this.col, true);
		this.setInit();
		this.addListener();
		this.showJFrame();
		this.initgame();
		
	}

	// �ʱ�ȭ
	private void init() {
		this.nowremain = 0;
		this.nowscore = 0;
		this.btnstate = new int[100];
		for (int i = 0; i < 100; i++) {
			this.btnstate[i] = 0;
		}
		this.btnpatten = new GameBtns();


		// JLabel �ʱ�ȭ ������ ����� ���
		this.jlblScore_Value = new JLabel(VALUE);
		this.jlblRemain_Value = new JLabel(VALUE);
		this.jlblTimer_Value = new JLabel(VALUE);

		this.jlblScore = new JLabel(SCORE);
		this.jlblRemain = new JLabel(REMAIN);
		this.jlblTimer = new JLabel(TIMER);
		this.jname = new JLabel("by �̼���, ������, ������");

		
		//���� ��ư�� ���� ���� ��ư �̴�. 
		this.hint = new JButton("��Ʈ ����");
		this.newgame = new JButton("���� ����");
		this.hint.addActionListener(this);
		this.newgame.addActionListener(this);
		
		hint.setBackground(Color.pink);
		newgame.setBackground(Color.pink);
	}

	// ��ư��� ���̾ƿ����� ��ư ��ġ �Լ� 
	private void setLayout() {

		// �� JPanel���� �����ϱ� ���� �ֻ��� JPanel�� jpnlEast�� FlowLayout�� GridLayout�� ����
		JPanel jpnlEast = new JPanel(new GridLayout(0, 1));
		JPanel jmenu= new JPanel(new FlowLayout());
		JPanel jpnlScore = new JPanel();
		JPanel jpnlRemain = new JPanel();
		JPanel jpnlTimer = new JPanel();
		JPanel jhint = new JPanel();
		JPanel jnewgame = new JPanel();
		JPanel jlabel= new JPanel();
		
		// ������ JPanel�� �˸°� �ش��ϴ� ������Ʈ�� ����
		// setBorder()�� �̿��Ͽ� ���� �ش��ϴ� TiltedBorder() �Ӽ��� �ο�

		//������ ���� �ִ� ī���� ���� Ÿ�̸� ���� �׸��� ���������� ���� ���� ��ư�� ���̾ƿ� ��ġ��
		//�����Ѵ� 
		jlabel.add(jname);
		
		jpnlScore.add(jlblScore_Value);
		jpnlScore.add(jlblScore);
		jpnlScore.setBorder(new TitledBorder("��������"));

		jpnlRemain.add(jlblRemain_Value);
		jpnlRemain.add(jlblRemain);
		jpnlRemain.setBorder(new TitledBorder("�ڳ��� ���� ������"));

		jpnlTimer.add(jlblTimer_Value);
		jpnlTimer.add(jlblTimer);
		jpnlTimer.setBorder(new TitledBorder("��Ÿ�̸ӡ�"));
		
		jhint.add(hint);
		jnewgame.add(newgame);
		// ������ ���� �ֻ��� JPanel�� ������ JPanel�� ����
		jpnlEast.add(jpnlScore);
		jpnlEast.add(jpnlRemain);
		jpnlEast.add(jpnlTimer);
		jmenu.add(jnewgame);
		jmenu.add(jhint);
		jmenu.add(jname);

		// �ֻ��� JPanel�� BorderLauout�� �̿��Ͽ� EAST�� ����
		this.add(jmenu, BorderLayout.NORTH);
		this.add(jpnlEast, BorderLayout.SOUTH);
		
		jmenu.setBackground(new Color(255,217,236));
		jpnlScore.setBackground(new Color(255,217,236));
		jpnlRemain.setBackground(new Color(255,217,236));
		jpnlTimer.setBackground(new Color(255,217,236));
		jhint.setBackground(new Color(255,217,236));
		jnewgame.setBackground(new Color(255,217,236));
		
	}

	private void addListener() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				// �����ư Ŭ���� �ƹ��� ������ ���� �ʴ´�.
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				// JOptionPane�� �̿��Ͽ� showConfirmDialog�� �����ְ� �� �ش簪�� choice��
				// �����Ѵ�.
				int choice = JOptionPane.showConfirmDialog(FindGame.this,
						"������ �����ڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);

				if (choice == 0) {
					// �����ư�� Ŭ���� �����Ѵ�.
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});

		// ��ư�� �׼��� �߰�
		this.InsertAction();
	}

	//��ư���� 
	private void showJFrame() {
		this.pack();
		this.setLocation(200, 200);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if(o == this.hint) {
			hintgame();
			initgame();
			scores -= 20;
		}else if(o == this.newgame){
			new StartDlg(this);
		}
		
		for (int i = 0; i < this.btn_Item; i++) {
			if (o.equals(this.coverBtns[i])) { // ������ ��ư�� ������ ����,
				doing(i);
			}
		}
	}

	// ó�� ������ �����Ͽ����� �̹������� ���� �ϴ� �κ��̴�. 
	//������ �����ϱ����� ���·μ� ��ư���� �����Ѵ� 
	private void setBtns(boolean flag) {

		this.row = 4;
		this.col = 4;
		this.btn_Item = this.row * this.col;

		this.coverBtns = new JButton[this.btn_Item];
		for (int index = 0; index < btn_Item; index++) {
			//ó�� �̹������� ��� �� �̹����� �����Ѵ� 
			this.coverBtns[index] = new JButton(
					new ImageIcon("./Image/que.PNG"));
			this.coverBtns[index].setEnabled(false);
			this.coverBtns[index].setBackground(Color.pink);//////////��ư���ٲ�(ùȭ��)
		}
		JPanel jpnlCenter = new JPanel(new GridLayout(this.row, this.col, 10,
				10));
		int index = 0;
		for (JButton temp : this.coverBtns) {
			jpnlCenter.add(temp);
			index++;
		}
		this.add(jpnlCenter, BorderLayout.CENTER);
		jpnlCenter.setBackground(new Color(255,217,236));
		
	}


	//���� ���� �Ҷ� ���� �ʱ�ȭ
	public void initbtnstate(int size) { 
		for (int i = 0; i < size; size++) {
			this.btnstate[i] = 0;
		}
	}

	// ��ư �ϳ��� Ŭ���Ұ�� ��ư�� id �ѹ��� ��id�� �´� �̹����� �־��ش� 
	private void showsign(JButton btn, String str, int i) { 
		btn.setIcon(new ImageIcon(str));					
		this.btnstate[i] = 1;
	}

	//��ư�� ���������� �� ���¸� �ο��̺�� �ٲ㼭 �����͵��� Ŭ������ �ʰ� �Ѵ� 
	private void showgreen(JButton btn) { 
		btn.setEnabled(false);
	}

	//ó�� �̹������� �����ְ� �� �̹����� �� ǥ�÷� �Ͽ� ī�带 ���´� 
	private void initgame() {
		imageThread thread = new imageThread(coverBtns, "./Image/que.png");
		thread.setDaemon(true);
		thread.start();
	}
	
	//��Ʈ�� ������ �Լ��� �̹����� ����ִ� �迭�� �̹��� ��ư�鿡 �°� �̹����� �ٽ� �ѹ� 
	//�����ְ� �ȴ�.
	private void hintgame() {
		imagehint thread = new imagehint(coverBtns, RanOutput);
		thread.setDaemon(true);
		thread.start();
	}
	
	//��ư �ϳ��� ����, ������� ���ư���.
	private void showorigin(JButton btn) { 
		btn.setIcon(new ImageIcon("./Image/que.png"));
		btn.setBackground(Color.black);
	}

	//���� ������ ��ư���� ������ ���� 
	private int howmany(int size) { 
		int howmany = 0;
		for (int i = 0; i < size; i++) {
			if (this.btnstate[i] == 1)
				howmany++;
		}
		return howmany; 
	}
	// �ΰ� �������� �� ���õ� �� ��ư�� ��ġ ������ �޾Ƽ� �� ��ư�� ���Ͽ�  ������ 
	//�ٸ����� ó���ϴ� �Լ��ν� ������� score�� ���� ���� ������ ���� ��Ű��
	//�ð����� ���ͼ� �� �ð��� ������ ȯ���ؼ� �ش� 
	private void ispair(int A, int B) { 

		String btn1 = this.RanOutput[A];
		String btn2 = this.RanOutput[B];

		//��ư�� id���� ������� ó�� 
		if (btn1.equals(btn2)) { 
	
			int remain = (this.btn_Item / 2)-this.nowremain-1;////////////////////////////////����������ħ
			
			
			if(remain == 0){//////////
				scores += timer(Integer.parseInt(this.jlblTimer_Value.getText()));	
			}
			
			scores += 20;
			this.nowremain++;
			this.jlblRemain_Value.setText(String.valueOf(remain));
			this.jlblScore_Value.setText(String.valueOf(this.scores));
			this.showgreen(this.coverBtns[A]);
			this.showgreen(this.coverBtns[B]);
			this.btnstate[A] = 2;
			this.btnstate[B] = 2;
		
		} else { 
			//��ư�� id���� �ٸ���� ó�� 
			scores -= 5;
			this.jlblScore_Value.setText(String.valueOf(this.scores));
			this.nowscore = 5;
			this.btnstate[A] = 1;
			this.btnstate[B] = 1;
		}
	}

	public void getpatten(int size) { 
		// �̹��� ��ư�� �������� �����Ͽ� �̹��� �����´� 
		this.RanOutput = this.btnpatten.RanArray(size);
	}
	
	private void doing(int i) { 
		// ��ư �ϳ��� ������ �� ����Ǵ� ���۵�. i�� �迭 ��ġ.
		this.showsign(this.coverBtns[i], this.RanOutput[i], i);
		if (howmany(this.btn_Item) == 2) { 
			// ������ �ΰ��� �������� ������,
			for (int a = 0; a < this.btn_Item; a++) { 
				// �ΰ��� ��ġ ������ ��,
				if (this.btnstate[a] == 1 && a != i) {
					this.temp1 = a;
				}
			}

			this.temp2 = i;
			this.ispair(temp1, temp2); // ������ ������, �ٸ��� ? �̹����� �ִ´� 
		}

		if (howmany(this.btn_Item) == 3) {
			// ���������� 3���̸� �ΰ��� ������ �ϳ��� ������ �ʰ� ����� 
			for (int a = 0; a < this.btn_Item && a != i; a++) {
				if (this.btnstate[a] == 1 && temp1 == -1)
					this.temp1 = a;
			}

			for (int a = 0; a < this.btn_Item && a != i && a != this.temp1; a++) {
				if (this.btnstate[a] == 1)
					this.temp2 = a;
			}
			
			// ���� �ٽ� ������,
			showorigin(this.coverBtns[temp1]); 
			showorigin(this.coverBtns[temp2]);
			this.btnstate[temp1] = 0;
			this.btnstate[temp2] = 0;
		}

	}

	//�ð����� ���� ������ �����ش� 
	private int timer(int i) {
		if(100>= i && 90 <=i){
			return 10;
		}else if(100>= i && 90 <=i){
			return 20;
		}else if(89>= i && 80 <=i){
			return 30;
		}else if(79>= i && 70 <=i){
			return 40;
		}else if(69>= i && 60 <=i){
			return 50;
		}else if(59>= i && 50 <=i){
			return 60;
		}else if(49>= i && 40 <=i){
			return 70;
		}else if(39>= i && 30 <=i){
			return 80;
		}else if(29>= i && 20 <=i){
			return 90;
		}else if(19>= i && 10 <=i){
			return 100;
		}else if(9>= i && 0 <=i){
			return 110;
		}
		return i;
		
	}
	public void setBtns(int row, int col, boolean flag) {
//		if (RanOutput == null)
			this.getpatten(row * col); // �̹��� �������� �̹����� �Ѹ��� �� ���� 
										//�����ϰ� �� �Ŀ� null�� �ƴҰ�� �������� �ʴ´� 
		
		this.row = row;
		this.col = col;
		this.btn_Item = this.row * this.col;

		this.coverBtns = new JButton[this.btn_Item];
		for (int index = 0; index < btn_Item; index++) {
			// ó�� ������ ����
			this.coverBtns[index] = new JButton(new ImageIcon(RanOutput[index]));
			this.coverBtns[index].setEnabled(true);
		}

		JPanel jpnlCenter = new JPanel(new GridLayout(this.row, this.col, 10,
				10));
		int index = 0;
		for (JButton temp : this.coverBtns) {
			// System.out.println(index);
			jpnlCenter.add(temp);
			index++;
		}
		this.add(jpnlCenter, BorderLayout.CENTER);
		jpnlCenter.setBackground(new Color(255,217,236));
	}

	//ó�� text�� �ʱ�ȭ �����ָ� �ð��� ���� ��Ų�� 
	private void setInit() {
		this.jlblRemain_Value.setText(String.valueOf(this.btn_Item / 2));
		this.jlblScore_Value.setText(String.valueOf(this.scores));
		
		thread = new TimeThread(this);
		thread.setDaemon(true);
		thread.start();
		
	}
	
	//�ð��� ���� �־��ִ� �Լ��̴�. 
	public void setTime(int timer) {
		this.jlblTimer_Value.setText(String.valueOf(timer));
	}
	
	// ��ư�� �׼� �߰�
	private void InsertAction() {
		for (int index = 0; index < this.btn_Item; index++) {
			coverBtns[index].addActionListener(this);
			coverBtns[index].setActionCommand(String.valueOf(index));
			coverBtns[index].setBackground(Color.pink);
		}
	}

	//main
	public static void main(String[] args) {
		new FindGame();
		
		
	}
}
