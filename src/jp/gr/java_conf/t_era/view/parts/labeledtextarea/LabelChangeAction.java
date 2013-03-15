package jp.gr.java_conf.t_era.view.parts.labeledtextarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jp.gr.java_conf.t_era.view.dialog.LabelChangeDialog;

/**
 * ���x����ύX����A�N�V����
 * <p>�{�^��/�L�[����ɂ��A���x���ύX����������B
 * @author y-terada
 *
 */
public class LabelChangeAction implements ActionListener, KeyListener{
	private final JLabel target;
	private final JFrame dialogParent;

	/**
	 * �C���X�^���X�������܂��B
	 * @param parent �_�C�A���O�\���̍ۂ́A�e�ƂȂ�R���|�[�l���g
	 * @param target ���̃A�N�V�����ɂ���ĕύX����郉�x��
	 */
	LabelChangeAction(JFrame parent, JLabel target) {
		this.dialogParent = parent;
		this.target = target;
	}

	/**
	 * �ύX����
	 */
	private void action() {
		LabelChangeDialog dialog = new LabelChangeDialog(dialogParent, target);

		dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_F2) {
			action();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
