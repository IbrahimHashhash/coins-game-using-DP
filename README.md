CoinCollectionGame is a dynamic programming-based game where two players take turns selecting coins from either end of a list to maximize their score. It demonstrates dynamic programming principles, with a visually appealing user interface built using JavaFX, complete with animations and smooth transitions, making the gameplay engaging and interactive.

Dynamic Programming Explanation:
The game uses dynamic programming to determine the optimal coin collection strategy. The state dp[i, j] represents the maximum number of coins a player can collect from the subarray of coins between indices i and j.

Base Case 1: If there’s only one coin (i == j), the maximum number of coins is simply coins[i].

Base Case 2: For two adjacent coins (|i - j| = 1), the maximum is the larger of coins[i] or coins[j].

Recursive Case: For more than two coins (i < j), the player has two choices:

Pick the coin at index i, then the second player plays optimally on the subarray [i+1, j].

Pick the coin at index j, then the second player plays optimally on the subarray [i, j-1]. The value of dp[i, j] is the maximum coins the current player can collect between these two choices.

Error Condition: If i > j, the subarray is invalid.


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
