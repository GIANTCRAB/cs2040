package leetcode.zigzagconversion;

// I am thinking in terms of how the row index progresses as I traverse through the original string
// I turns out that the row index increases and then thereafter, it decreases.
// up and down and up like a triangle
// I collect them into a fixed size String array based on their row
// Thereafter, I combine the array of information into a single string
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() == 1 || s.length() <= numRows) {
            return s;
        }

        String[] stringsByRow = new String[numRows];
        var rowNumberIndexToAssign = 0;
        var ascend = true;
        for (int charPosition = 0; charPosition < s.length(); charPosition++) {
            final var character = s.charAt(charPosition);
            if (stringsByRow[rowNumberIndexToAssign] != null) {
                final var newData = stringsByRow[rowNumberIndexToAssign] + character;
                stringsByRow[rowNumberIndexToAssign] = newData;
            } else {
                stringsByRow[rowNumberIndexToAssign] = "" + character;
            }

            if (ascend) {
                rowNumberIndexToAssign++;
            } else {
                rowNumberIndexToAssign--;
            }
            if(rowNumberIndexToAssign == numRows - 1) {
                ascend = false;
            }
            if(rowNumberIndexToAssign == 0) {
                ascend = true;
            }
        }

        var finalString = "";
        for (int i = 0; i < stringsByRow.length; i++) {
            finalString += stringsByRow[i];
        }
        return finalString;
    }
}
