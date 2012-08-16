package ytel.view.parts.graph;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * グラフ({@link Graph})を表示するコンテナ。
 * <p>表示するグラフは、{@link #addGraph(Graph)}メソッドで追加する。
 * 複数のグラフを表示する場合、先に{@link #addGraph(Graph)}したものから順に表示する。(あとに加えたもので上書きする)
 * @author y-terada
 */
public class GraphContainer extends Container {
	private static final long serialVersionUID = 1L;

	/**
	 * 表示するグラフの一覧
	 */
	private final List<Graph> graphs;

	/**
	 * 表示サイズを指定して、オブジェクトを生成します。
	 * @param size 表示サイズ
	 */
	public GraphContainer(Dimension size){
		super();
		this.graphs = new ArrayList<Graph>();

		setSize(size);
		setPreferredSize(size);
		setVisible(true);
	}

	/**
	 * 表示するグラフを追加します。({@link #paint(Graphics)}時に表示されます。)
	 * @param newGraph
	 */
	public void addGraph(Graph newGraph) {
		graphs.add(newGraph);
	}
	/**
	 * 表示するグラフの一覧をクリアします。
	 */
	public void clearGraph() {
		graphs.clear();
	}

	@Override
	public void paint(Graphics g){
		for (Graph graph: graphs) {
			graph.paint(g);
		}
	}
}
