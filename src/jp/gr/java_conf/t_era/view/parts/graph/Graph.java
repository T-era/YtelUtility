package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Graphics;

/**
 * {@link GraphContainer}で表示するグラフは、このインターフェースを実装する必要があります。
 * <p>実装側は、{@link Graphics}に対する描画を定義します。
 * @author y-terada
 *
 */
public interface Graph {
	/**
	 * {@link GraphContainer#paint(Graphics)}時にコールされる描画メソッド
	 * @param g
	 */
	void paint(Graphics g);
}
