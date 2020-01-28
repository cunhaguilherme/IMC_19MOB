package br.com.guilhermecunha.imc.extensions

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)