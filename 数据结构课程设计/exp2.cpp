/**
 * 马的遍历问题(8*8棋盘)
 *
 */

#include <iostream>
#include <stdio.h>


// 棋盘类型,边界增加两格，便于检测("日"字是否能行得通)
struct Elem {
    char c;     // 描述是否走过的标志位变量, 1表示走过了, 0　没走过
    int mark_direct[8];     // 走过的方向数组分量,对应direct_ay[]中的方向
}chessBoard[12][12];

// 位置坐标增量
struct direct_increment {
    int dx;
    int dy;
};

// 位置坐标类型封装
struct position {
    int x;
    int y;
};

// 位置坐标变化量数组
const direct_increment
direct_ay[8] = { {1,2},{2,1},{2,-1},{-1,2},
                                  {-1,-2},{-2,-1},{-2,1},{-1,2} };  // 8 个方向 其中 i 和 i +4 是相反的方向

/**
 * @brief 初始化棋盘8*8
 *
 */
void initChessBoard( Elem (*p)[12] ) {
    int i;
    int j;
    for ( i=0; i < 12; i++ )       // 全部置为 '#'
        for ( j=0; j<12; j++ ) {
            (p[i][j]).c = '#';
        }

    for ( i=2; i<=9; i++ )      // 8*8 置为 '0'
        for ( j=2; j<=9; j++) {
            (p[i][j]).c = '0';
            for ( int k =0; k < 8; k++ ) {
                ((p[i][j]).mark_direct)[k] = 0;
            }
        }

    return;
}

/**
 * @brief 检测给定的点是否在棋盘上
 * @param pos
 * @return
 */
bool checkPosition( position pos ) {
    if ( pos.x < 2 || pos.x > 9 || pos.y < 2 || pos.y > 9 )
        return false;
    return true;
}

/**
 * @brief 检测当前位置是否走过
 * @param pos
 * @return
 */
bool isVisited( Elem (*p)[12], position pos ) {
    if ( checkPosition(pos) ) {
        if ( (p[pos.x][pos.y]).c == '1' )
            return true;
        return false;
    }
    return false;
}

/**
 * @brief 将pos标记为已走过
 * @param pos
 */
void markStep( Elem(*p)[12], position pos ) {
    (p[pos.x][pos.y]).c = '1';
    return;
}

/**
 * @brief 判断pos是否是棋盘上 最后一个 没有 走过的点
 * @param pos
 * @return
 */
bool isLastPoint( Elem (*p)[12], position pos ) {
    char cc = (p[pos.x][pos.y]).c;
    if ( cc == '1' )    // 走过了
        return false;
    else {
        for ( int i = 2; i <= 9; i++ )
            for ( int j = 2; j <= 9; j++ ) {
                if ( p[i][j].c == '0'
                     && !( pos.x == i && pos.y == j)    // 不重复遍历
                     )
                    return false;
            }
    }
    return true;
}

/**
 * @brief 遍历结束
 * @return
 */
bool visitComplete( Elem (*p)[12] ) {
//TODO
    int i;
    int j;
    for ( i = 0; i < 9; i++ )
        for ( j = 0; j < 9; j++ ) {
            if ( p[i][j].c == '1' )
                continue;
            else
                return false;
        }

    return true;
}

/**
 * @brief 计算当前节点可能走的方向数
 * @param pos
 * @return
 */
int countWay( position pos ) {
    int counter = 0;
    for ( int i =0 ;i < 8; i++ ) {
        position pos1;
        pos1.x = pos.x + direct_ay[i].dx;
        pos1.y = pos.y + direct_ay[i].dy;

        if ( (chessBoard[pos.x][pos.y]).mark_direct[i] == 0
             && (chessBoard[pos1.x][pos1.y].c == '0')
             ) {
            ++ counter;
        }
    }

    return counter;
}

/**
 * @brief 启发式贪心算法,寻找下一最佳位置
 * @param ppos
 * @param bpos, 下一最佳位置
 * @return true if found, false or not
 * @note direct 记录最佳方向
 */
