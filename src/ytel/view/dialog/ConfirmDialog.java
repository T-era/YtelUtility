package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ytel.view.parts.AgreementListener;
import ytel.view.parts.OkCancelComponent;

/**
 * ユーザに同意/非同意を問い合わせるダイアログ
 * @author y-terada
 *
 */
public class ConfirmDialog extends JDialog implements AgreementListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean isOkClosed = false;

	/**
	 * このダイアログの表示モード
	 * @author y-terada
	 *
	 */
	private static enum Mode{
		/**
		 * OK/Cancel
		 */
		OkCancel,
		/**
		 * はい/いいえ
		 */
		YesNo
	}

	/**
	 * ユーザに同意/非同意を問い合わせるダイアログを生成します。<br/>
	 * ユーザの判定は、{@link #isOkClosed()}メソッドの真偽値で取得します。
	 * @param parent 親ウィンドウ
	 * @param title 表示タイトル
	 * @param message 表示メッセージ
	 * @param defaultChoice デフォルトの指定(trueの場合:同意)
	 *  同意/非同意どちらのボタンも押さずにダイアログが閉じられた場合、この値が適用されます。
	 * @param mode ダイアログの表示モード[OK,Cancel / Yes,No]
	 */
	private ConfirmDialog(JFrame parent, String title, String message, boolean defaultChoice, Mode mode){
		super(parent, title, true);
		this.isOkClosed = defaultChoice;
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(new JLabel(message), BorderLayout.CENTER);
		switch(mode){
		case OkCancel:
			container.add(OkCancelComponent.getOkCancelComponent(this), BorderLayout.SOUTH);
			break;
		case YesNo:
			container.add(OkCancelComponent.getYesNoComponent(this), BorderLayout.SOUTH);
			break;
		default:
			throw new IllegalStateException("期待外れのEnum");
		}
		this.pack();

		Point centerPoint = new Point(parent.getX() + (parent.getWidth() - this.getWidth()) / 2, parent.getY() + (parent.getHeight() - this.getHeight()) / 2 );
		this.setLocation(centerPoint);
	}

	/**
	 * ユーザの入力が同意である場合はtrue<p/>
	 * ダイアログを閉じる前に、このメソッドをコールするべきではありません。<br/>
	 * 基本的にはコンストラクタで指定されたデフォルト値が返りますが、特にダイアログが
	 * 終了する処理とタイミングが重なった場合など戻り値は未定義です。
	 * @return ユーザの入力
	 */
	private boolean isOkClosed(){
		return isOkClosed;
	}

	@Override
	public void cancelAction() {
		isOkClosed = false;
		super.dispose();
	}

	@Override
	public void okAction() {
		isOkClosed = true;
		super.dispose();
	}

	/**
	 * OK/Cancelのダイアログ表示をし、そのユーザ判定を返します。
	 * @param parent ダイアログの親ウィンドウ
	 * @param message ダイアログメッセージ
	 * @return ユーザの入力がOKの場合のみtrue
	 */
	public static boolean isOkCancelConfirmed(JFrame parent, String message){
		return isOkCancelConfirmed(parent, "", message, false);
	}
	/**
	 * OK/Cancelのダイアログ表示をし、そのユーザ判定を返します。<p/>
	 * [OK/Cancel]どちらのボタンも押されなかった場合、defaultChoiceの指定が
	 * 戻り値になります。
	 * @param parent ダイアログの親ウィンドウ
	 * @param title ダイアログタイトル
	 * @param message ダイアログメッセージ
	 * @param defaultChoice デフォルトでの指定
	 * @return ユーザの入力がOKの場合true
	 */
	public static boolean isOkCancelConfirmed(JFrame parent, String title, String message, boolean defaultChoice){
		ConfirmDialog dialog = new ConfirmDialog(parent, title, message, defaultChoice, Mode.OkCancel);
		dialog.setVisible(true);

		return dialog.isOkClosed();
	}

	/**
	 * Yes/Noのダイアログ表示をし、そのユーザ判定を返します。
	 * @param parent ダイアログの親ウィンドウ
	 * @param message ダイアログメッセージ
	 * @return ユーザの入力がYesの場合のみtrue
	 */
	public static boolean isYesNoConfirmed(JFrame parent, String message){
		return isYesNoConfirmed(parent, "", message, false);
	}
	/**
	 * Yes/Noのダイアログ表示をし、そのユーザ判定を返します。<p/>
	 * [Yes/No]どちらのボタンも押されなかった場合、defaultChoiceの指定が
	 * 戻り値になります。
	 * @param parent ダイアログの親ウィンドウ
	 * @param title ダイアログタイトル
	 * @param message ダイアログメッセージ
	 * @param defaultChoice デフォルトでの指定
	 * @return ユーザの入力がYesの場合true
	 */
	public static boolean isYesNoConfirmed(JFrame parent, String title, String message, boolean defaultChoice){
		ConfirmDialog dialog = new ConfirmDialog(parent, title, message, defaultChoice, Mode.YesNo);
		dialog.setVisible(true);

		return dialog.isOkClosed();
	}
}
