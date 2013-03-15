package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * ��̗v�f��\������~�O���t�B
 * <br>����v�f����߂銄�����A�\�����܂��B
 * @author y-terada
 *
 */
public class SingleElementCirgleGraph implements Graph {
	/**
	 * �~�O���t�̒��S�_
	 */
	private final Point center;
	/**
	 * �~�O���t�̔��a
	 */
	private final int radius;

	/**
	 * �\������A�����������I�u�W�F�N�g<br>
	 * ({@link HasPercentage#getPercentage()}��0����/100�ȏ�̏ꍇ�͖���`�B)
	 */
	private final HasPercentage element;
	/**
	 * �O���t�̕\���F
	 */
	private final Color color;

	/**
	 * �����������I�u�W�F�N�g���w�肵�ăO���t�𐶐����܂��B
	 * ({@link HasPercentage#getPercentage()}��0����/100�ȏ�̏ꍇ�͖���`�B)�ł��B
	 * @param center �O���t�̒��S�_(�e�R���|�[�l���g���ł̑��΍��W�Ŏw�肵�܂�)
	 * @param radius �O���t�̔��a
	 * @param element �����������I�u�W�F�N�g 
	 * @param color �O���t�̕\���F
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
