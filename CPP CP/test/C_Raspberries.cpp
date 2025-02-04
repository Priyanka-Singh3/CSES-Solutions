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
    vector<int> arr(n);
    bool div2 = 0,  div3 = 0, div4 = 0, div5 = 0;
    int even = 0, odd = 0, sum = 0, min5 = 5, min3 = 4, min4 = 4;
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
        if(arr[i] % 2 == 0) {
            even++;
            div2 = 1;
        }
        else {
            odd++;
        }

        if(arr[i] % 3 == 0) {
            div3 = 1;
        }
        else {
            int t = ((arr[i]/3) + 1) * 3;
            min3 = min(min3, t - arr[i]);
            
        }

        if(arr[i] % 4 == 0) {
            div4 = 1;
        }
        else {
            int t = ((arr[i]/4) + 1) * 4;
            min4 = min(min4, t - arr[i]);
        }

        if(arr[i] % 5 == 0) {
            div5 = 1;
        }
        else {
            if(arr[i] > 5) {
                min5 = min(min5, 10-arr[i]);
            }
            else {
                min5 = min(min5, 5 - arr[i]);
            }
        }
        
    }
    if(k == 2) {
        if(even > 0) {
            cout << 0 << endl;
        }
        else {
            cout << 1 << endl;
        }
    }
    else if(k == 3) {
        if(div3) {
            cout << 0 << endl;
        }
        else {
            cout << min3 << endl;
        }
    }
    else if(k == 4) {
        if(div4 || even >= 2) {
            cout << 0 << endl;
        }
        else if(even == 1){
            cout << 1 << endl;
        }
        else {
            cout << min(min4, 2) << endl;
        }
    }
    else {
        if(div5) {
            cout << 0 << endl;
        }
        else {
            cout << min5 << endl;
        }
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