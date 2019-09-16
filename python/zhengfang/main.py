#!/usr/bin/env python3
# encoding=utf-8
# Copyright: Qian Jipeng(C) 2019
"""
TODO:
	数据清洗与进一步解析!
"""

import os
import re
import urllib.parse

import requests
import numpy as np
import config_loader as cfl
import pytesser as ocr

from html.parser import *
from PIL import Image
from libtiff import TIFF


class TagParser(HTMLParser):
	# view_state = list()     # 有点像C++中的static变量，是类变量，不可行

	def __init__(self):
		super().__init__()
		self.view_state = list()    # 用来存放viewstate

	def __del__(self):
		del self.view_state         # 释放资源

	def handle_starttag(self, tag, attrs):
		if tag == 'input':
			attrs = dict(attrs)
			if attrs.__contains__('name') and attrs['name'] == '__VIEWSTATE':
				self.view_state.append(attrs['value'])

	def doParse(self, webData):
		self.feed(data=webData)


class Login:

	def __init__(self):
		self.user_id = cfl.getUserId()
		self.user_pwd = cfl.getUserPassword()
		self.user_name = ""
		self.login_url = cfl.getLoginUrl()
		self.checkcode_url = cfl.getCheckcodeUrl()
		self.cookies = requests.get(self.login_url).cookies
		self.headers = {
				'User-Agent': r'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36',
		}

		# self.query_headers = {
		# 	'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3',
		# 	'Accept-Encoding': 'gzip, deflate',
		# 	'Accept-Language': 'en-US,en;q=0.9',
		# 	'Connection': 'keep-alive',
		# 	'Content-Type': 'text/html; charset=gb2312',
		# 	'Referer': '',   # cfl.getIndexUrl() + 'xskbcx.aspx?xh=' + self.user_id + "&xm=" + self.user_name + "&gnmkdm=" + kdn_code,
		# 	# 'Referer': website + 'xs_main.aspx?xh=' + userxh,
		# 	'Upgrade-Insecure-Requests': '1',
		# 	'User-Agent': r'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36',
		# }

		self.config = {
			'__VIEWSTATE': '',  # viewstate
			'TextBox1': self.user_id,     # userid
			'TextBox2': self.user_pwd,     # password
			'TextBox3': '',  # checkcode
			'RadioButtonList1': '%D1%A7%C9%FA',     # session
			'Button1': "",
			'lbLanguage': '',
		}
		self.tag_parser = TagParser()
		self.tag_parser.doParse(requests.get(self.login_url).text)    # 解析

	# 获取并保存验证码图片
	def getAndSaveCheckCode(self, filename):

		pic = requests.post(url=self.checkcode_url, cookies=self.cookies, headers=self.headers)
		if os.path.exists(filename):
			os.remove(filename)
		# write as byte
		with open(filename, 'wb') as filewriter:
			filewriter.write(pic.content)
		image = Image.open(filename)        # PIL
		#image.show()

		out_tiff = TIFF.open(filename, mode = 'w')		# TTIF
		#img = cv2.imdecode(np.fromstring(pic.content, np.uint8) )
		out_tiff.write_image(np.array(image), compression=None, write_rgb=False)
		out_tiff.close()
	
	
	# 调用google ocr 
	# reutrn: string of the check code
	def getCheckCodeString(self, filename):
		text = str(ocr.image_file_to_string(filename))
		print("验证码: " + text)
		text = text.strip()     # 去除两边空格!!!

		os.remove(filename)     # 删除验证码
		return text


	# 应该在获取验证码后调用
	def updateConfig(self, viewstate, checkcode):
		self.config['__VIEWSTATE'] = viewstate
		self.config['TextBox3'] = checkcode

	# 是否登陆成功
	def checkIfSuccess(self, webContent):
		pattern = r'<title>(.*?)</title>'
		items = re.findall(pattern, webContent.text)
		if items[0] == "欢迎使用正方教务管理系统！请登录":      # 特征匹配
			return False
		else:
			# 抓取名字
			catch = '<span id="xhxm">(.*?)</span></em>'
			name = re.findall(catch, webContent.text)
			name = name[0][:-2]
			# name = name[:-2]
			print(name)
			self.user_name = urllib.parse.quote(name.encode("gb2312"))      # 更新用户姓名
			return True




