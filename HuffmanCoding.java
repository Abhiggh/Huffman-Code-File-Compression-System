package Huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private static Map<Character, String> charPrefixMap = new HashMap<>();
    
    private static PriorityQueue<HuffmanNode> priorityQueue;

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.huff";

        String text = readFile(inputFile);
        System.out.println("Original text length (characters): " + text.length());

        Map<Character, Integer> freqMap = buildFrequencyMap(text);
        printFrequencyTable(freqMap);  // Add this line

        HuffmanNode root = buildHuffmanTree(freqMap);
        buildCharPrefixMap(root, "");
        printHuffmanCodes(); 
        String encodedText = encodeText(text);
        System.out.println("Encoded text length (bits): " + encodedText.length());

        writeEncodedFile(encodedText, outputFile);

        // To decode
        String decodedText = decodeText(readEncodedFile(outputFile), root);
        System.out.println("Decoded Text: " + decodedText);
        


    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
        }
        return sb.toString();
    }

    private static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    private static void buildCharPrefixMap(HuffmanNode node, String prefix) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            charPrefixMap.put(node.character, prefix);
        }

        buildCharPrefixMap(node.left, prefix + '0');
        buildCharPrefixMap(node.right, prefix + '1');
    }

    private static String encodeText(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(charPrefixMap.get(c));
        }
        return sb.toString();
    }

    private static void writeEncodedFile(String encodedText, String fileName) throws IOException {
        try (BitOutputStream bos = new BitOutputStream(fileName)) {
            for (char bit : encodedText.toCharArray()) {
                bos.writeBit(bit - '0');
            }
        }
    }
    private static String readEncodedFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BitInputStram bis = new BitInputStram(fileName)) {
            int bit;
            while ((bit = bis.readBit()) != -1) {
                sb.append(bit);
            }
        }
        return sb.toString();
    }
    

    private static String decodeText(String encodedText, HuffmanNode root) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                sb.append(current.character);
                current = root;
            }
            
        }
        return sb.toString();
    }
    private static void printFrequencyTable(Map<Character, Integer> freqMap) {
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    private static void printHuffmanCodes() {
        for (Map.Entry<Character, String> entry : charPrefixMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
}
