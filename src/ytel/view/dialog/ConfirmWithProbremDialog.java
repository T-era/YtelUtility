package ytel.view.dialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ytel.view.parts.AgreementListener;
import ytel.view.parts.OkCancelComponent;

/**
 * ユーザに同意/非同意を問い合わせるダイアログ
 * @author y-terada
 *
 */
public class ConfirmWithProbremDialog extends JDialog implements AgreementListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static enum ViewMode {
		CardMode,
		BoxMode,
	}

	public static class Probrem {
		private final String notice;
		private final ProbremAction[] actions;

		public Probrem(String notice, ProbremAction... actions) {
			this.notice = notice;
			this.actions = actions;
		}
	}
	private class ProbremPanel extends JPanel {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public ProbremPanel(Probrem probrem) {
			super(new BorderLayout());

			this.setBorder(new CompoundBorder(new EmptyBorder(2,2,2,2), new LineBorder(Color.DARK_GRAY, 1)));
			this.add(new JLabel(probrem.notice), BorderLayout.CENTER);

			Container east = new Container();
			east.setLayout(new GridLayout(probrem.actions.length, 1));
			for (final ProbremAction action: probrem.actions) {
				JButton button = new JButton(action.getCaption());
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						action.doAction();
						ConfirmWithProbremDialog.this.RemoveProbrem(ProbremPanel.this);
					}
				});
				east.add(button);
			}
			this.add(east, BorderLayout.EAST);
		}
	}

	public static interface ProbremAction {
		public String getCaption();
		public void doAction();
	}

	private boolean isOkClosed = false;
	private final Container center;

	private ConfirmWithProbremDialog(JFrame parent, String title, String message, ViewMode mode, Probrem... probrems){
		super(parent, title, true);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		Container north = new Container();
		{
			north.setLayout(new BorderLayout());
			north.add(new JLabel(message), BorderLayout.NORTH);
			north.add(OkCancelComponent.getForceCancelComponent(this), BorderLayout.SOUTH);
		}
		container.add(north, BorderLayout.NORTH);

		switch(mode) {
		case CardMode:
			center = new Container();
			center.setLayout(new CardLayout());
			container.add(center, BorderLayout.CENTER);
			break;
		case BoxMode:
			center = new JPanel();
			((JPanel)center).setBorder(new EmptyBorder(4,4,4,4));
			center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
			JScrollPane centerPane = new JScrollPane(center);
			container.add(centerPane, BorderLayout.CENTER);
			break;
		default:
			throw new RuntimeException();
		}
		for (Probrem probrem : probrems) {
			center.add(new ProbremPanel(probrem), probrem.notice);
		}
		container.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		this.pack();

		Point centerPoint = new Point(parent.getX() + (parent.getWidth() - this.getWidth()) / 2, parent.getY() + (parent.getHeight() - this.getHeight()) / 2 );
		this.setLocation(centerPoint);
	}
	private void RemoveProbrem(ProbremPanel probrem) {
		center.remove(probrem);
		if (center.getComponentCount() == 0) {
			okAction();
		}
		center.repaint();
		this.pack();
	}

	/**
	 * ユーザの入力が同意である場合はtrue<p/>
	 * ダイアログを閉じる前に、このメソッドをコールするべきではありません。<br/>
	 * 基本的にはコンストラクタで指定されたデフォルト値が返りますが、特にダイアログが
	 * 終了する処理とタイミングが重なった場合など戻り値は未定義です。
	 * @return ユーザの入力
	 */
	private boolean isOkClosed(){
		return isOkClosed;
	}

	@Override
	public void cancelAction() {
		isOkClosed = false;
		super.dispose();
	}

	@Override
	public void okAction() {
		isOkClosed = true;
		super.dispose();
	}

	public static boolean isConfirmed(JFrame parent, String title, String message, ViewMode mode, Probrem... probrems){
		ConfirmWithProbremDialog dialog = new ConfirmWithProbremDialog(parent, title, message, mode, probrems);
		dialog.setVisible(true);

		return dialog.isOkClosed();
	}
	public static ConfirmWithProbremDialog getConfirmDialog(JFrame parent, String title, String message, ViewMode mode, Probrem... probrems){
		return new ConfirmWithProbremDialog(parent, title, message, mode, probrems);
	}
}
