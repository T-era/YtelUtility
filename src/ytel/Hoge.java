package ytel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ytel.view.dialog.ConfirmWithProbremDialog;
import ytel.view.dialog.ConfirmWithProbremDialog.ViewMode;

public class Hoge {
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ytel.view.dialog.ConfirmWithProbremDialog.isConfirmed(frame, "test", "�{�^�������ꂽ���ǁA�Ȃ񂩐F�X��肪������...�B", ViewMode.CardMode
						, new ConfirmWithProbremDialog.Probrem("���o�C"
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "���傤���Ȃ�";
									}
									@Override
									public void doAction() {
									}
								})
						, new ConfirmWithProbremDialog.Probrem("���邢"
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "����΂�";
									}
									@Override
									public void doAction() {
										System.out.println("1");
									}
								}
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "���ڂ�";
									}
									@Override
									public void doAction() {
										System.out.println("2");
									}
								})
						, new ytel.view.dialog.ConfirmWithProbremDialog.Probrem("���"
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "������";
									}
									@Override
									public void doAction() {
										System.out.println("A");
									}
								}
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "��������";
									}
									@Override
									public void doAction() {
										System.out.println("B");
									}
								}
								, new ytel.view.dialog.ConfirmWithProbremDialog.ProbremAction() {
									@Override
									public String getCaption() {
										return "�����t����";
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
