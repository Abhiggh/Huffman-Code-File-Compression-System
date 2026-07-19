# Huffman-compression
A lossless data compression tool built in Java using the Huffman Encoding Algorithm. This project compresses text files into binary .huff files and decompresses them back to the original — preserving complete data integrity.

# 📌 About the Project
Data compression is essential in storage optimization, communication systems, and real-time data transmission. This project implements the Huffman encoding algorithm from scratch, demonstrating how frequently occurring characters are assigned shorter binary codes to reduce overall file size.

# 🛠️ Technologies Used
- Language: Java
- Concepts: Huffman Encoding, Binary Trees, Priority Queue, Bit-level I/O
- File I/O: BufferedReader, BitInputStream, BitOutputStream

# ⚙️ How It Works

- Step 1 → Read input text file
- Step 2 → Calculate frequency of each character
- Step 3 → Build Huffman Tree using Priority Queue
- Step 4 → Generate unique binary codes for each character
- Step 5 → Encode text and write to .huff file (bit-level)
- Step 6 → Decode .huff file back to original text
- Step 7 → Verify data integrity

# 📈 Key Results

# 📈 Key Results

| Metric | Value |
| :--- | :--- |
| Compression Type | Lossless |
| Algorithm | Huffman Encoding |
| Data Integrity | 100% preserved |
| File Format | .huff (binary) |

