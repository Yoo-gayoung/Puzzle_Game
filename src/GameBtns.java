import javafx.scene.paint.Color;

class GameBtns {
	
	//defalut ������ 
	public GameBtns() {}

	public String[] RanArray(int size) {
		//�̹����� ������ ũ�⸦ ���Ѵ� �� 1���� �̹����� 2���� ���� ī�带 �����Ѵ� 
		String RanFix[] = { "./Image/1.png", "./Image/2.png", "./Image/3.png",
				"./Image/4.png", "./Image/5.png", "./Image/6.png",
				"./Image/7.png", "./Image/8.png", "./Image/9.png",
				"./Image/10.png", "./Image/11.png", "./Image/12.png",
				"./Image/13.png", "./Image/14.png", "./Image/15.png",
				"./Image/16.png" };

		//random���� ������ �̹������� path�� ���� ���� �̴�. 
		
		String RanOutput[];
		//size/2 ������ �迭�� ������ �ͼ�, ������ ���� �迭�� �ִ´� 
		int num = size / 2;
		int tempCount = 0;
		int temp = 0;
		//�������� �̾Ҵ��� �ƴ����� �������� flag�����̴� 
		boolean flag = false;
		//size���� ��ŭ String �迭�� �����Ͽ� �ʱ�ȭ ���ش� 
		RanOutput = new String[size];

		//for���� ���� random���� String�� ��ü�� �����Ͽ� �̹��� �������� ������ �غ� �Ѵ� 
		for (int i = 0; i < size; i++) {
			do {
				tempCount = 0;
				flag = false;
				temp = (int) (Math.random() * num); // 0~num-1 ���� ���� ���ڸ� �ϳ� �̾Ƽ�,
				RanOutput[i] = new String(RanFix[temp]);

				for (int j = 0; j < i; j++) { // �ߺ� �˻�. �ΰ����� ����Ѵ� 
					if (RanOutput[i].equals(RanOutput[j])) { // �������� �迭�� �����Ѱ� ����
																// ����,
						tempCount++;
						if (tempCount > 1) { // ���� ���� ���� �ִٸ�
							flag = true; // �ٽ� �̴´�. flag �����Ͽ� �ٽ� �̾ƾߵǴ°��� �˷��ش�
							break;
						}
					}
				}
			} while (flag);
		}
		//�̹����� random path�� size��ŭ �����Ͽ� �� String �迭�� return �Ѵ� 
		return RanOutput;
	}
}
