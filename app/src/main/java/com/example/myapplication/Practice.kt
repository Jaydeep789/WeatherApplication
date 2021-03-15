package com.example.myapplication

import java.lang.IllegalArgumentException

fun main() {
//    println(noTriples(intArrayOf(6,6,7,12,6,5,2,1,9)))
//    val word = "This is a sample text for demo".split(" ")
//    val count: List<String> = word.sortedBy {
//        it.length
//    }
//    println(count[count.size-2])
//    println(twoSum(intArrayOf(2,7,11,15,8,1,6,3),9))
}

fun left2(str1: String): String {
    return str1
}

fun nonStart(str1: String, str2 : String): String {
    return "${str1.substring(1,str1.length)}${str2.substring(1,str2.length)}"
}

fun noTriples(nums: IntArray): Boolean {
    for (i in nums.dropLast(2).indices) {
        val first = nums[i] + 5
        val second = nums[i] - 1
        if (nums[i + 1] == first && nums[i + 2] == second) {
            return true
        }
    }
    return false
}

//fun twoSum(nums: IntArray, target: Int): IntArray {
//
//    for (i in nums.indices){
//        if (nums[i] + nums[i+1] == target){
//            return IntArray()
//        }
//    }
//    return intArrayOf()
//}