# n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
#
#
#
#  上图为 8 皇后问题的一种解法。
#
#  给定一个整数 n，返回 n 皇后不同的解决方案的数量。
#
#  示例:
#
#  输入: 4
# 输出: 2
# 解释: 4 皇后问题存在如下两个不同的解法。
# [
#  [".Q..",  // 解法 1
#   "...Q",
#   "Q...",
#   "..Q."],
#
#  ["..Q.",  // 解法 2
#   "Q...",
#   "...Q",
#   ".Q.."]
# ]
#
#  Related Topics 回溯算法


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def totalNQueens(self, n: int) -> int:
        if n < 1: return []
        self.count = 0
        self.DFS(n, 0, 0, 0, 0)
        return self.count

    def DFS(self, n, row, cols, pie, na):
        # recursion terminator
        if row >= n:
            self.count += 1
            return
        bits = (~(cols | pie | na)) & ((1 << n) - 1)  # 得到当前所有的空位

        while bits:
            p = bits & -bits  # 取到最低位的1
            bits = bits & (bits - 1)  # 表示在p位置上放入皇后
            self.DFS(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1)
            # 不需要revert  cols, pie, na 的状态

# leetcode submit region end(Prohibit modification and deletion)
