package jp.gr.java_conf.t_era.view.parts.labeledtextarea;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalIconFactory;

import jp.gr.java_conf.t_era.view.dialog.ConfirmDialog;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbrem;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.Probrem;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ViewMode;
import jp.gr.java_conf.t_era.view.parts.ScrollerWithTextArea;
import jp.gr.java_conf.t_era.view.parts.UndoableTextArea;

/**
 * タブによって選択切り替え可能な、複数テキストエリア<p/>
 * このコンポーネントは、切り替え用タブ/テキストエリア/(テキストエリアの)スクロールバーなどを組み合わせた
 * 複合的なコンポーネントです。<br/>
 * 各パーツの詳細な使用は、それぞれのクラスのAPIを参照して下さい。
 * @author y-terada
 *
 */
public class TextAreaTabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	/**
	 * テキストエリアで使用するデフォルト(推奨)のフォント。
	 */
	private final Font recomendFont;
	/**
	 * テキストエリアのデフォルト(推奨)のタブサイズ。
	 */
	public static final int RECOMENDED_TAB_SIZE = 4;

	/**
	 * 表示状態にあるテキストエリアの一覧<br/>
	 * ラベル(タブの表示文字列)をキーとしてテキストエリアを管理します。
	 */
	private final Map<Labeled, ScrollerWithTextArea> tabLabelMap = new LinkedHashMap<Labeled, ScrollerWithTextArea>();

	/**
	 * 表示する親フレーム
	 */
	private final JFrame parent;

	/**
	 * ラベル(タブの表示文字列)のテンプレート
	 */
	private final String labelNameTemplate;

	/**
	 * このクラスのデフォルトのサイズ指定
	 */
	private static final Dimension DEFAULT_PANE_SIZE = new Dimension( 300, 120 );

	/**
	 * ラベル名を区別するためのカウンタ<br/>
	 * この値を変えるのは、単純に表示上の見分けをするためです。
	 */
	protected int tabNameCounter = 0;

	private final boolean tabCloseButtonFocusable;
	/**
	 * 親フレームを指定してインスタンス生成します。<p/>
	 * 生成されたオブジェクトは、テキストエリアをデフォルトで1件もちます。
	 * テキストエリアはデフォルト状態で、指定された文字列を表示します。
	 * <p>このコンストラクタを使用した場合、各タブを閉じるボタンはタブキーではフォーカスされません。
	 * @param parent 親フレーム
	 * @param defaultText デフォルトで表示されるテキストエリアに表示する文字列
	 * @param labelNameTemplate タブラベルのテンプレート
	 * @param recomendFont 使用するフォント
	 */
	public TextAreaTabbedPane(JFrame parent, String defaultText, String labelNameTemplate, Font recomendFont){
		this(parent, defaultText, labelNameTemplate, recomendFont, false);
	}

	/**
	 * 親フレームを指定してインスタンス生成します。<p/>
	 * 生成されたオブジェクトは、テキストエリアをデフォルトで1件もちます。
	 * テキストエリアはデフォルト状態で、指定された文字列を表示します。
	 * @param parent 親フレーム
	 * @param defaultText デフォルトで表示されるテキストエリアに表示する文字列
	 * @param labelNameTemplate タブラベルのテンプレート
	 * @param recomendFont 使用するフォント
	 * @param tabCloseButtonFocusable true:各タブの閉じるボタンがタブキーでフォーカスされます。
	 */
	public TextAreaTabbedPane(JFrame parent, String defaultText, String labelNameTemplate, Font recomendFont, boolean tabCloseButtonFocusable){
		super( JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
		this.setPreferredSize( DEFAULT_PANE_SIZE );

		this.parent = parent;
		this.labelNameTemplate = labelNameTemplate;
		this.recomendFont = recomendFont;
		this.tabCloseButtonFocusable = tabCloseButtonFocusable;

		// DefaultPane
		addNewPane( defaultText );
	}

	/**
	 * 親フレームを指定してインスタンス生成します。<p/>
	 * 生成されたオブジェクトは、テキストエリアをデフォルトで1件もちます。
	 * テキストエリアはデフォルトで空表示です。
	 * @param parent 親フレーム
	 * @param recomendFont 使用するフォント
	 */
	public TextAreaTabbedPane(JFrame parent, Font recomendFont){
		this( parent, "", "TabbedTextArea", recomendFont );
	}

	/**
	 * このオブジェクトに、テキストエリアを追加します。
	 * @param labelText ラベルに表示する文字列
	 * @param defaultText テキストエリアの内容のデフォルト
	 */
	public void addNewPane(String labelText, String defaultText){
		UndoableTextArea textArea = new UndoableTextArea(defaultText);
		textArea.setTabSize( RECOMENDED_TAB_SIZE );
		textArea.setFont( recomendFont );

		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		ScrollerWithTextArea sta = new ScrollerWithTextArea(scrollPane, textArea );
		TableViewPortTabPanel labelPanel = new TableViewPortTabPanel(labelText, tabCloseButtonFocusable);
		this.addTab( labelText, scrollPane );
		this.setTabComponentAt( this.getTabCount() - 1, labelPanel );

		tabLabelMap.put( labelPanel, sta );
		textArea.addKeyListener(new LabelChangeAction(parent, labelPanel.getLabel()));
	}

	/**
	 * このオブジェクトに、テキストエリアを追加します。<p/>
	 * ラベルの表示文字列は、コンストラクタで指定されたテンプレートから自動生成します。
	 * @param defaultText テキストエリアの内容のデフォルト
	 */
	public void addNewPane( String defaultText ){
		tabNameCounter ++;
		addNewPane( this.labelNameTemplate + tabNameCounter, defaultText );
	}

	/**
	 * 指定されたタブと、そのタブで管理されているテキストエリアを閉じます。<br/>
	 * {@link #removePane(jp.gr.java_conf.t_era.view.parts.labeledtextarea.TextAreaTabbedPane.TableViewPortTabPanel, boolean)}メソッドの非強制動作を行います。
	 * @param key 閉じる処理を行う対象のタブ
	 */
	private void removePane(TableViewPortTabPanel key){
		removePane(key, false);
	}

	/**
	 * 指定されたタブと、そのタブで管理されているテキストエリアを閉じます。<br/>
	 * 強制削除を指定された場合、UIでの確認(閉じてよいか？)を省略します。<p/>
	 * 強制モードではない場合、UIでの確認を行います。<br/>
	 * このメソッドの実装では、確認を行うのは、Undoにバッファが残されている場合だけです。
	 * @param key 閉じる処理を行う対象のタブ
	 * @param force 強制削除
	 */
	private void removePane(TableViewPortTabPanel key, boolean force){
		ScrollerWithTextArea entry = tabLabelMap.get(key);

		// 閉じることができるのは以下の条件どれかを満たす場合
		// ・強制閉じ(全閉じ)
		// ・Undoの効かない状態(初期表示状態)
		// ・ユーザの同意を得た状態
		String message = MessageFormat.format("[{0}]タブを閉じます", key.getLabel().getText());
		if (force || ! entry.getTextArea().canUndo() || ConfirmDialog.isYesNoConfirmed(parent, "変更の確認", message, false)){
			this.tabLabelMap.remove(key);
			this.remove(entry.getScrollPane());
		}
	}

	/**
	 * 現在選択され、表示状態のテキストエリアを返します。
	 * @return 表示状態のテキストエリア
	 */
	public UndoableTextArea getCurrentTextArea(){
		JScrollPane currentPane = (JScrollPane)this.getSelectedComponent();
		return (UndoableTextArea)currentPane.getViewport().getComponent(0);
	}

	/**
	 * 指定された番号のタブオブジェクトを返します。
	 * @param index
	 * @return タブオブジェクト
	 */
	public TableViewPortTabPanel getTabPanelAt(int index){
		return (TableViewPortTabPanel)this.getTabComponentAt(index);
	}

	/**
	 * 管理されているすべてのタブを閉じます。
	 */
	public void closeAll(){
		ConfirmWithProbrem cwp = new ConfirmWithProbrem("確認", "アプリケーションを終了します。");
		Set<Labeled> keySet = new HashSet<Labeled>(tabLabelMap.keySet());
		for (final Labeled key : keySet) {
			ScrollerWithTextArea entry = tabLabelMap.get(key);
			if (entry.getTextArea().canUndo()) {
				cwp.addProbrem(new Probrem("保存されていないファイルがあります。保存しますか？" + key.getLabel().getText(),
						new ProbremAction() {
							@Override
							public String getCaption() {
								return "破棄";
							}
							@Override
							public void doAction() {
								removePane((TableViewPortTabPanel)key, true);
							}
				}));
			}
		}
		if (cwp.askConfirm(parent, ViewMode.BoxMode)) {
			System.exit(0);
		}
	}

	/**
	 * Undo可能かを返します。<br/>
	 * 表示状態のテキストエリアがUndo可能な場合のみtrueを返します。
	 * @return 表示状態のテキストエリアがUndo可能な場合
	 */
	public boolean canUndo(){
		return getCurrentTextArea().canUndo();
	}
	/**
	 * Redo可能かを返します。<br/>
	 * 表示状態のテキストエリアがRedo可能な場合のみtrueを返します。
	 * @return 表示状態のテキストエリアがRedo可能な場合
	 */
	public boolean canRedo(){
		return getCurrentTextArea().canRedo();
	}

	/**
	 * 表示状態のテキストエリアをUndoします。
	 */
	public void undo(){
		getCurrentTextArea().undo();
	}
	/**
	 * 表示状態のテキストエリアをRedoします。
	 */
	public void redo(){
		getCurrentTextArea().redo();
	}
	public Map<Labeled, ScrollerWithTextArea> getTabes(){
		return Collections.unmodifiableMap(tabLabelMap);
	}

	/**
	 * TableViewPort用タブ<p/>
	 * 閉じるボタン&名称変更機能 を加えた複合的なコンポーネントです。
	 * @author y-terada
	 *
	 */
	protected class TableViewPortTabPanel extends JPanel implements Labeled{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 閉じるボタンのサイズ(一辺)
		 */
		private static final int BUTTON_SIZE = 16;

		/**
		 * 閉じるボタンのサイズ(矩形)
		 */
		private final Dimension CLOSE_BUTTON_SIZE = new Dimension( BUTTON_SIZE, BUTTON_SIZE );

		/**
		 * タブの表示ラベル
		 */
		private final JLabel label;

		/**
		 * タブの表示文字列を指定して、インスタンス生成します。
		 * @param labelString
		 */
		public TableViewPortTabPanel(String labelString, boolean closeButtonFocusable){
			setLayout( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			setOpaque(false);

			label = new JLabel( labelString );
			JButton buttonClose = new JButton( new MetalIconFactory.PaletteCloseIcon() );
			buttonClose.setFocusable(closeButtonFocusable);
			buttonClose.setPreferredSize( CLOSE_BUTTON_SIZE );

			buttonClose.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					close();
				}
			});

			add( label );
			label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
			add( buttonClose );
			setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
			this.setComponentPopupMenu( new TabbedPanePopup( parent, this, label ) );
			this.addMouseListener( new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					TextAreaTabbedPane.this.setSelectedComponent( tabLabelMap.get( TableViewPortTabPanel.this ).getScrollPane() );
				}
			});
		}

		/**
		 * このオブジェクトのタブを閉じます。<br/>
		 * 一般的に、タブを閉じる場合には、そのタブが管理するコンポーネントを同時に破棄する必要があります。
		 */
		public void close(){
			TextAreaTabbedPane.this.removePane(TableViewPortTabPanel.this);
		}

		/**
		 * 表示ラベルを返します。
		 */
		public JLabel getLabel(){
			return label;
		}
	}
}