# 全局函数，对外接口
def doLogin(loginobject:Login, filename:str, resultdir:str):
	loginobject.getAndSaveCheckCode(filename)
	#checkcode = input("输入验证码: ")
	checkcode = loginobject.getCheckCodeString(filename)		# update
	#print(checkcode)

	loginobject.updateConfig(loginobject.tag_parser.view_state[0], checkcode)
	# print(loginobject.config)
	content = requests.post(url=loginobject.login_url, data=loginobject.config,
	                        headers=loginobject.headers, cookies=loginobject.cookies)

	if loginobject.checkIfSuccess(content):
		print("登陆成功!!!")

		# 检查结果路径
		if not os.path.exists(resultdir):
			os.makedirs(resultdir)
		else:
			os.system("rm -r " + resultdir)

	else:
		print("登录失败~~~")
		return

	# query = Query()
	# query.queryCourse()

	print("-------------开始查询----------------")
	# 配置区
	course_url = cfl.getIndexUrl() + 'xskbcx.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121603"
	exam_url = cfl.getIndexUrl() + 'xskscx.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121604"
	classexam_url = cfl.getIndexUrl() + 'xsdjkscx.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121606"
	plan_url = cfl.getIndexUrl() + 'pyjh.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121607"
	select_course_url = cfl.getIndexUrl() + 'pyjh.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121615"
	add_exam_url = cfl.getIndexUrl() + 'xsbkkscx.aspx?xh=' + loginobject.user_id + "&xm=" + loginobject.user_name + "&gnmkdm=" + "N121613"


	query_config = {
		'__EVENTTARGET': '',
		'__EVENTARGUMENT': '',
		'__VIEWSTATE': '',
	}
	query_headers = {
		'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3',
		'Accept-Encoding': 'gzip, deflate', 'Accept-Language': 'en-US,en;q=0.9', 'Connection': 'keep-alive',
		'Content-Type': 'text/html; charset=gb2312', 'Referer': '', 'Upgrade-Insecure-Requests': '1',
		'User-Agent': r'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'}
	# end 配置区

	# ------------------------- 查询课表 ----------------------
	query_headers['Referer'] = course_url
	# 先get一下，获取view_state
	course_html = requests.get(course_url, cookies=loginobject.cookies,
	                    headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, course_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	course = requests.session().get(url=course_url, data=query_config,
	                                headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	course_table = re.findall(catch, course.text)
	del course

	f = open(resultdir +"/course_table.txt", "w")
	for each_line in course_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del course_table
	# ------------------------- 课表结束 ------------------------

	# ------------------------- 查询考试安排 -----------------------
	query_headers['Referer'] = exam_url
	exam_html = requests.get(exam_url, cookies=loginobject.cookies,
	                           headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, exam_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	exam = requests.session().get(url=exam_url, data=query_config,
	                                headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	exam_table = re.findall(catch, exam.text)
	del exam

	f = open(resultdir +"/exam_arrangement.txt", "w")
	for each_line in exam_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del exam_table
	# ----------------------------------- 结束 -----------------------------------------

	# ----------------------------------等级考试成绩查询 --------------------------------
	query_headers['Referer'] = classexam_url
	classexam_html = requests.get(classexam_url, cookies=loginobject.cookies,
	                         headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, classexam_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	classexam = requests.session().get(url=classexam_url, data=query_config,
	                              headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	classexam_table = re.findall(catch, classexam.text)
	del classexam

	f = open(resultdir +"/class_exam.txt", "w")
	for each_line in classexam_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del classexam_table
	# --------------------------- 结束 --------------------------

	# -------------------- 培养计划查询 ------------------------
	query_headers['Referer'] = plan_url
	plan_html = requests.get(plan_url, cookies=loginobject.cookies,
	                         headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, plan_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	plan = requests.session().get(url=plan_url, data=query_config,
	                              headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	plan_table = re.findall(catch, plan.text)
	del plan

	f = open( resultdir+"/development_plan.txt", "w")
	for each_line in plan_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del plan_table
	# --------------------- 结束 ----------------------------

	# --------------------- 学生选课情况查询 ------------------------------
	query_headers['Referer'] = select_course_url
	select_course_html = requests.get(select_course_url, cookies=loginobject.cookies,
	                         headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, select_course_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	select_course = requests.session().get(url=select_course_url, data=query_config,
	                              headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	select_course_table = re.findall(catch, select_course.text)
	del select_course

	f = open(resultdir +"/select_course.txt", "w")
	for each_line in select_course_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del select_course_table
	# --------------------- 结束 ----------------------------

	# ------------------- 补考开始查询 ----------------------
	query_headers['Referer'] = add_exam_url
	add_exam_html = requests.get(add_exam_url, cookies=loginobject.cookies,
	                             headers=query_headers)
	catch = '<input type="hidden" name="__VIEWSTATE" value="(.*?)" />'
	query_state = re.findall(catch, add_exam_html.text)[0]
	query_config['__VIEWSTATE'] = query_state
	del query_state
	add_exam = requests.session().get(url=add_exam_url, data=query_config,
	                                  headers=query_headers, cookies=loginobject.cookies)
	# print(course.text)        # 测试ok
	# 写入文件
	catch = '<td>(.*?)</td>'
	add_exam_table = re.findall(catch, add_exam.text)
	del add_exam

	f = open(resultdir +"/add_exam.txt", "w")
	for each_line in add_exam_table:
		if "&nbsp" in each_line:
			# TODO: 数据清洗
			pass
		f.write(each_line + "\n")
	f.close()
	del add_exam_table
	# ------------------- 结束 ------------------------

	print("------------查询成功-----------")


if __name__ == '__main__':

	login = Login()
	doLogin(login, "./checkcode.tif", "./result")



