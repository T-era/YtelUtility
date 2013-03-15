package jp.gr.java_conf.t_era.view.parts.labeledtextarea;

import javax.swing.JLabel;

/**
 * ラベル(タイトル表示)が付いていることを示すインターフェース
 * @author y-terada
 *
 */
public interface Labeled {
	/**
	 * ラベル(タイトル表示)を返します
	 * @return ラベル
	 */
	JLabel getLabel();
}
