package ytel.model;

import ytel.model.ProgressObserver;

/**
 * �d�����������s���ꍇ�Ɏ�������ׂ��C���^�[�t�F�[�X�ł��B
 * <p>
 * {@link HeavyThread}�́A���̃C���^�[�t�F�[�X����������Ɨ������X���b�h�ŏ������܂��B
 * @author y-terada
 *
 */
public interface HeavyJob extends ProgressObserver{
	/**
	 * �d�����������`���܂��B{@link ProgressObserver#getProgressPercentage()}�́A���̃��\�b�h��
	 * �����󋵂�Ԃ��ׂ��ł��B
	 */
	void heavyProcess();

	/**
	 * �d���������̊�����ɍs���ׂ��������`���܂��B
	 */
	void doneNext();
}
