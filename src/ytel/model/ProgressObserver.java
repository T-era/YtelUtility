package ytel.model;

/**
 * {@link ytel.view.dialog.ProgressDialog}のための、プログレス(処理状況)監視クラス
 * @author y-terada
 *
 */
public interface ProgressObserver {
	/**
	 * 完了した処理の割合を百分率(%)で返します。
	 * @return 完了した処理の割合
	 */
	int getProgressPercentage();
}
