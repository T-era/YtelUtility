package ytel.view.parts;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

/**
 * Undo/Redo�@�\��������e�L�X�g�G���A�ł��B
 * @author y-terada
 *
 */
public class UndoableTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Undo/Redo���Ǘ�����}�l�[�W��
	 */
	private final UndoManager undo;

	public UndoableTextArea(String defaultText){
		super(defaultText);

		this.undo = new UndoManager();
		super.getDocument().addUndoableEditListener(this.undo);
	}

	/**
	 * Undo�ł��邩�ǂ������A�}�l�[�W���ɖ₢���킹�܂��B
	 * @return Undo�\�ȏꍇtrue
	 */
	public boolean canUndo(){
		return undo.canUndo();
	}
	/**
	 * Redo�ł��邩�ǂ������A�}�l�[�W���ɖ₢���킹�܂��B
	 * @return Redo�\�ȏꍇtrue
	 */
	public boolean canRedo(){
		return undo.canRedo();
	}
	/**
	 * Undo/Redo�ł��邩�ǂ������A�}�l�[�W���ɖ₢���킹�܂��B
	 * @return Undo/Redo�\�ȏꍇtrue
	 */
	public boolean canUndoOrRedu(){
		return undo.canUndoOrRedo();
	}
	/**
	 * Undo���܂�
	 */
	public void undo(){
		undo.undo();
	}
	/**
	 * Redo���܂�
	 */
	public void redo(){
		undo.redo();
	}
	public void undoOrRedo(){
		undo.undoOrRedo();
	}
}
