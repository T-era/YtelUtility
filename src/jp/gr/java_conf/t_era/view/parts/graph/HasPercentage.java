package jp.gr.java_conf.t_era.view.parts.graph;

/**
 * (円グラフなど)割合を表示するグラフのための、割合情報をもつインターフェース
 * @author y-terada
 *
 */
public interface HasPercentage {
	/**
	 * オブジェクト持つ割合情報を返します。
	 * @return 割合(%)
	 */
	double getPercentage();
}
