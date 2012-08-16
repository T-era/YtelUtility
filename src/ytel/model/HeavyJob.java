package ytel.model;

import ytel.model.ProgressObserver;

/**
 * 重たい処理を行う場合に実装するべきインターフェースです。
 * <p>
 * {@link HeavyThread}は、このインターフェースを持つ処理を独立したスレッドで処理します。
 * @author y-terada
 *
 */
public interface HeavyJob extends ProgressObserver{
	/**
	 * 重たい処理を定義します。{@link ProgressObserver#getProgressPercentage()}は、このメソッドの
	 * 完了状況を返すべきです。
	 */
	void heavyProcess();

	/**
	 * 重たい処理の完了後に行うべき処理を定義します。
	 */
	void doneNext();
}
