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

    int random[30] = {12,32,45,56,65,43,33,76,88,99,55,45,100,21,47,48,49,98,87,101,123,124,111,144,155,166,177,188,199,200};
    s->length = 0;
    for ( int i=0; i < 30; i++ ) {
        s->r[i+1].key = random[i];
                //100 - i + 12;
        s->length ++;
    }
    s->length ++;       //31
    return;
}

void showResultforOneTime(sqlist *s) {
    for (int i = 1; i < s->length; ++i) {       // 1 开始
        printf("%d ", s->r[i].key);
    }
    printf("\n\n");
    return;
}

int quickSortForOneTime( sqlist *s, int low, int high ) {
    keyType pivotkey;       // 轴值
    s->r[0] = s->r[low];
    pivotkey = s->r[low].key;

    while ( low < high ) {
        while ( low < high && s->r[high].key >= pivotkey )
            high --;
        s->r[low] = s->r[high];
        while ( low < high && s->r[low].key <= pivotkey )
            low ++;
        s->r[high] = s->r[low];
    }
    s->r[low] = s->r[0];
    return low;
}

void quickSort( sqlist *s, int low, int high ) {
    int pivotloc;
    if ( low < high ) {
        pivotloc = quickSortForOneTime(s, low, high);
        showResultforOneTime(s);
        quickSort(s, low, pivotloc-1);      // left side
        quickSort(s, pivotloc+1, high);     // right side
    }
    return;
}

/**
 * @brief 二分查找(对有序表)
 * @param l
 * @param k
 * @return
 */
int binSearch(sqlist l, keyType k) {
    int low,mid,high;
    //low = 0;
    //high = l.length - 1;
    low =1;
    high = l.length;
    while ( low <= high ) {
        mid = (low + high)/2;    // 二分区间
        if ( k == l.r[mid].key )
            return mid;
        else if ( k < l.r[mid].key )
            high = mid - 1;        // 左区间查找
        else
            low = mid + 1;        // 右区间查找
    }
    return -1;
}

int main() {
    sqlist S;
    creatList(&S);
    quickSort(&S, 0, 31);
    showResultforOneTime(&S);

    int loc = binSearch(S, 200);
    printf("200的位置: %d\n\n", loc);
    return 0;
}
