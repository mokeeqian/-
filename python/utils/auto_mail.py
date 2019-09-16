#!/usr/bin/env python3
# -*-encoding: utf-8
# author: Qian Jp(C)
# get the weather info automaticly and send an email to someone everyday

import re
import time
import smtplib
import datetime
from bs4 import BeautifulSoup
from urllib.request import urlopen   
from email.mime.text import MIMEText    
from email.mime.image import MIMEImage
from email.header import Header


def getWeather():
	result = []
	resp=urlopen('http://www.weather.com.cn/weather/101270101.shtml')

	# phrase the url
	soup=BeautifulSoup(resp,'html.parser')

	# match the time 
	tagDate=soup.find('ul', class_="t clearfix")

	# get the time info
	dates=tagDate.h1.string

	tagToday=soup.find('p', class_="tem")

	
	try:
		temperatureHigh=tagToday.span.string
	except AttributeError as e:
		temperatureHigh=tagToday.find_next('p', class_="tem").span.string


	temperatureLow=tagToday.i.string

	weather=soup.find('p', class_="wea").string

	tagWind=soup.find('p',class_="win")

	winL=tagWind.i.string

	print('今天是：'+dates)
	print('风级：'+winL)
	print('最低温度：'+temperatureLow)
	print('最高温度：'+temperatureHigh)
	print('天气：'+weather)

	result.append(dates)
	result.append(winL)
	result.append(temperatureLow)
	result.append(temperatureHigh)
	result.append( weather)
	return result


def sendMail(title, context):
	try:
		usrname = ''
		
		# qq 邮箱需要授权码登录
		password = ''
		sender = 'xxx@qq.com'
		receiver = 'xxx@qq.com'
		msg = MIMEText(str(context), 'plain', 'utf-8')
		
		msg['From'] = Header(sender)   # 发送者
		msg['To'] =  Header(str(";".join(receiver)))        # 接收者
		msg['Subject'] = Header(title)

		#msg = MIMEText(str(context))
		
		smtp = smtplib.SMTP_SSL() 
		smtp.connect(host= 'smtp.qq.com', port= 465) 
		smtp.login(usrname, password) 
		smtp.sendmail(sender, receiver, msg.as_string()) 
		smtp.quit()
		return 1
	
	except smtplib.SMTPException:
		return 0
	


def genInfo():
	timeCurrent = str(datetime.datetime.now())			# '2019-03-25 21:41:22.987429'
	
	# remove useless info,get a list of time
	timeCurrent = timeCurrent.split('.')[0].split(' ')			# ['2019-03-25', '21:41:22']
	
	
	if timeCurrent[1] == '7:13:14':
	#if timeCurrent[1]:	
		theme = '早安'
		newContext = ''
		context0 = getWeather()
		low = context0[2]
		if int(context0[2][:-1]) < 15:
			newContext = '宝宝今天有点冷，记得添衣服\n'
		if int(context0[2][:-1]) > 25:
			newContext = '宝宝今天温度高哦，要防晒，戴帽子嘿嘿\n'
	
		context0[0] = str('今天是: ') + context0[0]
		context0[1] = str('风级: ') + context0[1]
	
		context0[2] = str('最低温度: ') + context0[2]
		context0[3] = str('最高温度: ') + context0[3]
		context0[4] = str('天气: ') + context0[4]
	
		# format	
		for i in context0:
			newContext += (i + '\n')
	
		sendMail(theme, newContext)
		
		time.sleep(10)
		#print(sendMail('早安', context0))

	'''
	if timeCurrent[1] == '22:13:14':
		theme = "晚安"
		newContext = ''
		context0 = getWeather()
	
		context0[0] = str('今天是: ') + context0[0]
		context0[1] = str('风级: ') + context0[1]
	
		context0[2] = str('最低温度: ') + context0[2]
		context0[3] = str('最高温度: ') + context0[3]
		context0[4] = str('天气: ') + context0[4]
	
		# format
		for i in context0:
			newContext += (i + '\n')
	
		sendMail(theme, newContext)
	'''
def everyDay():
	while True:
		genInfo()

if __name__ == "__main__":
	everyDay()
