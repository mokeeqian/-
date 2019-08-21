#include "graph_matrix.h"
#include <iostream>
#include <stack>
using namespace std;


/**
 * @brief shortestPathDji
 * @param G, graph
 * @param v0, the start vertex
 * @param pre[v], the prefix vertex of v,前驱顶点
 * @param dist[v], the shortest path length from v0 to v
 * @note pre[v] = -1, 表示v没有前驱顶点
 * @note final[v] = true, 表示找到了从v0到v的最短路径
 */
void shortestPath( MGraph G, int v0, int pre[], int dist[] ) {
    bool final[MAXVERTEXNUM];

    int i;
    int w;
    int v;
    int current_tmp_min;
    // 初始化
    for ( v=0; v<=G.vertexNum-1; v++ ) {
        final[v] = false;
        dist[v] = G.edges[v0][v].weight;
#if 1
        pre[v] = -1;      // v无前驱，置为 -1
#else
        pre[v] = NONE;
#endif
        if ( dist[v] < MAXWEIGHT )  // v 到 v0 连通
            pre[v] = v0;
    }

    // 开始时V0属于Ｓ集合，默认已经找到
    dist[v0] = 0;
    final[v0] = true;

    // main loop
    for ( i=1; i<G.vertexNum+1; i++ ) {      // 寻找其余6个节点
        v = -1;     // ---> if (v==-1)
        current_tmp_min = MAXWEIGHT;    // 初始化为不连通状态
        for ( w=0; w<G.vertexNum; w++ ) {
            if ( final[w] == false && dist[w] < current_tmp_min ) {  // w还没找到最短路径，并且d[w]比当前min 还要小
                v = w;                          // 更新
                current_tmp_min = dist[w];
            }
        }

        if ( v == -1 )                      // 所有与V0相通的点都找到了最短路径，退出main loop
            break;
        final[v] = true;

        for ( w=0; w<G.vertexNum; w++ ) {
            if ( final[w] == false && (current_tmp_min+(G.edges[v][w].weight) < dist[w]) )  // w还没找到最短路径,
            {
                dist[w] = current_tmp_min + (G.edges[v][w].weight);     // 更新最短路径长度,加上当前边的权值
                pre[w] = v;     // v作为w的前驱顶点
            }
        }
    }   //end main loop
    return;
}

/**
 * @brief showShortestPath
 * @param G, graph
 * @param v0, 起始点序号
 * @param pre, 存放前驱节点
 * @param dist, 最短路径大小
 */
void showShortestPath( MGraph G, int v0, int pre[], int dist[] ) {
    int v;
    int i;
    stack<int> s;
    cout << "从顶点 " << v0 << "　到其他顶点: " << endl;
#if 0
    for ( v=0; v<G.vertexNum; v++ ) {
        if ( pre[v] == -1 )   //v0 到 v 不通
            continue;
        cout <<  "顶点" << v << endl;
        cout << "最短路径长度: " << dist[v] << endl;
        i = v;
        while ( pre[i] != -1 ) {
            //cout << "前驱: " << pre[i] << endl;
            s.push(pre[i]);             // 入栈，交换顺序

            i = pre[i];
        }
        cout << "最短路径: ";
        for ( unsigned long size = s.size(); size > 0; size -- ) {
            cout << s.top() << " -> ";
            s.pop();
        }
        cout << v << endl << endl;
    }
#else
    i = 6;
    cout <<  "顶点" << i << endl;
    cout << "最小路径长度: " << dist[i] << endl;
    cout << "前驱: " << pre[i] << endl;
    cout << "顶点数: " << G.vertexNum << endl;
#endif
    return;
}


int main() {
    MGraph G;
    createGraphMatrix(&G);
    int pre[MAXVERTEXNUM];
    int dist[MAXVERTEXNUM];
    shortestPath(G, 0, pre, dist);
    showShortestPath(G, 0, pre, dist);

    return 0;
}
