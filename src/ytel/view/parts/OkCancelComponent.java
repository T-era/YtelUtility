package ytel.view.parts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * ����/�񓯈Ӄ{�^��<p/>
 * ����(Ok/Yes)�Ɣ񓯈�(Cancel/No)�̂Q�{�^�������R���|�[�l���g�ł��B
 * ���̃R���|�[�l���g�𗘗p����Ƃ��́A����/�񓯈ӂ̓��̓C�x���g������
 * ���邽�߁A{@link AgreementListener}�C���^�[�t�F�[�X�����������N���X���K�v�ł��B
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
	 * �C�x���g���������郊�X�i���w�肵�āA�C���X�^���X�������܂��B
	 * @param listener ����/�񓯈ӂ̃C�x���g���������郊�X�i
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
	 * Ok/Cancel�œ��ӂ����߂�R���|�[�l���g�𐶐����܂��B
	 * @param listener
	 * @return ���������R���|�[�l���g
	 */
	public static OkCancelComponent getOkCancelComponent(AgreementListener listener){
		return new OkCancelComponent(listener, OK_BUTTON_LABEL, CANCEL_BUTTON_LABEL);
	}

	/**
	 * Yes/No�œ��ӂ����߂�R���|�[�l���g�𐶐����܂��B
	 * @param listener
	 * @return ���������R���|�[�l���g
	 */
	public static OkCancelComponent getYesNoComponent(AgreementListener listener){
		return new OkCancelComponent(listener, YES_BUTTON_LABEL, NO_BUTTON_LABEL);
	}

	/**
	 * Force/Cancel�œ��ӂ����߂�R���|�[�l���g�𐶐����܂��B
	 * @param listener
	 * @return ���������R���|�[�l���g
	 */
	public static OkCancelComponent getForceCancelComponent(AgreementListener listener){
		return new OkCancelComponent(listener, FORCE_BUTTON_LABEL, CANCEL_BUTTON_LABEL);
	}
}
