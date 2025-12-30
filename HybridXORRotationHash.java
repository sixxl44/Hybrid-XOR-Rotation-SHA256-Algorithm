

package com.mycompany.hybridxorrotationhash;

import java.security.MessageDigest;
import java.util.Scanner;


public class HybridXORRotationHash {

    // XOR encryption/decryption
    static String xorEncryptDecrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append((char) (text.charAt(i) ^ key));
        }
        return result.toString();
    }

    // Rotation encryption/decryption
    static String rotate(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append((char) (text.charAt(i) + shift));
        }
        return result.toString();
    }

    // SHA-256 hash function
    static String sha256(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(text.getBytes());
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public static void main(String[] args) throws Exception {
     Scanner sc = new Scanner(System.in);
        System.out.println("=== Hybrid XOR–Rotation–SHA256 Algorithm ===");
        boolean running = true;

while (running) {
    System.out.println("\nChoose Mode: 1 = Encrypt | 2 = Decrypt | 3 = Exit");
    System.out.print("Enter your choice: ");
    int mode = sc.nextInt();
    sc.nextLine(); // consume newline

    if (mode == 1) {
        // === ENCRYPTION MODE ===
        System.out.print("Enter your name (plaintext): ");
        String name = sc.nextLine();

        System.out.print("Enter XOR key: ");
        int key = sc.nextInt();

        System.out.print("Enter rotation value: ");
        int shift = sc.nextInt();
        sc.nextLine();

        String xorCipher = xorEncryptDecrypt(name, key);
        String rotatedCipher = rotate(xorCipher, shift);
        String hashCipher = sha256(rotatedCipher);

        System.out.println("\n--- ENCRYPTION RESULT ---");
        System.out.println("Plaintext: " + name);
        System.out.println("Key: " + key + " | Rotation: " + shift);
        System.out.println("Ciphertext: " + rotatedCipher);
        System.out.println("SHA-256 of Ciphertext: " + hashCipher);
        System.out.println("Save your Ciphertext and Hash for later decryption.");
    }

    else if (mode == 2) {
        // === DECRYPTION MODE ===
        System.out.print("Enter Ciphertext to decrypt: ");
        String cipher = sc.nextLine();

        System.out.print("Enter XOR key used for encryption: ");
        int key = sc.nextInt();

        System.out.print("Enter rotation value used for encryption: ");
        int shift = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the original SHA-256 hash (optional, press Enter to skip): ");
        String hashOriginal = sc.nextLine().trim();

        String unrotated = rotate(cipher, -shift);
        String decrypted = xorEncryptDecrypt(unrotated, key);
        String hashCipherAgain = sha256(cipher);

        System.out.println("\n--- DECRYPTION RESULT ---");
        System.out.println("Decrypted Text: " + decrypted);
        System.out.println(" Recalculated SHA-256 of Ciphertextt: " + hashCipherAgain);
        
        
       // Integrity check: compares recalculated SHA-256 hash with the original to confirm authenticity.
        if (!hashOriginal.isEmpty()) {
            boolean verified = hashCipherAgain.equals(hashOriginal);
            System.out.println("Integrity Verified: " + verified );
        } else {
            System.out.println("No hash provided — integrity check skipped.");
        }
    }

    else if (mode == 3) {
        System.out.println("\nExiting program... Goodbye ");
        running = false;
    }

    else {
        System.out.println("Invalid choice! Please enter 1, 2, or 3.");
    }
}
    }
}
