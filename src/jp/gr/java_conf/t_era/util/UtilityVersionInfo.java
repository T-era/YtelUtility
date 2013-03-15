package jp.gr.java_conf.t_era.util;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.xml.sax.SAXException;

import jp.gr.java_conf.t_era.version.model.version.VersionInfo;
import jp.gr.java_conf.t_era.version.view.VersionDialog;

/**
 * ���̃��[�e�B���e�B�̃o�[�W��������Ԃ����߂̃t�@�N�g���ł��B<br>
 * �o�[�W�������́A�t�@�C��{@link #VERSION_FILE}�ɋL�ڂ���K�v������܂��B
 * @author y-terada
 *
 */
public class UtilityVersionInfo {
	/**
	 * �o�[�W�������t�@�C����
	 */
	private static final String VERSION_FILE = "/ytel/util/version.xml";

	/**
	 * �o�[�W��������Ԃ��܂��B
	 * @return �o�[�W�������
	 * @throws IOException ���t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 * @throws SAXException ���t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 */
	public static VersionInfo getVersion() throws IOException, SAXException {
		InputStream is = UtilityVersionInfo.class.getResourceAsStream(VERSION_FILE);
		try {
			return VersionInfo.getVersionInfo(is);
		}finally{
			if (is != null)
				is.close();
		}
	}

	/**
	 * �o�[�W��������\������_�C�A���O��Ԃ��܂��B
	 * @param parent �_�C�A���O�\����̐e�t���[��
	 * @param title �_�C�A���O�^�C�g��
	 * @param appName �A�v���P�[�V������(�ȗ��\)
	 * @return �_�C�A���O
	 * @throws IOException ���t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 * @throws SAXException ���t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 */
	public static JDialog getVersionDialog(JFrame parent, String title) throws IOException, SAXException {
		return VersionDialog.getDialog(parent, title, getVersion());
	}
}