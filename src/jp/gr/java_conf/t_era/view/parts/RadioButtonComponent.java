package jp.gr.java_conf.t_era.view.parts;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.Border;

/**
 * 2�ґ��ꎮ�̃��W�I�{�^���I��g�R���|�[�l���g
 * @author y-terada
 *
 */
public class RadioButtonComponent extends JRadioButtonMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ButtonGroup buttonGroup;
	/**
	 * �g�Ɉ͂܂ꂽ�{�^���O���[�v���쐬���܂��B
	 * @param title �͂ݘg�̃^�C�g��
	 * @param buttonA �I����A
	 * @param buttonB �I����B
	 */
	public RadioButtonComponent( String title, JRadioButton buttonA, JRadioButton buttonB ){
		super( title );

		setLayout( new GridLayout(1, 2) );
		Border border = BorderFactory.createTitledBorder(title);
		setBorder( border );
		buttonGroup = new ButtonGroup();
		buttonGroup.add( buttonA );
		buttonGroup.add( buttonB );
		add( buttonA );
		add( buttonB );
	}

	/**
	 * �I������Ă��郉�W�I�{�^����Ԃ��܂��B
	 * @return �I������Ă��郉�W�I�{�^��
	 */
	public ButtonModel getSelected(){
		return buttonGroup.getSelection();
	}
}
