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
 * ������w��̃_�C�A���O<br/>
 * UI�ł̕�����w��̂��߂̃_�C�A���O
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
	 * ���͗�
	 */
	private final JTextField textField; 

	/**
	 * �ύX�Ώۂ̃��x�����w�肵�āA���̓_�C�A���O�𐶐����܂��B
	 * @param owner ���̃_�C�A���O�̐e�E�B���h�E
	 * @param target �ύX�Ώۂ̃��x��
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
	 * UI�œ��͂��ꂽ�������Ԃ��܂��B
	 * @return ���͂��ꂽ������
	 */
	public String getText(){
		return textField.getText();
	}
}
