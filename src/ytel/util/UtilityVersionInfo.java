package ytel.util;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.xml.sax.SAXException;

import ytel.version.model.version.VersionInfo;
import ytel.version.view.VersionDialog;

/**
 * このユーティリティのバージョン情報を返すためのファクトリです。<br>
 * バージョン情報は、ファイル{@link #VERSION_FILE}に記載する必要があります。
 * @author y-terada
 *
 */
public class UtilityVersionInfo {
	/**
	 * バージョン情報ファイル名
	 */
	private static final String VERSION_FILE = "/ytel/util/version.properties";

	/**
	 * バージョン情報を返します。
	 * @return バージョン情報
	 * @throws IOException 情報ファイルの読み込みに失敗した場合
	 * @throws SAXException 情報ファイルの読み込みに失敗した場合
	 */
	public static VersionInfo getVersion() throws IOException, SAXException {
		InputStream is = UtilityVersionInfo.class.getResourceAsStream("/ytel/util/version.properties");
		try {
			return VersionInfo.getVersionInfo(is);
		}finally{
			if (is != null)
				is.close();
		}
	}

	/**
	 * バージョン情報を表示するダイアログを返します。
	 * @param parent ダイアログ表示上の親フレーム
	 * @param title ダイアログタイトル
	 * @param appName アプリケーション名(省略可能)
	 * @return ダイアログ
	 * @throws IOException 情報ファイルの読み込みに失敗した場合
	 * @throws SAXException 情報ファイルの読み込みに失敗した場合
	 */
	public static JDialog getVersionDialog(JFrame parent, String title, String appName) throws IOException, SAXException {
		return VersionDialog.getDialog(parent, title, appName, getVersion());
	}
}
