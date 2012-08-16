package ytel.view.parts;

import java.awt.Font;
import java.util.Arrays;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

/**
 * 一覧表を表示する{@link JViewport}<p/>
 * このクラスの実装では、一覧表の表示内容を配列で保持します。<br/>
 * 一覧表は、先頭行となるタイトル行と、各カラムのデータ内容を表示するバリュー行列
 * で構成されます。
 * @author y-terada
 *
 */
public class TableViewport extends JViewport {
	private static final long serialVersionUID = 1L;

	/**
	 * 表示する一覧表
	 */
	protected JTable table;
	/**
	 * 一覧表の各列のタイトル
	 */
	protected Object[] titles;
	/**
	 * 一覧表に表示するデータ内容
	 */
	protected Object[][] values;

	private final Font recomendFont;

	/**
	 * このビューの持つポップアップメニューです。
	 * (表示テーブルが切り替わると、再セットします。)
	 */
	private JPopupMenu popupMenu;

	/**
	 * 単行選択の表を持つインスタンスを生成します。
	 */
	public TableViewport(Font recomendFont){
		this( ListSelectionModel.SINGLE_SELECTION, recomendFont );
	}
	/**
	 * 単行選択の表を持つインスタンスを生成します。
	 * 初期表示に表示内容を指定します。
	 * @param titles 表示内容(項目名) 
	 * @param values 表示内容(表示内容)
	 */
	public TableViewport( Object[] titles, Object[][] values, Font recomendFont ){
		this( titles, values, ListSelectionModel.SINGLE_SELECTION, recomendFont );
	}
	/**
	 * [1レコード選択/複数件選択]を指定して、インスタンスを生成します。
	 * <br/>一覧表の中身は空です。
	 * @param selectionMode [1レコード選択/複数件選択]s
	 */
	public TableViewport( int selectionMode, Font recomendFont  ){
		this(selectionMode, new JTable(), recomendFont );
	}

	/**
	 * [1レコード選択/複数件選択]の指定と、初期表示に表示内容の指定をして、インスタンスを生成します。
	 * @param titles 表示内容(項目名) 
	 * @param values 表示内容(表示内容)
	 * @param selectionMode [1レコード選択/複数件選択]s
	 */
	public TableViewport( Object[] titles, Object[][] values, int selectionMode, Font recomendFont ){
		this(selectionMode, new JTable(values, titles), recomendFont);
		this.titles = titles;
		this.values = values;
	}
	/**
	 * * [1レコード選択/複数件選択]の指定と、表示する一覧表を指定して、インスタンスを生成します。
	 * @param selectionMode [1レコード選択/複数件選択]s
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
	 * 一覧表の表示データを指定します。
	 * 使用するJTableクラスに指定がある場合、{@link #setDatas(Object[], Object[][], JTable)}を使用します。
	 * @param titles 項目名一覧
	 * @param values 設定値一覧
	 */
	public void setDatas( Object[] titles, Object[][] values ){
		setDatas(titles, values, new JTable( values, titles ));
	}
	/**
	 * 一覧表の表示データを指定します。<p/>
	 * 使用するテーブルクラスに指定がある場合、このメソッドを使用します。
	 * 一般的な表の場合({@link JTable}クラスを使用する場合)、{@link #setDatas(Object[], Object[][])}を使用することで、
	 * newTableの指定を省略できます。<p/>
	 * このメソッドの実装では、与えられるnewTableに、titles, valuesの内容を反映する処理を含みません。<br/>
	 * newTableに適切なデータ内容を与えるのは呼び出し側の責任です。
	 * @param titles 項目名一覧
	 * @param values 設定値一覧
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
	 * 一覧表のタイトルを返します。<br>
	 * この実装では、戻り値はタイトル一覧のコピーです。このメソッドの戻り値に加えた変更は元のデータに反映されません。
	 * @return タイトル一覧
	 */
	public Object[] getTitles(){
		return Arrays.copyOf(titles, titles.length);
	}

	/**
	 * 一覧表の表示値を返します。<br>
	 * この実装では、戻り値は表示値の一覧のコピーです。このメソッドの戻り値に加えた変更は、元のデータには反映されません。
	 * @return 表示値の一覧
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
