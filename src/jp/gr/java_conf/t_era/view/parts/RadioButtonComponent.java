package jp.gr.java_conf.t_era.view.parts;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.Border;

/**
 * 2者択一式のラジオボタン選択枠コンポーネント
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
	 * 枠に囲まれたボタングループを作成します。
	 * @param title 囲み枠のタイトル
	 * @param buttonA 選択肢A
	 * @param buttonB 選択肢B
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
	 * 選択されているラジオボタンを返します。
	 * @return 選択されているラジオボタン
	 */
	public ButtonModel getSelected(){
		return buttonGroup.getSelection();
	}
}
