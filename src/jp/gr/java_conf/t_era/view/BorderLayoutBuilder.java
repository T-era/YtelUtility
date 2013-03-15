package jp.gr.java_conf.t_era.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BorderLayout �̎菕�������܂��B
 * ���̃N���X�C���X�^���X�́A���C�A�E�g�����R���|�[�l���g�����󂯂��A{@link BorderLayoutBuilder#layoutComponent(Container)}
 * ���\�b�h�ŁA�R���e�i�̎w�肳�ꂽ�ꏊ�Ƀ��C�A�E�g���܂��B
 * <p>
 * {@link #add(Component, boolean, Direction...)}�ŃR���|�[�l���g��ǉ����܂��B
 * <br/>
 * Direction�Ń��C�A�E�g�ʒu���w�肵�܂��B
 * �ʒu�w��́A�擪���珇�ɓK�p����܂��B
 * <example>add(arg, false, Direction.South, Direction.East)</example>
 * �̎w��̏ꍇ�A���C���R���e�i��South�R���e�i�����A����South�R���e�i��East�R���e�i�����������
 * �R���|�[�l���g�͔z�u����܂��B
 * </p>
 * <p>
 * ����ӏ��ɕ����̃R���|�[�l���g���w�肵���ꍇ�AGridLayout�ŏc���܂��́A�����ɕ��ׂĔz�u����܂��B
 * ���̎w��́A{@link #add(Component, boolean, Direction...)}�̑������Ŏw�肵�܂��B�ȗ����ꂽ�ꍇ��false�ł��B
 * <example>add(arg1, true, Direction.South); add(arg2, true, Direction.South)</example>
 * �̏ꍇ�Aarg1��arg2�͏c���ł��B(false�̏ꍇ�͉����ł�)
 * <br/><br/>
 * �w��͏�ɏ㏑����������܂��B�܂�A�ŏI�I�ɓK�p�����̂́A���̏ꏊ�ɍŌ��add��������boolean�l�ł��B
 * <remarks>
 * boolean�l���ȗ����ꂽ�Ƃ��ɂ́A���w��̈����ł͂Ȃ��A<strong>�ÖٓI��false�w��</strong>���Ƃ������Ƃɗ��ӂ��Ă��������B<br/>
 * ���Ƃ��΁A�c1���3�̃{�^�������񂾃A�v���P�[�V�����ɁA4�ڂ̃{�^����ǉ��������Ƃ��܂��B
 * ���̂Ƃ��ɁA4�ڂ�add��boolean���ȗ����Ă��܂��Ɖ�1��ɕ\�������Ƃ������Ƃł��B
 * </remarks>
 * </p>
 * @author 22677478
 *
 */
public class BorderLayoutBuilder {
//	public static void main(String[] args) {
//		javax.swing.JFrame frame = new javax.swing.JFrame();
//		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//		Container con = frame.getContentPane();
//
//		BorderLayoutBuilder blb = new BorderLayoutBuilder();
//		blb.add(new javax.swing.JLabel("ID:"), Direction.North, Direction.West);
//		blb.add(new javax.swing.JTextArea("b"), Direction.North);
//		blb.add(new javax.swing.JButton("Search"), Direction.North, Direction.East);
//		blb.add(new javax.swing.JList());
//		blb.add(new javax.swing.JButton("OK"), Direction.South, Direction.East);
//		blb.add(new javax.swing.JButton("Cancel"), Direction.South, Direction.East);
//		blb.layoutComponent(con);
//		frame.pack();
//		frame.setVisible(true);
//	}
	public static enum Direction {
		North(BorderLayout.NORTH),
		West(BorderLayout.WEST),
		East(BorderLayout.EAST),
		South(BorderLayout.SOUTH),
		Center(BorderLayout.CENTER);

		private String borderLayoutString;
		Direction(String borderLayoutString) {
			this.borderLayoutString = borderLayoutString;
		}
	}

	private final Map<Direction, BorderLayoutBuilder> componentMap;
	private final List<Component> content;
	private boolean orientationHorizonal;

	public BorderLayoutBuilder() {
		this.content = new ArrayList<Component>();
		this.componentMap = new HashMap<BorderLayoutBuilder.Direction, BorderLayoutBuilder>();
	}
	/**
	 * CENTER�Ƀ��C�A�E�g�����R���|�[�l���g�����p���ŃC���X�^���X�𐶐����܂��B
	 * ���C�A�E�g�����ŊK�w���[���Ȃ����ꍇ�̂��߂̃R���X�g���N�^�ł��B
	 * ���C�A�E�g�����ŊK�w���[���Ȃ����ꍇ�A���̊K�w�ɕ\�������͂��������R���|�[�l���g�́A�V���ȊK�w��CENTER��
	 * �ێ�����܂��B
	 * @param content
	 */
	protected BorderLayoutBuilder(List<Component> content) {
		this.content = content;
		this.componentMap = new HashMap<BorderLayoutBuilder.Direction, BorderLayoutBuilder>();
	}

	/**
	 * ���C�A�E�g����R���|�[�l���g��ǉ����܂��B
	 * ���C�A�E�g�����ꏊ�́A{@link Direction}�񋓑̂̉ϒ������Ŏw�肵�܂��B
	 * Direction���ȗ������ꍇ�́ACENTER�ɔz�u����܂��B
	 *
	 * ���ꃌ�C���ɕ����̃R���|�[�l���g���z�u���ꂽ�ꍇ�̕��ѕ��́A���ł��B
	 * @param arg ���C�A�E�g����R���|�[�l���g
	 * @param directions �z�u�ʒu�B�擪���珇�ɓK�p����܂��B
	 */
	public void add(Component arg, Direction... directions) {
		add(arg, false, directions);
	}
	/**
	 * ���C�A�E�g����R���|�[�l���g��ǉ����܂��B
	 * ���ꃌ�C���ɕ����̃R���|�[�l���g���z�u���ꂽ�ꍇ�̕��ѕ�(�c/��)�𖾊m�Ɏw�肵�܂��B
	 *
	 * ���C�A�E�g�����ꏊ�́A{@link Direction}�񋓑̂̉ϒ������Ŏw�肵�܂��B
	 * Direction���ȗ������ꍇ�́ACENTER�ɔz�u����܂��B
	 * @param arg ���C�A�E�g����R���|�[�l���g
	 * @param directions �z�u�ʒu�B�擪���珇�ɓK�p����܂��B
	 */
	public void add(Component arg, boolean orientationHorizonal, Direction... directions) {
		if (directions.length == 0) {
			BorderLayoutBuilder center = componentMap.get(Direction.Center);
			if (center == null) {
				this.orientationHorizonal = orientationHorizonal;
				content.add(arg);
			} else {
				center.add(arg);
			}
		}else {
			Direction dir = directions[0];
			if (componentMap.get(dir) == null) {
				BorderLayoutBuilder blb;
				if (dir == Direction.Center) {
					blb = new BorderLayoutBuilder(this.content);
				} else {
					blb = new BorderLayoutBuilder();
				}
				blb.add(arg, orientationHorizonal, cdr(directions));
				componentMap.put(dir, blb);
			} else {
				BorderLayoutBuilder blb = componentMap.get(dir);
				blb.add(arg, orientationHorizonal, cdr(directions));
			}
		}
	}

	/**
	 * ���̃I�u�W�F�N�g�Ǘ����̃R���|�[�l���g���A�w�肳�ꂽ�R���e�i�Ƀ��C�A�E�g���܂��B
	 * @param container
	 */
	public void layoutComponent(Container container) {
		container.setLayout(new BorderLayout());

		if (componentMap.get(Direction.Center) == null) {
			int mainSize = this.content.size();
			if (mainSize > 1) {
				Container center = new Container();
				if (orientationHorizonal) {
					center.setLayout(new GridLayout(mainSize, 1));
				} else {
					center.setLayout(new GridLayout(1, mainSize));
				}
				for (Component element : this.content) {
					center.add(element);
				}
				container.add(center, BorderLayout.CENTER);
			} else if (mainSize == 1) {
				container.add(this.content.get(0), BorderLayout.CENTER);
			}
		} else {
			setContainerInDirection(container, Direction.Center);
		}
		setContainerInDirection(container, Direction.North);
		setContainerInDirection(container, Direction.South);
		setContainerInDirection(container, Direction.East);
		setContainerInDirection(container, Direction.West);
	}
	private void setContainerInDirection(Container container, Direction d) {
		if (componentMap.get(d) != null) {
			Container temp = new Container();
			componentMap.get(d).layoutComponent(temp);
			container.add(temp, d.borderLayoutString);
		}
	}

	private static Direction[] cdr(Direction[] arg) {
		Direction[] ret = new Direction[arg.length - 1];
		for (int i = 1; i < arg.length; i ++) {
			ret[i-1] = arg[i];
		}
		return ret;
	}
}
