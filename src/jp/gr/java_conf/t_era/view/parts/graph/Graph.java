package jp.gr.java_conf.t_era.view.parts.graph;

import java.awt.Graphics;

/**
 * {@link GraphContainer}�ŕ\������O���t�́A���̃C���^�[�t�F�[�X����������K�v������܂��B
 * <p>�������́A{@link Graphics}�ɑ΂���`����`���܂��B
 * @author y-terada
 *
 */
public interface Graph {
	/**
	 * {@link GraphContainer#paint(Graphics)}���ɃR�[�������`�惁�\�b�h
	 * @param g
	 */
	void paint(Graphics g);
}
