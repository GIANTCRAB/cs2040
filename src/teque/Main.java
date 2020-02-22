package teque;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var teque = new TequeJava();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int commandCount = Integer.parseInt(br.readLine());

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                // read strings
                // start from index 0
                for (int i = 0; i < commandCount; i++) {
                    var operationLine = new StringTokenizer(br.readLine());
                    var operationCommand = operationLine.nextToken();
                    int operationNumber = Integer.parseInt(operationLine.nextToken());

                    switch (operationCommand) {
                        case "push_back":
                            teque.addBack(operationNumber);
                            break;
                        case "push_front":
                            teque.addFront(operationNumber);
                            break;
                        case "push_middle":
                            teque.addMiddle(operationNumber);
                            break;
                        case "get":
                            bw.write(teque.getElement(operationNumber) + "\n");
                            break;
                    }
                }

                bw.flush();
            }
        }
    }
}