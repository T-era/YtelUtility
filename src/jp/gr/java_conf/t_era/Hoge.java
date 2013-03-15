package jp.gr.java_conf.t_era;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ViewMode;

public class Hoge {
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.isConfirmed(frame, "test", "ボタン押されたけど、なんか色々問題があって...。", ViewMode.CardMode
						, new ConfirmWithProbremDialog.Probrem("ヤバイ"
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "しょうがない";
									}
									@Override
									public void doAction() {
									}
								})
						, new ConfirmWithProbremDialog.Probrem("だるい"
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "がんばる";
									}
									@Override
									public void doAction() {
										System.out.println("1");
									}
								}
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "さぼる";
									}
									@Override
									public void doAction() {
										System.out.println("2");
									}
								})
						, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.Probrem("大変"
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "逃げる";
									}
									@Override
									public void doAction() {
										System.out.println("A");
									}
								}
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "無視する";
									}
									@Override
									public void doAction() {
										System.out.println("B");
									}
								}
								, new jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "押し付ける";
									}
									@Override
									public void doAction() {
										System.out.println("C");
									}
								}));
			}
		});
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}
}
