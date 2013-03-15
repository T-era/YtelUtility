package jp.gr.java_conf.t_era.view.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.Probrem;
import jp.gr.java_conf.t_era.view.dialog.ConfirmWithProbremDialog.ViewMode;

public class ConfirmWithProbrem {
	private final List<Probrem> probremList;
	private final String title;
	private final String message;

	public ConfirmWithProbrem(String title, String message) {
		this.title = title;
		this.message = message;
		this.probremList = new ArrayList<Probrem>();
	}

	public void addProbrem(Probrem probrem)
	{
		probremList.add(probrem);
	}

	public ConfirmWithProbremDialog getDialog(JFrame parent, ViewMode mode) {
		Probrem[] array = new Probrem[probremList.size()];
		array = probremList.toArray(array);
		return ConfirmWithProbremDialog.getConfirmDialog(parent, title, message, mode, array);
	}
	public boolean askConfirm(JFrame parent, ViewMode mode) {
		Probrem[] array = new Probrem[probremList.size()];
		array = probremList.toArray(array);
		return ConfirmWithProbremDialog.isConfirmed(parent, title, message, mode, array);
	}
}
