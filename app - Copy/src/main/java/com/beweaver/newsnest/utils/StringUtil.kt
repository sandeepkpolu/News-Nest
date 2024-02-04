package com.beweaver.newsnest.utils

class StringUtil {

    companion object {
        fun removeImgTags(input: String): String {
            // Define the regular expression pattern for <img> tags
            val imgPattern = """<img[^>]+/>""".toRegex()

            // Use replace to remove all occurrences of <img> tags in the input string
            return input.replace(imgPattern, "")
        }
        fun removeATags(input: String): String {
            // Define the regular expression pattern for <a> tags
            val aTagPattern = """<a\s+[^>]*href[^>]*>.*?</a>""".toRegex()

            // Use replace to remove all occurrences of <a> tags in the input string
            return input.replace(aTagPattern, "")
        }

        fun removeTags(input: String): String {
            val i1 = removeImgTags(input)
            return removeATags(i1)
        }

    }

}