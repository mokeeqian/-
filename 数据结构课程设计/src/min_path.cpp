#include <iostream>
#include <stack>
#include <boost/lexical_cast.hpp>
#include "min_path.h"
using namespace std;

/**
 * @brief MinPath::initGraph
 * @note the start vertex must start with '0'
 * @todo fix the vertex info(automatically to certain question)
 */
void MinPath::initGraph() {
    M_vertexNum = 6;
    M_edgeNum = 10;
    int i;
    int j;
    for ( i=0; i<M_vertexNum; i++ ) {
        this->M_vertexs[i] = i;
    }

    for ( i=0; i<M_vertexNum; i++ )
        for ( j=0; j<M_vertexNum; j++ )
            this->M_edges[i][j] = MAXWEIGHT;

    M_edges[0][1] = 3;
    M_edges[0][2] = 2;
    M_edges[0][3] = 5;
    M_edges[2][3] = 1;
    M_edges[3][1] = 2;
    M_edges[1][5] = 7;
    M_edges[4][2] = 5;
    M_edges[3][5] = 5;
    M_edges[4][3] = 3;
    M_edges[4][5] = 1;
    return;
}

/**
 * @brief MinPath::computeShortestPath
 * @param v0, the start vertex
 * @param pre, array to store the current vertex's pirror vertex
 * @param dist, array to store the current vertex's weight to the start vertex
 */
void MinPath::computeShortestPath(int v0, int *pre, int *dist) {
    bool final[MAXVERTEXNUM];

    int i;
    int w;
    int v;
    int current_tmp_min;
    // 初始化
    for ( v=0; v<=M_vertexNum-1; v++ ) {
        final[v] = false;
        dist[v] = M_edges[v0][v];       // 如果不直接连通，dist[v]就是MAXWEIGHT,否则是相应的权值, 我称此时dist[v] 为 "估计值"
        pre[v] = -1;      // 所有的顶点都无前驱，置pre数组为 -1

        if ( dist[v] < MAXWEIGHT )  // v 到 v0 (直接)连通
            pre[v] = v0;
    }

    // 开始时V0属于Ｓ集合，默认已经找到最短路径
    dist[v0] = 0;
    final[v0] = true;       // (final[i] = true 相当于把i 加入S集合)

    // main loop
    // 寻找其余6个节点
    for ( i=1; i<M_vertexNum+1; i++ ) {
        v = -1;     // ---> if (v==-1)
        current_tmp_min = MAXWEIGHT;    // 当前距V0路径最短的权值 ,初始化为不连通状态

        // 寻找当前离V0最近的顶点 V
        // 第一次main loop 下, current_tmp_min 变化情况: dist[1] -> dist[2]
        for ( w=0; w<M_vertexNum; w++ ) {
            if ( final[w] == false && dist[w] < current_tmp_min ) {  // w还没找到最短路径，并且d[w]比当前min 还要小
                v = w;                          // 更新
                current_tmp_min = dist[v];
            }
        }

        if ( v == -1 )                      // 所有与V0相通的点都找到了最短路径(不满足line 108 的if )，则退出main loop
            break;

        final[v] = true;		// 把V 加入S集合

        // 更新当前最短路径及距离(V 作为中间点)
        for ( w=0; w<M_vertexNum; w++ ) {
            if ( final[w] == false && (current_tmp_min+(M_edges[v][w]) < dist[w]) )  // v0 -> v -> w 比 v0 -> w 短
            {
                // 第一次main loop, dist[1] 更新成 4, 2 作为1 的前驱
                dist[w] = current_tmp_min + (M_edges[v][w]);     // 更新最短路径长度, 此时dist[w]为 "确定值"
                pre[w] = v;     // v作为w的前驱顶点
            }
        }
    }   //end main loop
    return;
}

/**
 * @brief MinPath::showShortestPath
 * @param v0
 * @param pre
 * @param dist
 */
void MinPath::showShortestPath(int v0, int *pre, int *dist) {
    int v;
    int i;
    stack<int> s;
    cout << "从顶点 " << v0 << "　到其他顶点: " << endl;

    for ( v=0; v<M_vertexNum; v++ ) {
        if ( pre[v] == -1 )   //v0 到 v 不通
        {
            cout << "\t" << v << " 没有通路" << endl << endl;
            continue;
        }
        cout <<  "\t顶点" << v << endl;
        cout << "\t最短路径长度: " << dist[v] << endl;
        i = v;
        while ( pre[i] != -1 ) {
            //cout << "前驱: " << pre[i] << endl;
            s.push(pre[i]);             // 入栈，交换顺序

            i = pre[i];
        }
        cout << "\t最短路径: ";
        for ( unsigned long size = s.size(); size > 0; size -- ) {
            cout << s.top() << " -> ";
            s.pop();
        }
        cout << v << endl << endl;
    }
    return;
}

/**
 * @brief main
 * @return
 */
int main() {
    MinPath G;
    int pre[MAXVERTEXNUM];
    int dist[MAXVERTEXNUM];
    G.initGraph();
    G.showOriginalGraph();
    G.computeShortestPath(0, pre, dist);
    G.showShortestPath(0, pre, dist);

    return 0;
}
