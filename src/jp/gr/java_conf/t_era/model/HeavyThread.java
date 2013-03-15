package jp.gr.java_conf.t_era.model;

import java.awt.Window;

/**
 * �����ԏ�����Ɨ������X���b�h�Ŏ��s���邽�߂̃��W���[���B
 * <p>
 * SwingGUI�̃A�N�V�����ŁAGUI���ł܂�Ȃ��悤�ɂ��邽�߂̏��u�ł��B<br>
 * (Swing�ł̓C�x���g�����Ɏ��Ԃ�������ꍇ�A�ĕ`�揈�����T���܂��B
 * ���̂��߁AUI����̓t���[�Y�������̂悤�ȓ���ɂȂ�܂��B
 * ���̃��W���[���������ԏ�����S�����邱�ƂŁAGUI���X���[�Y�Ɍ����܂��B)
 * <p>
 * ���̃N���X��({@link java.lang.Thread#start()})����ƁA����I��GUI�ɍĕ`��({@link java.awt.Window#repaint()})
 * ���b�Z�[�W�𓊂��鑷�X���b�h���N�����A���̃N���X�̃X���b�h����������^�C�~���O�܂�GUI���X�V�������܂��B
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
	 * �R���X�g���N�^
	 * @param job ���̃X���b�h���S�����鏈��
	 * @param targetGui �X�V����ΏۂƂȂ�GUI��Window�B
	 *  (�T�u�X���b�h������A����I�ɍĕ`���ʒm���܂��B)
	 * @param guiDisposeOnDone true���w�肷��ƁA�X���b�h��~��Ɏ����I��targetGui���N���[�Y{@link Window#dispose()}���܂��B
	 * @param guiRefreshIntervalMSec GUI���ĕ`�悳���C���^�[�o��(�~���b)
	 */
	public HeavyThread(HeavyJob job, Window targetGui, boolean guiDisposeOnDone, long guiRefreshIntervalMSec) {
		this.job = job;
		this.targetGui = targetGui;
		this.guiDisposeOnDone = guiDisposeOnDone;
		this.guiRefreshIntervalMSec = guiRefreshIntervalMSec;
	}

	/**
	 * �q�X���b�h�F�d����������������S������B
	 */
	public void run() {
		new GuiRefresher().start();

		job.heavyProcess();

		// �����㏈��
		done = true;
		if (guiDisposeOnDone) {
			targetGui.dispose();
		}
		job.doneNext();
	}

	/**
	 * ���X���b�h�F����I��GUI�����t���b�V������B
	 */
	private class GuiRefresher extends Thread {
		/**
		 * ���X���b�h�F����I��GUI�����t���b�V������B
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
