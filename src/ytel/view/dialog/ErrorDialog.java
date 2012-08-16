package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * エラー表示をするダイアログ
 * @author y-terada
 *
 */
public class ErrorDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 例外を表示するダイアログを生成します。<p/>
	 * 指定された例外オブジェクトがnullの場合、メッセージだけが表示されます。
	 * @param message メッセージ
	 * @param t 表示対象の例外
	 * @param frame ダイアログの親ウィンドウ
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
