import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {
    private static boolean IS_MULTI_PROBLEM = false;

    public static void main(String[] args) {
        Scanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        Solver solver = new Solver(in, out);

        for (int i = 0, n = IS_MULTI_PROBLEM ? in.nextInt() : 1; i < n; i++) {
            solver.solve();
        }

        out.flush();
    }
}

class Solver {
    private Scanner in;
    private PrintWriter out;

    Solver(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    void solve() {}
}

interface Scanner {
    boolean hasNext();
    String next();
    long nextLong();
    int nextInt();
    double nextDouble();
}

class FastScanner implements Scanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }

    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}

    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}

    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}

    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }

    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble() { return Double.parseDouble(next());}
}

class UnionFind {
    private int[] data;

    UnionFind(int size) {
        data = new int[size];
        Arrays.fill(data, -1);
    }

    int getRoot(int x) {
        return data[x] < 0 ? x : (data[x] = getRoot(data[x]));
    }

    int getSize(int x) {
        return -data[getRoot(x)];
    }

    boolean merge(int x, int y) {
        x = getRoot(x);
        y = getRoot(y);
        if (x != y) {
            if (data[y] < data[x]) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            data[x] += data[y];
            data[y] = x;
        }
        return x != y;
    }
}
