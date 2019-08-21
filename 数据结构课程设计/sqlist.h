/*
 *  查找表
 */

#ifndef SQLIST_H
#define SQLIST_H

/****************线性查找表**************************/
#define maxsize 100
typedef int keyType;
// 带key的数据类型
typedef struct {
    keyType key;
    //...
}dataType;

// 线性查找表
typedef struct {
    dataType r[maxsize];        // 数据元素存储空间
    int length;
}sqlist;

/****************分块查找表*************************/
#define numBlocks 3    // 分多少个block
// 索引表
typedef struct {
    keyType maxKey;        // 最大关键字
    int beginAddr;          // 起始地址, 假设从1 开始
    int indexListLen;
}indexList;

// 分块查找表
typedef struct {
    dataType data[maxsize];    // 数据表
    indexList index[numBlocks];
    int blockListLen;
}blockList;

/***************二叉排序树*******************
又叫二叉查找树, 或者是一颗空树, 或者是: 1.若任一节点左子树非空, 左子树所有节点的值都不大于更节点的值
                                  2.若任一节点右子树非空，右子树所有节点的值都不小于根节点的值
                                  (1,2同时满足)
*/
typedef struct BinSTreeNode {
    dataType elem;
    struct BinSTreeNode *lchild;
    struct BinSTreeNode *rchild;
}*BinSTree;

#endif // SQLIST_H
