package ytel.view.parts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * 同意/非同意ボタン<p/>
 * 同意(Ok/Yes)と非同意(Cancel/No)の２ボタンを持つコンポーネントです。
 * このコンポーネントを利用するときは、同意/非同意の入力イベントを処理
 * するため、{@link AgreementListener}インターフェースを実装したクラスが必要です。
 * @author y-terada
 *
 */
public class OkCancelComponent extends Container {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final String OK_BUTTON_LABEL = "O.K.";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";

	private static final String YES_BUTTON_LABEL = "Yes";
	private static final String NO_BUTTON_LABEL = "No";

	private static final String FORCE_BUTTON_LABEL = "Force";

	/**
	 * イベントを処理するリスナを指定して、インスタンス生成します。
	 * @param listener 同意/非同意のイベントを処理するリスナ
	 * @param okButtonLabel
	 * @param cancelButtonLabel
	 */
	private OkCancelComponent(final AgreementListener listener, String okButtonLabel, String cancelButtonLabel){
		JButton buttonOk = new JButton(okButtonLabel);
		JButton buttonCancel = new JButton(cancelButtonLabel);

		buttonOk.setMnemonic( KeyEvent.VK_O );
		buttonOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.okAction();
			}
		});
		buttonCancel.setMnemonic( KeyEvent.VK_C );
		buttonCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.cancelAction();
			}
		});

		this.setLayout(new BorderLayout());
		Container conEast = new Container();
		{
			conEast.setLayout(new GridLayout(0, 2));
			conEast.add(buttonOk);
			conEast.add(buttonCancel);
		}
		this.add(conEast, BorderLayout.EAST);
	}

	/**
	 * Ok/Cancelで同意を求めるコンポーネントを生成します。
	 * @param listener
	 * @return 生成したコンポーネント
	 */
	public static OkCancelComponent getOkCancelComponent(AgreementListener listener){
		return new OkCancelComponent(listener, OK_BUTTON_LABEL, CANCEL_BUTTON_LABEL);
	}

	/**
	 * Yes/Noで同意を求めるコンポーネントを生成します。
	 * @param listener
	 * @return 生成したコンポーネント
	 */
	public static OkCancelComponent getYesNoComponent(AgreementListener listener){
		return new OkCancelComponent(listener, YES_BUTTON_LABEL, NO_BUTTON_LABEL);
	}

	/**
	 * Force/Cancelで同意を求めるコンポーネントを生成します。
	 * @param listener
	 * @return 生成したコンポーネント
	 */
	public static OkCancelComponent getForceCancelComponent(AgreementListener listener){
		return new OkCancelComponent(listener, FORCE_BUTTON_LABEL, CANCEL_BUTTON_LABEL);
	}
}
