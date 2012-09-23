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
 * ���[�U�ɓ���/�񓯈ӂ�₢���킹��_�C�A���O
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
	 * ���̃_�C�A���O�̕\�����[�h
	 * @author y-terada
	 *
	 */
	private static enum Mode{
		/**
		 * OK/Cancel
		 */
		OkCancel,
		/**
		 * �͂�/������
		 */
		YesNo
	}

	/**
	 * ���[�U�ɓ���/�񓯈ӂ�₢���킹��_�C�A���O�𐶐����܂��B<br/>
	 * ���[�U�̔���́A{@link #isOkClosed()}���\�b�h�̐^�U�l�Ŏ擾���܂��B
	 * @param parent �e�E�B���h�E
	 * @param title �\���^�C�g��
	 * @param message �\�����b�Z�[�W
	 * @param defaultChoice �f�t�H���g�̎w��(true�̏ꍇ:����)
	 *  ����/�񓯈ӂǂ���̃{�^�����������Ƀ_�C�A���O������ꂽ�ꍇ�A���̒l���K�p����܂��B
	 * @param mode �_�C�A���O�̕\�����[�h[OK,Cancel / Yes,No]
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
			throw new IllegalStateException("���ҊO���Enum");
		}
		this.pack();

		Point centerPoint = new Point(parent.getX() + (parent.getWidth() - this.getWidth()) / 2, parent.getY() + (parent.getHeight() - this.getHeight()) / 2 );
		this.setLocation(centerPoint);
	}

	/**
	 * ���[�U�̓��͂����ӂł���ꍇ��true<p/>
	 * �_�C�A���O�����O�ɁA���̃��\�b�h���R�[������ׂ��ł͂���܂���B<br/>
	 * ��{�I�ɂ̓R���X�g���N�^�Ŏw�肳�ꂽ�f�t�H���g�l���Ԃ�܂����A���Ƀ_�C�A���O��
	 * �I�����鏈���ƃ^�C�~���O���d�Ȃ����ꍇ�Ȃǖ߂�l�͖���`�ł��B
	 * @return ���[�U�̓���
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
	 * OK/Cancel�̃_�C�A���O�\�������A���̃��[�U�����Ԃ��܂��B
	 * @param parent �_�C�A���O�̐e�E�B���h�E
	 * @param message �_�C�A���O���b�Z�[�W
	 * @return ���[�U�̓��͂�OK�̏ꍇ�̂�true
	 */
	public static boolean isOkCancelConfirmed(JFrame parent, String message){
		return isOkCancelConfirmed(parent, "", message, false);
	}
	/**
	 * OK/Cancel�̃_�C�A���O�\�������A���̃��[�U�����Ԃ��܂��B<p/>
	 * [OK/Cancel]�ǂ���̃{�^����������Ȃ������ꍇ�AdefaultChoice�̎w�肪
	 * �߂�l�ɂȂ�܂��B
	 * @param parent �_�C�A���O�̐e�E�B���h�E
	 * @param title �_�C�A���O�^�C�g��
	 * @param message �_�C�A���O���b�Z�[�W
	 * @param defaultChoice �f�t�H���g�ł̎w��
	 * @return ���[�U�̓��͂�OK�̏ꍇtrue
	 */
	public static boolean isOkCancelConfirmed(JFrame parent, String title, String message, boolean defaultChoice){
		ConfirmDialog dialog = new ConfirmDialog(parent, title, message, defaultChoice, Mode.OkCancel);
		dialog.setVisible(true);

		return dialog.isOkClosed();
	}

	/**
	 * Yes/No�̃_�C�A���O�\�������A���̃��[�U�����Ԃ��܂��B
	 * @param parent �_�C�A���O�̐e�E�B���h�E
	 * @param message �_�C�A���O���b�Z�[�W
	 * @return ���[�U�̓��͂�Yes�̏ꍇ�̂�true
	 */
	public static boolean isYesNoConfirmed(JFrame parent, String message){
		return isYesNoConfirmed(parent, "", message, false);
	}
	/**
	 * Yes/No�̃_�C�A���O�\�������A���̃��[�U�����Ԃ��܂��B<p/>
	 * [Yes/No]�ǂ���̃{�^����������Ȃ������ꍇ�AdefaultChoice�̎w�肪
	 * �߂�l�ɂȂ�܂��B
	 * @param parent �_�C�A���O�̐e�E�B���h�E
	 * @param title �_�C�A���O�^�C�g��
	 * @param message �_�C�A���O���b�Z�[�W
	 * @param defaultChoice �f�t�H���g�ł̎w��
	 * @return ���[�U�̓��͂�Yes�̏ꍇtrue
	 */
	public static boolean isYesNoConfirmed(JFrame parent, String title, String message, boolean defaultChoice){
		ConfirmDialog dialog = new ConfirmDialog(parent, title, message, defaultChoice, Mode.YesNo);
		dialog.setVisible(true);

		return dialog.isOkClosed();
	}
}
