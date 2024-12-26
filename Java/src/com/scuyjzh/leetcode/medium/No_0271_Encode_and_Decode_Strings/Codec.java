package com.scuyjzh.leetcode.medium.No_0271_Encode_and_Decode_Strings;

import java.util.*;

/**
 * 271. 字符串的编码与解码
 * <p>
 * 请你设计一个算法，可以将一个 字符串列表 编码成为一个 字符串。这个编
 * 码后的字符串是可以通过网络进行高效传送的，并且可以在接收端被解码回
 * 原来的字符串列表。
 * <p>
 * 1 号机（发送方）有如下函数：
 *     string encode(vector<string> strs) {
 *         // ... your code
 *         return encoded_string;
 *     }
 * 2 号机（接收方）有如下函数：
 *     vector<string> decode(string s) {
 *         //... your code
 *         return strs;
 *     }
 * 1 号机（发送方）执行：
 *     string encoded_string = encode(strs);
 * 2 号机（接收方）执行：
 *     vector<string> strs2 = decode(encoded_string);
 * 此时，2 号机（接收方）的 strs2 需要和 1 号机（发送方）的 strs 相
 * 同。
 * 请你来实现这个 encode 和 decode 方法。
 * <p>
 * 注意：
 *   • 因为字符串可能会包含 256 个合法 ascii 字符中的任何字符，所以您
 *     的算法必须要能够处理任何可能会出现的字符。
 *   • 请勿使用 “类成员”、“全局变量” 或 “静态变量” 来存储这些状态，您
 *     的编码和解码算法应该是非状态依赖的。
 *   • 请不要依赖任何方法库，例如 eval 又或者是 serialize 之类的方
 *     法。本题的宗旨是需要您自己实现 “编码” 和 “解码” 算法。
 */
class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(intToString(str));
            sb.append(str);
        }
        return sb.toString();
    }

    // Encodes string length to bytes string
    private String intToString(String str) {
        int len = str.length();
        char[] bytes = new char[4];
        for (int i = 3; i >= 0; --i) {
            bytes[3 - i] = (char) (len >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String str) {
        int i = 0, len = str.length();
        List<String> output = new ArrayList<>();
        while (i < len) {
            int length = stringToInt(str.substring(i, i + 4));
            i += 4;
            output.add(str.substring(i, i + length));
            i += length;
        }
        return output;
    }

    // Decodes bytes string to integer
    private int stringToInt(String bytesStr) {
        int result = 0;
        for (char b : bytesStr.toCharArray()) {
            result = (result << 8) + (int) b;
        }
        return result;
    }
}
