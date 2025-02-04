#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // Read n, m, k
    int n, m, k;
    cin >> n >> m >> k;

    // Read desired sizes
    vector<int> desiredSizeOfApartments(n);
    for (int i = 0; i < n; i++) {
        cin >> desiredSizeOfApartments[i];
    }

    // Read available sizes
    vector<int> sizeOfEachApartment(m);
    for (int i = 0; i < m; i++) {
        cin >> sizeOfEachApartment[i];
    }

    // Sort both arrays
    sort(desiredSizeOfApartments.begin(), desiredSizeOfApartments.end());
    sort(sizeOfEachApartment.begin(), sizeOfEachApartment.end());

    int i = 0, j = 0, matches = 0;

    // Two pointer approach
    while (i < n && j < m) {
        int desired = desiredSizeOfApartments[i];
        int apartment = sizeOfEachApartment[j];

        if (apartment >= desired - k && apartment <= desired + k) {
            // Found a match
            matches++;
            i++;
            j++;
        } else if (apartment < desired - k) {
            j++; // Apartment too small, move to the next apartment
        } else {
            i++; // Desired size too small, move to the next desired size
        }
    }

    // Output the result
    cout << matches << "\n";

    return 0;
}
