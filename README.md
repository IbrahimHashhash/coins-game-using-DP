# CoinCollectionGame

**CoinCollectionGame** is a dynamic programming-based game where two players take turns selecting coins from either end of a list to maximize their score. The game demonstrates dynamic programming principles with a **visually appealing user interface** built using **JavaFX**, complete with **animations** for smooth and engaging gameplay.

---

## Dynamic Programming Explanation

The game uses **dynamic programming** to determine the optimal coin collection strategy. The state `dp[i, j]` represents the **maximum number of coins** a player can collect from the subarray of coins between indices `i` and `j`.

### Base Cases
1. **Single Coin (`i == j`)**:
   - If there's only one coin, the maximum number of coins you can collect is simply `coins[i]`.
   
2. **Two Adjacent Coins (`|i - j| = 1`)**:
   - If there are two adjacent coins, the maximum is the larger of the two:  
     `Max{coins[i], coins[j]}`.

### Recursive Case: More Than Two Coins (`i < j`)
If there are more than two coins, the player has two choices:
- **Pick the coin at index `i`**: The remaining coins are from index `i+1` to `j`. The second player will then play optimally on this subarray. The current player collects `coins[i]` plus the sum of the remaining coins minus the optimal move of the second player (`dp[i+1, j]`).
  
- **Pick the coin at index `j`**: The remaining coins are from index `i` to `j-1`. The second player plays optimally on this subarray, and the current player collects `coins[j]` plus the


![image](https://github.com/user-attachments/assets/9d300245-552f-472f-87ae-dd4cddf79ae7)
![image](https://github.com/user-attachments/assets/75f0166e-308c-40d9-ae5a-643bdaee452d)
![image](https://github.com/user-attachments/assets/368b70f0-12ab-4719-90e2-3c3dedbcbd44)
![image](https://github.com/user-attachments/assets/dcc7f72b-c8fa-47cc-967f-4e18229f8a67)
![image](https://github.com/user-attachments/assets/de211bdb-98e7-4e88-a98c-8f43ad5df878)
![image](https://github.com/user-attachments/assets/5406de14-e34c-4025-93e7-620c0c8f5b45)
![image](https://github.com/user-attachments/assets/50eca652-a459-4d9d-9cf4-e9804f399cf6)
![image](https://github.com/user-attachments/assets/6f7af244-7bae-4fb7-8c4e-31e683310176)
![image](https://github.com/user-attachments/assets/919f9ad3-7f62-4171-8e5a-37902228dc74)
![image](https://github.com/user-attachments/assets/a1b97038-a5cc-4a85-a7db-b263d0a081ed)
