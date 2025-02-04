#include <iostream>
#include <vector>
using namespace std;

const int MOD = 1000000007;

class FastIO {
private:
    static const int buf_size = 1 << 16;
    char buf[buf_size];
    int curChar, numChars;
    FILE *inputFile, *outputFile;

    int nextByte() {
        if (numChars == -1)
            throw invalid_argument("InputMismatchException");
        if (curChar >= numChars) {
            curChar = 0;
            numChars = fread(buf, 1, buf_size, inputFile);
            if (numChars == -1)
                return -1;
            if (numChars == 0)
                return -1;
        }
        return buf[curChar++];
    }

public:
    FastIO(FILE *input = stdin, FILE *output = stdout) : inputFile(input), outputFile(output) {
        curChar = 0;
        numChars = 0;
    }

    string next() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        string res;
        do {
            res.push_back(c);
            c = nextByte();
        } while (c > ' ');
        return res;
    }

    int nextInt() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw invalid_argument("InputMismatchException");
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    long long nextLong() {
        int c;
        do {
            c = nextByte();
        } while (c <= ' ');
        long long sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }
        long long res = 0;
        do {
            if (c < '0' || c > '9')
                throw invalid_argument("InputMismatchException");
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    double nextDouble() {
        return stod(next());
    }
};

int main() {
    FastIO io;
    int t = io.nextInt();
    while (t > 0) {
        int n = io.nextInt();
        vector<long long> next(2, 1), curr(2);
        
        for (int i = n - 1; i >= 1; i--) {
            long long op1 = (next[1] + next[0]) ;
            long long op2 = (next[0] + (2 * next[0]));
            
            curr[0] = (op1 + op2) % MOD;
            curr[1] = (op1 + next[1]) % MOD;
            
            next[0] = curr[0];
            next[1] = curr[1];
        }
        long long ways = (next[0] + next[1]) % MOD;
        cout << ways << endl;
        t--;
    }
    return 0;
}
