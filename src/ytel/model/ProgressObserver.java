package ytel.model;

/**
 * {@link ytel.view.dialog.ProgressDialog}�̂��߂́A�v���O���X(������)�Ď��N���X
 * @author y-terada
 *
 */
public interface ProgressObserver {
	/**
	 * �������������̊�����S����(%)�ŕԂ��܂��B
	 * @return �������������̊���
	 */
	int getProgressPercentage();
}
