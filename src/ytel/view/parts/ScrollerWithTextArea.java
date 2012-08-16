package ytel.view.parts;

import javax.swing.JScrollPane;

/**
 * �X�N���[���o�[�t���̃e�L�X�g�G���A<p/>
 * �X�N���[���o�[�ƃe�L�X�g�G���A�ɑ΂���Q�Ƃ��܂Ƃ߂ĕێ����܂��B
 * @author y-terada
 *
 */
public class ScrollerWithTextArea {
	/**
	 * �X�N���[���o�[
	 */
	private final JScrollPane scrollPane;
	/**
	 * �e�L�X�g�G���A
	 */
	private final UndoableTextArea textArea;

	public ScrollerWithTextArea(JScrollPane scrollPane, UndoableTextArea textArea){
		this.scrollPane = scrollPane;
		this.textArea = textArea;
	}

	/**
	 * �X�N���[���o�[��Ԃ��܂��B
	 * @return �X�N���[���o�[
	 */
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	/**
	 * �e�L�X�g�G���A��Ԃ��܂��B
	 * @return �e�L�X�g�G���A
	 */
	public UndoableTextArea getTextArea(){
		return textArea;
	}
}
