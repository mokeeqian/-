/**
 *  有/无向图的邻接表存储
 **/

#include <stdlib.h>
#include <stdio.h>

const int maxVertexNum = 30;

#define InfoType int
#define VertextType char

#define WuXiangTu 0			// 是否创建为无向图

typedef struct node {    // 边表节点
    int adjvertex;    // 存放顶点对应的序号
    InfoType info;    // 权值
    struct node *next;
}EdgeNode;

typedef struct vnode {    //顶点节点
    VertextType vertex;
    EdgeNode *firstedge;
}VertextNode;

typedef struct {
    VertextNode adjlist[maxVertexNum];
    int vertexNum,edgeNum;
}ALGraph;

// DIJ算法用
typedef struct Node {
    VertextType vPoint;
    InfoType dist2v0;      // 到V0的距离
    friend bool operator< (struct Node a, struct Node another ) {    // 比较权值用
        if ( a.dist2v0 < another.dist2v0 )
            return true;
        return false;
    }
}Node;


/*
 *                          图的邻接表示意图
 *      顶点      边表头指针              邻接点       边上信息   next ptr
 *  |---------|-------------|      |--------------|--------|----------|
 *  |  vertex |  firstedge  | ---> |  adjvertex   |  info  |   next   | ---> ...       ...
 *  |_________|_____________|      |______________|________|__________|
 *         顶点节点                                边表节点 1                        2         more
 *
 */

/**
 * @brief creatALGraph
 * @param *G
 */
void creatALGraph(ALGraph *G) {
    int i, j, k;
    int x;
    EdgeNode *p;

    printf("输入顶点数和边数: \n");
    scanf("%d,%d", &(G->vertexNum), &(G->edgeNum));

    printf("输入顶点信息: \n");
    for ( i=0; i< G->vertexNum; i++ ) {    // 顶点表初始化
        getchar();
        scanf("%c", &(G->adjlist[i].vertex));
        G->adjlist[i].firstedge = NULL;
    }

    printf("输入顶点对应的序号: \n");
    // 注意创建有向图，必须先输入起点，再输入终点
    for ( k=0; k<G->edgeNum; k++ ) {    // linklist邻接节点连起来
        scanf("%d,%d", &x, &j);       // 读入顶点对用的序号
        p = (EdgeNode *)malloc(sizeof(EdgeNode));
        p -> adjvertex = j;
        // 将p节点插入到Vx的链表头部
        p -> next = G->adjlist[x].firstedge;    // p的next ptr 连接原来链表的firstedge
        G->adjlist[x].firstedge = p;    // 现在p是链表第一个节点了

#if WuXiangTu
        p = (EdgeNode *)malloc(sizeof(EdgeNode));
        p -> adjvertex = x;
        p -> next = G->adjlist[j].firstedge;
        G->adjlist[j].firstedge = p;
#endif

    }
    printf("图创建完毕\n");

/*
    G->vertexNum = 8;
    G->edgeNum = 9;

    for ( i=0; i<G->vertexNum; i++ ) {
        G->adjlist[i].vertex = i;
        G->adjlist[i].firstedge = NULL;
    }

    printf("输入顶点对应的序号: \n");
    for ( k=0; k<G->edgeNum; k++ ) {
        scanf("%d,%d", &i, &j);       // 读入顶点对用的序号
        p = (EdgeNode *)malloc(sizeof(EdgeNode));
        p -> adjvertex = j;
        p -> next = G->adjlist[i].firstedge;
        G->adjlist[i].firstedge = p;
    }
*/

}

void Visit(ALGraph G, int v) {
    //printf("%c -> ", G.adjlist->vertex);           // wrong
    printf("%c -> ", G.adjlist[v].vertex);

}
