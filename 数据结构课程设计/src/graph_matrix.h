#ifndef GRAPH_MATRIX_H
#define GRAPH_MATRIX_H


/**
  图的邻接矩阵存储
  */

const int MAXVERTEXNUM = 20;
const int MAXWEIGHT = 100;

// 边的状态枚举类型
typedef enum status {
    Connected,      // 边邻接
    Disconnected    //不邻接
}EdgeStatus;

// 带权边类型封装
typedef struct wEdge {
    int weight;
    EdgeStatus status;
}wEdgeType;

#if 0
typedef enum vertex {
    V1 = 1,
    V2,
    V3,
    V4,
    V5,
    V6,
    V7,
    NONE    //不存在
}VertexInfo;
#endif

// 图类型封装
typedef struct {
    int vertexNum;
    int edgeNum;
#if 1
    int vertexs[MAXVERTEXNUM];       // 存放顶点 -> 0,1,2,3,4,5,6 代表v0 - v6
#else
    VertexInfo vertexs[MAXVERTEXNUM];
#endif
    wEdgeType edges[MAXVERTEXNUM][MAXVERTEXNUM];     // 邻接矩阵,存放边的关系,
}MGraph;

/**
 * @brief createGraphMatrix
 * @param G, pointer to the graph
 */
void createGraphMatrix( MGraph *G ) {
    int i;
    int j;

    G->vertexNum = 7;
    G->edgeNum = 11;
    // 初始化顶点信息,
#if 1
    for ( i=0; i<G->vertexNum; i++ )
        G->vertexs[i] = i;
#else
    G->vertexs[0] = V1;
    G->vertexs[1] = V2;
    G->vertexs[2] = V3;
    G->vertexs[3] = V4;
    G->vertexs[4] = V5;
    G->vertexs[5] = V6;
    G->vertexs[6] = V7;
#endif
    // 初始化邻接矩阵为非连通状态
    for ( i=0; i<G->vertexNum; i++ )
        for ( j=0; j<G->vertexNum; j++ )
        {
            //G->edges[i][j].status = Disconnected;
            G->edges[i][j].weight = MAXWEIGHT;      //表示不连通
        }

    // 建立邻接矩阵
#if 0
    G->edges[0][1].status = Connected;
    G->edges[0][2].status = Connected;
    G->edges[0][3].status = Connected;
    G->edges[2][1].status = Connected;
    G->edges[2][3].status = Connected;
    G->edges[1][4].status = Connected;
    G->edges[2][4].status = Connected;
    G->edges[2][5].status = Connected;
    G->edges[3][5].status = Connected;
    G->edges[4][6].status = Connected;
    G->edges[5][6].status = Connected;
#endif

    G->edges[0][1].weight = 6;
    G->edges[0][2].weight = 3;
    G->edges[0][3].weight = 5;
    G->edges[2][1].weight = 1;
    G->edges[2][3].weight = 3;
    G->edges[1][4].weight = 5;
    G->edges[2][4].weight = 3;
    G->edges[2][5].weight = 4;
    G->edges[3][5].weight = 5;
    G->edges[4][6].weight = 8;
    G->edges[5][6].weight = 6;
}

#endif // GRAPH_MATRIX_H
