class TimeThread extends Thread {
//	private int time;
	private int timer;

	private int score;

	private FindGame clock;

	//시간 설정 정보이다. 
	TimeThread(FindGame clock) {
		this.clock = clock;
		//this.time = 0;
		this.timer = 0;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			this.clock.setTime(timer);
			try {
				Thread.sleep(1000); //1초씩 카운트..
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.timer++;
		} 
	}
}