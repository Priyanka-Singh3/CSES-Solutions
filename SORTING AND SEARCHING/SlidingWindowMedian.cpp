#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp> 
#include <ext/pb_ds/tree_policy.hpp> 

using namespace std;
using namespace __gnu_pbds;

using ll = long long;
using ld = long double;
#define endl "\n"
#define ff first
#define ss second

template <typename T>
using oset = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    oset<pair<int, int>> st;
    
    for (int i = 0; i < k; i++) {
        st.insert({arr[i], i});
    }

    cout << st.find_by_order((k-1)/2)->first << " ";

    for (int i = k; i < n; i++) {
        st.erase({arr[i-k], (i-k)});
        st.insert({arr[i], i});

        cout << st.find_by_order((k-1)/2)->first << " ";
    }
    cout << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int T = 1;
    // cin >> T;
    while (T--) {
        solve();
    }
    
    return 0;
}
