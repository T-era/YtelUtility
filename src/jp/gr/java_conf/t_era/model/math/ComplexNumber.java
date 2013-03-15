package jp.gr.java_conf.t_era.model.math;

import java.text.MessageFormat;

/**
 * 複素数クラス
 * <p>
 * 四則演算と累乗処理をサポートする
 * <p>
 * このクラスのオブジェクトは不変オブジェクトであり、四則演算の結果は戻り値として扱う。
 * @author y-terada
 *
 */
public class ComplexNumber {
	/**
	 * 実部
	 */
	private final double realPart;
	/**
	 * 虚部
	 */
	private final double imaginaryPart;

	public ComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}

	/**
	 * 足し算をします。結果は、戻り値として返します。
	 * @param arg 
	 * @return 足し算の結果
	 */
	public ComplexNumber plus(ComplexNumber arg) {
		return new ComplexNumber(realPart + arg.realPart, imaginaryPart + arg.imaginaryPart);
	}
	/**
	 * 引き算をします。結果は、戻り値として返します。
	 * @param arg 
	 * @return 引き算の結果
	 */
	public ComplexNumber minus(ComplexNumber arg) {
		return new ComplexNumber(realPart - arg.realPart, imaginaryPart - arg.imaginaryPart);
	}
	/**
	 * 掛け算をします。結果は、戻り値として返します。
	 * @param arg 
	 * @return 掛け算の結果
	 */
	public ComplexNumber multiply(ComplexNumber arg) {
		return new ComplexNumber(realPart * arg.realPart - imaginaryPart * arg.imaginaryPart
				, realPart * arg.imaginaryPart + imaginaryPart * arg.realPart);
	}
	/**
	 * 割り算をします。結果は、戻り値として返します。
	 * @param arg 
	 * @return 割り算の結果
	 */
	public ComplexNumber divide(ComplexNumber arg) {
		double denominator = arg.realPart * arg.realPart + arg.imaginaryPart + arg.imaginaryPart;
		return new ComplexNumber((this.realPart * arg.realPart + this.imaginaryPart * arg.imaginaryPart) / denominator
				, arg.realPart * this.imaginaryPart - this.realPart * arg.imaginaryPart);
	}

	/**
	 * 実部を返します。
	 * @return 実部
	 */
	public double getReal() {
		return realPart;
	}

	/**
	 * 虚部を返します
	 * @return 虚部
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
	 * 虚数の乗算をします。
	 * 乗数は整数のみ受け付けます。
	 * @param a 
	 * @param b 乗数
	 * @return 乗算結果
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
			throw new IllegalStateException("コーディングエラー");
		}
	}
}
