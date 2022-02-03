package com.proyect.User.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@Min(value = 999, message = "Debes introducir 4 dígitos")
	@Max(value = 10000, message = "Debes introducir 4 dígitos")
	@NotNull(message = "Campo obligatorio")
	private Integer num1;

	@Min(value = 999, message = "Debes introducir 4 dígitos")
	@Max(value = 10000, message = "Debes introducir 4 dígitos")
	@NotNull(message = "Campo obligatorio")
	private Integer num2;

	@Min(value = 999, message = "Debes introducir 4 dígitos")
	@Max(value = 10000, message = "Debes introducir 4 dígitos")
	@NotNull(message = "Campo obligatorio")
	private Integer num3;

	@Min(value = 999, message = "Debes introducir 4 dígitos")
	@Max(value = 10000, message = "Debes introducir 4 dígitos")
	@NotNull(message = "Campo obligatorio")
	private Integer num4;

	@Min(value = 02, message = "Fecha no válida")
	@Max(value = 12, message = "Fecha no válida")
	@NotNull(message = "Campo obligatorio")
	private Integer month;
	@Min(value = 22, message = "Fecha no válida")
	@Max(value = 99, message = "Fecha no válida")
	@NotNull(message = "Campo obligatorio")
	private Integer year;

	public Card() {
		super();
	}

	public Card(@Min(999) @Max(10000) @NotNull Integer num1, @Min(999) @Max(10000) @NotNull Integer num2,
			@Min(999) @Max(10000) @NotNull Integer num3, @Min(999) @Max(10000) @NotNull Integer num4,
			@Min(2) @Max(12) @NotNull Integer month, @Min(22) @Max(99) @NotNull Integer year) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.month = month;
		this.year = year;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	public Integer getNum4() {
		return num4;
	}

	public void setNum4(Integer num4) {
		this.num4 = num4;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Card [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", month=" + month
				+ ", year=" + year + "]";
	}
}
