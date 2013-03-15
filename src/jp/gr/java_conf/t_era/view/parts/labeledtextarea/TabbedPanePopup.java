package jp.gr.java_conf.t_era.view.parts.labeledtextarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import jp.gr.java_conf.t_era.view.parts.labeledtextarea.TextAreaTabbedPane.TableViewPortTabPanel;

/**
 * {@link TextAreaTabbedPane}のためのポップアップメニュー
 * @author y-terada
 *
 */
public class TabbedPanePopup extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TabbedPanePopup( final JFrame parent, final TableViewPortTabPanel panel, final JLabel target ){
		JMenuItem change = new JMenuItem( "Change" );
		change.setMnemonic('h');
		JMenuItem close = new JMenuItem( "Close" );
		change.setMnemonic('l');

		this.add( change );
		this.add( new JSeparator() );
		this.add( close );

		change.addActionListener(new LabelChangeAction(parent, target));
		close.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				panel.close();
			}
		});
	}
}
