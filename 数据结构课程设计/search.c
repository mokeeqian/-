/*
 * 查找算法集合
 */

#include "sqlist.h"
#include <stdlib.h>

/**
 * @brief 顺序查找
 * @param l
 * @param k
 * @return
 */
int seqSearch(sqlist l, keyType k) {
    int i;
    for ( i=0; i< l.length; i++ ) {
        if ( l.r[i].key == k )
            return i;
    }

    return -1;
}


int seqSearch_gai(sqlist l, keyType k) {
    int n;
    int i = 0;
    n = l.length;
    l.r[n].key = k;
    while (l.r[i].key != k) {
        i++;
    }
    if ( i == n )
        return i;
    return -1;
}

/**
 * @brief 二分查找(对有序表)
 * @param l
 * @param k
 * @return
 */
int binSearch(sqlist l, keyType k) {
    int low,mid,high;
    low = 0;
    high = l.length - 1;

    while ( low <= high ) {
        mid = 1/2 *(low + high);    // 二分区间
        if ( k == l.r[mid].key )
            return mid;
        else if ( k < l.r[mid].key )
            high = mid - 1;        // 左区间查找
        else
            high = mid + 1;        // 右区间查找
    }
    return -1;
}

/**
 * @brief 分块查找(索引表要求有序，max前 < max后, 块间要求有序，块内不要求)
 * @param l
 * @param k
 * @return
 */
int blockSearch(blockList l, keyType k) {
    int i;
    int beginAddr = 0;
    int j;
    for ( i = 0; i < sizeof(l.index)/sizeof(l.index[0]) - 1; i++ ) {
        if ( k < l.index[i].maxKey ) {
            beginAddr = l.index[i].beginAddr;
            // 查找所在的block
            for ( j = 0; j < l.index[i+1].beginAddr - l.index[i].beginAddr; j++ ) {
                if ( l.data[j].key == k )
                    return k;
            }
            //return -1;
        }

        else if ( k > l.index[i].maxKey )
            continue;
    }
    return -1;
}

/**
 * @brief 二叉排序树查找,递归算法
 * @param s
 * @param k
 * @return ptr to the certain node match the key
 */
BinSTree BSTreeSearch(BinSTree t, keyType k) {
    if (!t) {
        // return NULL;
    }
    if ( t->elem.key == k )
        return t;
    if ( k < t->elem.key )
        return BSTreeSearch(t->lchild, k);    // search in the left child tree
    return BSTreeSearch(t->rchild, k);
}

/**
 * @brief 二叉排序树的插入
 * @param t
 * @param k
 */
void BSTreeInsert(BinSTree *t, keyType k) {
    // *t 指向排序树根节点
    BinSTree r;
    if (!*t) {    // 排序树为空
        r = (BinSTree)malloc(sizeof(struct BinSTreeNode));
        r->elem.key = k;
        r->lchild = r->rchild = NULL;
        *t = r;    // 作为根节点
        return;
    }
    else {
        if ( k < ((*t)->elem.key) )
            BSTreeInsert(&((*t)->lchild), k);
        else
            BSTreeInsert(&((*t)->rchild), k);
    }
}

/**
 * @brief 删除排序树中key 为k 的节点
 * @param bt
 * @param k
 * @return k if delete success, else 0
 */
keyType BSTreeDelete(BinSTree *bt, keyType k) {
    // *bt 指向排序树根节点
    BinSTree f, p, q, s;
    p = *bt;
    f = NULL;
    while ( p && p->elem.key != k ) {
        if ( k < p->elem.key )
            p = p->lchild;
        else
            p = p->rchild;
    }
    if (!p)
        return 0;

    if ( p->lchild == NULL ) {      // 待删节点左子树为空
        if ( f == NULL )                // 为根节点
            *bt = p->rchild;
        else if ( f->rchild == p )      // 待删节点是其双亲节点的左节点
            f->lchild = p->rchild;
        else
            f->rchild = p->rchild;

        free(p);
    }
    else {                          // 左子树不为空
        q = p;
        s = p->lchild;
        while ( s->rchild ) {           // 在待删节点的左子树中查找最右下角的节点(最大的)
            q = s;
            s = s->rchild;
        }
        if ( q == p )                   // 右下角的节点的左子树接到待删节点上
            q->lchild = s->lchild;
        else
            q->rchild = s->lchild;

        p->elem.key = s->elem.key;
        free(s);
        return k;
    }
    return 0;
}
