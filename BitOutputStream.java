package Huffman;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream implements Closeable{
    private final OutputStream out;
    private int currentByte;
    private int numBitsInCurrentByte;

    public BitOutputStream(String fileName) throws IOException {
        this.out = new BufferedOutputStream(new FileOutputStream(fileName));
        this.currentByte = 0;
        this.numBitsInCurrentByte = 0;
    }

    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        currentByte = (currentByte << 1) | bit;
        numBitsInCurrentByte++;
        if (numBitsInCurrentByte == 8) {
            out.write(currentByte);
            currentByte = 0;
            numBitsInCurrentByte = 0;
        }
    }

    @Override
    public void close() throws IOException {
        if (numBitsInCurrentByte > 0) {
            currentByte <<= (8 - numBitsInCurrentByte);
            out.write(currentByte);
        }
        out.close();
    }
}
