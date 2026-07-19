package Huffman;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitInputStram implements Closeable {
    private final InputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStram(String fileName) throws IOException {
        this.in = new BufferedInputStream(new FileInputStream(fileName));
        this.numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = in.read();
            if (currentByte == -1) return -1;
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >> numBitsRemaining) & 1;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
