package com.hangrycoder.jetpackcalculator

fun main(args: Array<String>) {
    val input =
        arrayOf("10C", "9C", "7C", "4.5C", "-2C", "23F", "0C", "-1.5C", "41F", "3C")

    val tempInCelcius = input.map {
        if (it.endsWith("F")) {
            convertToCelcius(it.dropLast(1).toDouble())
        } else {
            it.dropLast(1).toDouble()
        }
    }
    println(tempInCelcius)

    val avg = tempInCelcius.average()

    val subtractedAvgTemp = tempInCelcius.map {
        it - avg
    }

    println(subtractedAvgTemp)

    val result = subtractedAvgTemp.mapIndexed { index, value ->
        if (input[index].endsWith("F")) {
            convertToFarenheit(value).toString() + "F"
        } else {
            value.toString() + "C"
        }
    }

    println(result)
}

fun convertToCelcius(farenheit: Double): Double {
    return (farenheit - 32) * 5 / 9
}

fun convertToFarenheit(celcius: Double): Double {
    return ((9.0 / 5.0) * celcius) + 32
}