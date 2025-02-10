#include<bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;
using namespace std;
template <class T>
 
#define endl '\n'
using oset = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
const long long MOD = 1000000007;
const long long N = 200001;
long long a[N];
 
signed main(){
    long long n,q;
    cin>>n>>q;
    oset<pair<long long,long long> > s;
 
    for(long long i=1;i<=n;i++){
        cin>>a[i];
        s.insert({a[i],i});
    }
    while(q--){
        char c;
        cin>>c;
        if (c=='!'){
            long long x,y;
            cin>>x>>y;
            s.erase({a[x],x});
            a[x] = y;
            s.insert({a[x],x});
        } else {
            long long x,y;
            cin>>x>>y;
            cout<<s.order_of_key({y,MOD}) - s.order_of_key({x-1,MOD}) <<endl;
        }
    }
}