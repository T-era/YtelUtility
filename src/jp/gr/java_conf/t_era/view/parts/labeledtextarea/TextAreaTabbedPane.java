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
 * �^�u�ɂ���đI��؂�ւ��\�ȁA�����e�L�X�g�G���A<p/>
 * ���̃R���|�[�l���g�́A�؂�ւ��p�^�u/�e�L�X�g�G���A/(�e�L�X�g�G���A��)�X�N���[���o�[�Ȃǂ�g�ݍ��킹��
 * �����I�ȃR���|�[�l���g�ł��B<br/>
 * �e�p�[�c�̏ڍׂȎg�p�́A���ꂼ��̃N���X��API���Q�Ƃ��ĉ������B
 * @author y-terada
 *
 */
public class TextAreaTabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	/**
	 * �e�L�X�g�G���A�Ŏg�p����f�t�H���g(����)�̃t�H���g�B
	 */
	private final Font recomendFont;
	/**
	 * �e�L�X�g�G���A�̃f�t�H���g(����)�̃^�u�T�C�Y�B
	 */
	public static final int RECOMENDED_TAB_SIZE = 4;

	/**
	 * �\����Ԃɂ���e�L�X�g�G���A�̈ꗗ<br/>
	 * ���x��(�^�u�̕\��������)���L�[�Ƃ��ăe�L�X�g�G���A���Ǘ����܂��B
	 */
	private final Map<Labeled, ScrollerWithTextArea> tabLabelMap = new LinkedHashMap<Labeled, ScrollerWithTextArea>();

	/**
	 * �\������e�t���[��
	 */
	private final JFrame parent;

	/**
	 * ���x��(�^�u�̕\��������)�̃e���v���[�g
	 */
	private final String labelNameTemplate;

	/**
	 * ���̃N���X�̃f�t�H���g�̃T�C�Y�w��
	 */
	private static final Dimension DEFAULT_PANE_SIZE = new Dimension( 300, 120 );

	/**
	 * ���x��������ʂ��邽�߂̃J�E���^<br/>
	 * ���̒l��ς���̂́A�P���ɕ\����̌����������邽�߂ł��B
	 */
	protected int tabNameCounter = 0;

	private final boolean tabCloseButtonFocusable;
	/**
	 * �e�t���[�����w�肵�ăC���X�^���X�������܂��B<p/>
	 * �������ꂽ�I�u�W�F�N�g�́A�e�L�X�g�G���A���f�t�H���g��1�������܂��B
	 * �e�L�X�g�G���A�̓f�t�H���g��ԂŁA�w�肳�ꂽ�������\�����܂��B
	 * <p>���̃R���X�g���N�^���g�p�����ꍇ�A�e�^�u�����{�^���̓^�u�L�[�ł̓t�H�[�J�X����܂���B
	 * @param parent �e�t���[��
	 * @param defaultText �f�t�H���g�ŕ\�������e�L�X�g�G���A�ɕ\�����镶����
	 * @param labelNameTemplate �^�u���x���̃e���v���[�g
	 * @param recomendFont �g�p����t�H���g
	 */
	public TextAreaTabbedPane(JFrame parent, String defaultText, String labelNameTemplate, Font recomendFont){
		this(parent, defaultText, labelNameTemplate, recomendFont, false);
	}

	/**
	 * �e�t���[�����w�肵�ăC���X�^���X�������܂��B<p/>
	 * �������ꂽ�I�u�W�F�N�g�́A�e�L�X�g�G���A���f�t�H���g��1�������܂��B
	 * �e�L�X�g�G���A�̓f�t�H���g��ԂŁA�w�肳�ꂽ�������\�����܂��B
	 * @param parent �e�t���[��
	 * @param defaultText �f�t�H���g�ŕ\�������e�L�X�g�G���A�ɕ\�����镶����
	 * @param labelNameTemplate �^�u���x���̃e���v���[�g
	 * @param recomendFont �g�p����t�H���g
	 * @param tabCloseButtonFocusable true:�e�^�u�̕���{�^�����^�u�L�[�Ńt�H�[�J�X����܂��B
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
	 * �e�t���[�����w�肵�ăC���X�^���X�������܂��B<p/>
	 * �������ꂽ�I�u�W�F�N�g�́A�e�L�X�g�G���A���f�t�H���g��1�������܂��B
	 * �e�L�X�g�G���A�̓f�t�H���g�ŋ�\���ł��B
	 * @param parent �e�t���[��
	 * @param recomendFont �g�p����t�H���g
	 */
	public TextAreaTabbedPane(JFrame parent, Font recomendFont){
		this( parent, "", "TabbedTextArea", recomendFont );
	}

	/**
	 * ���̃I�u�W�F�N�g�ɁA�e�L�X�g�G���A��ǉ����܂��B
	 * @param labelText ���x���ɕ\�����镶����
	 * @param defaultText �e�L�X�g�G���A�̓��e�̃f�t�H���g
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
	 * ���̃I�u�W�F�N�g�ɁA�e�L�X�g�G���A��ǉ����܂��B<p/>
	 * ���x���̕\��������́A�R���X�g���N�^�Ŏw�肳�ꂽ�e���v���[�g���玩���������܂��B
	 * @param defaultText �e�L�X�g�G���A�̓��e�̃f�t�H���g
	 */
	public void addNewPane( String defaultText ){
		tabNameCounter ++;
		addNewPane( this.labelNameTemplate + tabNameCounter, defaultText );
	}

	/**
	 * �w�肳�ꂽ�^�u�ƁA���̃^�u�ŊǗ�����Ă���e�L�X�g�G���A����܂��B<br/>
	 * {@link #removePane(jp.gr.java_conf.t_era.view.parts.labeledtextarea.TextAreaTabbedPane.TableViewPortTabPanel, boolean)}���\�b�h�̔񋭐�������s���܂��B
	 * @param key ���鏈�����s���Ώۂ̃^�u
	 */
	private void removePane(TableViewPortTabPanel key){
		removePane(key, false);
	}

	/**
	 * �w�肳�ꂽ�^�u�ƁA���̃^�u�ŊǗ�����Ă���e�L�X�g�G���A����܂��B<br/>
	 * �����폜���w�肳�ꂽ�ꍇ�AUI�ł̊m�F(���Ă悢���H)���ȗ����܂��B<p/>
	 * �������[�h�ł͂Ȃ��ꍇ�AUI�ł̊m�F���s���܂��B<br/>
	 * ���̃��\�b�h�̎����ł́A�m�F���s���̂́AUndo�Ƀo�b�t�@���c����Ă���ꍇ�����ł��B
	 * @param key ���鏈�����s���Ώۂ̃^�u
	 * @param force �����폜
	 */
	private void removePane(TableViewPortTabPanel key, boolean force){
		ScrollerWithTextArea entry = tabLabelMap.get(key);

		// ���邱�Ƃ��ł���͈̂ȉ��̏����ǂꂩ�𖞂����ꍇ
		// �E������(�S��)
		// �EUndo�̌����Ȃ����(�����\�����)
		// �E���[�U�̓��ӂ𓾂����
		String message = MessageFormat.format("[{0}]�^�u����܂�", key.getLabel().getText());
		if (force || ! entry.getTextArea().canUndo() || ConfirmDialog.isYesNoConfirmed(parent, "�ύX�̊m�F", message, false)){
			this.tabLabelMap.remove(key);
			this.remove(entry.getScrollPane());
		}
	}

	/**
	 * ���ݑI������A�\����Ԃ̃e�L�X�g�G���A��Ԃ��܂��B
	 * @return �\����Ԃ̃e�L�X�g�G���A
	 */
	public UndoableTextArea getCurrentTextArea(){
		JScrollPane currentPane = (JScrollPane)this.getSelectedComponent();
		return (UndoableTextArea)currentPane.getViewport().getComponent(0);
	}

	/**
	 * �w�肳�ꂽ�ԍ��̃^�u�I�u�W�F�N�g��Ԃ��܂��B
	 * @param index
	 * @return �^�u�I�u�W�F�N�g
	 */
	public TableViewPortTabPanel getTabPanelAt(int index){
		return (TableViewPortTabPanel)this.getTabComponentAt(index);
	}

	/**
	 * �Ǘ�����Ă��邷�ׂẴ^�u����܂��B
	 */
	public void closeAll(){
		ConfirmWithProbrem cwp = new ConfirmWithProbrem("�m�F", "�A�v���P�[�V�������I�����܂��B");
		Set<Labeled> keySet = new HashSet<Labeled>(tabLabelMap.keySet());
		for (final Labeled key : keySet) {
			ScrollerWithTextArea entry = tabLabelMap.get(key);
			if (entry.getTextArea().canUndo()) {
				cwp.addProbrem(new Probrem("�ۑ�����Ă��Ȃ��t�@�C��������܂��B�ۑ����܂����H" + key.getLabel().getText(),
						new ProbremAction() {
							@Override
							public String getCaption() {
								return "�j��";
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
	 * Undo�\����Ԃ��܂��B<br/>
	 * �\����Ԃ̃e�L�X�g�G���A��Undo�\�ȏꍇ�̂�true��Ԃ��܂��B
	 * @return �\����Ԃ̃e�L�X�g�G���A��Undo�\�ȏꍇ
	 */
	public boolean canUndo(){
		return getCurrentTextArea().canUndo();
	}
	/**
	 * Redo�\����Ԃ��܂��B<br/>
	 * �\����Ԃ̃e�L�X�g�G���A��Redo�\�ȏꍇ�̂�true��Ԃ��܂��B
	 * @return �\����Ԃ̃e�L�X�g�G���A��Redo�\�ȏꍇ
	 */
	public boolean canRedo(){
		return getCurrentTextArea().canRedo();
	}

	/**
	 * �\����Ԃ̃e�L�X�g�G���A��Undo���܂��B
	 */
	public void undo(){
		getCurrentTextArea().undo();
	}
	/**
	 * �\����Ԃ̃e�L�X�g�G���A��Redo���܂��B
	 */
	public void redo(){
		getCurrentTextArea().redo();
	}
	public Map<Labeled, ScrollerWithTextArea> getTabes(){
		return Collections.unmodifiableMap(tabLabelMap);
	}

	/**
	 * TableViewPort�p�^�u<p/>
	 * ����{�^��&���̕ύX�@�\ �������������I�ȃR���|�[�l���g�ł��B
	 * @author y-terada
	 *
	 */
	protected class TableViewPortTabPanel extends JPanel implements Labeled{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * ����{�^���̃T�C�Y(���)
		 */
		private static final int BUTTON_SIZE = 16;

		/**
		 * ����{�^���̃T�C�Y(��`)
		 */
		private final Dimension CLOSE_BUTTON_SIZE = new Dimension( BUTTON_SIZE, BUTTON_SIZE );

		/**
		 * �^�u�̕\�����x��
		 */
		private final JLabel label;

		/**
		 * �^�u�̕\����������w�肵�āA�C���X�^���X�������܂��B
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
		 * ���̃I�u�W�F�N�g�̃^�u����܂��B<br/>
		 * ��ʓI�ɁA�^�u�����ꍇ�ɂ́A���̃^�u���Ǘ�����R���|�[�l���g�𓯎��ɔj������K�v������܂��B
		 */
		public void close(){
			TextAreaTabbedPane.this.removePane(TableViewPortTabPanel.this);
		}

		/**
		 * �\�����x����Ԃ��܂��B
		 */
		public JLabel getLabel(){
			return label;
		}
	}
}
