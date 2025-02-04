#include<bits/stdc++.h>
using ll = long long;
using ld = long double;
using namespace std;

#define endl "\n"
#define ff first
#define ss second
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

void solve() {
    ll n, m;
    cin >> n >> m;
    cout << max(n, m) + 1 << endl;
    return;
}

int main() {
    FAST_IO;
    int T;
    cin >> T;
    while (T--) {
        solve();
    }
    return 0;
}