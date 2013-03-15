package jp.gr.java_conf.t_era.view.parts.labeledtextarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jp.gr.java_conf.t_era.view.dialog.LabelChangeDialog;

/**
 * ラベルを変更するアクション
 * <p>ボタン/キー操作による、ラベル変更を実装する。
 * @author y-terada
 *
 */
public class LabelChangeAction implements ActionListener, KeyListener{
	private final JLabel target;
	private final JFrame dialogParent;

	/**
	 * インスタンス生成します。
	 * @param parent ダイアログ表示の際の、親となるコンポーネント
	 * @param target このアクションによって変更されるラベル
	 */
	LabelChangeAction(JFrame parent, JLabel target) {
		this.dialogParent = parent;
		this.target = target;
	}

	/**
	 * 変更動作
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
