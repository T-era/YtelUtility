package ytel.view.parts;

import java.awt.Font;
import java.util.Arrays;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

/**
 * �ꗗ�\��\������{@link JViewport}<p/>
 * ���̃N���X�̎����ł́A�ꗗ�\�̕\�����e��z��ŕێ����܂��B<br/>
 * �ꗗ�\�́A�擪�s�ƂȂ�^�C�g���s�ƁA�e�J�����̃f�[�^���e��\������o�����[�s��
 * �ō\������܂��B
 * @author y-terada
 *
 */
public class TableViewport extends JViewport {
	private static final long serialVersionUID = 1L;

	/**
	 * �\������ꗗ�\
	 */
	protected JTable table;
	/**
	 * �ꗗ�\�̊e��̃^�C�g��
	 */
	protected Object[] titles;
	/**
	 * �ꗗ�\�ɕ\������f�[�^���e
	 */
	protected Object[][] values;

	private final Font recomendFont;

	/**
	 * ���̃r���[�̎��|�b�v�A�b�v���j���[�ł��B
	 * (�\���e�[�u�����؂�ւ��ƁA�ăZ�b�g���܂��B)
	 */
	private JPopupMenu popupMenu;

	/**
	 * �P�s�I���̕\�����C���X�^���X�𐶐����܂��B
	 */
	public TableViewport(Font recomendFont){
		this( ListSelectionModel.SINGLE_SELECTION, recomendFont );
	}
	/**
	 * �P�s�I���̕\�����C���X�^���X�𐶐����܂��B
	 * �����\���ɕ\�����e���w�肵�܂��B
	 * @param titles �\�����e(���ږ�) 
	 * @param values �\�����e(�\�����e)
	 */
	public TableViewport( Object[] titles, Object[][] values, Font recomendFont ){
		this( titles, values, ListSelectionModel.SINGLE_SELECTION, recomendFont );
	}
	/**
	 * [1���R�[�h�I��/�������I��]���w�肵�āA�C���X�^���X�𐶐����܂��B
	 * <br/>�ꗗ�\�̒��g�͋�ł��B
	 * @param selectionMode [1���R�[�h�I��/�������I��]s
	 */
	public TableViewport( int selectionMode, Font recomendFont  ){
		this(selectionMode, new JTable(), recomendFont );
	}

	/**
	 * [1���R�[�h�I��/�������I��]�̎w��ƁA�����\���ɕ\�����e�̎w������āA�C���X�^���X�𐶐����܂��B
	 * @param titles �\�����e(���ږ�) 
	 * @param values �\�����e(�\�����e)
	 * @param selectionMode [1���R�[�h�I��/�������I��]s
	 */
	public TableViewport( Object[] titles, Object[][] values, int selectionMode, Font recomendFont ){
		this(selectionMode, new JTable(values, titles), recomendFont);
		this.titles = titles;
		this.values = values;
	}
	/**
	 * * [1���R�[�h�I��/�������I��]�̎w��ƁA�\������ꗗ�\���w�肵�āA�C���X�^���X�𐶐����܂��B
	 * @param selectionMode [1���R�[�h�I��/�������I��]s
	 * @param newTable
	 */
	protected TableViewport(int selectionMode, JTable newTable, Font recomendFont){
		super();
		this.recomendFont = recomendFont;

		table = newTable;
		table.setFont(this.recomendFont);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setComponentPopupMenu(popupMenu);
		add( table );
		table.setSelectionMode( selectionMode );
	}

	/**
	 * �ꗗ�\�̕\���f�[�^���w�肵�܂��B
	 * �g�p����JTable�N���X�Ɏw�肪����ꍇ�A{@link #setDatas(Object[], Object[][], JTable)}���g�p���܂��B
	 * @param titles ���ږ��ꗗ
	 * @param values �ݒ�l�ꗗ
	 */
	public void setDatas( Object[] titles, Object[][] values ){
		setDatas(titles, values, new JTable( values, titles ));
	}
	/**
	 * �ꗗ�\�̕\���f�[�^���w�肵�܂��B<p/>
	 * �g�p����e�[�u���N���X�Ɏw�肪����ꍇ�A���̃��\�b�h���g�p���܂��B
	 * ��ʓI�ȕ\�̏ꍇ({@link JTable}�N���X���g�p����ꍇ)�A{@link #setDatas(Object[], Object[][])}���g�p���邱�ƂŁA
	 * newTable�̎w����ȗ��ł��܂��B<p/>
	 * ���̃��\�b�h�̎����ł́A�^������newTable�ɁAtitles, values�̓��e�𔽉f���鏈�����܂݂܂���B<br/>
	 * newTable�ɓK�؂ȃf�[�^���e��^����̂͌Ăяo�����̐ӔC�ł��B
	 * @param titles ���ږ��ꗗ
	 * @param values �ݒ�l�ꗗ
	 * @param newTable 
	 */
	protected final void setDatas(Object[] titles, Object[][] values, JTable newTable){
		if (table != null) {
			remove(table);
		}
		this.table = newTable;
		table.setFont(this.recomendFont);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setComponentPopupMenu(popupMenu);
		add( table );

		this.titles = titles;
		this.values = values;
	}

	/**
	 * �ꗗ�\�̃^�C�g����Ԃ��܂��B<br>
	 * ���̎����ł́A�߂�l�̓^�C�g���ꗗ�̃R�s�[�ł��B���̃��\�b�h�̖߂�l�ɉ������ύX�͌��̃f�[�^�ɔ��f����܂���B
	 * @return �^�C�g���ꗗ
	 */
	public Object[] getTitles(){
		return Arrays.copyOf(titles, titles.length);
	}

	/**
	 * �ꗗ�\�̕\���l��Ԃ��܂��B<br>
	 * ���̎����ł́A�߂�l�͕\���l�̈ꗗ�̃R�s�[�ł��B���̃��\�b�h�̖߂�l�ɉ������ύX�́A���̃f�[�^�ɂ͔��f����܂���B
	 * @return �\���l�̈ꗗ
	 */
	public Object[][] getValues(){
		Object[][] ret = new Object[values.length][];

		for (int i = 0; i < values.length; i ++) {
			ret[i] = Arrays.copyOf(values[i], values[i].length);
		}
		return ret;
	}

	@Override
	public void setComponentPopupMenu(JPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}
}
