#include <stdio.h>

typedef int keyType;
#define maxSize 100


typedef struct {
keyType key;

}dataType;

typedef struct {
    dataType r[maxSize];
    int length;
}sqlist;


void creatList( sqlist *s ) {

    int random[30] = {100,87,52,61,27,170,37,45,61,118,14,88,32};
    s->length = 0;
    for ( int i=0; i < 13; i++ ) {
        s->r[i].key = random[i];
                //100 - i + 12;
        s->length ++;
    }
    return;
}

void showOriginalList(sqlist *s) {
    printf("原始数据: \n");
    int i;
    for (i=0; i<s->length; i++)
        printf("%d ", s->r[i].key);
    printf("\n\n");
    return;
}

/**
 * @brief 一趟排序结果
 * @param s
 */
void showResultforOneTime(sqlist *s) {
    for (int i = 0; i < s->length; ++i) {
        //if( s->r[i].key )
        if ( s->r[i].key )
            printf("%d ", s->r[i].key);
    }
    printf("\n\n");
    return;
}

void shellInset( sqlist *s, int gap ) {
    int i, j;
    for ( i = gap + 1; i <= s->length; i++ ) {
        if ( s->r[i].key < s->r[i-gap].key ) {
            s->r[0] = s->r[i];
            for ( j = i-gap; j > 0 && s->r[0].key < s->r[j].key; j = j-gap ) {
                s->r[j+gap] = s->r[j];
            }
            s->r[j+gap] = s->r[0];
        }
    }
    return;
}

/**
 * @brief shellSort
 * @param s
 * @param gap[] 增量序列
 * @param t strlen(gap)
 */
void shellSort( sqlist *s, int gap[] ) {
    int k;
    for ( k=0; k<3; k++ ) {
        shellInset(s, gap[k]);
        showResultforOneTime(s);
    }
    //if ( k == 5 ) {
    //    printf("%d | %d", s->r[0].key, s->r[1].key);
    //}

    return;
}

#if 1
/**
 * @brief 二分查找(对有序表)
 * @param l
 * @param k
 * @return
 */
int binSearch(sqlist l, keyType k) {
    int low,mid,high;
    low = 1;
    high = l.length - 1;
    //high = 29;
    while ( low <= high ) {
        mid = 1/2 *(low + high);    // 二分区间
        if ( k == l.r[mid].key )
            return mid;
        else if ( k < l.r[mid].key )
            high = mid - 1;        // 左区间查找
        else
            low = mid + 1;        // 右区间查找
    }
    return -1;
}
#endif

int main() {
    sqlist S;
    creatList(&S);
    showOriginalList(&S);
    int gap[3] = {5,3,1};
    shellSort(&S, gap);

    //int loc= binSearch(S, 32);
    //printf("二分查找32的位置: %d\n", loc);
    return 0;
}
