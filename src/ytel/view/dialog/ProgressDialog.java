package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import ytel.model.HeavyJob;
import ytel.model.HeavyThread;
import ytel.model.ProgressObserver;

/**
 * �v���O���X�o�[��\������_�C�A���O�ł��B
 * <p>�\���̈��{@link BorderLayout}�Ń��C�A�E�g���A{@link BorderLayout#CENTER}�Ƀv���O���X�o�[��\�����܂��B
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
	 * �v���O���X�o�[�̊�����ێ�����observer���w�肵�āA�_�C�A���O�I�u�W�F�N�g�𐶐����܂��B
	 * @param observer �v���O���X�o�[�̊�����ێ�����I�u�U�[�o
	 */
	public ProgressDialog(ProgressObserver observer) {
		super();
		super.setTitle("������");

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
	 * �v���O���X�o�[�_�C�A���O��\�����܂��B
	 * @param job �����󋵂�����"�d��������"
	 * @param refreshIntervalMSec �_�C�A���O�̎����ĕ`��̃C���^�[�o��(�~���b)
	 */
	public static void showIt(HeavyJob job, long refreshIntervalMSec) {
		ProgressDialog dialog = new ProgressDialog(job);
		dialog.setVisible(true);

		Thread thread = new HeavyThread(job, dialog, true, refreshIntervalMSec);

		thread.start();
	}
}
