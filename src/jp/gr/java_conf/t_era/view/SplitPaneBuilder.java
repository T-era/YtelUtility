package jp.gr.java_conf.t_era.view;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;

/**
 * JSplitPane�Ń��C�A�E�g���邽�߂̃w���p�c�[���ł��B
 * 
 * �W����JS������Pane��2�̃R���|�[�l���g�����E(�܂��͏㉺)�ɕ��ׂĕ\�����܂��B
 * ��葽���̃R���|�[�l���g�𓯂������ɕ��ׂ邾���Ȃ�A���̃c�[���ł�add��A�����ăR�[�����邾���ł��B
 * �܂��A{@link #splitCrossway()}���\�b�h���g���Ε����Ⴂ�̃R���|�[�l���g���ȒP�ɒ��Ɋ܂߂邱�Ƃ��ł��܂��B
 * 
 * �ȉ��́ABorderLayout��ɕ��ׂ�T���v���ł��B
 * <code>
 * SplitPaneBuilder level1 = new SplitPaneBuilder(Orientation.Left_Right);
 * level1.add(new javax.swing.JLabel("left"));
 * SplitPaneBuilder level2 = level1.splitCrossway();
 * level2.add(new javax.swing.JLabel("north"));
 * level2.add(new javax.swing.JLabel("center"));
 * level2.add(new javax.swing.JLabel("south"));
 * level1.add(new javax.swing.JLabel("right"));
 * 
 * level1.build(frame.getContentPane());
 * </code>
 * @author y-terada
 *
 */
public class SplitPaneBuilder {
//public static void main(String[] args) {
//	javax.swing.JFrame frame = new javax.swing.JFrame();
//SplitPaneBuilder level1 = new SplitPaneBuilder(Orientation.Left_Right);
//level1.add(new javax.swing.JLabel("left"));
//SplitPaneBuilder level2 = level1.splitCrossway();
//level2.add(new javax.swing.JLabel("north"));
//level2.add(new javax.swing.JLabel("center"));
//level2.add(new javax.swing.JLabel("south"));
//level1.add(new javax.swing.JLabel("right"));
//
//level1.build(frame.getContentPane());
//frame.setVisible(true);
//}
	public static enum Orientation {
		Top_Bottom(JSplitPane.VERTICAL_SPLIT),
		Left_Right(JSplitPane.HORIZONTAL_SPLIT);

		private int splitOrientation;
		Orientation(int splitOrientation) {
			this.splitOrientation = splitOrientation;
		}
		Orientation crossWay() {
			if (this == Top_Bottom) {
				return Left_Right;
			} else {
				return Top_Bottom;
			}
		}
	}

	private List<ComponentOrSplitter> components;
	private final Orientation orientation;

	public SplitPaneBuilder(Orientation orientation) {
		this.orientation = orientation;
		components = new ArrayList<>();
	}

	public void add(Component item) {
		components.add(new ComponentOrSplitter(item));
	}
	public SplitPaneBuilder splitCrossway() {
		SplitPaneBuilder newItem = new SplitPaneBuilder(orientation.crossWay());
		components.add(new ComponentOrSplitter(newItem));
		return newItem;
	}
	public void build(Container con) {
		con.add(toSplitPane());
	}
	public Component toSplitPane() {
		int size = components.size();
		if (size == 0) return null;
		else if (size == 1) return components.get(0).getComponent();
		ComponentOrSplitter[] array = new ComponentOrSplitter[components.size()];
		return toSplitPaneRecursive(0, components.toArray(array));
	}
	private Component toSplitPaneRecursive(int startIndex, ComponentOrSplitter... array) {
		return new JSplitPane(orientation.splitOrientation,
				array[startIndex].getComponent(),
				array.length == startIndex + 2 ? array[startIndex + 1].getComponent()
						: toSplitPaneRecursive(startIndex + 1, array));
	}
	private static class ComponentOrSplitter {
		private final Component component;
		private final SplitPaneBuilder splitter;
		private ComponentOrSplitter(Component component) {
			this.component = component;
			this.splitter = null;
		}
		private ComponentOrSplitter(SplitPaneBuilder splitter) {
			this.component = null;
			this.splitter = splitter;
		}
		
		private Component getComponent() {
			if (this.splitter == null) {
				return component;
			} else {
				return splitter.toSplitPane();
			}
		}
	}
}
