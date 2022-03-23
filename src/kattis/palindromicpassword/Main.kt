package kattis.palindromicpassword

import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.math.abs

fun main(args: Array<String>) {
    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        BufferedWriter(OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512).use { bw ->
            val passwordCount = br.readLine().toInt()

            for (i in 0 until passwordCount) {
                val password: String = br.readLine().toString()
                val passwordDigits: Int = password.toInt()
                val firstThreeChars: String = password.slice(IntRange(0, 2))
                val firstThreeDigits: Int = firstThreeChars.toInt()

                val smallerDigits: Int = firstThreeDigits - 1
                val smallerChars: String = smallerDigits.toString()
                val smallerPalindrom: Int = smallerChars.plus(smallerChars.reversed()).toInt()
                val smallDiff: Int = abs(smallerPalindrom.minus(passwordDigits))

                val palindrom: Int = firstThreeChars.plus(firstThreeChars.reversed()).toInt()
                val palDiff: Int = abs(palindrom.minus(passwordDigits))

                val largerChars: String = (firstThreeDigits + 1).toString()
                val largerPalindrom: Int = largerChars.plus(largerChars.reversed()).toInt()
                val largeDiff: Int = abs(largerPalindrom.minus(passwordDigits))

                var newPassword = palindrom

                if (hasNoLeadingZeros(smallerDigits)) {
                    // Prefer the smaller palindrom
                    if (smallDiff <= palDiff) {
                        newPassword = if (smallDiff <= largeDiff) {
                            smallerPalindrom
                        } else {
                            largerPalindrom
                        }
                    } else {
                        if (largeDiff < palDiff) {
                            newPassword = largerPalindrom
                        }
                    }
                } else {
                    if (largeDiff < palDiff) {
                        newPassword = largerPalindrom
                    }
                }

                bw.write(newPassword.toString())
                bw.write("\n")
            }

            bw.flush()
        }
    }
}

fun hasNoLeadingZeros(number: Int) = number >= 100