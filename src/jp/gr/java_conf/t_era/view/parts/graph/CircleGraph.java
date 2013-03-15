package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �~�O���t
 * <p>�w�肳�ꂽ�v�f���ꂼ��̐�߂銄�����A�F�����\�����܂��B
 * @author y-terada
 *
 */
public class CircleGraph implements Graph{
	/**
	 * �~�̒��S
	 */
	private final Point center;
	/**
	 * �~�̔��a
	 */
	private final int radius;

	/**
	 * �O���t�ɕ\������e�v�f
	 */
	private final List<HasPercentage> elements;
	/**
	 * �O���t�̐F��`(�e�v�f�́A���̐F���X�g�����[�e�[�g���Ĕz�F�����{@link CircleGraphColors})
	 */
	private final List<Color> colorList;
	/**
	 * �O���t�̔z�F���s���I�u�W�F�N�g
	 */
	private final CircleGraphColors colors;

	/**
	 * �~�O���t�I�u�W�F�N�g�𐶐����܂��B<p>
	 * <ul>
	 *  <li>�v�f��"����"�ɂ���<br>
	 *  �e�\���v�f�̎���"����"�́A�ȉ��̊e�����𖞂����K�v������܂��B
	 * 	 <ul>
	 *    <li>�e�v�f�̊�����0�ȏ�
	 *    <li>�e�v�f�̍��v��100�ȉ�
	 *   </ul>
	 *  <li>�\���F�ɂ���<br>
	 *  �e�v�f�́A�w�肳�ꂽ�F�̃��X�g�ɏ]���Ē��F����܂��B<br>
	 *  �v�f�̐����A�F�̐��𒴂���ꍇ�A�F�̃��X�g�͏z���Ďg�p���܂��B<br>
	 *  �v�f�̐��������Ȃ�\��������ꍇ�A�F���X�g�̐擪�Ɩ����͗אڂ��ĕ\�������̂ŁA�قȂ�F�ɂ���ׂ��ł��B<br>
	 *  <ul><li>�v�f�̐擪�Ɩ������~�O���t�̌��_�ŗאڂ��邱�Ƃ����蓾�܂��B<br>
	 *   ���̃N���X�̎����ł́A�v�f�̖������A�F���X�g�̐擪�ɂȂ�ꍇ�A��L���Q�𓦂�邽�߂ɁA�F���X�g��2�Ԗڂ�
	 *   �g�p���܂��B
	 *  </ul>
	 * </ul>
	 * @param center �~�O���t�̒��S�_
	 * @param radius �~�O���t�̔��a
	 * @param elements �~�O���t�ŕ\������v�f�̈ꗗ
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
	 * �w�肳�ꂽ�v�f���g�p���Ă���F���擾���܂��B
	 * @param element 
	 * @return �F
	 */
	public Color getColor(HasPercentage element) {
		Color ret = colors.getColor(element);
		if (ret == null) {
			ret = colors.decideColor(element);
		}
		return ret;
	}

	/**
	 * �v�f���Ɏg�p����F�̌���^�ێ����s���N���X
	 * @author y-terada
	 *
	 */
	private class CircleGraphColors {
		/**
		 * �e�v�f�ɂ��āA����ς݂̐F��ێ�����Map
		 */
		private final Map<HasPercentage, Color> colorMap;

		private CircleGraphColors() {
			if (colorList.size() < 3) {
				throw new IllegalStateException("�F���R�F�����ł͉~�O���t�͕`���Ȃ�");
			}
			colorMap = new HashMap<HasPercentage, Color>();
		}

		/**
		 * �w�肳�ꂽ�v�f�̂��߂̐F�����肵�܂��B
		 * <br>����ɂ͎w�肳�ꂽ�F���X�g���g�p���A���X�g��������͐擪�Ƀ��[�v���܂��B
		 * <br>�w�肳�ꂽ�v�f���S�v�f�̖����ƈ�v����ꍇ�A�����̎����[�v�̐擪�̐F���I�����ꂽ�ꍇ��
		 * (�O���t�̌��_�œ����F���אڂ��邱�Ƃ�����邽��)�A�F���X�g��2�Ԗڂ�Ԃ��܂��B
		 * @param element
		 * @return �F
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
