package ytel.view.parts;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

/**
 * Undo/Redo機能を備えたテキストエリアです。
 * @author y-terada
 *
 */
public class UndoableTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Undo/Redoを管理するマネージャ
	 */
	private final UndoManager undo;

	public UndoableTextArea(String defaultText){
		super(defaultText);

		this.undo = new UndoManager();
		super.getDocument().addUndoableEditListener(this.undo);
	}

	/**
	 * Undoできるかどうかを、マネージャに問い合わせます。
	 * @return Undo可能な場合true
	 */
	public boolean canUndo(){
		return undo.canUndo();
	}
	/**
	 * Redoできるかどうかを、マネージャに問い合わせます。
	 * @return Redo可能な場合true
	 */
	public boolean canRedo(){
		return undo.canRedo();
	}
	/**
	 * Undo/Redoできるかどうかを、マネージャに問い合わせます。
	 * @return Undo/Redo可能な場合true
	 */
	public boolean canUndoOrRedu(){
		return undo.canUndoOrRedo();
	}
	/**
	 * Undoします
	 */
	public void undo(){
		undo.undo();
	}
	/**
	 * Redoします
	 */
	public void redo(){
		undo.redo();
	}
	public void undoOrRedo(){
		undo.undoOrRedo();
	}
}
