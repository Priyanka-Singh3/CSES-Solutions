#include<bits/stdc++.h>
using ll = long long;
using ld = long double;
using namespace std;

#define endl "\n"
#define ff first
#define ss second
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

void solve() {
    string s;
    cin >> s;
    int n = s.size();
    int one = 0, zero = 0;
    for(auto ch: s) {
        if(ch == '1') {
            one++;
        }
        else {
            zero++;
        }
    }
    string t = "";
    for(auto ch: s) {
        if(ch == '1' && zero > 0) {
            t.push_back('0');
            zero--;
        }
        else if(ch == '0' && one > 0){
            t.push_back('1');
            one--;
        }
        else {
            t.push_back(ch);
        }
    }
    int i = 0, j = 0;
    while(i < n && j < t.size()) {
        if(s[i] == t[j]) {
            j++;
        }
        else {
            i++;
            j++;
        }
    }
    cout << (t.size()-j) << endl;
}

int main() {
    FAST_IO;
    int T = 1;
    cin >> T;
    while (T--) {
        solve();
    }
    return 0;
}