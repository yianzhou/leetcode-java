package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode3 {

    /* keep a HashMap (Character, position)
	 * keep two pointers which define the max substring - [left, right]
	 * move the right pointer to scan through the string, and meanwhile update the HashMap.
	 * If the character is already in the HashMap,
	 * then move the left pointer to the right of the same character last found.
	 */

    public int solution1(String s) {
        if (s == null) return 0; // boundary

        // variables
        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;
        int length = s.length();

        if (length == 0) return 0; // boundary

        for (int left = 0, right = 0; right < length; right++) {
            char c = s.charAt(right);

            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            answer = Math.max(answer, right - left + 1);
            map.put(c, right); // always override previous
        }

        return answer;
    }


    /*
    * 因为字符都能用 ASCII 码表示，所以直接使用 int[] 代替哈希表使用，减少了哈希表的寻址时间
    * 当 HashMap 的 key 为 Character 时，都可以用 int[128] 来替代 map.get(*);
    * 子字符串表示为 (left, right]
    */

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0; // boundary

        // variables
        int[] ascii = new int[128];
        int answer = 0;
        int length = s.length();

        if (length == 0) return 0; // boundary

        for (int left = 0, right = 0; right < length; right++) {
            char c = s.charAt(right);

            left = Math.max(left, ascii[c]);
            answer = Math.max(answer, right - left + 1);
            ascii[c] = right  + 1;
        }

        return answer;
    }

}
