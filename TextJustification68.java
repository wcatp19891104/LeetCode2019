package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaichengwang on 1/26/19.
 * https://leetcode.com/problems/text-justification/
 */
public class TextJustification68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        // 1. 决定可以放几个: sizeOfWords + (n - 1) < maxWidth
        // 2. 计算空格: maxWidth - (2n + 1)
        // 3. 放空格
        // 4. 最后一行特殊处理
        // 5. 一行只有一个字母
        List<String> answer = new ArrayList<>();
        if (words == null || words.length == 0) {
            return answer;
        }


        if (maxWidth == 0) {
            return answer;
        }

        List<String> currentWords = new ArrayList<>();
        int currentWidth = 0;
        for (int i = 0; i < words.length; i++) {
            // fill words
            if (currentWidth + words[i].length() + currentWords.size() > maxWidth) {
                int blank = maxWidth - currentWidth;
                int averageBlank = (currentWords.size() - 1 == 0) ? blank:  blank / (currentWords.size() - 1);
                int remainingBlank = blank - averageBlank * (currentWords.size() - 1);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < currentWords.size(); j++) {
                    sb.append(currentWords.get(j));
                        for(int k = 0; k < averageBlank; k++) {
                            sb.append(' ');
                        }
                    if (remainingBlank != 0) {
                        sb.append(' ');
                        remainingBlank--;
                    }
                }
                sb.setLength(maxWidth);
                answer.add(sb.toString());
                currentWords = new ArrayList<>();
                currentWidth = 0;
            }
            // add to currentWords
            currentWords.add(words[i]);
            currentWidth += words[i].length();
        }

        // fill last line if needed
        if (!currentWords.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentWords.size(); i++) {
                sb.append(currentWords.get(i));
                sb.append(' ');
            }
            for(int i = sb.length(); i < maxWidth; i++) {
                sb.append(' ');
            }
            sb.setLength(maxWidth);
            answer.add(sb.toString());
        }

        return answer;
    }

    public static void main(String[] argv) {
        String[] input = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] input2 = {"What","must","be","acknowledgment","shall","be"};
        System.out.println(new TextJustification68().fullJustify(input, 16));
    }
}
