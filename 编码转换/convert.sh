#!/bin/bash

# 文件编码转换工具 V1.0
# author: Qian Jipeng(C)
# date: 2019-5-10

## 遍历 myDir 文件夹下所有文件 (这里只考虑文件夹下没有子目录的情况)
for file in ./fromFiles/*
do	
	if test -f $file
	then
		#file_list=(${file_list[*]} $(file))    # 带路径名
		file_list=(${file_list[*]} ${file##*/})  # {} 的理解
	fi
done
echo 你要转换的文件有: 
echo ${file_list[@]}


# 文件夹处理
if [[ -d toFiles ]]
then
	rm toFiles/*
else
	mkdir toFiles
fi


## 获取当前文件编码 (依次处理每个文件)
#if [ -f "codings.txt" ]
#then
#	rm ./codings.txt
#fi

# main loop
for fromFile in ${file_list[@]}
do
	
#	file ./fromFiles/${fromFile} >> codings.txt		# > 是覆盖写，　>> 追加写
#	cat codings.txt | while read myFromFile
#	do
#		echo 
#	done

	declare toFileName=$fromFile
	#declare toCoding=UTF-8				# TODO: 改成 config.conf文件
	toCoding=$(cat config.conf)
	
	# 获取命令行输出
	info=`file ./fromFiles/${fromFile}`    # 带文件名的
	#echo $info
	
	codings=(UTF-8 ISO-8859 ASCII)	# to be added
	for coding in ${codings[@]}
	do
		if [[ $info =~ $coding ]]
		then
			echo $coding
		
			# 异常处理
			if [[ $coding = ISO-8859 ]]
			then
				coding=GBK			# shell 赋值的 = 两边不能有空格
			fi
			
			# 编码转换
			iconv -f $coding -t $toCoding ./fromFiles/$fromFile -o ./toFiles/$toFileName
		fi
	done			
done



	
			