bool selectBestPoint( position *ppos, position *bpos ) {
    int ct;
    int i;
    int direct;
    int direct_ct = 8;
    position pos1, pos_best;
    int flag = 0;

    for ( i = 0; i < 8; i++ ) {
        if ( (chessBoard[ppos->x][ppos->y]).mark_direct[i] == 0 )  {        // 该方向没走过
            pos1.x = ppos->x + direct_ay[i].dx;
            pos1.y = ppos->y + direct_ay[i].dy;
            if ( checkPosition(pos1)            // 在棋盘上
                 && !isVisited(chessBoard, pos1)    // pos1没走过
                 ) {
                if ( isLastPoint(chessBoard, pos1) )
                    ct = 1;
                else
                    ct = countWay(pos1);
            }
            else                                // 不在棋盘上
                ct = -1;

            if ( ct <= direct_ct
                 && ct > 0
                 ) {
                pos_best.x = pos1.x;
                pos_best.y = pos1.y;
                flag = 1;
                direct_ct = ct;
                direct = i;
            }
        }
        else {
            continue;
        }
    }

    if ( flag ) {       //NOTE: OK???
        bpos->x = pos_best.x;
        bpos->y = pos_best.y;
        int reverse_direct = (direct>4) ? (direct-4) : (direct+4);      // 取刚刚试探方向的反方向
        (chessBoard[ppos->x][ppos->y]).mark_direct[direct] = 1;
        (chessBoard[bpos->x][bpos->y]).mark_direct[reverse_direct] = 1;     // 反方向标记为不可走，防止死循环

        return true;
    }
    else {
        return false;
    }
}

/**
 * @brief clearPos
 * @param pos
 * @bug ******************************************
 */
void clearPos( Elem (*p)[12], position pos ) {
    p[pos.x][pos.y].c = '0';
    //p[pos.x][pos.y].mark_direct[]
    return;
}

/**
 * @brief 求马的周游路径，递归调用
 * @param (*p)[12], 指向棋盘的指针
 * @param pos, 起始位置
 * @return true if exist a path, false or not
 */
bool visitRecursion( Elem (*p)[12], position pos ) {
    position next_pos;
    static position pos_beg = pos;      // 记下初始位置,作为判断是否找到路径的依据
    int succ = 0;       // 标记是否周游成功
    static int ct = 0;      // 记录打印次数
    int i;
    markStep( chessBoard, pos );       // 标记当前pos为已经走过了

    if ( visitComplete( chessBoard ) ) {        // 已经周游完了
        succ = 1;
        ct = 0;
        return true;
    }
    else {
        // if succ = 1,中止其他方向的搜索,当还有节点没有遍历过，继续
        for ( i = 0; succ != 1 && i < 0; i++ ) {
            if ( !selectBestPoint(&pos, &next_pos) )
                continue;
            if ( !visitRecursion(p, next_pos) ) {
                clearPos( p, next_pos );        // 清除已放弃位置的走过标记
                continue;
            }
            else
                succ = 1;

            ++ ct;
            std::cout <<" <= " << next_pos.x << " : " << next_pos.y;
            if ( ct % 8 == 0 )
                std::cout << std::endl;
        }
        if ( !succ )        //NOTE: maybe bug!!!
            return false;
    }
    return true;
}

/**
 * @brief main
 * @return
 */
int main() {
    position pos1;
    char ch;
    initChessBoard(chessBoard);
    while (1) {
        std::cout << "输入起始位置(2<=x,y<=9,空格分割，回车结束): " << std::endl;
        scanf("%d %d", &(pos1.x), &(pos1.y));
        if ( visitRecursion(chessBoard, pos1) ) {
            printf("<=%d:%d\n", pos1.x, pos1.y);       // 打印起始点
            std::cout << "逆向打印走过的路径" << std::endl;
            std::cout << "遍历结束" << std::endl;
        }
        else {
            std::cout << "没有满足条件的路径" << std::endl;
            std::cout << "还要试试其他的其实位置吗? (y/n)" << std::endl;
            //getchar(ch);
            std::cin >> ch;
            initChessBoard(chessBoard);
            if ( ch == 'y' )
                continue;
            else
                break;
        }
    }
    return 0;
}


