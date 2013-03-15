package jp.gr.java_conf.t_era.model.math;

import java.text.MessageFormat;

/**
 * ���f���N���X
 * <p>
 * �l�����Z�Ɨݏ揈�����T�|�[�g����
 * <p>
 * ���̃N���X�̃I�u�W�F�N�g�͕s�σI�u�W�F�N�g�ł���A�l�����Z�̌��ʂ͖߂�l�Ƃ��Ĉ����B
 * @author y-terada
 *
 */
public class ComplexNumber {
	/**
	 * ����
	 */
	private final double realPart;
	/**
	 * ����
	 */
	private final double imaginaryPart;

	public ComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}

	/**
	 * �����Z�����܂��B���ʂ́A�߂�l�Ƃ��ĕԂ��܂��B
	 * @param arg 
	 * @return �����Z�̌���
	 */
	public ComplexNumber plus(ComplexNumber arg) {
		return new ComplexNumber(realPart + arg.realPart, imaginaryPart + arg.imaginaryPart);
	}
	/**
	 * �����Z�����܂��B���ʂ́A�߂�l�Ƃ��ĕԂ��܂��B
	 * @param arg 
	 * @return �����Z�̌���
	 */
	public ComplexNumber minus(ComplexNumber arg) {
		return new ComplexNumber(realPart - arg.realPart, imaginaryPart - arg.imaginaryPart);
	}
	/**
	 * �|���Z�����܂��B���ʂ́A�߂�l�Ƃ��ĕԂ��܂��B
	 * @param arg 
	 * @return �|���Z�̌���
	 */
	public ComplexNumber multiply(ComplexNumber arg) {
		return new ComplexNumber(realPart * arg.realPart - imaginaryPart * arg.imaginaryPart
				, realPart * arg.imaginaryPart + imaginaryPart * arg.realPart);
	}
	/**
	 * ����Z�����܂��B���ʂ́A�߂�l�Ƃ��ĕԂ��܂��B
	 * @param arg 
	 * @return ����Z�̌���
	 */
	public ComplexNumber divide(ComplexNumber arg) {
		double denominator = arg.realPart * arg.realPart + arg.imaginaryPart + arg.imaginaryPart;
		return new ComplexNumber((this.realPart * arg.realPart + this.imaginaryPart * arg.imaginaryPart) / denominator
				, arg.realPart * this.imaginaryPart - this.realPart * arg.imaginaryPart);
	}

	/**
	 * ������Ԃ��܂��B
	 * @return ����
	 */
	public double getReal() {
		return realPart;
	}

	/**
	 * ������Ԃ��܂�
	 * @return ����
	 */
	public double getImaginal() {
		return imaginaryPart;
	}

	private static final MessageFormat format = new MessageFormat("{0} - {1} i");
	@Override
	public String toString() {
		return format.format(new Object[]{Double.toString(realPart), Double.toString(imaginaryPart)});
	}

	/**
	 * �����̏�Z�����܂��B
	 * �搔�͐����̂ݎ󂯕t���܂��B
	 * @param a 
	 * @param b �搔
	 * @return ��Z����
	 */
	public static ComplexNumber pow(ComplexNumber a, int b) {
		if (b == 0) {
			return new ComplexNumber(1, 0);
		} else if (b > 0) {
			ComplexNumber temp = a;
			for (int i = 1; i < b; i ++) {
				temp = a.multiply(a);
			}
			return temp;
		} else if (b < 0) {
			ComplexNumber denominator = pow(a, -b);
			return new ComplexNumber(1, 0).divide(denominator);
		} else {
			throw new IllegalStateException("�R�[�f�B���O�G���[");
		}
	}
}
