#ifndef MYGRAPH_H
#define MYGRAPH_H

/**
*	graph head source file
*
*/

#include <iostream>
#include <boost/lexical_cast.hpp>
using namespace std;

const int MAXVERTEXNUM = 100;
const int MAXWEIGHT = 100;

class MyGraph
{
//private:
public:
    int M_vertexNum;
    int M_edgeNum;
    int M_vertexs[MAXVERTEXNUM];                // 顶点序号
    int M_edges[MAXVERTEXNUM][MAXVERTEXNUM];    // 权值

public:
    MyGraph()
    { }
    virtual ~MyGraph()
    { }

    /**
     * @brief initGraph, virtual method in super class!
     * @note you should overwrite this method in drivered class!
     */
    virtual void initGraph() = 0;

    /**
     * @brief showOriginalGraph
     */
    void showOriginalGraph() {
        int i;
        int j;
        int count = 0 ;
        cout << "图的存储: " << endl;
        for ( i=0; i<M_vertexNum; i++ )
            for ( j=0; j<M_vertexNum; j++ )
            {
                count ++;
                string tmp = boost::lexical_cast<string>( M_edges[j][i] );

                if ( M_edges[j][i] == MAXWEIGHT )
                    tmp = "oo";

                cout << tmp << "\t";

                if ( count%7 == 0 )
                    cout << endl;
            }
        cout << endl;
        return;
    }

};

#endif // MYGRAPH_H
