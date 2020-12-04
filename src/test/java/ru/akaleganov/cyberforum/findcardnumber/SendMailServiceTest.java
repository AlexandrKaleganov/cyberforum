package ru.akaleganov.cyberforum.findcardnumber;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * нахождение номера карты в теле письма
 */
@Log4j2
class SendMailServiceTest {

    private final String body = Constant.BODY;
    private final String message = Constant.MESSAGE;

    @Test
    public void testCardNumber() {
        List<String> foundCardNumbers = new ArrayList<>();
        Map<Integer, List<Integer>> indexes = new HashMap<>();

        Matcher matcher = Pattern.compile("\\b(\\d{4}|\\d{2}|\\d{3})((\\d|\\s|-|&nbsp;){10,18})(\\d{4}|\\d{3}|\\d{2})", Pattern.CASE_INSENSITIVE).matcher(body);

        while (matcher.find()) {
            String newChars = body.substring(matcher.start(), matcher.end()).trim().replace(" ", "");
            foundCardNumbers.add(newChars);
            System.out.println(newChars);
        }
        int[] index = {0};
        for (int i = 0; i < foundCardNumbers.size(); i++) {
            this.findIndexes(indexes, foundCardNumbers.get(i), this.getLastIndex(foundCardNumbers.get(i)), i, index);
        }
        indexes.forEach((k, v) -> {
            System.out.println(v);
            v.forEach(e ->
                    System.out.print(message.charAt(e))
            );
            System.out.println();
        });
        List<Integer> test = new ArrayList<>(Arrays.asList(2631, 2632, 2633, 2634, 2636, 2637, 2638, 2639, 2712, 2713, 2714));

        Assert.assertArrayEquals(new List[]{indexes.get(0)}, new List[]{test});
    }

    private void findIndexes(Map<Integer, List<Integer>> indexes, String cardNumber, Integer lastIndex, Integer key, int[] startIndex) {
        if (lastIndex != 0) {
            indexes.put(key, new ArrayList<>());
            while (indexes.get(key).size() < lastIndex && startIndex[0] < message.length()) {
                boolean isBreak = false;
                for (int i = 0; i < lastIndex; i++) {
                    boolean isTag = false;
                    boolean isFind = false;
                    for (; startIndex[0] < message.length() && indexes.get(key).size() < lastIndex; startIndex[0]++) {
                        if (message.charAt(startIndex[0]) == "<".charAt(0)) {
                            isTag = true;
                        }
                        if (message.charAt(startIndex[0]) == ">".charAt(0)) {
                            isTag = false;
                        }
                        if (isTag) {
                            continue;
                        }
                        if (indexes.get(key).size() > 0 && Character.isDigit(message.charAt(startIndex[0])) && cardNumber.charAt(i) != message.charAt(startIndex[0])) {
                            indexes.put(key, new ArrayList<>());
                            isBreak = true;
                            break;
                        }
                        if (Character.isDigit(message.charAt(startIndex[0])) && cardNumber.charAt(i) != message.charAt(startIndex[0])) {
                            continue;
                        }
                        if (cardNumber.charAt(i) == message.charAt(startIndex[0])) {
                            indexes.get(key).add(startIndex[0]);
                            startIndex[0]++;
                            isFind = true;
                            break;
                        }
                    }
                    if (isFind) {
                        continue;
                    }
                    if (isBreak) {
                        break;
                    }
                }
            }
            if (indexes.get(key).size() < lastIndex) {
                indexes.remove(key);
            }
        }
    }

    private int getLastIndex(String element) {
        int lastIndex;
        if (element != null && (element.trim().length() == 16 || element.trim().length() == 18)) {
            lastIndex = element.length() == 16 ? 11 : 13;
        } else {
            lastIndex = 0;
        }
        return lastIndex;
    }
}


