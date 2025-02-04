#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class FastIO {
private:
    static const int BUFFER_SIZE = 1 << 16;
    char buffer[BUFFER_SIZE];
    int bufferPointer = 0, bytesRead = 0;
    FILE *inputFile, *outputFile;

    char readChar() {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0;
            bytesRead = fread(buffer, 1, BUFFER_SIZE, inputFile);
            if (bytesRead == 0) return EOF;
        }
        return buffer[bufferPointer++];
    }

public:
    FastIO(FILE *in = stdin, FILE *out = stdout) {
        inputFile = in;
        outputFile = out;
    }

    int nextInt() {
        char c;
        while ((c = readChar()) <= ' ');
        int sign = 1, result = 0;
        if (c == '-') {
            sign = -1;
            c = readChar();
        }
        do {
            result = result * 10 + (c - '0');
            c = readChar();
        } while (c > ' ');
        return result * sign;
    }

    void println(int x) {
        fprintf(outputFile, "%d\n", x);
    }

    void close() {
        fflush(outputFile);
    }
};

int main() {
    FastIO io;

    int n = io.nextInt();
    int x = io.nextInt();

    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        arr[i] = io.nextInt();
    }

    sort(arr.begin(), arr.end());

    int gondolas = 0;
    int i = 0, j = n - 1;

    while (i < j) {
        if (arr[i] + arr[j] <= x) {
            i++;
            j--;
        } else {
            j--;
        }
        gondolas++;
    }

    if (i == j) {
        gondolas++;
    }

    io.println(gondolas);
    io.close();

    return 0;
}
