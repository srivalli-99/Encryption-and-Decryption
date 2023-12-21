import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Railfence {
    public static int key[];

    public char mat[][] = new char[10][8];
    public char cmat[][] = new char[10][8];

    int rows = 0;

    public String rfencryption(String text) {
        int len = text.length();
        if ((len % key.length) != 0) {
            rows = (len / key.length) + 1;
            int ch = len % key.length;
            for (int i = 0; i < (key.length - ch); i++)
                text += 'X';
        } else {
            rows = len / key.length;
        }

        int k = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= key.length; j++) {
                mat[i][j] = text.charAt(k++);
            }
        }

        System.out.println("Encrypted Matrix:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= key.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder enctxt = new StringBuilder();
        for (int kVal : key) {
            for (int i = 1; i <= rows; i++) {
                enctxt.append(mat[i][kVal]);
            }
        }

        System.out.println("Encrypted Text: " + enctxt);
        return enctxt.toString();
    }

    public String rfdecryption(String txt) {
        int k = 1;
        int q = 0;
        StringBuilder dectxt = new StringBuilder();

        while (k <= key.length) {
            for (int p = 0; p < key.length; p++) {
                if (key[p] == k) {
                    int j = p + 1;
                    k++;
                    for (int i = 1; i <= rows; i++) {
                        cmat[i][j] = txt.charAt(q++);
                    }
                    break;
                }
            }
        }

        System.out.println("Decrypted Matrix:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= key.length; j++) {
                System.out.print(cmat[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= key.length; j++) {
                dectxt.append(cmat[i][j]);
            }
        }

        System.out.println("Decrypted Text: " + dectxt);
        return dectxt.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Railfence rf = new Railfence();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter key (space-separated integers):");
        String[] keyInput = br.readLine().split(" ");
        key = new int[keyInput.length];
        for (int i = 0; i < keyInput.length; i++) {
            key[i] = Integer.parseInt(keyInput[i]);
        }

        System.out.println("Enter PLAIN TEXT:");
        String plain = sc.nextLine();
        System.out.println("Plain Text: " + plain);

        String ctext = rf.rfencryption(plain);
        System.out.println("\nCIPHER TEXT: " + ctext);

        String plaintext = rf.rfdecryption(ctext);
        System.out.println("\nPLAIN TEXT: " + plaintext);

        sc.close();
    }
}