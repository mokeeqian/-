#include <iostream>
#include <string>
#include "min_span_tree.h"
using namespace std;

/**
 * @brief MinSpanTree::initGraph
 */
void MinSpanTree::initGraph() {
    M_vertexNum = 7;
    M_edgeNum = 12;

    int i;
    int j;
    for ( i = 0; i < 7; ++i) {
        M_vertexs[i] = i;
    }

    for ( i=0; i<7; i++ )
        for ( j=0; j<7; j++ )
            M_edges[i][j] = MAXWEIGHT;

    M_edges[0][1] = 20;
    M_edges[1][2] = 15;
    M_edges[2][3] = 3;
    M_edges[3][4] = 17;
    M_edges[4][5] = 28;
    M_edges[5][0] = 23;
    M_edges[0][6] = 1;
    M_edges[1][6] = 4;
    M_edges[2][6] = 9;
    M_edges[3][6] = 16;
    M_edges[4][6] = 25;
    M_edges[5][6] = 36;

    M_edges[1][0] = 20;
    M_edges[2][1] = 15;
    M_edges[3][2] = 3;
    M_edges[4][3] = 17;
    M_edges[5][4] = 28;
    M_edges[0][5] = 23;
    M_edges[6][0] = 1;
    M_edges[6][1] = 4;
    M_edges[6][2] = 9;
    M_edges[6][3] = 16;
    M_edges[6][4] = 25;
    M_edges[6][5] = 36;

    return;
}

/**
 * @brief MyGraph::minSpanTree
 * @param u, 起始点
 * @param closedge, 存放最小生成树的顶点信息
 */
void MinSpanTree::computeMinSpanTree(int u, closeEdge closedge) {

    int i;
    int j;
    int w;
    int k;
    // 辅助数组初始化
    for ( i=0; i<M_vertexNum; i++ ) {
        if ( i != u ) {
            closedge[i].adjvertex = u;
            closedge[i].lowcost = M_edges[u][i];
        }
    }
    closedge[u].lowcost = 0;

    // 选择其余的n-1的顶点
    for (i = 0;  i< M_vertexNum-1; i++) {
        w = MAXWEIGHT;
        for ( j=0; j<M_vertexNum; j++ ) {
            if ( closedge[j].lowcost != 0 && closedge[j].lowcost < w ) {
                w = closedge[j].lowcost;
                k = j;
            }
        }

        closedge[k].lowcost = 0;    // 第k顶点并入U集合
        // 修改辅助数组
        for ( j=0; j<M_vertexNum; j++ ) {
            if ( M_edges[k][j] < closedge[j].lowcost ) {
                closedge[j].adjvertex = k;
                closedge[j].lowcost = M_edges[k][j];
            }
        }
    }

    // 打印每条边
    string start;
    string next;
    for ( i=0; i<M_vertexNum; i++ ) {
        if ( i != u ) {
#if 0
            // handle the vertex info
            switch (i) {
            case 0:
                start = "V1";
                break;
            case 1:
                start = "V2";
                break;
            case 2:
                start = "V3";
                break;
            case 3:
                start = "V4";
                break;
            case 4:
                start = "V5";
                break;
            case 5:
                start = "V6";
                break;
            case 6:
                start = "V7";
                break;
            default:
                start = "NONE";
            }

            switch (closedge[i].adjvertex) {
            case 0:
                next = "V1";
                break;
            case 1:
                next = "V2";
                break;
            case 2:
                next = "V3";
                break;
            case 3:
                next = "V4";
                break;
            case 4:
                next = "V5";
                break;
            case 5:
                next = "V6";
                break;
            case 6:
                next = "V7";
                break;
            default:
                next = "NONE";
            }
#else
            start = boost::lexical_cast<string> (i);
            next = boost::lexical_cast<string> (closedge[i].adjvertex);
#endif
            cout << "顶点路径: " << start << " -> " << next << " 权值: " << M_edges[i][closedge[i].adjvertex] << endl;
        }
    }

    return;
}

/**
 * @brief main
 * @return
 */
int main() {
    MinSpanTree S;
    closeEdge closedge;
    S.initGraph();
    //S.showOriginalGraph();
    S.computeMinSpanTree(2, closedge);

    return 0;
}
