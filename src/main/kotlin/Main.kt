const val ITEM_PRICE = 100U
const val SMALL_DISCOUNT = 100U
const val SMALL_DISCOUNT_START = 1001U
const val BIG_DISCOUNT = 5U
const val BIG_DISCOUNT_START = 10_001U
const val MUSIC_LOVER_DISCOUNT = 1U

fun main() {

    var numberOfPurchases = 0
    var sumOfPurchases = 0U

    while (true) {

        print("Введите количество товара(для выхода введите \"end\"): ")

        try {
            val input = readLine()

            if (input.equals("end", ignoreCase = true)) {
                println("Bye!!")
                break
            }

            val itemCount = input?.toUInt() ?: return

            val shoppingBasket = itemCount * ITEM_PRICE

            numberOfPurchases += 1
            sumOfPurchases += shoppingBasket

            val isRegularCustomer = if (numberOfPurchases > 3) true else false
            val regularCustomerStatus = if (isRegularCustomer) "Да!" else "Пока нет.."

            println("Кол-во покупок: $numberOfPurchases; Сумма всех покупок: $sumOfPurchases руб; " +
                    "Текущая корзина: $shoppingBasket руб; Постоянный клиент( >3 покупок)? -$regularCustomerStatus")
            println("(Суммарная скидка не может превышать половины стоимости корзины)")

            val discount = discountCalculator(sumOfPurchases, isRegularCustomer, shoppingBasket)

            println("С учетом общей скидки ($discount руб) сумма покупки составила: ${shoppingBasket - discount} руб")
            println()

        } catch (e: NumberFormatException) {
            println("Неверный ввод")
        }
    }
}

fun discountCalculator(sumOfPurchases: UInt, isRegularCustomer: Boolean, shoppingBasket: UInt): UInt {
    var discount = 0U

    if ((sumOfPurchases >= SMALL_DISCOUNT_START) && (sumOfPurchases < BIG_DISCOUNT_START)) {
        discount = SMALL_DISCOUNT
    } else if (sumOfPurchases >= BIG_DISCOUNT_START) {
        discount = (sumOfPurchases * BIG_DISCOUNT) / 100U
    } else {
        discount = 0U
    }

    if (isRegularCustomer) {
        discount += ((shoppingBasket - discount) * MUSIC_LOVER_DISCOUNT) / 100U
    }

    discount = if (discount > (shoppingBasket / 2U)) (shoppingBasket / 2U) else discount

    return discount
}