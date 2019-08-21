#include <cstdio>
#include <cstring>
//#include <assert.h>
#include <fstream>
#include <cstdlib>
#include <vector>
#include <iostream>
using namespace std;

//位置类
class Pos
{
public:
    int x;
    int y;
    Pos(int x, int y):x(x),y(y)
    {

    }
};

// STL 容器
vector<Pos> store;

//棋盘数据：8*8
const int WIDTH = 8;
const int HEIGHT = 8;
int board[WIDTH+1][HEIGHT+1];  //棋盘数组保存数据为每个位置对应马的路线的第几步

//dir为马的八个走向
const int dir[8][2] = {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};


/**
 * @brief 求当前(i,j)位置马可走的路线(出口)
 * @param i, 横坐标
 * @param j, 纵坐标
 * @param s, 开始
 * @param a[], 存放出口信息
 * @return 出口个数和a[]
 */
int exitn(int i,int j,int s,int a[])
{
    int k,i1,j1;
    int count;  //计数出口的个数
    for (count=k=0; k<8; k++)
    {
        i1 = i + dir[(s+k)%8][0];  //八方向横坐标和纵坐标，(s+k)%8让索引保持在八方向上
        j1 = j + dir[(s+k)%8][1];
        if( i1 >= 0 && i1 < HEIGHT && j1 >= 0
                && j1 < WIDTH && board[i1][j1] == 0 ) //在棋盘的范围内并且没有走过
            a[count++] = (s+k) % 8;
    }
    return count;
}

//将(i,j)节点以s开始的下一个节点序列中，节点数最小的一个返回  //next(istartX, istartY, start)
/**
 * @brief 获取出口位置最少的方向索引
 * @param i, 横坐标
 * @param j, 纵坐标
 * @param s, 开始
 * @return 方向索引
 */
int next(int i,int j,int s)
{
    int m,k,go,current_tmp_min,a[8],b[8],temp;
    m = exitn(i, j, s, a);
    if( m==0 )                //没有出口
        return -1;
    for( current_tmp_min=8,k=0; k<m; k++)       //搜寻出口最少的位置
    {
        temp = exitn(i+dir[a[k]][0], j+dir[a[k]][1], s, b);
        if(temp < current_tmp_min)             //保存最小出口
        {
            current_tmp_min = temp;
            go = a[k];             //保存方向索引
        }
    }
    return go;             //返回最少出口位置的方向索引
}


int main()
{
    //表示当前起始位置
    int istartX = 0;
    int istartY = 0;
    while (1)
    {
        cout << "请输入马的起始位置行号(0~7)：";
        cin >> istartX;
        if ( istartX >= 0 && istartX <=7 )  // 满足条件
            break;
    }

    while (1)
    {
        cout << "请输入马的起始位置列号(0~7)：";
        cin >> istartY;
        if ( istartY >= 0 && istartY <= 7 )
            break;
    }

    //step表示第几步,flag标记下一步的方向，start表示方向索引的初始值
    int step;
    int flag;
    int start=1;

    //初始化棋盘，0表示没有走过//
    memset(board,0,sizeof(int)*WIDTH*HEIGHT);


    //起始位置的第一步，1表示第一步，N表示第N步
    board[istartX][istartY] = 1;
    Pos v(istartX, istartY);
    store.push_back(v);

    cout << "\n棋盘中的轨迹：（数字表示第几步，所在位置为棋盘位置）" << endl;

    //从第二步开始，直到走满整个棋盘
    for(step=2; step <= WIDTH*HEIGHT; step++)
    {
        flag = next(istartX, istartY, start);
        if ( flag  == -1 )      //-1，没有找到出口
            break;

        //下一步的起始坐标
        istartX += dir[flag][0];
        istartY += dir[flag][1];

        //保存当前步到棋盘作为标记
        board[istartX][istartY] = step;

        Pos v(istartX,istartY);
        //存放位置坐标
        store.push_back(v);
    }

    //输出棋盘保存的路径
    int i, j;
    for(i=0; i<HEIGHT; i++)
    {
        for(j=0; j<WIDTH; j++)
            printf("%5d", board[i][j]);
        cout << endl;
    }

    int count = 0;
    ofstream fout;
    remove("../data/path.txt");
    fout.open("../data/path.txt", ios::app);
    cout << "马遍历的路径为：" << endl;
    for(vector<Pos>::const_iterator it = store.begin(); it != store.end(); ++it)
    {
        cout << "第" << ++count << "步: (" << (*it).x << "," << (*it).y << ")" << endl;

        fout << (*it).x << "," << (*it).y << endl;
        //system("pause");
        system("python3 draw_path.py");     // 动态画图     2,5为例
    }
    fout.close();
    return 0;
}
