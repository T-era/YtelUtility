package ytel.view.parts.graph;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * �O���t({@link Graph})��\������R���e�i�B
 * <p>�\������O���t�́A{@link #addGraph(Graph)}���\�b�h�Œǉ�����B
 * �����̃O���t��\������ꍇ�A���{@link #addGraph(Graph)}�������̂��珇�ɕ\������B(���Ƃɉ��������̂ŏ㏑������)
 * @author y-terada
 */
public class GraphContainer extends Container {
	private static final long serialVersionUID = 1L;

	/**
	 * �\������O���t�̈ꗗ
	 */
	private final List<Graph> graphs;

	/**
	 * �\���T�C�Y���w�肵�āA�I�u�W�F�N�g�𐶐����܂��B
	 * @param size �\���T�C�Y
	 */
	public GraphContainer(Dimension size){
		super();
		this.graphs = new ArrayList<Graph>();

		setSize(size);
		setPreferredSize(size);
		setVisible(true);
	}

	/**
	 * �\������O���t��ǉ����܂��B({@link #paint(Graphics)}���ɕ\������܂��B)
	 * @param newGraph
	 */
	public void addGraph(Graph newGraph) {
		graphs.add(newGraph);
	}
	/**
	 * �\������O���t�̈ꗗ���N���A���܂��B
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
