package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * �G���[�\��������_�C�A���O
 * @author y-terada
 *
 */
public class ErrorDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��O��\������_�C�A���O�𐶐����܂��B<p/>
	 * �w�肳�ꂽ��O�I�u�W�F�N�g��null�̏ꍇ�A���b�Z�[�W�������\������܂��B
	 * @param message ���b�Z�[�W
	 * @param t �\���Ώۂ̗�O
	 * @param frame �_�C�A���O�̐e�E�B���h�E
	 */
	public ErrorDialog(String message, Throwable t, JFrame frame) {
		super(frame);
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());

		con.add(new JLabel(message)
				, BorderLayout.NORTH);
		if (t != null) {
			con.add(new JLabel(t.getMessage())
				, BorderLayout.CENTER);
			JTextArea ta = new JTextArea();
			for (StackTraceElement ste : t.getStackTrace()) {
				ta.append(ste.toString());
				ta.append(System.getProperty("line.separator"));
			}
			ta.setEditable(false);
			con.add(ta, BorderLayout.SOUTH);
		}

		this.pack();
	}
}
