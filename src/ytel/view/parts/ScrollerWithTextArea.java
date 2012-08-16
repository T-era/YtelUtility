package ytel.view.parts;

import javax.swing.JScrollPane;

/**
 * スクロールバー付きのテキストエリア<p/>
 * スクロールバーとテキストエリアに対する参照をまとめて保持します。
 * @author y-terada
 *
 */
public class ScrollerWithTextArea {
	/**
	 * スクロールバー
	 */
	private final JScrollPane scrollPane;
	/**
	 * テキストエリア
	 */
	private final UndoableTextArea textArea;

	public ScrollerWithTextArea(JScrollPane scrollPane, UndoableTextArea textArea){
		this.scrollPane = scrollPane;
		this.textArea = textArea;
	}

	/**
	 * スクロールバーを返します。
	 * @return スクロールバー
	 */
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	/**
	 * テキストエリアを返します。
	 * @return テキストエリア
	 */
	public UndoableTextArea getTextArea(){
		return textArea;
	}
}
