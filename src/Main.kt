import java.util.*

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)

fun main() {
    // Завдання 1.1: Квадрати елементів масиву і їх сума
    processArrayTasks()

    // Завдання 1.2: Комбінації елементів з двох масивів
    generatePairs()

    // Торговці та транзакції
    processTradersAndTransactions()
}

fun processArrayTasks() {
    val array = intArrayOf(1, 2, 3, 4, 5)
    val arr = array.map { it * it }
    val sum = arr.sum()

    arr.forEach { print("$it, ") }
    println("\nSum = $sum")
}

fun generatePairs() {
    val arr1 = intArrayOf(1, 2, 3, 4, 5)
    val arr2 = intArrayOf(1, 2, 3)
    val res = Array(arr1.size * arr2.size) { Array(2) { 0 } }
    var k = 0
    for (i in arr1) {
        for (j in arr2) {
            res[k][0] = i
            res[k][1] = j
            k++
        }
    }
    for (i in res) {
        print("(${i[0]}, ${i[1]}) ")
    }
    println("")
}

fun processTradersAndTransactions() {
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")

    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, Currency.getInstance("UAH")),
        Transaction(raoul, 2011, 11, 400, Currency.getInstance("USD")),
        Transaction(raoul, 2012, 10, 1000, Currency.getInstance("UAH")),
        Transaction(mario, 2012, 9, 710, Currency.getInstance("UAH")),
        Transaction(mario, 2012, 7, 700, Currency.getInstance("USD")),
        Transaction(alan, 2012, 4, 950, Currency.getInstance("EUR"))
    )

    val traders = listOf(raoul, mario, alan, brian)

    // Усі транзакції за 2011 рік, посортувати за вартістю
    println("Транзакції за 2011 рік, посортовані за вартістю:")
    transactions.filter { it.year == 2011 }
        .sortedBy { it.value }
        .forEach { println("${it.trader.name} ${it.value}${it.currency.symbol} ${it.year}-${it.month}") }

    // Унікальні міста трейдерів
    println("\nУнікальні міста трейдерів:")
    traders.map { it.city }.distinct().forEach { println(it) }

    // Трейдери з Кембриджа
    println("\nТрейдери з Кембриджа:")
    traders.filter { it.city == "Cambridge" }
        .sortedBy { it.name }
        .forEach { println("${it.name} ${it.city}") }

    // Рядок імен трейдерів
    println("\nІмена трейдерів (за алфавітом):")
    println(traders.sortedBy { it.name }.joinToString { it.name })

    // Чи є трейдери в Мілані?
    println("\nЧи є трейдери в Мілані?")
    println(traders.any { it.city == "Milan" })

    // Значення транзакцій трейдерів з Кембриджа
    println("\nТранзакції трейдерів з Кембриджа:")
    transactions.filter { it.trader.city == "Cambridge" }
        .forEach { println("${it.value}${it.currency.symbol}") }

    // Транзакція з найбільшою вартістю
    println("\nТранзакція з найбільшою вартістю:")
    println(transactions.maxOfOrNull { it.value })

    // Транзакції, згруповані за валютою
    println("\nТранзакції, згруповані за валютою:")
    transactions.groupBy { it.currency }.forEach { (currency, trans) ->
        println("\n$currency")
        trans.forEach { println("${it.value}${it.currency.symbol} ${it.year}-${it.month}") }
    }

    // Сума транзакцій у гривнях
    println("\nСума транзакцій у гривнях:")
    val exchangeRate = mapOf("UAH" to 1.0, "USD" to 41.23, "EUR" to 44.71)
    println(transactions.sumOf { it.value * exchangeRate[it.currency.currencyCode]!! })

    // Послідовність транзакцій
    println("\nПослідовність транзакцій:")
    transactions.sortedWith(compareBy({ it.year }, { it.month })).withIndex()
        .forEach { (i, trans) ->
            print("${i + 1}. ${trans.month}-${trans.year}: ${trans.value} ${trans.currency.symbol} -> ")
        }
    println("END")
}
