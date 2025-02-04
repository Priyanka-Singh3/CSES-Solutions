#include<bits/stdc++.h>
using ll = long long;
using ld = long double;
using namespace std;

#define endl "\n"
#define ff first
#define ss second
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <climits> // For INT_MAX and INT_MIN
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;
using namespace std;
template <class T>
using oset = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
const long long MOD = 1000000007;

struct Pair {
    int s, e, idx;
    Pair(int start, int end, int index) : s(start), e(end), idx(index) {}
};
void solve() {
    int n;
    cin >> n;
    vector<Pair> arr;
    for(int i = 0; i < n; i++) {
        int s, e;
        cin >> s >> e;
        arr.emplace_back(s, e, i);
    }
    sort(arr.begin(), arr.end(), [](const Pair& p1, const Pair& p2) {
        if (p1.s == p2.s) {
            return p1.e < p2.e;
        }
        return p1.s < p2.s;
    });
    vector<int> ans(n);
    set<int> orderedSet;
    oset<pair<int, int>> s;
    int roomNo = 1;
    for(int i = 0; i < n; i++) {
        auto it = s.lower_bound({arr[i].s, 0}); 

        if (it == s.begin()) {
            // No room available, allocate a new room
            s.insert({arr[i].e, roomNo});
            ans[arr[i].idx] = roomNo;
            roomNo++;
        } else {
            // Reuse the closest room available
            --it;
            int currRoomNo = it->ss;
            s.erase(it);
            s.insert({arr[i].e, currRoomNo});
            ans[arr[i].idx] = currRoomNo;
        }
    }
    cout << s.size() << endl;
    for (int i = 0; i < n; ++i) {
        cout << ans[i] << " ";
    }
    cout << endl;
}

int main() {
    solve();
    return 0;
}