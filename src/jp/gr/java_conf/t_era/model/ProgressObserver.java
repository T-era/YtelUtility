package jp.gr.java_conf.t_era.model;

/**
 * {@link jp.gr.java_conf.t_era.view.dialog.ProgressDialog}�̂��߂́A�v���O���X(������)�Ď��N���X
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
