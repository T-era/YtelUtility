package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 円グラフ
 * <p>指定された要素それぞれの占める割合を、色分け表示します。
 * @author y-terada
 *
 */
public class CircleGraph implements Graph{
	/**
	 * 円の中心
	 */
	private final Point center;
	/**
	 * 円の半径
	 */
	private final int radius;

	/**
	 * グラフに表示する各要素
	 */
	private final List<HasPercentage> elements;
	/**
	 * グラフの色定義(各要素は、この色リストをローテートして配色される{@link CircleGraphColors})
	 */
	private final List<Color> colorList;
	/**
	 * グラフの配色を行うオブジェクト
	 */
	private final CircleGraphColors colors;

	/**
	 * 円グラフオブジェクトを生成します。<p>
	 * <ul>
	 *  <li>要素の"割合"について<br>
	 *  各表示要素の持つ"割合"は、以下の各条件を満たす必要があります。
	 * 	 <ul>
	 *    <li>各要素の割合は0以上
	 *    <li>各要素の合計は100以下
	 *   </ul>
	 *  <li>表示色について<br>
	 *  各要素は、指定された色のリストに従って着色されます。<br>
	 *  要素の数が、色の数を超える場合、色のリストは循環して使用します。<br>
	 *  要素の数が多くなる可能性がある場合、色リストの先頭と末尾は隣接して表示されるので、異なる色にするべきです。<br>
	 *  <ul><li>要素の先頭と末尾も円グラフの原点で隣接することがあり得ます。<br>
	 *   このクラスの実装では、要素の末尾が、色リストの先頭になる場合、上記弊害を逃れるために、色リストの2番目を
	 *   使用します。
	 *  </ul>
	 * </ul>
	 * @param center 円グラフの中心点
	 * @param radius 円グラフの半径
	 * @param elements 円グラフで表示する要素の一覧
	 * @param colors 
	 */
	public CircleGraph(Point center, int radius, List<? extends HasPercentage> elements, List<Color> colors) {
		this.center = center;
		this.radius = radius;

		this.elements = Collections.unmodifiableList(elements);
		this.colorList = Collections.unmodifiableList(colors);
		this.colors = new CircleGraphColors();
	}

	@Override
	public void paint(Graphics g) {
		double angleStart = 0;

		for (HasPercentage element: elements) {
			double angleRange = 3.6 * element.getPercentage();

			g.setColor(getColor(element));
			g.fillArc( center.x - radius, center.y - radius, radius * 2, radius * 2, (int)(90 - angleStart), (int)( -angleRange - 1 ) );
			angleStart += angleRange;
		}
	}

	/**
	 * 指定された要素が使用している色を取得します。
	 * @param element 
	 * @return 色
	 */
	public Color getColor(HasPercentage element) {
		Color ret = colors.getColor(element);
		if (ret == null) {
			ret = colors.decideColor(element);
		}
		return ret;
	}

	/**
	 * 要素毎に使用する色の決定／保持を行うクラス
	 * @author y-terada
	 *
	 */
	private class CircleGraphColors {
		/**
		 * 各要素について、決定済みの色を保持するMap
		 */
		private final Map<HasPercentage, Color> colorMap;

		private CircleGraphColors() {
			if (colorList.size() < 3) {
				throw new IllegalStateException("色数３色未満では円グラフは描けない");
			}
			colorMap = new HashMap<HasPercentage, Color>();
		}

		/**
		 * 指定された要素のための色を決定します。
		 * <br>決定には指定された色リストを使用し、リスト末尾からは先頭にループします。
		 * <br>指定された要素が全要素の末尾と一致する場合、かつその時ループの先頭の色が選択された場合は
		 * (グラフの原点で同じ色が隣接することを避けるため)、色リストの2番目を返します。
		 * @param element
		 * @return 色
		 */
		private Color decideColor(HasPercentage element){
			int indexOfElement = elements.indexOf(element);

			int sizeOfElements = elements.size();
			int sizeOfColors = colorList.size();
			if (indexOfElement + 1 == sizeOfElements && sizeOfElements % sizeOfColors == 1) {
				return colorList.get(1);
			}

			Color ret = colorList.get(indexOfElement % colorList.size());
			colorMap.put(element, ret);
			return ret; 
		}

		private Color getColor(HasPercentage element) {
			return colorMap.get(element);
		}
	}
}
