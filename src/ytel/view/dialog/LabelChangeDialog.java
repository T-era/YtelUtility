package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ytel.view.parts.AgreementListener;
import ytel.view.parts.OkCancelComponent;

/**
 * 文字列指定のダイアログ<br/>
 * UIでの文字列指定のためのダイアログ
 * @author y-terada
 *
 */
public class LabelChangeDialog extends JDialog implements AgreementListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JLabel target;
	/**
	 * 入力欄
	 */
	private final JTextField textField; 

	/**
	 * 変更対象のラベルを指定して、入力ダイアログを生成します。
	 * @param owner このダイアログの親ウィンドウ
	 * @param target 変更対象のラベル
	 */
	public LabelChangeDialog( JFrame owner, JLabel target){
		super(owner, target.getText(), ModalityType.TOOLKIT_MODAL);
		this.setLocation(target.getLocationOnScreen());

		Container con = this.getContentPane();
		con.setLayout( new BorderLayout() );

		this.target = target;
		textField = new JTextField(target.getText());

		con.add( textField, BorderLayout.CENTER );
		con.add(OkCancelComponent.getOkCancelComponent(this), BorderLayout.SOUTH);

		this.pack();
	}

	@Override
	public void okAction() {
		target.setText(textField.getText());
		dispose();
	}
	@Override
	public void cancelAction() {
		dispose();
	}
	/**
	 * UIで入力された文字列を返します。
	 * @return 入力された文字列
	 */
	public String getText(){
		return textField.getText();
	}
}
