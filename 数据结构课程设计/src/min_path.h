#ifndef MIN_PATH_H
#define MIN_PATH_H

/**
  * dijkstra to compute the shortest path
  */

#include "mygraph.h"

class MinPath:public MyGraph {
public:
    MinPath()
    { }
    virtual ~MinPath()
    { }

    virtual void initGraph();
    void computeShortestPath(int v0, int *pre, int *dist);
    void showShortestPath(int v0, int *pre, int *dist);
};

#endif // MIN_PATH_H
