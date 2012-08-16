package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import ytel.model.HeavyJob;
import ytel.model.HeavyThread;
import ytel.model.ProgressObserver;

/**
 * プログレスバーを表示するダイアログです。
 * <p>表示領域を{@link BorderLayout}でレイアウトし、{@link BorderLayout#CENTER}にプログレスバーを表示します。
 * @author y-terada
 *
 */
public class ProgressDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JProgressBar progressBar;
	private final ProgressObserver observer;

	/**
	 * プログレスバーの割合を保持するobserverを指定して、ダイアログオブジェクトを生成します。
	 * @param observer プログレスバーの割合を保持するオブザーバ
	 */
	public ProgressDialog(ProgressObserver observer) {
		super();
		super.setTitle("処理中");

		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());

		this.observer = observer;
		this.progressBar = new JProgressBar(0, 100);

		progressBar.setStringPainted(true);
		con.add(progressBar, BorderLayout.CENTER);

		this.pack();
	}

	@Override
	public void repaint() {
		progressBar.setValue(observer.getProgressPercentage());

		super.repaint();
	}

	/**
	 * プログレスバーダイアログを表示します。
	 * @param job 処理状況を示す"重たい処理"
	 * @param refreshIntervalMSec ダイアログの自動再描画のインターバル(ミリ秒)
	 */
	public static void showIt(HeavyJob job, long refreshIntervalMSec) {
		ProgressDialog dialog = new ProgressDialog(job);
		dialog.setVisible(true);

		Thread thread = new HeavyThread(job, dialog, true, refreshIntervalMSec);

		thread.start();
	}
}
