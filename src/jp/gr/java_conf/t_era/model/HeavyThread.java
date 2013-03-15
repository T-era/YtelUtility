package jp.gr.java_conf.t_era.model;

import java.awt.Window;

/**
 * 長時間処理を独立したスレッドで実行するためのモジュール。
 * <p>
 * SwingGUIのアクションで、GUIが固まらないようにするための処置です。<br>
 * (Swingではイベント処理に時間がかかる場合、再描画処理を控えます。
 * このため、UIからはフリーズしたかのような動作になります。
 * このモジュールが長時間処理を担当することで、GUIをスムーズに見せます。)
 * <p>
 * このクラスを({@link java.lang.Thread#start()})すると、定期的にGUIに再描画({@link java.awt.Window#repaint()})
 * メッセージを投げる孫スレッドが起動し、このクラスのスレッドが完了するタイミングまでGUIを更新し続けます。
 * @author y-terada
 *
 */
public class HeavyThread extends Thread{
	private final HeavyJob job;
	private final Window targetGui;
	private final long guiRefreshIntervalMSec;
	private final boolean guiDisposeOnDone;

	private volatile boolean done;

	/**
	 * コンストラクタ
	 * @param job このスレッドが担当する処理
	 * @param targetGui 更新する対象となるGUIのWindow。
	 *  (サブスレッドが走り、定期的に再描画を通知します。)
	 * @param guiDisposeOnDone trueを指定すると、スレッド停止後に自動的にtargetGuiをクローズ{@link Window#dispose()}します。
	 * @param guiRefreshIntervalMSec GUIが再描画されるインターバル(ミリ秒)
	 */
	public HeavyThread(HeavyJob job, Window targetGui, boolean guiDisposeOnDone, long guiRefreshIntervalMSec) {
		this.job = job;
		this.targetGui = targetGui;
		this.guiDisposeOnDone = guiDisposeOnDone;
		this.guiRefreshIntervalMSec = guiRefreshIntervalMSec;
	}

	/**
	 * 子スレッド：重たい初期化処理を担当する。
	 */
	public void run() {
		new GuiRefresher().start();

		job.heavyProcess();

		// 完了後処理
		done = true;
		if (guiDisposeOnDone) {
			targetGui.dispose();
		}
		job.doneNext();
	}

	/**
	 * 孫スレッド：定期的にGUIをリフレッシュする。
	 */
	private class GuiRefresher extends Thread {
		/**
		 * 孫スレッド：定期的にGUIをリフレッシュする。
		 */
		public void run() {
			while (! done) {
				try {
					Thread.sleep(guiRefreshIntervalMSec);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				targetGui.repaint();
			}
		}
	}
}
