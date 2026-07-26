# Hybrid XOR–Rotation–SHA256 Algorithm

A simple Java implementation of a hybrid encryption scheme that combines **XOR encryption**, **character rotation**, and **SHA-256 hashing** to provide both confidentiality and data integrity.

## Overview

This project encrypts plaintext using two lightweight, reversible transformations layered together, then generates a SHA-256 hash of the resulting ciphertext so that any later tampering with the data can be detected.

- **Confidentiality** is provided by the XOR + rotation layers.
- **Integrity** is provided by comparing a freshly computed SHA-256 hash against the one generated at encryption time.

## How It Works

### Encryption
1. Enter the plaintext to encrypt.
2. Enter a numeric XOR key.
3. Enter a rotation (shift) value.
4. XOR each character of the plaintext with the key.
5. Rotate (shift) each resulting character by the shift value → this produces the ciphertext.
6. Generate a SHA-256 hash of the ciphertext.
7. Output: plaintext, key, rotation, ciphertext, and hash.

### Decryption
1. Enter the ciphertext.
2. Enter the same XOR key and rotation value used during encryption.
3. Reverse the rotation (subtract the shift).
4. Reverse the XOR operation using the same key → recovers the original plaintext.
5. Recompute the SHA-256 hash of the ciphertext.
6. (Optional) Compare it against the original hash to verify integrity.

## Requirements

- Java JDK 8 or later
- Maven
- No external libraries (uses only `java.security.MessageDigest`, `java.lang.StringBuilder`, and `java.util.Scanner`)

## Project Structure

HybridXORRotationHash/
├── pom.xml
├── src/
│ ├── main/java/com/mycompany/hybridxorrotationhash/
│ │ └── HybridXORRotationHash.java # Main program (encryption, decryption, hashing, CLI)
│ └── test/java/
└── README.md

## How to Run

**With Maven:**
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.mycompany.hybridxorrotationhash.HybridXORRotationHash"
```

**Or compile and run directly with the JDK:**
```bash
javac -d target/classes src/main/java/com/mycompany/hybridxorrotationhash/HybridXORRotationHash.java
java -cp target/classes com.mycompany.hybridxorrotationhash.HybridXORRotationHash
```

### Example — Encryption
Enter your choice: 1
Enter your name (plaintext): Fajr
Enter XOR key: 4
Enter rotation value: 3

--- ENCRYPTION RESULT ---
Plaintext: Fajr
Key: 4 | Rotation: 3
Ciphertext: Ehqy
SHA-256 of Ciphertext: 435de611e55a714fa1a0e664dd6edbf0793a58df893c1cc0c48c57c1ee7acd61
Save your Ciphertext and Hash for later decryption.

### Example — Decryption
Enter your choice: 2
Enter Ciphertext to decrypt: Ehqy
Enter XOR key used for encryption: 4
Enter rotation value used for encryption: 3
Enter the original SHA-256 hash (optional, press Enter to skip): 435de611e55a714fa1a0e664dd6edbf0793a58df893c1cc0c48c57c1ee7acd61

--- DECRYPTION RESULT ---
Decrypted Text: Fajr
Recalculated SHA-256 of Ciphertext: 435de611e55a714fa1a0e664dd6edbf0793a58df893c1cc0c48c57c1ee7acd61
Integrity Verified: true

## Integrity Verification — Failure Cases

| Scenario | Result |
|---|---|
| No original hash provided at decryption | `No hash provided — integrity check skipped.` |
| Ciphertext altered (e.g. an extra space added) before decryption | Recalculated hash differs from the original → `Integrity Verified: false` |
