package jp.gr.java_conf.t_era.view.parts;

/**
 * ユーザの同意/非同意操作イベントを処理するためのインターフェース<p/>
 * 
 * @author y-terada
 *
 */
public interface AgreementListener {
	/**
	 * "同意"時のアクション
	 */
	void okAction();
	/**
	 * "非同意"時のアクション
	 */
	void cancelAction();
}
