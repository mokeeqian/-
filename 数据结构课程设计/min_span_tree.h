#ifndef MIN_SPAN_TREE_H
#define MIN_SPAN_TREE_H
#include "mygraph.h"


// prim 算法求最小生成树的辅助数组
typedef struct array{
    int adjvertex;  // 某顶点与已经构件好的部分生成树的顶点之间 权值最小的顶点
    int lowcost;    // .................................的最小权值
}closeEdge[MAXVERTEXNUM];

// min span tree class
class MinSpanTree:public MyGraph
{
public:
    MinSpanTree() {

    }
    virtual ~MinSpanTree() {

    }

    /**
     * @brief initGraph, virtual method in super class!
     */
    virtual void initGraph();

    void computeMinSpanTree(int u, closeEdge closedge);
};

#endif // MIN_SPAN_TREE_H
