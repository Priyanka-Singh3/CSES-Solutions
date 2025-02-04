#include<bits/stdc++.h>
using ll = long long;
using ld = long double;
using namespace std;

#define endl "\n"
#define ff first
#define ss second
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

void solve() {
    int n, k;
    cin >> n >> k;
    string s;
    cin >> s;
    map<char, int> mpp;
    for(auto ch: s) {
        mpp[ch]++;
    }
    int cnt = 0;
    for(auto &pair: mpp) {
        if(pair.second & 1) {
            cnt++;
        }
    }
    if(k >= cnt-1) {
        cout << "yes" << endl;
    }
    else {
        cout << "no" << endl;
    }
    
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