/*
    矩阵求逆及相乘
    A*B-1
    done!!!

*/





#include <iostream>
#include <stdio.h>
#include <stdlib.h>

#define debug 0
using namespace std;



const int size = 3;     // 方阵阶数



/**
 * @brief createMatrix
 * @param row
 * @param col
 * @param ptr to the matrix
 * @return
 */
void createMatrix( int row, int col, double *a/*(*a)[col]*/ ) {
    cout << "你即将创建 " << row << " * " << col << " 阶矩阵" << endl;
    int i;

    cout << "输入这个矩阵: " << endl;

    for ( i = 0; i < row*col; i++ )
        cin >> *(a+i);
    //cin >> a[i];            // 二维数组行主序, a 代表首行元素首地址
    cout << "创建完毕!" << endl<<endl;
    return;
}

/**
 * @brief computeHanglieshi
 * @param row, the row size of the matrix
 * @return |A|
 */
//double computeHanglieshi( int row, double (*a)[size] ) {
double computeHanglieshi( int row, double a[size][size] ) {
    if( row == 1 )
    {
        return a[0][0];
    }
    int ans = 0;
    double temp[size][size]={{0}};
    int i, j, k;
    for( i=0; i<row; i++ )
    {
        for( j=0; j<row-1; j++ )
        {
            for( k=0; k<row-1; k++ )
            {
                temp[j][k] = a[j+1][(k>=i) ? (k+1) : k];

            }
        }
        double t = computeHanglieshi(row-1, temp);      // 递归调用
        if( i % 2 == 0 )
        {
            ans += a[0][i]*t;
        }
        else
        {
            ans -=  a[0][i]*t;
        }
    }
    return ans;
}


/**
 * @brief 计算每行每列元素的余子式,组成伴随矩阵
 * @param arcs, 原给定矩阵
 * @param n, 矩阵位数
 * @param ans, 存结果用
 */
void computeYuzishi(double arcs[size][size],int n,double ans[size][size])
{
    if( n == 1 )
    {
        ans[0][0] = 1;
        return;
    }
    int i,j,k,t;
    double temp[size][size];
    for( i=0; i<n; i++ )
    {
        for( j=0; j<n; j++ )
        {
            for( k=0; k<n-1; k++ )
            {
                for( t=0; t<n-1; t++ )
                {
                    temp[k][t] = arcs[k>=i ? k+1 :k][t>=j ? t+1 : t];
                }
            }


            ans[j][i]  =  computeHanglieshi(n-1, temp);  //此处顺便进行了转置
            if( (i+j) % 2 == 1 )
            {
                ans[j][i] = - ans[j][i];
            }
        }
    }
}


/**
 * @brief computeInverseMatrix
 * @param src, the given square matrix
 * @param n, the size of the matrix
 * @param des, the inverse matrix of src, used later
 * @return true if computed, false or not
 */
bool computeInverseMatrix(double src[size][size], int n, double des[size][size])
{
    double flag = computeHanglieshi(n, src);
    double t[size][size];
    if( flag == 0.0 )
    {
        cout<< "原矩阵行列式为0，无法求逆。请重新运行" <<endl;
        return false;
    }
    else
    {
        computeYuzishi(src,n,t);    // 算yuzishi
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                des[i][j] = t[i][j] / flag;
            }

        }
    }

    return true;
}

/**
 * @brief 矩阵相乘
 * @param a
 * @param b
 * @return
 * @note size must equals to size
 */
void matrixMultipy(double a[size][size], double b[size][size], double res[size][size]) {
    if ( size != size ) {
        cout << "矩阵A的列数和矩阵B的行数不相等，不能相乘!!!" << endl;
        return;
    }
    else {
        int i;
        int j;
        int k;
        for ( i = 0; i < size; i++)  //a的行数
        {
            for ( j = 0; j < size; j++)      //b的列数
            {
                for ( k = 0; k < size; k++)  //a的列数
                {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return;
    }
}

/**
 * @brief 打印矩阵
 * @param row
 * @param col
 * @param a, ptr to the matrix
 */
void printMatrix( int row, int col, double *a ) {
    int i;
    int j;
    int count = 0;
    for ( i=0; i<row*col; i++ ) {
        //for ( j=0; j< col; j++ )
        cout << *(a+i) << "\t";         // 有点东西
        count ++;

        if ( count % col == 0 )
            cout << "\n";
    }
}


int main() {
    cout << "矩阵运算A*B-1" << endl << endl;
    //double *A = new double[size*size];
    //double *B = new double[size*size];
    //double *B_inverse = new double[size*size];
    double A[size][size];
    double B[size][size];
    double B_inverse[size][size];
    double res[size][size];

    createMatrix(size, size, *A);
    createMatrix(size,size,*B);     // 创建 2*2

    cout << "你创建的A矩阵(" << size << " * " << size <<  "): " << endl;
    printMatrix(size, size, *A);
    cout << endl;

    cout << "你创建的B矩阵(" << size << " * " << size <<  "): " << endl;
    printMatrix(size, size, *B);
    cout << endl;
    //double A[3][2] = {{1,1},{1,1},{1,1}};
    //double B[2][2] = {{2,1},{1,1}};

    if ( computeInverseMatrix(B, size, B_inverse) ) {
        cout << "B的逆矩阵: " << endl;
        printMatrix(size, size, *B_inverse);
        cout << endl;

        matrixMultipy(A,B_inverse,res);

        cout << "A*B的结果为: "<< endl;
        printMatrix(size, size, *res);
    }
    return 0;
}



