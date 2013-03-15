package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 一つの要素を表示する円グラフ。
 * <br>特定要素が占める割合を、表示します。
 * @author y-terada
 *
 */
public class SingleElementCirgleGraph implements Graph {
	/**
	 * 円グラフの中心点
	 */
	private final Point center;
	/**
	 * 円グラフの半径
	 */
	private final int radius;

	/**
	 * 表示する、割合を示すオブジェクト<br>
	 * ({@link HasPercentage#getPercentage()}が0未満/100以上の場合は未定義。)
	 */
	private final HasPercentage element;
	/**
	 * グラフの表示色
	 */
	private final Color color;

	/**
	 * 割合を示すオブジェクトを指定してグラフを生成します。
	 * ({@link HasPercentage#getPercentage()}が0未満/100以上の場合は未定義。)です。
	 * @param center グラフの中心点(親コンポーネント内での相対座標で指定します)
	 * @param radius グラフの半径
	 * @param element 割合を示すオブジェクト 
	 * @param color グラフの表示色
	 */
	public SingleElementCirgleGraph(Point center, int radius, HasPercentage element, Color color) {
		this.center = center;
		this.radius = radius;

		this.element = element;
		this.color= color;
	}

	@Override
	public void paint(Graphics g) {
		double angleStart = 0;

		double angleRange = 360.0 * element.getPercentage();
		g.setColor(color);
		g.fillArc( center.x - radius, center.y - radius, radius * 2, radius * 2, (int)(90 - angleStart), (int)( -angleRange - 1 ) );
		angleStart += angleRange;
	}
}
